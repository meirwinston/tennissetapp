package com.tennissetapp.auth;

import java.io.IOException;
import java.security.Principal;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import com.tennissetapp.Constants;

public class TennisSetAppAuthenticationHandler extends SimpleUrlAuthenticationSuccessHandler{
	private Logger logger = Logger.getLogger(getClass());
	private SessionFactory sessionFactory;
	
//	@Inject
//	private MailService mailService;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
	throws IOException, ServletException {
		String url = (String) request.getSession().getAttribute(Constants.SessionAttributeKey.MOST_RECENT_URL);
		logger.debug("------> login to " + url);
		if (url != null) {
			this.setDefaultTargetUrl(url);
		} else {
			this.setDefaultTargetUrl("/");
		}
		// I think you should call for the principal before super???
		Principal principal = request.getUserPrincipal();
//		WebAuthenticationDetails details = (WebAuthenticationDetails)((UsernamePasswordAuthenticationToken)principal).getDetails();
		super.onAuthenticationSuccess(request, response, authentication);
		logger.debug("------> login complete: " + principal.getName());
	}
	
//	static class AuthenticationDetails extends WebAuthenticationDetails{
//		AuthenticationDetails(HttpServletRequest request){
//			super(request);
//		}
//	}
}
