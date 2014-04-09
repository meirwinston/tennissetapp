package com.tennissetapp.rest;

import java.io.IOException;
import java.security.Principal;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import org.apache.log4j.Logger;
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

import com.tennissetapp.args.MessageArgs;
import com.tennissetapp.args.ScrollByMateAccountIdArgs;
import com.tennissetapp.args.ScrollMessagesArgs;
import com.tennissetapp.controller.response.ServiceResponse;
import com.tennissetapp.exception.NotAuthorizedException;
import com.tennissetapp.forms.MessageForm;
import com.tennissetapp.forms.ScrollByMateAccountIdForm;
import com.tennissetapp.forms.ScrollForm;
import com.tennissetapp.persistence.dao.DaoManager;
import com.tennissetapp.persistence.entities.UserPost;
import com.tennissetapp.persistence.entities.UserPostSelect;
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
	
	@InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(new MessageServiceValidator());
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
		}
		return response;
	}
	
	@ResponseBody 
	@RequestMapping(value = {"/service/messages/scroll"}, method = {RequestMethod.GET})
	@Transactional
	public ServiceResponse scrollMessage(@Valid ScrollForm form, Principal principal)
	throws IOException {
		logger.debug("scrollMessages " + form);
		ServiceResponse response = new ServiceResponse();
		
		if(principal == null){
			throw new NotAuthorizedException("Not authorized to open this page");
		}
		
		ScrollMessagesArgs args = form.getArguments(ScrollMessagesArgs.class);
		args.userAccountId = TennisSetAppUtils.cast(principal).getUserAccountId();
		
		List<UserPost> list = daoManager.scrollUserPosts(args);
//		Long count = daoManager.countUserPosts(args.userAccountId);
		if(list != null){
			for(UserPost p : list){
				p.getUserAccount();
				if(p.getPlayerProfile().getProfileImageFile() != null){
					p.getPlayerProfile().getProfileImageFile().getFileName();
				}
			}
			Collections.reverse(list);
			response.put("list", list);
//			response.put("count", count);
		}
//		logger.info("THE SERIALIZED LIST: " + JacksonUtils.serialize(list));
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
			}
			Collections.reverse(list);
			response.put("list", list);
		}
		return response;
	}
}
