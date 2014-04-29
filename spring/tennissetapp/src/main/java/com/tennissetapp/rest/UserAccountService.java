package com.tennissetapp.rest;

import java.io.IOException;
import java.security.Principal;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.ReflectionSaltSource;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tennissetapp.Constants;
import com.tennissetapp.args.CreatePlayerProfileArgs;
import com.tennissetapp.args.SigninArgs;
import com.tennissetapp.args.SignupArgs;
import com.tennissetapp.args.UserAccountPrimaryArgs;
import com.tennissetapp.auth.AppUserDetails;
import com.tennissetapp.controller.response.ServiceResponse;
import com.tennissetapp.exception.DuplicateRecordException;
import com.tennissetapp.forms.CreatePlayerProfileForm;
import com.tennissetapp.forms.SigninForm;
import com.tennissetapp.forms.SignupForm;
import com.tennissetapp.forms.UserAccountPrimaryForm;
import com.tennissetapp.json.JacksonUtils;
import com.tennissetapp.persistence.dao.DaoManager;
import com.tennissetapp.persistence.entities.TennisPlayerProfile;
import com.tennissetapp.persistence.entities.UserAccount;
import com.tennissetapp.persistence.entities.UserAccount.AccountStatus;
import com.tennissetapp.util.TennisSetAppUtils;
import com.tennissetapp.validation.UserAccountServiceValidator;

@Controller
public class UserAccountService {
	protected Logger logger = Logger.getLogger(getClass());
	
	@Inject 
	protected DaoManager daoManager;
	
	@Inject
	protected AuthenticationManager authenticationManager;
	
	@Inject 
	protected Md5PasswordEncoder passwordEncoder;
	
	@Inject
	protected ReflectionSaltSource saltSource;
	
	
	@Inject
	protected UserDetailsService userDetailsService;
	
	@InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(new UserAccountServiceValidator());
    }
	
//	@Inject 
//	protected RequestCache requestCache;
	
//	@ExceptionHandler(DataIntegrityViolationException.class)
//	protected RedirectView handleDataIntegrityViolationException(DataIntegrityViolationException exp, HttpServletRequest request, HttpServletResponse response){
//		RedirectView rw = new RedirectView("signup");
////	    rw.setStatusCode(HttpStatus.MOVED_PERMANENTLY); // you might not need this
//	    FlashMap outputFlashMap = RequestContextUtils.getOutputFlashMap(request);
//	    if (outputFlashMap != null){
//	        outputFlashMap.put("exception", exp.getClass().getName());
//	    }
//	    return rw;
//	}
//	
//	@Transactional
//	@RequestMapping(value = {"/signup"}, method = {RequestMethod.POST})
//	public String signup(final SignupForm form, HttpServletRequest request)
//	throws IOException {
//		form.setIpAddress(TennisSetAppUtils.getClientIP(request));
//		logger.debug("--->signup " + form);
//		UserAccount userAccount = daoManager.createUserAccount(form);
//		if(userAccount != null){
//			Authentication auth = new UsernamePasswordAuthenticationToken(form.getEmail(), form.getPassword());
//			SecurityContextHolder.getContext().setAuthentication(auth);
//		}
//		
//		return "redirect:/profile/create";
//	}
	
	//-----------------------------
	
	
//	@ExceptionHandler(DataIntegrityViolationException.class)
//	protected void handleDataIntegrityViolationException(DataIntegrityViolationException exp, HttpServletResponse response){
//		logger.debug("Handle DataIntegrityViolationException");
//		logger.error(exp.getMessage(), exp);
//		ServiceResponse sr = new ServiceResponse();
//		sr.put("exception", "A user with the same email already exists in the system");
//		
//		try {
//			JacksonUtils.serialize(sr, response.getWriter());
//		} catch (IOException e) {
//			logger.error(e.getMessage(),e);
//		}
//	}
	
	@ExceptionHandler(RuntimeException.class)
	protected void handleRuntimeException(RuntimeException exp, HttpServletResponse response){
		logger.debug("Handle RuntimeException");
		logger.warn(exp.getMessage(), exp);
		ServiceResponse sr = new ServiceResponse();
//		sr.put("exception", "A user with the same email already exists in the system");
		sr.put("exception", exp.getMessage());
		try {
			JacksonUtils.serialize(sr, response.getWriter());
		} catch (IOException e) {
			logger.error(e.getMessage(),e);
		}
	}
	
