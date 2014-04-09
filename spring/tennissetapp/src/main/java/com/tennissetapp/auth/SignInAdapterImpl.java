package com.tennissetapp.auth;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.web.context.request.NativeWebRequest;

import com.tennissetapp.Constants;
import com.tennissetapp.controller.response.ServiceResponse;
import com.tennissetapp.service.FacebookService;
import com.tennissetapp.service.TwitterService;

//---->SignInAdapterImpl.signIn
//---->SignInAdapterImpl.extractOriginalUrl
//this will be called if there is a record in UserConnection I believe
public class SignInAdapterImpl implements SignInAdapter {
	protected Logger logger = Logger.getLogger(getClass());
	
	protected RequestCache requestCache;
	protected FacebookService facebookService;
	protected TwitterService twitterService;
	
	@Inject
	public SignInAdapterImpl(RequestCache requestCache) {
		this.requestCache = requestCache;
	}
	
	public FacebookService getFacebookService() {
		return facebookService;
	}

	public void setFacebookService(FacebookService facebookService) {
		this.facebookService = facebookService;
	}
	
	public TwitterService getTwitterService() {
		return twitterService;
	}

	public void setTwitterService(TwitterService twitterService) {
		this.twitterService = twitterService;
	}

	/**
	 * called right after login with facebook
	 */
	@Override
	public String signIn(String localUserId, final Connection<?> connection, final NativeWebRequest request) {
		logger.debug("signIn " + localUserId);
		ServiceResponse signupResponse = null;
		if (connection != null) {
			try {
				signupResponse = facebookService.signup(connection.fetchUserProfile(), request.getNativeRequest(HttpServletRequest.class));
			} catch (Exception e) {
				logger.error(e.getMessage(),e);
			}
		}
		if(signupResponse != null){
			logger.debug("signIn " + localUserId + " redirecting to: " + request.getContextPath() + "/profile/create?userId=" + signupResponse.get(Constants.SessionAttributeKey.USER_ID));
			return "/profile/create?userId=" + signupResponse.get(Constants.SessionAttributeKey.USER_ID);
		}
		else{
			return "redirect:connect/facebook";
//			return "redirect:" + request.getContextPath() + "/connect/facebook";
		}
	}
	
	
	//not in use
	protected String extractOriginalUrl(NativeWebRequest request) {
		HttpServletRequest nativeReq = request.getNativeRequest(HttpServletRequest.class);
		HttpServletResponse nativeRes = request.getNativeResponse(HttpServletResponse.class);
		SavedRequest savedRequest = requestCache.getRequest(nativeReq, nativeRes);
		if (savedRequest == null) {
			return null;
		}
		logger.debug("saved request " + savedRequest.getRedirectUrl());
		
		requestCache.removeRequest(nativeReq, nativeRes);
		removeAutheticationAttributes(nativeReq.getSession(false));
		return savedRequest.getRedirectUrl();
	}
		 
	private void removeAutheticationAttributes(HttpSession session) {
		logger.debug("---->removeAutheticationAttributes");
		if (session == null) {
			return;
		}
		session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
	}
}
