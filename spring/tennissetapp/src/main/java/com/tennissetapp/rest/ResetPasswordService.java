package com.tennissetapp.rest;

import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.tennissetapp.Constants;
import com.tennissetapp.controller.response.ServiceResponse;
import com.tennissetapp.exception.DateExpriedException;
import com.tennissetapp.forms.ResetPasswordForm;
import com.tennissetapp.json.JacksonUtils;
import com.tennissetapp.persistence.dao.DaoManager;
import com.tennissetapp.validation.PlayerProfileServiceValidator;
import com.tennissetapp.validation.ResetPasswordFormValidator;

@Controller
public class ResetPasswordService {
protected Logger logger = Logger.getLogger(getClass());
	
	@Inject 
	protected DaoManager daoManager;
	
	@InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(new ResetPasswordFormValidator());
    }
	
	@ExceptionHandler(RuntimeException.class)
	protected void handleRuntimeException(RuntimeException exp, HttpServletResponse response){
		logger.debug("Handle RuntimeException");
		logger.warn(exp.getMessage(), exp);
		ServiceResponse sr = new ServiceResponse();
//		sr.put("exception", "A user with the same email already exists in the system");
		sr.put("exception", exp.getClass().getName());
		try {
			JacksonUtils.serialize(sr, response.getWriter());
		} catch (IOException e) {
			logger.error(e.getMessage(),e);
		}
	}
	
	@ResponseBody
	@Transactional
	@RequestMapping(value = {"/service/profile/resetpassword"}, method = {RequestMethod.POST})
	public ServiceResponse reset(@Valid ResetPasswordForm form, BindingResult result, HttpServletRequest request)
	throws IOException {
		logger.debug("reset password");
		
		ServiceResponse response = new ServiceResponse(result);
		try {
			resetPassword(request,form);
			response.put("redirect", "home");
		} catch (DateExpriedException exp) {
			response.putError("password", "password was not updated");
		}
		return response;
	}
	
	@Transactional
	@RequestMapping(value = {"/profile/resetpassword"}, method = {RequestMethod.POST})
	public String resetWithHeader(final ResetPasswordForm form, BindingResult result, HttpServletRequest request)
	throws IOException {
		resetPassword(request,form);

		return "redirect:/";
	}

	private void resetPassword(HttpServletRequest request,ResetPasswordForm form){
//		String userId = (String)request.getSession().getAttribute(Constants.SessionAttributeKey.USER_ID);
		
		
//		form.setEmail(userId);
		daoManager.resetPassword(form);
		logger.debug("--->loggin in after reset password");
		Authentication auth = new UsernamePasswordAuthenticationToken(form.getEmail(), form.getPassword());
		SecurityContextHolder.getContext().setAuthentication(auth);
		
	}
}
