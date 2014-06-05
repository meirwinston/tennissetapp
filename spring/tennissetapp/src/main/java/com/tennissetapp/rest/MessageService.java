package com.tennissetapp.rest;

import java.io.IOException;
import java.security.Principal;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Sender;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.tennissetapp.args.MessageArgs;
import com.tennissetapp.args.ScrollByMateAccountIdArgs;
import com.tennissetapp.args.ScrollMessagesArgs;
import com.tennissetapp.controller.response.ServiceResponse;
import com.tennissetapp.exception.NotAuthorizedException;
import com.tennissetapp.forms.MessageForm;
import com.tennissetapp.forms.ScrollByMateAccountIdForm;
import com.tennissetapp.forms.ScrollForm;
import com.tennissetapp.persistence.dao.DaoManager;
import com.tennissetapp.persistence.entities.GcmToken;
import com.tennissetapp.persistence.entities.UserPost;
import com.tennissetapp.util.TennisSetAppUtils;
import com.tennissetapp.validation.MessageServiceValidator;

@PreAuthorize("hasRole('ROLE_USER')")
@Controller
public class MessageService extends AbstractService{
	protected Logger logger = Logger.getLogger(getClass());
	
	@Inject 
	protected SecurityContextLogoutHandler securityContextLogoutHandler;
	
	@Inject
	protected AuthenticationManager authenticationManager;
	
	@Inject
	protected DaoManager daoManager;
	
	@Value("${tennissetapp.gcm.projectNumber}")
	private String projectNumber;
	
	@Value("${tennissetapp.gcm.apiKey}")
	private String apiKey;
	
	protected Sender sender;
	

	@PostConstruct
	public void afterPropertiesSet() throws Exception {
		sender = new Sender(apiKey);
	}



