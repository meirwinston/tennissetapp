package com.tennissetapp.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;

public class TennisSetAppLogoutHandler extends org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler{
	protected Logger logger = Logger.getLogger(getClass());
	
	//reacts: <form id="signup.loginForm" method="POST" action='j_spring_security_check'>
	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response,Authentication authentication){
		logger.debug("--->logout");
		super.logout(request, response, authentication);
	}
}
