package com.tennissetapp.rest;

import java.io.IOException;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tennissetapp.controller.response.ServiceResponse;
import com.tennissetapp.persistence.dao.DaoManager;
import com.tennissetapp.persistence.entities.UserAccount;
import com.tennissetapp.validation.CreateUserProfileFormVallidator;

/**
 * try to combine all profile functions in PlayerProfileService
 * 
 * @author mwinston
 */
@Deprecated
//@Controller
public class ActivateUserAccountService {
	Logger logger = Logger.getLogger(getClass());
	
	@Inject
	protected DaoManager daoManager;
	
//	@InitBinder
//    protected void initBinder(WebDataBinder binder) {
//        binder.setValidator(new CreateUserProfileFormVallidator());
//    }
	
	@ResponseBody
	@Transactional
	@RequestMapping(value = {"/service/profile/activate/{activationToken}"}, method = {RequestMethod.GET})
	public ServiceResponse activateProfile(@PathVariable String activationToken)
	throws IOException {
		ServiceResponse response = new ServiceResponse();
		
		UserAccount userAccount = daoManager.findUserAccountByActivationToken(activationToken);
//		UserAccount userAccount = daoManager.activateUserAccount(activationToken);
		if(userAccount == null){
//			mv = new ModelAndView("profile/activation-failed.jsp");
			response.putError("token", "Invalid request - activation failed");
		}
		//the account was created by normal signup process
		else if(userAccount.getPassword() != null){
//			mv = new ModelAndView("redirect:/");
		}
		//the account was created by social media API, there is no password yet
		else{
			response.put("redirect", "profile/resetpassword/" + activationToken);
		}
		
		return response;
	}
}

/*

@Transactional
@RequestMapping(value = {"/profile/activate/{activationToken}"}, method = {RequestMethod.GET})
public ModelAndView activateProfile(@PathVariable String activationToken)
throws IOException {
	UserAccount userAccount = daoManager.findUserAccountByActivationToken(activationToken);
//	UserAccount userAccount = daoManager.activateUserAccount(activationToken);
	ModelAndView mv;
	if(userAccount == null){
		mv = new ModelAndView("profile/activation-failed.jsp");
	}
	//the account was created by normal signup process
	else if(userAccount.getPassword() != null){
		mv = new ModelAndView("redirect:/");
	}
	//the account was created by social media API, there is no password yet
	else{
		mv = new ModelAndView("redirect:/profile/resetpassword/" + activationToken);
	}
	
	return mv;
}

*/