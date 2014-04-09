package com.tennissetapp.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.FacebookProfile;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.TransactionManager;
import javax.validation.Valid;

import com.tennissetapp.controller.response.ServiceResponse;
import com.tennissetapp.forms.CreateProfileForm;
import com.tennissetapp.forms.SignupForm;
import com.tennissetapp.persistence.dao.AuthDao;
import com.tennissetapp.persistence.dao.DaoManager;
import com.tennissetapp.persistence.entities.UserAccount;
import com.tennissetapp.service.TestService;
import com.tennissetapp.util.TennisSetAppUtils;
import com.tennissetapp.validation.FormValidator;

//@Controller
//@Transactional
public class AuthenticationContoller {
	protected Logger logger = Logger.getLogger(AuthenticationContoller.class);
	
//	@Inject
//	protected TestService testService;
	
//	@Inject
//	protected DaoManager daoManager;
	
//	@Inject
//	private ConnectionRepository connectionRepository;
	
//	@Inject
//	protected AuthenticationManager authenticationManager;
	
//	@ExceptionHandler(Exception.class)
//	protected void handleException(Exception exp, HttpServletRequest request, HttpServletResponse response){
//		logger.error("handleDataIntegrityViolationException" + exp.getMessage(), exp);
//		try {
//			response.sendRedirect("");
//		} catch (IOException e) {
//			logger.error(e.getMessage(), e);
//		}
//	}
//	@ExceptionHandler(NullPointerException.class)
//	protected void handleNullPointerException(NullPointerException exp, HttpServletRequest request, HttpServletResponse response){
//		logger.error("handleDataIntegrityViolationException" + exp.getMessage(), exp);
//		try {
//			response.sendRedirect("");
//		} catch (IOException e) {
//			logger.error(e.getMessage(), e);
//		}
//	}
//	
//	@ExceptionHandler(DataIntegrityViolationException.class)
//	protected void handleDataIntegrityViolationException(DataIntegrityViolationException exp, HttpServletRequest request, HttpServletResponse response){
//		logger.error("handleDataIntegrityViolationException" + exp.getMessage(), exp);
//		try {
//			response.sendRedirect("");
//		} catch (IOException e) {
//			logger.error(e.getMessage(), e);
//		}
//	}
	