//	@Transactional
//	@RequestMapping(value = {"/signup"}, method = {RequestMethod.POST})
//	public String signupWithHeader(@Valid SignupForm form, BindingResult result, HttpServletRequest request)
//	throws IOException {
//		signupUser(form,result,request);
//		return "redirect:/profile/create";
//	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@Transactional
	@ResponseBody
	@RequestMapping(value = {"/service/testAuth"}, method = {RequestMethod.POST})
	public ServiceResponse testAuth(Principal p){
		ServiceResponse r = new ServiceResponse();
		System.out.println("Principal " + p);
		logger.info("users details: " + TennisSetAppUtils.cast(p));
		return r;
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@Transactional
	@ResponseBody
	@RequestMapping(value = {"/service/accountId"}, method = {RequestMethod.GET})
	public ServiceResponse accountId(Principal p){
		ServiceResponse r = new ServiceResponse();
		if(p != null){
			r.put("userAccountId", TennisSetAppUtils.cast(p).getUserAccountId());
		}
		
		return r;
	}
	
	@ResponseBody
	@RequestMapping(value = {"/service/login"}, method = {RequestMethod.POST})
	public ServiceResponse login(@Valid SigninForm form, BindingResult result, HttpServletRequest request){
		ServiceResponse response = new ServiceResponse(result); //keep before signup to initialize the errors before the exception
		if(!result.hasErrors()){
			SigninArgs args = form.getArguments(SigninArgs.class);
		 	try {
				Authentication auth = new UsernamePasswordAuthenticationToken(args.email, args.password);
				Authentication authResult = authenticationManager.authenticate(auth);
				SecurityContextHolder.getContext().setAuthentication(authResult);
				AppUserDetails userDetails = (AppUserDetails)authResult.getPrincipal();
				response.put("userAccountId", userDetails.getUserAccountId());
//				org.springframework.security.authentication.UsernamePasswordAuthenticationToken@bbdc91b3: Principal: AppUserDetails [userAccountId=63, username=darawinston, password=null, isEnabled=true, isCredentialsNonExpired=true, isAccountNonLocked=true, isAccountNonExpired=true]; Credentials: [PROTECTED]; Authenticated: true; Details: null; Granted Authorities: ROLE_USER

			} catch (Exception exp) {
				response.put("exception", exp.getMessage());
			}
		}
		return response;
	}
	
	//MOBILE
	@ResponseBody
	@Transactional
	@RequestMapping(value = {"/service/signup"}, method = {RequestMethod.POST})
	public ServiceResponse signup(@Valid SignupForm form, BindingResult result, HttpServletRequest request)
	throws IOException {
//		logger.debug("signup error count: " + result.getErrorCount());
		ServiceResponse response = new ServiceResponse(result); //keep before signup to initialize the errors before the exception
		if(result.getErrorCount() <=0){
			try {
				
				SignupArgs args = form.getArguments(SignupArgs.class);
				String rawPassword = args.password;
				
				args.username = generateUsername(args.email);
				args.password = passwordEncoder.encodePassword(args.password, args.email);
				UserAccount userAccount = daoManager.createUserAccount(args);
				if(userAccount != null){
					response.put("userAccountId", userAccount.getUserAccountId());
					response.put("redirect", "profile/create?userId=" + userAccount.getUserAccountId());
					userAccount.setStatus(AccountStatus.ACTIVE);
					
					Authentication auth = new UsernamePasswordAuthenticationToken(args.email, rawPassword);
					Authentication authResult = authenticationManager.authenticate(auth);
					SecurityContextHolder.getContext().setAuthentication(authResult);
					logger.debug("User logged in " + userAccount);
				}
			}
			catch (DuplicateRecordException exp) {
				response.putError("email", "A user with the same email already exists in the system");
			}
//			catch (AccountExistsException exp) {
//				response.putError("email", "A user with the same email already exists in the system");
//			}
			catch (Exception exp) {
				logger.error(exp.getMessage(),exp);
				response.put("exception", exp.getMessage());
			}
		}
		
		return response;
	}
	
	private String generateUsername(String email){
		String username = StringUtils.substringBefore(email, "@");
		int count = 1;
		while(daoManager.usernameExists(username)){
			username = (username + count);
		}
			
		return username;
	}
	
	public UserAccount signupUser(SignupForm form, BindingResult result, HttpServletRequest request)
	throws IOException {
		
//		logger.debug("the session is " + request.getSession().getId());
//		logger.debug("--->signupUser " + form);
		SignupArgs args = (SignupArgs)form.getArguments(SignupArgs.class);
		args.ipAddress = TennisSetAppUtils.getClientIP(request);
		args.username = generateUsername(args.email);
		UserAccount userAccount = daoManager.createUserAccount(args);
		
		request.getSession().setAttribute(Constants.SessionAttributeKey.USER_ID, userAccount.getUserAccountId());
		
		return userAccount;
		
		
		
		
		//----------------
//		if(userAccount != null){
//			Authentication auth = new UsernamePasswordAuthenticationToken(form.getEmail(), form.getPassword());
			
//			SecurityContextHolder.getContext().setAuthentication(auth);
//			request.getSession().setAttribute("authentication", auth);
			
//		}
	}
	
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@Transactional
	@ResponseBody //return the body only
	@RequestMapping(value = {"/service/account/updatePrimary"}, method = {RequestMethod.POST})
	public ServiceResponse updatePrimary(@Valid UserAccountPrimaryForm form, BindingResult result,Principal principal)
	throws IOException {
		logger.debug("updatePrimary: " + principal + ", " + form);
		ServiceResponse response = new ServiceResponse(result);
		if(result.hasErrors()){
			return response;
		}
		UserAccountPrimaryArgs args = form.getArguments(UserAccountPrimaryArgs.class);
		AppUserDetails userDetails = TennisSetAppUtils.cast(principal);
//		logger.debug("updatePrimary++++++++++ " + userDetails + ", " + principal);
		args.userAccountId = userDetails.getUserAccountId();
		
		UserAccount userAccount = daoManager.updateAccountPrimaryFields(args);
		response.put("userAccountId", userAccount.getUserAccountId());		
		
		return response;
	}

	
}
