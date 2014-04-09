package com.tennissetapp.rest;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.social.NotAuthorizedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.tennissetapp.controller.ControllerBase;
import com.tennissetapp.json.JacksonUtils;

public abstract class AbstractService extends ControllerBase{
	
	protected void writeException(HttpServletResponse response,String message, String code){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("exception", message);
		map.put("code", code);
		try {
			response.getWriter().print(JacksonUtils.serialize(map));
		} catch (IOException e) {
			logger.error(e.getMessage(),e);
		}
	}
	
	@ExceptionHandler(Exception.class)
	protected void handleException(Exception exp, HttpServletRequest request, HttpServletResponse response){
		logger.error(exp.getMessage(),exp);
		writeException(response, exp.getMessage(), exp.getClass().getName());
	}
	
	@ExceptionHandler(MySQLIntegrityConstraintViolationException.class)
	protected void handleMySQLIntegrityConstraintViolationException(MySQLIntegrityConstraintViolationException exp, HttpServletRequest request, HttpServletResponse response){
		logger.error(exp.getMessage(),exp);
		writeException(response, exp.getMessage(),exp.getClass().getName());
	}
	
	@ExceptionHandler(AuthenticationCredentialsNotFoundException.class)
	protected void handleAuthenticationCredentialsNotFoundException(AuthenticationCredentialsNotFoundException exp, HttpServletRequest request, HttpServletResponse response){
//		logger.error(exp.getMessage(),exp);
		writeException(response, "You must be logged in to complete this action",com.tennissetapp.exception.NotAuthorizedException.class.getName());
	}
	
	@ExceptionHandler(NotAuthorizedException.class)
	protected void handleNotAuthorizedException(com.tennissetapp.exception.NotAuthorizedException exp, HttpServletRequest request, HttpServletResponse response){
		writeException(response, "You must be logged in to complete this action",exp.getClass().getName());
	}
}
