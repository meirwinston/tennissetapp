package com.tennissetapp.auth;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import com.tennissetapp.persistence.dao.DaoManager;

public class TennisSetAppAuthenticationDetailsSource extends WebAuthenticationDetailsSource{
	Logger logger = Logger.getLogger(getClass());
	@Inject
	protected DaoManager daoManager;
	
	@Override
	public WebAuthenticationDetails buildDetails(HttpServletRequest request) {
		logger.debug("buildDetails: " + request.getParameter("j_username") + ", " + daoManager);
		
		WebUserDetails details = new WebUserDetails(request);
		String email = request.getParameter("j_username");
		Long userAccountId = daoManager.findUserAccountIdByEmail(email);
		if(userAccountId != null){
			details.setUserAccountId(userAccountId);
		}
		
		return details;
	}

}
