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
import com.tennissetapp.Constants;
import com.tennissetapp.controller.response.ServiceResponse;
import com.tennissetapp.exception.NotAuthorizedException;
import com.tennissetapp.forms.PlayerProfileForm;
import com.tennissetapp.forms.TeacherProfileForm;
import com.tennissetapp.persistence.dao.DaoManager;
import com.tennissetapp.persistence.entities.UserAccount;
import com.tennissetapp.service.MailService;
import com.tennissetapp.validation.PlayerProfileServiceValidator;
import com.tennissetapp.validation.TeacherProfileFormValidator;

@Controller
public class SubmitTeacherProfileService {
protected Logger logger = Logger.getLogger(getClass());
	
	@Inject 
	protected SecurityContextLogoutHandler securityContextLogoutHandler;
	
	@Inject
	protected DaoManager daoManager;
	
	@Inject
	protected MailService mailService;
	
	@InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(new TeacherProfileFormValidator());
    }
	
	@ResponseBody //return the body only
	@RequestMapping(value = {"/service/profile/teacher/update"}, method = {RequestMethod.POST})
	@Transactional
	public ServiceResponse updateProfile(@Valid TeacherProfileForm form, BindingResult result, Principal principal)
	throws IOException {
		logger.debug("--->updateProfile " + form + "\nresult\n" + result);
		if(principal == null){
			throw new NotAuthorizedException("Not authorized to open this page");
		}
		form.setEmail(principal.getName());
//		daoManager.updateAccount(form);
		ServiceResponse response = new ServiceResponse(result);
		return response;
	}
	
	@ResponseBody 
	@RequestMapping(value = {"/service/profile/teacher/complete"}, method = {RequestMethod.POST})
	@Transactional
	public ServiceResponse completeProfile(@Valid TeacherProfileForm form, BindingResult result, HttpServletRequest request,HttpServletResponse response)
	throws IOException {
		logger.debug("--->completeProfile " + form + "\nresult\n" + result);
		ServiceResponse r = new ServiceResponse(result);
		String userId = (String)request.getSession().getAttribute(Constants.SessionAttributeKey.USER_ID);
//		userId = "meirwinston@yahoo.com";
		if(userId == null){
			throw new NotAuthorizedException("Not authorized to open this page");
		}
		if(result.getErrorCount() == 0){
			form.setEmail(userId);
			UserAccount userAccount = daoManager.updateAccount(form);
			
			//token
			String activationToken = UUID.randomUUID().toString();
			userAccount.setActivationToken(activationToken);
			userAccount.setActivationTokenExpires(new Date(System.currentTimeMillis() + 7200000l)); //2 hours
			logger.debug("completeProfile: " + activationToken + ", " + userAccount.getActivationTokenExpires());
			
			//mail
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("activationToken", activationToken);
			mailService.sendActivateAccount(form.getEmail(), map);
			
			//sign out
			request.getSession().removeAttribute(Constants.SessionAttributeKey.USER_ID);
		}
		
		return r;
	}

}
