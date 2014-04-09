package com.tennissetapp.rest;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.fileupload.FileItem;
import org.apache.log4j.Logger;
import org.springframework.social.NotAuthorizedException;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tennissetapp.Constants;
import com.tennissetapp.args.CreateProfileArgs;
import com.tennissetapp.controller.response.ServiceResponse;
import com.tennissetapp.forms.CreateProfileForm;
import com.tennissetapp.persistence.dao.DaoManager;
import com.tennissetapp.validation.CreateUserProfileFormVallidator;

/**
 * try to combine all profile functions in PlayerProfileService
 * 
 * @author mwinston
 */
@Deprecated //userPlayerProfile instead
//@Controller
public class CreateUserProfileService {
	Logger logger = Logger.getLogger(getClass());
	
	@Inject
	protected DaoManager daoManager;
	
	@InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(new CreateUserProfileFormVallidator());
    }
	
//	@Transactional
//	@ResponseBody //return the body only
//	@RequestMapping(value = {"/service/profile/create"}, method = {RequestMethod.POST})
//	public ServiceResponse createProfile(@Valid CreateProfileForm form, BindingResult result,HttpServletRequest request)
//	throws IOException {
//		logger.debug("createProfile: " + form);
//		if(form.getUserAccountId() == null){
//			throw new NotAuthorizedException("Not authorized to open this page");
//		}
//		ServiceResponse response = new ServiceResponse(result);
//		if(Constants.ProfileType.TENNIS_PLAYER.equals(form.getProfileType())){
//			CreateProfileArgs args = form.toArgs();
//			args.fileItem = (FileItem)request.getSession().getAttribute(form.getProfileFileItemId());
//			daoManager.createPlayerProfile(args);
//			request.getSession().removeAttribute(form.getProfileFileItemId());
//		}
//		if(Constants.ProfileType.TENNIS_TEACHER.equals(form.getProfileType())){
//			CreateProfileArgs args = form.toArgs();
//			args.fileItem = (FileItem)request.getSession().getAttribute(form.getProfileFileItemId());
//			daoManager.createTeacherProfile(args);
//			request.getSession().removeAttribute(form.getProfileFileItemId());
//		}
//		return response;
//	}
	
//	@Transactional
//	@ResponseBody //return the body only
//	@RequestMapping(value = {"/service/profile/create"}, method = {RequestMethod.POST})
//	public ServiceResponse createProfile(@Valid CreateProfileForm form, BindingResult result,HttpServletRequest request)
//	throws IOException {
////		logger.debug("---> " + ": " + form + "\nresult\n" + result);
////		logger.debug("-2--> " + form.getProfileFileItemId());
////		logger.debug("-3--> " + request.getSession().getAttribute(form.getProfileFileItemId()));
//		Long userId = (Long)request.getSession().getAttribute(Constants.SessionAttributeKey.USER_ID); 
//		if(userId == null){
//			throw new NotAuthorizedException("Not authorized to open this page");
//		}
//		ServiceResponse response = new ServiceResponse(result);
//		form.setUserAccountId(userId);
//		if(Constants.ProfileType.TENNIS_PLAYER.equals(form.getProfileType())){
//			form.setFileItem((FileItem)request.getSession().getAttribute(form.getProfileFileItemId()));
//			daoManager.createPlayerProfile(form);
//			request.getSession().removeAttribute(form.getProfileFileItemId());
//		}
//		if(Constants.ProfileType.TENNIS_TEACHER.equals(form.getProfileType())){
//			form.setFileItem((FileItem)request.getSession().getAttribute(form.getProfileFileItemId()));
//			daoManager.createTeacherProfile(form);
//			request.getSession().removeAttribute(form.getProfileFileItemId());
//		}
//		return response;
//	}
}