	//org.springframework.dao.
	
//	@Inject
//	protected PlatformTransactionManager platformTransactionManager;
	
//	@InitBinder
//    protected void initBinder(WebDataBinder binder) {
//		logger.debug("---->initBinder: create FormValidator");
//        binder.setValidator(new FormValidator());
//    }
	
	
//	private void connectToFacebook(UserProfile userProfile, WebRequest request){
//		//Spring signIn - required for connectionRepository
//		SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userProfile.getUsername(), null, null));
//		ProviderSignInUtils.handlePostSignUp(userProfile.getUsername(), request);
//		
//		logger.debug("connectionRepository: " + connectionRepository);
//		Connection<Facebook> facebookConnection = connectionRepository.findPrimaryConnection(Facebook.class);
//		logger.debug("--API-->" + facebookConnection);
//		
//		//https://www.facebook.com/dialog/oauth?client_id=603067949713524&response_type=code&redirect_uri=http%3A%2F%2Flocalhost%3A8080%2Ftennissetapp%2Fconnect%2Ffacebook&scope=publish_stream%2Cuser_photos%2Coffline_access
//		if (facebookConnection == null) {
//			logger.debug("--->redirecting to redirect:/connect/facebook");
//			return "redirect:connect/facebook";
//		}
//		else{
//			logger.debug("--Facebook User Profile-->" + facebookConnection.getApi().userOperations().getUserProfile());
//		}
////		logger.debug("---->" + facebookConnection.getApi().userOperations().getUserProfile());
//		
//		//connection.getApi().userOperations().getUserProfile()
//	}
//	/**
//	 * Called after approve in provider's authorise page 
//	 */
//	@RequestMapping(value = {"/signup"}, method = {RequestMethod.GET})
//	public String signup(WebRequest request)
//	throws IOException {
//		logger.debug("--------------------------------" + new ClassPathResource("config.properties").getFile().getAbsolutePath());
//		logger.debug("--->signup");
//		Connection<?> connection = ProviderSignInUtils.getConnection(request);
//		if (connection != null) {
//			logger.debug("signup - Here I am 1");
//			UserProfile userProfile = connection.fetchUserProfile();
//			logger.debug("Connection fetch user: " + userProfile.getUsername());
//			logger.debug("Email: " + userProfile.getEmail());
//			logger.debug("First: " + userProfile.getFirstName());
//			logger.debug("Last: " + userProfile.getLastName());
//			logger.debug("Name: " + userProfile.getName());
//			
////			//Spring signIn - required for connectionRepository
//			SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userProfile.getUsername(), null, null));
//			ProviderSignInUtils.handlePostSignUp(userProfile.getUsername(), request);
//			
//			
////			
//			//------------Twitter
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
//			//-----------Facebook
////			logger.debug("connectionRepository: " + connectionRepository);
////			Connection<Facebook> facebookConnection = connectionRepository.findPrimaryConnection(Facebook.class);
////			logger.debug("--API-->" + facebookConnection);
////			if (facebookConnection == null) {
////				logger.debug("--->redirecting to redirect:/connect/facebook");
////				return "redirect:connect/facebook";
////			}
////			else{
////				logger.debug("--Facebook User Profile-->" + facebookConnection.getApi().userOperations().getUserProfile());
////			}
//		} else {
//			logger.debug("connection is null");
//		}
//		return "signup.jsp";
//	}
	
//	public UserAccount newUserAccount(SignupForm form){
//		
//		
//		return userAccount;
//	}
	
//	@Transactional
//	@RequestMapping(value = {"/signup"}, method = {RequestMethod.POST})
//	public String signup(final SignupForm form, HttpServletRequest request)
//	throws IOException {
//		try {
//			form.setIpAddress(TennisSetAppUtils.getClientIP(request));
//			logger.debug("--->signup " + form);
//			UserAccount userAccount = daoManager.createUserAccount(form);
//			
//			Authentication auth = new UsernamePasswordAuthenticationToken(form.getEmail(), form.getPassword());
//			SecurityContextHolder.getContext().setAuthentication(auth);
//
//			return "redirect:/profile/create";
//		} 
//		catch(DataIntegrityViolationException exp){
//			logger.error("11111111111111111111111" + exp.getMessage(), exp);
//			return "/signup?error=duplicate_entry";
//		}
//		catch (Exception exp) {
//			logger.error("2222222222222" + exp.getMessage(), exp);
//			
//			return "/signup?error";
//		}
//		
//	}
	
//	@RequestMapping(value = {"/account/create"}, method = {RequestMethod.POST})
//	public String newUserAccount(CreateProfileForm form)
//	throws IOException {
//		logger.debug("--->AuthenticationController.createAccount ");
//		return "home.jsp";
//	}
	
	
//	//a callback after facebook is called by <form action="connect/facebook" method="POST">
//	////https://www.facebook.com/dialog/oauth?client_id=603067949713524&response_type=code&redirect_uri=http%3A%2F%2Flocalhost%3A8080%2Ftennissetapp%2Fconnect%2Ffacebook&scope=publish_stream%2Cuser_photos%2Coffline_access
//	//at this point facebookConnection won't be empty
//	//this is the callback: 
//	// http://localhost:8080/tennissetapp/connect/facebook?code=AQAv1BmxVPGgAHyAd2tF4XOQQT78RYFtwqd7rgvflDOL5qc9zjdC4O5pLZERvBlUZjmnaPVZJwJtMLFGrgK2PHer2Z8GkIaP-6HokxWr6N12sMNT_qLC8SGrJ7-ffvGkK8G-sXBNMiFcKSuU4LYDfkb37Q_XTeOIOTo0ZPIsFRWXiqEdPlZ0OqN-Pzsi5O8pm3rTmu5aFEdQkPc7SF6RW8I76kd9qoYd6fIF20W_cmCS7LBOh3wpthkgMuRE8dBKIQZLs5PEq5uzeEEDrOljZOPhpmovlVWFcwPU94FuOq4zoufCBmn4zQp3NxmNRnBDbao#_=_
//	//notice the difference: this method is GET and not POST
//	@RequestMapping(value = {"/connect/facebook"}, method = {RequestMethod.GET})
//	public String facebookConnect(WebRequest request)
//	throws IOException {
//		logger.debug("--->connect facebook");
//		//
//		logger.debug("connectionRepository: " + connectionRepository);
//		Connection<Facebook> facebookConnection = connectionRepository.findPrimaryConnection(Facebook.class);
//		logger.debug("--API-->" + facebookConnection);
//		FacebookProfile profile = facebookConnection.getApi().userOperations().getUserProfile();
//		logger.debug("---->" + profile + ", " + profile.getEmail());
//		
//		return "home.jsp";
//	}
	
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
	
