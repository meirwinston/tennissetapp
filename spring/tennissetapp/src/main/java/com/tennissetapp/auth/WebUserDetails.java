package com.tennissetapp.auth;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import com.tennissetapp.exception.NotAuthorizedException;
import com.tennissetapp.util.TennisSetAppUtils;

@Deprecated
public class WebUserDetails extends WebAuthenticationDetails{
	private static final long serialVersionUID = 1L;
	protected Long userAccountId;

	public WebUserDetails(HttpServletRequest request){
		super(request);
	}

	public Long getUserAccountId() {
		return userAccountId;
	}

	public void setUserAccountId(Long userAccountId) {
		this.userAccountId = userAccountId;
	}
	
	public static WebUserDetails userDetailsOf(Principal principal){
		if(principal == null) throw new NotAuthorizedException("User not logged in");
//		TennisSetAppAuthenticationDetails userDetails = (TennisSetAppAuthenticationDetails)((UsernamePasswordAuthenticationToken)principal).getDetails();
//		return userDetails;
//		return TennisSetAppUtils.cast(principal);
		return null;
	}

	@Override
	public String toString() {
		return "TennisSetAppAuthenticationDetails [userAccountId="
				+ userAccountId + ", getRemoteAddress()=" + getRemoteAddress()
				+ ", getSessionId()=" + getSessionId() + "]";
	}
}
