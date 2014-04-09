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
import com.tennissetapp.persistence.entities.Notification;
import com.tennissetapp.util.TennisSetAppUtils;
import com.tennissetapp.validation.NotificationServiceValidator;

@PreAuthorize("hasRole('ROLE_USER')")
@Controller
public class NotificationService extends AbstractService{
	protected Logger logger = Logger.getLogger(getClass());
	
	@Inject 
	protected SecurityContextLogoutHandler securityContextLogoutHandler;
	
	@Inject
	protected AuthenticationManager authenticationManager;
	
	@Inject
	protected DaoManager daoManager;
	
	@InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(new NotificationServiceValidator());
    }
	
	
	@ResponseBody 
	@RequestMapping(value = {"/service/notifications/scroll"}, method = {RequestMethod.GET})
	@Transactional
	public ServiceResponse scrollNotifications(@Valid ScrollForm form, Principal principal)
	throws IOException {
		logger.debug("scrollNotifications " + form);
		ServiceResponse response = new ServiceResponse();
		
		if(principal == null){
			throw new NotAuthorizedException("Not authorized to open this page");
		}
		
		ScrollMessagesArgs args = form.getArguments(ScrollMessagesArgs.class);
		args.userAccountId = TennisSetAppUtils.cast(principal).getUserAccountId();
		
		List<Notification> list = daoManager.scrollNotifications(args);
		if(list != null){
			//fetch
			for(Notification n : list){
				n.getOriginatorUserAccount();
				if(n.getOriginatorProfile().getProfileImageFile() != null){
					n.getOriginatorProfile().getProfileImageFile().getFileName();
				}
			}
			Collections.reverse(list);
			response.put("list", list);
		}
		return response;
	}
	
}