	@RequestMapping(value = {"/signin"}, method = {RequestMethod.GET})
	public ModelAndView signin(WebRequest request)
	throws IOException {
//		logger.debug("--->signin " + request.getUserPrincipal());
		Connection<?> connection = ProviderSignInUtils.getConnection(request);
		if (connection != null) {
			logger.debug("signin with connection");
			UserProfile userProfile = connection.fetchUserProfile();
			logger.debug("Connection fetch user: " + userProfile.getUsername());
			logger.debug("Email: " + userProfile.getEmail());
			logger.debug("First: " + userProfile.getFirstName());
			logger.debug("Last: " + userProfile.getLastName());
			logger.debug("Name: " + userProfile.getName());
			
//			logger.debug("connectionRepository: " + connectionRepository);
//			Connection<Facebook> facebookConnection = connectionRepository.findPrimaryConnection(Facebook.class);
//			logger.debug("---->" + facebookConnection.getApi().userOperations().getUserProfile());
			
			//connection.getApi().userOperations().getUserProfile()
		} else {
			logger.debug("signin, connection is null");
		}
		ModelAndView mv = new ModelAndView("signin.jsp");
		return mv;
	}	
	
//	//simple login - no social connection
//	@RequestMapping(value = {"/login"}, method = {RequestMethod.GET})
//	public ModelAndView login(WebRequest request)
//	throws IOException {
//		ModelAndView mv = new ModelAndView("login.jsp");
//		return mv;
//	}
	
//	@RequestMapping(value = {"/signin"}, method = {RequestMethod.GET})
//	public SigninForm signin(WebRequest request)
//	throws IOException {
//		logger.debug("--->signin");
//		SigninForm form = new SigninForm();
//		Connection<?> connection = ProviderSignInUtils.getConnection(request);
//		if (connection != null) {
//			UserProfile userProfile = connection.fetchUserProfile();
//			form.setEmail(userProfile.getEmail());
//			logger.debug("Connection fetch user: " + userProfile.getUsername());
//			logger.debug("Email: " + userProfile.getEmail());
//			logger.debug("First: " + userProfile.getFirstName());
//			logger.debug("Last: " + userProfile.getLastName());
//			logger.debug("Name: " + userProfile.getName());
//			
//		} else {
//			logger.debug("Here I am 2, connection is null");
//		}
//		return form;
//	}	
	
}

/*
 
 import org.springframework.social.showcase.account.Account;
import org.springframework.social.showcase.account.AccountRepository;
import org.springframework.social.showcase.account.UsernameAlreadyInUseException;
import org.springframework.social.showcase.message.Message;
import org.springframework.social.showcase.message.MessageType;
import org.springframework.social.showcase.signin.SignInUtils;
@Controller
public class SignupController {

	private final AccountRepository accountRepository;

	@Inject
	public SignupController(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	@RequestMapping(value="/signup", method=RequestMethod.GET)
	public SignupForm signupForm(WebRequest request) {
		Connection<?> connection = ProviderSignInUtils.getConnection(request);
		if (connection != null) {
			request.setAttribute("message", new Message(MessageType.INFO, "Your " + StringUtils.capitalize(connection.getKey().getProviderId()) + " account is not associated with a Spring Social Showcase account. If you're new, please sign up."), WebRequest.SCOPE_REQUEST);
			return SignupForm.fromProviderUser(connection.fetchUserProfile());
		} else {
			return new SignupForm();
		}
	}

	@RequestMapping(value="/signup", method=RequestMethod.POST)
	public String signup(@Valid SignupForm form, BindingResult formBinding, WebRequest request) {
		if (formBinding.hasErrors()) {
			return null;
		}
		Account account = createAccount(form, formBinding);
		if (account != null) {
			SignInUtils.signin(account.getUsername());
			ProviderSignInUtils.handlePostSignUp(account.getUsername(), request);
			return "redirect:/";
		}
		return null;
	}

	// internal helpers
	
	private Account createAccount(SignupForm form, BindingResult formBinding) {
		try {
			Account account = new Account(form.getUsername(), form.getPassword(), form.getFirstName(), form.getLastName());
			accountRepository.createAccount(account);
			return account;
		} catch (UsernameAlreadyInUseException e) {
			formBinding.rejectValue("username", "user.duplicateUsername", "already in use");
			return null;
		}
	}

}

 */