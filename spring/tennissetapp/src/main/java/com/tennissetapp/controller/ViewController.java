package com.tennissetapp.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.Date;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.FacebookProfile;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.tennissetapp.Constants;
import com.tennissetapp.exception.NotAuthorizedException;
import com.tennissetapp.persistence.dao.DaoManager;
import com.tennissetapp.persistence.entities.TennisPlayerProfile;
import com.tennissetapp.persistence.entities.UserAccount;
import com.tennissetapp.util.TennisSetAppUtils;

/**
 * returns JSP, HTML views. This controller is responsible for serving page requests.
 * 
 * @author meir
 */
@Controller
public class ViewController extends ControllerBase{
	@Inject
	protected DaoManager daoManager;
	
	@Inject 
	protected AuthenticationManager authenticationManager;
	
	@Value(value="${tennissetapp.globalUrl}")
	private String globalUrl;

//	@Inject MailService mailService;
	
	public ViewController(){
	}
	
	@ExceptionHandler(NotAuthorizedException.class)
	protected void handleNotAuthorizedException(NotAuthorizedException exp, HttpServletRequest request, HttpServletResponse response){
		try {
			logger.debug("handleNotAuthorizedException - Storing most reset URL in session " + TennisSetAppUtils.extractUrl(request));
			request.getSession().setAttribute(Constants.SessionAttributeKey.MOST_RECENT_URL, TennisSetAppUtils.extractUrl(request));
			response.sendRedirect(request.getContextPath() + "/login");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@ExceptionHandler(AuthenticationCredentialsNotFoundException.class)
	protected void handleAuthenticationCredentialsNotFoundException(AuthenticationCredentialsNotFoundException exp, HttpServletRequest request, HttpServletResponse response){
		try {
			logger.debug("handleAuthenticationCredentialsNotFoundException - Storing most reset URL in session " + TennisSetAppUtils.extractUrl(request));
			request.getSession().setAttribute(Constants.SessionAttributeKey.MOST_RECENT_URL, TennisSetAppUtils.extractUrl(request));
			response.sendRedirect(request.getContextPath() + "/login");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@ExceptionHandler(BadCredentialsException.class)
	protected void handleBadCredentialsException(BadCredentialsException exp, HttpServletRequest request, HttpServletResponse response){
		try {
			logger.debug("handleBadCredentialsException - Deleting most reset URL from session " + TennisSetAppUtils.extractUrl(request));
			request.getSession().removeAttribute(Constants.SessionAttributeKey.MOST_RECENT_URL);
			response.sendRedirect(request.getContextPath() + "/pages/pagenotfound");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//
	
//	@ExceptionHandler(NullPointerException.class)
//	protected void handleNullPointerException(NullPointerException exp, HttpServletRequest request, HttpServletResponse response){
//		logger.error(exp.getMessage(), exp);
//		try {
//			response.sendRedirect("/");
//		} catch (IOException e) {
//			logger.error(e.getMessage(), e);
//		}
//	}
//	
//	@ExceptionHandler(NotAuthorizedException.class)
//	protected void handleNotAuthorizedException(NotAuthorizedException exp, HttpServletRequest request, HttpServletResponse response){
//		logger.debug(exp.getMessage(), exp);
//		try {
//			response.sendRedirect(globalUrl + "/pages/pagenotfound");
//		} catch (IOException e) {
//			logger.error(e.getMessage(), e);
//		}
//	}
	
	@RequestMapping(value = {"/pages/pagenotfound"}, method = {RequestMethod.GET})
	public String pageNotFound(){
		return "pages/page-not-found.jsp";
	}
	
	//simple login - no social connection
	@RequestMapping(value = {"/login"}, method = {RequestMethod.GET})
	public ModelAndView login(WebRequest request)
	throws IOException {
		ModelAndView mv = new ModelAndView("login.jsp");
		return mv;
	}
	
	@Transactional
	@RequestMapping(value = {"/profile/activate/{activationToken}"}, method = {RequestMethod.GET})
	public ModelAndView activateProfile(@PathVariable String activationToken)
	throws IOException {
		ModelAndView mv = new ModelAndView("profile/activation-failed.jsp");
		
		UserAccount userAccount = daoManager.findUserAccountByActivationToken(activationToken);
		if(userAccount != null){
			if(userAccount.getPassword() == null){ //signup via social network
				mv.setViewName("redirect:/profile/resetpassword/" + activationToken);
			}
			else if(userAccount.getActivationToken() != null){
				if(userAccount.getActivationToken().equals(activationToken)){
					if(userAccount.getActivationTokenExpires() != null && userAccount.getActivationTokenExpires().after(new Date())){
						userAccount.setStatus(UserAccount.AccountStatus.ACTIVE);
						userAccount.setActivationToken(null);
						userAccount.setActivationTokenExpires(null);
						
						//must update the databse to set the account to ACTIVE before trying to login
						daoManager.flush();
						
						//BadCredentialsException will get caught in the handler
						Authentication token = new UsernamePasswordAuthenticationToken(userAccount.getEmail(),userAccount.getPassword());
						Authentication auth = authenticationManager.authenticate(token);
						SecurityContextHolder.getContext().setAuthentication(auth);
						
					}
				}
			}
			
		}
//		UserAccount userAccount = daoManager.activateUserAccount(activationToken);
		
		return mv;
	}
	
	@RequestMapping(value = {"/profile/resetpassword/{token}"}, method = {RequestMethod.GET})
	public ModelAndView resetPassword(@PathVariable String token)
	throws IOException {
		logger.debug("resetPassword " + token);
		ModelAndView mv = new ModelAndView("profile/reset-password.jsp");
		mv.addObject("token", token);
		return mv;
	}
	
	@RequestMapping(value = {"/home","/"}, method = {RequestMethod.GET})
	public ModelAndView home(Principal principal)
	throws IOException {
		if(principal == null){
			return new ModelAndView("login.jsp");
		}
		ModelAndView mv = new ModelAndView("home.jsp");
		return mv;
	}
	
	@Transactional
	@RequestMapping(value = {"/profile/teacher/complete"}, method = {RequestMethod.GET})
	public ModelAndView test(HttpServletRequest request)
	throws IOException {
		System.out.println("--->test: " + request.getSession().getId());
		ModelAndView mv = new ModelAndView("profile/complete-teacher-profile.jsp");
		return mv;
	}
	
	@Transactional
	@RequestMapping(value = {"/matches/create"}, method = {RequestMethod.GET})
	public ModelAndView createMatche(HttpServletRequest request)
	throws IOException {
		ModelAndView mv = new ModelAndView("matches/create-match.jsp");
		return mv;
	}
	
	@Transactional
	@RequestMapping(value = {"/matches"}, method = {RequestMethod.GET})
	public ModelAndView matches(HttpServletRequest request)
	throws IOException {
		ModelAndView mv = new ModelAndView("matches/browse-matches.jsp");
		return mv;
	}
	@PreAuthorize("hasRole('ROLE_USER')")
//	@RolesAllowed({"ROLE_USER"})
	@RequestMapping(value = {"/mates"}, method = {RequestMethod.GET})
	public ModelAndView mates(HttpServletRequest request)
	throws IOException {
		logger.debug("TTTTTTTTTTTTTTTTTTTTTT Mates called");
		ModelAndView mv = new ModelAndView("mates/browse-mates.jsp");
		return mv;
	}
	
	@Transactional
	@RequestMapping(value = {"/profile/{username}"}, method = {RequestMethod.GET})
	public ModelAndView userProfile(@PathVariable String username, HttpServletRequest request)
	throws IOException {
		ModelAndView mv = new ModelAndView("profile/user-profile.jsp");
		UserAccount userAccount = daoManager.findUserAccountByUsername(username);
		if(userAccount != null){
			TennisPlayerProfile profile = userAccount.getPlayerProfile();
			
			String profileUrl = TennisSetAppUtils.extractProfileImageUrl(profile);
			if(profileUrl != null){
				mv.addObject("profileImageUrl",profileUrl);
			}
			mv.addObject("levelOfPlay",profile.getLevelOfPlay());
			mv.addObject("address",userAccount.getAddress().getLocalityShortName() + ", " + userAccount.getAddress().getCountry());
			
			StringBuilder sb = new StringBuilder();
			if(profile.getPlayDoubles()){
				sb.append("Doubles, ");
			}
			if(profile.getPlayFullMatch()){
				sb.append("Full Match, ");
			}
			if(profile.getPlayHittingAround()){
				sb.append("Hitting Ground, ");
			}
			if(profile.getPlayPoints()){
				sb.append("Play Points, ");
			}
			if(profile.getPlaySingles()){
				sb.append("Play Singles, ");
			}
			sb.replace(sb.length()-2, sb.length()-1, "" );
			mv.addObject("typeOfGame", sb.toString());
			
			sb = new StringBuilder();
			if(profile.getAvailableWeekdayMorning()){
				sb.append("Weekday Morning, ");
			}
			if(profile.getAvailableWeekdayAfternoon()){
				sb.append("Weekday Afternoon, ");
			}
			if(profile.getAvailableWeekdayEvening()){
				sb.append("Weekday Evning, ");
			}
			
			if(profile.getAvailableWeekendMorning()){
				sb.append("Weekend Morning, ");
			}
			if(profile.getAvailableWeekendAfternoon()){
				sb.append("Weekend Afternoon, ");
			}
			if(profile.getAvailableWeekendEvening()){
				sb.append("Weekend Evening, ");
			}
			sb.replace(sb.length()-2, sb.length()-1, "" );
			mv.addObject("availability", sb.toString());
		}
		
		
		return mv;
	}
	
	@Transactional
	@RequestMapping(value = {"/profile/player/complete"}, method = {RequestMethod.GET})
	public ModelAndView completeProfile(@RequestParam(required=false) Long userAccountId, HttpServletRequest request)
	throws IOException {
		ModelAndView mv = new ModelAndView("profile/complete-player-profile.jsp");
		
		if(userAccountId == null){
			throw new com.tennissetapp.exception.NotAuthorizedException();
		}
		
		//pre populate fields from DB.
		UserAccount userAccount = daoManager.find(UserAccount.class, userAccountId);
		if(userAccount == null){
			throw new com.tennissetapp.exception.NotAuthorizedException();
		}
		
		mv.addObject("userAccountId",userAccountId);
		return mv;
	}
	
	//using 2 level url pattern may break css url??
	//resources/css/some.css will be broken
	//tennissetapp/resources/css/some.css will work
	@Transactional
	@RequestMapping(value = {"/profile/create"}, method = {RequestMethod.GET})
	public ModelAndView createProfile(@RequestParam(required=false) Long userId, HttpServletRequest request)
	throws IOException {
		logger.debug("createProfile: userId PARAM: " + userId);
		ModelAndView mv = new ModelAndView("profile/create-account.jsp");
		
		//pre populate fields from DB.
		if(userId == null){
			throw new com.tennissetapp.exception.NotAuthorizedException();
		}
		UserAccount userAccount = daoManager.find(UserAccount.class, userId);
		if(userAccount == null){
			throw new com.tennissetapp.exception.NotAuthorizedException();
		}
		//TODO
//		String profileUrl = TennisSetAppUtils.extractProfileImageUrl(userAccount);
//		if(profileUrl != null){
//			mv.addObject("profilePhotoUrl",request.getContextPath() +"/" + profileUrl);
//		}
		
		mv.addObject("userAccountId", userAccount.getUserAccountId());
//		mv.addObject("profileType", userAccount);
//		mv.addObject("agreesToTerms", args.birthMonth);
		mv.addObject("firstName", userAccount.getFirstName());
		mv.addObject("lastName", userAccount.getLastName());
		if(userAccount.getBirthDate() != null){
			String[] arr = userAccount.getBirthDate().split("-");
			mv.addObject("birthDay", arr[2]);
			mv.addObject("birthMonth", arr[1]);
			mv.addObject("birthYear", arr[0]);
		}
		mv.addObject("gender", userAccount.getGender());
		return mv;
	}
//	@Transactional
//	@RequestMapping(value = {"/profile/create"}, method = {RequestMethod.GET})
//	public ModelAndView createProfile(HttpServletRequest request)
//	throws IOException {
//		logger.debug("createProfile: " + ", " + request.getSession().getAttribute(Constants.SessionAttributeKey.USER_ID));
//		ModelAndView mv = new ModelAndView("profile/create-account.jsp");
//		
//		//pre populate fields from DB.
//		Long userId = (Long)request.getSession().getAttribute(Constants.SessionAttributeKey.USER_ID);
//		logger.debug("createProfile " + userId);
//		if(userId == null){
//			throw new com.tennissetapp.exception.NotAuthorizedException();
//		}
//		UserAccount userAccount = daoManager.find(UserAccount.class, userId);
//		if(userAccount == null){
//			throw new com.tennissetapp.exception.NotAuthorizedException();
//		}
//		CreateProfileForm form = new CreateProfileForm(userAccount);
//		mv.addAllObjects(form.toMap());
////		UserProfile userProfile = daoManager.findUserProfileByEmail(request.getUserPrincipal().getName());
////		if(userProfile != null){
////			CreatePlayerProfileRequest form = new CreatePlayerProfileRequest(userProfile);
////			mv.addAllObjects(form.toMap());
////		}
//		
//		return mv;
//	}
	
	
	
	//using 2 level url pattern may break css url??
	//resources/css/some.css will be broken
	//tennissetapp/resources/css/some.css will work
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = {"/courts/create"}, method = {RequestMethod.GET})
	public ModelAndView createTennisCourt(Principal principal)
	throws IOException {
		ModelAndView mv = new ModelAndView("/courts/create-tennis-center.jsp");
		return mv;
	}
	
	@RequestMapping(value = {"/courts"}, method = {RequestMethod.GET})
	public String courts(WebRequest request)
	throws IOException {
		logger.debug("--->courts");
		
		return "/courts/browse-tennis-courts.jsp";
	}
	
	@RequestMapping(value = {"/profile/completed"}, method = {RequestMethod.GET})
	public ModelAndView profileAccountCompleted(Principal principal)
	throws IOException {
		ModelAndView mv = new ModelAndView("profile/completed.jsp");
		
		return mv;
	}
	
	@Inject
	private ConnectionRepository connectionRepository;
	
	
	/**
	 * Called after approve in provider's authorise page 
	 */
	@RequestMapping(value = {"/signup"}, method = {RequestMethod.GET})
	public String signup(WebRequest request)
	throws IOException {
		logger.debug("signup");
		Connection<?> connection = ProviderSignInUtils.getConnection(request);
		if (connection != null) {
			logger.debug("signup - Here I am 1");
			UserProfile userProfile = connection.fetchUserProfile();
			logger.debug("Connection fetch user: " + userProfile.getUsername());
			logger.debug("Email: " + userProfile.getEmail());
			logger.debug("First: " + userProfile.getFirstName());
			logger.debug("Last: " + userProfile.getLastName());
			logger.debug("Name: " + userProfile.getName());
			
//			//Spring signIn - required for connectionRepository
			SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userProfile.getUsername(), null, null));
			ProviderSignInUtils.handlePostSignUp(userProfile.getUsername(), request);
			
			
//			
			//------------Twitter
//			logger.debug("connectionRepository: " + connectionRepository);
//			Connection<Twitter> twitterConnection = connectionRepository.findPrimaryConnection(Twitter.class);
//			logger.debug("--API twitter-->" + twitterConnection);
//			if (twitterConnection == null) {
//				logger.debug("--->redirecting to redirect:/connect/twitter");
//				return "redirect:connect/twitter";
//			}
//			else{
//				logger.debug("--Twitter User Profile-->" + twitterConnection.getApi().userOperations().getUserProfile().getName());
//			}
			//-----------Facebook
//			logger.debug("connectionRepository: " + connectionRepository);
//			Connection<Facebook> facebookConnection = connectionRepository.findPrimaryConnection(Facebook.class);
//			logger.debug("--API-->" + facebookConnection);
//			if (facebookConnection == null) {
//				logger.debug("--->redirecting to redirect:/connect/facebook");
//				return "redirect:connect/facebook";
//			}
//			else{
//				logger.debug("--Facebook User Profile-->" + facebookConnection.getApi().userOperations().getUserProfile());
//			}
		} else {
			logger.debug("connection is null");
		}
		return "signup.jsp";
	}
	
	//a callback after facebook is called by <form action="connect/facebook" method="POST">
	////https://www.facebook.com/dialog/oauth?client_id=603067949713524&response_type=code&redirect_uri=http%3A%2F%2Flocalhost%3A8080%2Ftennissetapp%2Fconnect%2Ffacebook&scope=publish_stream%2Cuser_photos%2Coffline_access
	//at this point facebookConnection won't be empty
	//this is the callback: 
	// http://localhost:8080/tennissetapp/connect/facebook?code=AQAv1BmxVPGgAHyAd2tF4XOQQT78RYFtwqd7rgvflDOL5qc9zjdC4O5pLZERvBlUZjmnaPVZJwJtMLFGrgK2PHer2Z8GkIaP-6HokxWr6N12sMNT_qLC8SGrJ7-ffvGkK8G-sXBNMiFcKSuU4LYDfkb37Q_XTeOIOTo0ZPIsFRWXiqEdPlZ0OqN-Pzsi5O8pm3rTmu5aFEdQkPc7SF6RW8I76kd9qoYd6fIF20W_cmCS7LBOh3wpthkgMuRE8dBKIQZLs5PEq5uzeEEDrOljZOPhpmovlVWFcwPU94FuOq4zoufCBmn4zQp3NxmNRnBDbao#_=_
	//notice the difference: this method is GET and not POST
	@RequestMapping(value = {"/connect/facebook"}, method = {RequestMethod.GET})
	public String facebookConnect(WebRequest request)
	throws IOException {
		logger.debug("--->connect facebook");
		//
		logger.debug("connectionRepository: " + connectionRepository);
		Connection<Facebook> facebookConnection = connectionRepository.findPrimaryConnection(Facebook.class);
		logger.debug("--API-->" + facebookConnection);
		FacebookProfile profile = facebookConnection.getApi().userOperations().getUserProfile();
		logger.debug("---->" + profile + ", " + profile.getEmail());
		
		return "home.jsp";
	}
	
//	@RequestMapping(value = {"/connect/twitter"}, method = {RequestMethod.GET})
//	public String twitterConnect(WebRequest request)
//	throws IOException {
//		logger.debug("--->connect twitter");
//		//
//		logger.debug("connectionRepository: " + connectionRepository);
//		Connection<Facebook> twitterConnection = connectionRepository.findPrimaryConnection(Facebook.class);
//		logger.debug("--API-->" + twitterConnection);
//		FacebookProfile profile = twitterConnection.getApi().userOperations().getUserProfile();
//		logger.debug("---->" + profile + ", " + profile.getEmail());
//		
//		return "home.jsp";
//	}
	
	
	
	
//	@RequestMapping(value = {"/courts/create"}, method = {RequestMethod.GET})
//	public String createCourt(WebRequest request)
//	throws IOException {
//		logger.debug("--->createCourt");
//		
//		return "courts/create.jsp";
//	}
}