	@InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(new MessageServiceValidator());
    }
	
	@ResponseBody 
	@RequestMapping(value = {"/service/messages/test"}, method = {RequestMethod.GET})
	public ServiceResponse test()
	throws IOException {
		ServiceResponse response = new ServiceResponse();
		response.put("test", "my value");
		return response;
	}

	
	@ResponseBody 
	@RequestMapping(value = {"/service/messages/post"}, method = {RequestMethod.POST})
	@Transactional
	public ServiceResponse postMessage(@Valid MessageForm form, Principal principal)
	throws IOException {
		ServiceResponse response = new ServiceResponse();
		
		if(principal == null){
			throw new NotAuthorizedException("Not authorized to open this page");
		}
		
		MessageArgs args = form.getArguments(MessageArgs.class);
		args.userAccountId = TennisSetAppUtils.cast(principal).getUserAccountId();
		args.createdOn = new Date();
		
		
		UserPost userPost = daoManager.insertUserPost(args);
		if(userPost != null){
			response.put("userPostId", userPost.getUserPostId());

//			//TEST
//			String t = "APA91bFAD3zbyURohBfalTwCudULgZgRzjadjbY4ZW9lUZh7jcVrAxM10iWMsalxL0Px18r48NSZElRrMCrs46AGplWqL21HFQc1hI_0646fNtBAiskReP852uM2ahVk5Eb67SZwC6rdOgwRavNOyh6_5ZT6ItrASA";
//			pushGcmMessage(userPost, t);
			
			List<GcmToken> recipientTokens = daoManager.selectGcmTokens(args.toUserAccountId);
			for(GcmToken token : recipientTokens){
				pushGcmMessage(userPost, token.getToken());
			}
			
		}
		return response;
	}
	
	private void pushGcmMessage(UserPost userPost,String token) throws IOException{
		Message message = new Message.Builder()
//		.collapseKey("collapseKey")
		.delayWhileIdle(true)
		.dryRun(true)
//		.restrictedPackageName("com.tennissetapp")
		.timeToLive(108) //ttl
		.addData("content", userPost.getContent())
		.addData("postedOn", String.valueOf(userPost.getPostedOn().getTime()))
		.addData("toUserAccountId", String.valueOf(userPost.getToUserAccountId()))
		.addData("userPostId", String.valueOf(userPost.getUserPostId()))
		.build();
		logger.debug("post message : sending a message to " + token);
		sender.send(message, token, 2);
	}
	@ResponseBody 
	@RequestMapping(value = {"/service/messages/scroll"}, method = {RequestMethod.GET})
	@Transactional
	public ServiceResponse scrollMessage(@Valid ScrollForm form, Principal principal)
	throws IOException {
		ServiceResponse response = new ServiceResponse();
		if(principal == null){
			throw new NotAuthorizedException("Not authorized to open this page");
		}
		ScrollMessagesArgs args = form.getArguments(ScrollMessagesArgs.class);
		args.userAccountId = TennisSetAppUtils.cast(principal).getUserAccountId();
//		logger.debug("scrollMessages " + args);
		List<UserPost> posts = daoManager.scrollUserPosts(args);
		List<Map<String,Object>> list = Lists.newArrayList();
		
		if(posts != null){
			for(UserPost p : posts){
				Map<String,Object> map = Maps.newHashMapWithExpectedSize(posts.size());
				map.put("userPostId", p.getUserPostId());
				if(args.userAccountId.equals(p.getUserAccountId())){ //I sent
					map.put("userAccountId", p.getToUserAccountId());	
					map.put("direction", "out");
					
				
					if(p.getToUserAccount() != null && p.getToUserAccount().getPlayerProfile() != null && p.getToUserAccount().getPlayerProfile().getProfileImageFile() != null){
						map.put("userProfileImage", p.getToUserAccount().getPlayerProfile().getProfileImageFile().getFileName());
					}
					
					map.put("userFirstName", p.getToUserAccount().getFirstName());
					map.put("userLastName", p.getToUserAccount().getLastName());
				}
				else{ //I got
					map.put("userAccountId", p.getUserAccountId());
					map.put("direction", "in");
					
					if(p.getPlayerProfile() != null && p.getPlayerProfile().getProfileImageFile() != null){
						map.put("userProfileImage", p.getPlayerProfile().getProfileImageFile().getFileName());
					}
					
					map.put("userFirstName", p.getUserAccount().getFirstName());
					map.put("userLastName", p.getUserAccount().getLastName());
				}
				map.put("content", p.getContent());
				map.put("postedOn", p.getPostedOn().getTime());
				map.put("status", p.getStatus().toString());
				
				list.add(map);
			}
			Collections.reverse(list);
			response.put("list", list);
		}
		return response;
	}
	
	@ResponseBody 
	@RequestMapping(value = {"/service/messages/scrollByMate"}, method = {RequestMethod.GET})
	@Transactional
	public ServiceResponse scrollMessageByMate(@Valid ScrollByMateAccountIdForm form, Principal principal)
	throws IOException {
		logger.debug("scrollMessageByMate " + form);
		ServiceResponse response = new ServiceResponse();
		
		if(principal == null){
			throw new NotAuthorizedException("Not authorized to open this page");
		}
		
		ScrollByMateAccountIdArgs args = form.getArguments(ScrollByMateAccountIdArgs.class);
		args.userAccountId = TennisSetAppUtils.cast(principal).getUserAccountId();
		
		List<UserPost> list = daoManager.scrollMateConversation(args);
//		logger.debug("ScrollByMate response:::" + list);
		if(list != null){
			for(UserPost p : list){
				p.getUserAccount();
				if(p.getPlayerProfile() != null){
					if(p.getPlayerProfile().getProfileImageFile() != null){
						p.getPlayerProfile().getProfileImageFile().getFileName();
					}
				}
				if(p.getToUserAccount() != null){
					if(p.getToUserAccount().getPlayerProfile() != null && p.getToUserAccount().getPlayerProfile().getProfileImageFile() != null){
						p.getToUserAccount().getPlayerProfile().getProfileImageFile().getFileName();
					}
				}
			}
			Collections.reverse(list);
			response.put("list", list);
		}
		return response;
	}
}
