package com.tennissetapp.rest;

import java.io.IOException;
import java.security.Principal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tennissetapp.args.PlayerProfileArgs;
import com.tennissetapp.args.SignupArgs;
import com.tennissetapp.auth.AppUserDetails;
import com.tennissetapp.auth.WebUserDetails;
import com.tennissetapp.controller.response.ServiceResponse;
import com.tennissetapp.exception.NotAuthorizedException;
import com.tennissetapp.forms.PlayerProfileForm;
import com.tennissetapp.persistence.dao.DaoManager;
import com.tennissetapp.persistence.entities.UserAccount;
import com.tennissetapp.service.MailService;
import com.tennissetapp.util.TennisSetAppUtils;
import com.tennissetapp.validation.PlayerProfileServiceValidator;

/**
 * try to combine all profile functions in PlayerProfileService
 * 
 * @author mwinston
 */
@Deprecated
//@Controller
public class SubmitPlayerProfileService extends AbstractService{
	protected Logger logger = Logger.getLogger(getClass());
	
	@Inject 
	protected SecurityContextLogoutHandler securityContextLogoutHandler;
	
	@Inject
	protected DaoManager daoManager;
	
	@Inject
	protected MailService mailService;
	
	@InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(new PlayerProfileServiceValidator());
    }
	
	@ResponseBody //return the body only
	@RequestMapping(value = {"/service/profile/player/update"}, method = {RequestMethod.POST})
	@Transactional
	public ServiceResponse updateProfile(@Valid PlayerProfileForm form, BindingResult result, Principal principal)
	throws IOException {
		logger.debug("updateProfile " + form);
		
		if(principal == null){
			throw new NotAuthorizedException("Not authorized to open this page");
		}
		AppUserDetails auth = TennisSetAppUtils.cast(principal);
//		form.setUserAccountId(Long.valueOf(principal.getName()));
		form.setUserAccountId(auth.getUserAccountId().toString());
		
		daoManager.updateAccount((PlayerProfileArgs)form.getArguments(PlayerProfileArgs.class));
		ServiceResponse response = new ServiceResponse(result);
		return response;
	}
	
	@ResponseBody 
	@RequestMapping(value = {"/service/profile/player/complete"}, method = {RequestMethod.POST})
	@Transactional
	public ServiceResponse completeProfile(@Valid PlayerProfileForm form, BindingResult result, HttpServletRequest request,HttpServletResponse response)
	throws IOException {
		logger.debug("completeProfile " + form);
		ServiceResponse r = new ServiceResponse(result);
		if(result.getErrorCount() > 0){
			return r;
		}
		PlayerProfileArgs args = (PlayerProfileArgs)form.getArguments(PlayerProfileArgs.class);
		
		UserAccount userAccount = daoManager.updateAccount(args);
		
		//token
		String activationToken = UUID.randomUUID().toString();
		userAccount.setActivationToken(activationToken);
		userAccount.setActivationTokenExpires(new Date(System.currentTimeMillis() + 7200000l)); //2 hours
		logger.debug("completeProfile: " + activationToken + ", " + userAccount.getActivationTokenExpires());
		
		//mail
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("activationToken", activationToken);
		mailService.sendActivateAccount(userAccount.getEmail(), map);
		
		return r;
	}
	
}
