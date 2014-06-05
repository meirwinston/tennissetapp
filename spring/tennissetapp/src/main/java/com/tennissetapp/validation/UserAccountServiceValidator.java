package com.tennissetapp.validation;

import java.util.regex.Matcher;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.validation.Errors;

import com.tennissetapp.args.SigninArgs;
import com.tennissetapp.args.SignupArgs;
import com.tennissetapp.args.UpdateTokenArgs;
import com.tennissetapp.args.UserAccountPrimaryArgs;
import com.tennissetapp.forms.AddressForm;
import com.tennissetapp.forms.SigninForm;
import com.tennissetapp.forms.SignupForm;
import com.tennissetapp.forms.UpdateTokenForm;
import com.tennissetapp.forms.UserAccountPrimaryForm;
import com.tennissetapp.persistence.entities.UserAccount;

public class UserAccountServiceValidator extends Validator{
	protected Logger logger = Logger.getLogger(getClass());
	
//	private static final String EMAIL_PATTERN = 
//			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
//			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
//	private Pattern pattern;
	private Matcher matcher;
	
	public UserAccountServiceValidator(){
//		pattern = Pattern.compile(EMAIL_PATTERN);
	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		return SignupForm.class.equals(clazz) || 
				SigninForm.class.equals(clazz) ||
				UserAccountPrimaryForm.class.equals(clazz) ||
				UpdateTokenForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		logger.debug("validate " + target);
		if(target instanceof SignupForm){
			SignupForm form = (SignupForm)target;
			validate(form,errors, true);
		}
		else if(target instanceof SigninForm){
			SigninForm form = (SigninForm)target;
			validate(form,errors);
		}
		else if(target instanceof UserAccountPrimaryForm){
			validate((UserAccountPrimaryForm)target,errors);
		}
		else if(target instanceof UpdateTokenForm){
			validate((UpdateTokenForm)target,errors);
		}
		
		
	}

	private void validate(UserAccountPrimaryForm form, Errors errors) {
		UserAccountPrimaryArgs args = new UserAccountPrimaryArgs();
		super.validate((AddressForm)form, errors,args);
		if(StringUtils.isEmpty(form.getAgreesToTerms())){
			errors.rejectValue("agressToTerms", ErrorCode.EMPTY_FIELD, "You must agree to the terms");
		}
		else{
			try {
				args.agreesToTerms = Boolean.valueOf(form.getAgreesToTerms());
			} catch (Exception exp) {
				errors.rejectValue("agressToTerms", ErrorCode.INVALID_EXPRESSION, "wrong value on agree to terms");
			}
		}
		
		//-- first name
		if(StringUtils.isEmpty(form.getFirstName())){
			errors.rejectValue("firstName", ErrorCode.EMPTY_FIELD, "First name cannot be empty");
		}
		else if(form.getFirstName().length() > NAME_INPUT_MAX_LENGTH){
			errors.rejectValue("firstName", ErrorCode.EMPTY_FIELD, "Field must not exceed limit of " + NAME_INPUT_MAX_LENGTH + "characters");
		}
		else{
			args.firstName = form.getFirstName();
		}

		//-- last name

		if(StringUtils.isEmpty(form.getLastName())){
			errors.rejectValue("lastName", ErrorCode.EMPTY_FIELD, "Last name cannot be empty");
		}
		else if(form.getLastName().length() > NAME_INPUT_MAX_LENGTH){
			errors.rejectValue("lastName", ErrorCode.EMPTY_FIELD, "Field must not exceed limit of " + NAME_INPUT_MAX_LENGTH + "characters");
		}
		else{
			args.lastName = form.getLastName();
		}

		//-- birthdate
		//				logger.debug("Validating Birthday Date: " + form.getBirthDay() + ", " + form.getBirthMonth() + ", " + form.getBirthYear());

		if(StringUtils.isEmpty(form.getBirthDay()) || StringUtils.isEmpty(form.getBirthMonth()) || StringUtils.isEmpty(form.getBirthYear())){
			errors.rejectValue("birthDay", ErrorCode.EMPTY_FIELD, "Date of birth cannot be empty");
		}
		else if("0".equals(form.getBirthDay()) || "0".equals(form.getBirthMonth()) || "0".equals(form.getBirthYear())){
			errors.rejectValue("birthDay", ErrorCode.EMPTY_FIELD, "Date of birth cannot be empty");
		}
		else{
			try{
				args.birthDay = Integer.valueOf(form.getBirthDay());
				args.birthMonth = Integer.valueOf(form.getBirthMonth());
				args.birthYear = Integer.valueOf(form.getBirthYear());
			}
			catch(Exception exp){
				errors.rejectValue("birthDay", ErrorCode.EMPTY_FIELD, "Date of birth cannot be empty");
			}
		}

		//-- gender
		if(StringUtils.isEmpty(form.getGender())){
			errors.rejectValue("gender", ErrorCode.EMPTY_FIELD, "Please specify your gender");
		}
		else{
			try {
				args.gender = UserAccount.Gender.valueOf(form.getGender());
			} 
			catch (Exception exp) {
				errors.rejectValue("gender", ErrorCode.INVALID_EXPRESSION, "Invalid input 'gender'");
			}

		}
		
		if(!errors.hasErrors()){
			form.setArguments(args);
		}
	}
	
	public void validate(SignupForm form, Errors errors, boolean validatePassword) {
		SignupArgs args = new SignupArgs();
		if(StringUtils.isEmpty(form.getEmail())){
			errors.rejectValue("email", ErrorCode.EMPTY_FIELD, "Email cannot be empty");
		}
		else{
			matcher = emailPattern.matcher(form.getEmail());
			if(!matcher.matches()){
				errors.rejectValue("email", ErrorCode.INVALID_EXPRESSION, "Invalid email expression");
			}
			else{
				args.email = form.getEmail();
			}
		}
		if(validatePassword){
			if(StringUtils.isEmpty(form.getPassword())){
				errors.rejectValue("password", ErrorCode.EMPTY_FIELD, "Password cannot be empty");
			}
			else if(form.getPassword().length() < PASSWORD_MIN_LENGTH){
				errors.rejectValue("password", ErrorCode.BELOW_MIN, "Password must contain at least " + PASSWORD_MIN_LENGTH + " characters");
			}
		}
		
//		if(StringUtils.isEmpty(form.getConfirmPassword())){
//			errors.rejectValue("confirmPassword", ErrorCode.EMPTY_FIELD, "Please re-type password");
//		}
//		else if(form.getConfirmPassword().length() < PASSWORD_MIN_LENGTH){
//			errors.rejectValue("confirmPassword", ErrorCode.BELOW_MIN_LENGTH, "Password must contain at least " + PASSWORD_MIN_LENGTH + " characters");
//		}
		if(StringUtils.isNotEmpty(form.getPassword()) && !form.getPassword().equals(form.getConfirmPassword())){
			errors.rejectValue("confirmPassword", ErrorCode.NOT_EQUAL, "Passwords do not match");
		}
		
		if(!errors.hasErrors()){
			args.email = form.getEmail();
			args.password = form.getPassword();
//			args.ipAddress = form.getIpAddress();
			args.username = form.getUsername();
			form.setArguments(args);
		}
	}
	
	public void validate(SigninForm form, Errors errors) {
		SigninArgs args = new SigninArgs();
		if(StringUtils.isEmpty(form.getJ_username())){
			errors.rejectValue("email", ErrorCode.EMPTY_FIELD, "Email cannot be empty");
		}
		else{
			matcher = emailPattern.matcher(form.getJ_username());
			if(!matcher.matches()){
				errors.rejectValue("j_username", ErrorCode.INVALID_EXPRESSION, "Invalid email expression");
			}
			else{
				args.email = form.getJ_username();
			}
		}
		if(StringUtils.isEmpty(form.getJ_password())){
			errors.rejectValue("password", ErrorCode.EMPTY_FIELD, "Password cannot be empty");
		}
		else if(form.getJ_password().length() < PASSWORD_MIN_LENGTH){
			errors.rejectValue("password", ErrorCode.BELOW_MIN, "Password must contain at least " + PASSWORD_MIN_LENGTH + " characters");
		}
		
		if(!errors.hasErrors()){
			args.email = form.getJ_username();
			args.password = form.getJ_password();
			form.setArguments(args);
		}
	}
	
	public void validate(UpdateTokenForm form, Errors errors) {
		UpdateTokenArgs args = new UpdateTokenArgs();
		if(StringUtils.isEmpty(form.getToken())){
			errors.rejectValue("token", ErrorCode.EMPTY_FIELD, "Token cannot be empty");
		}
		else{
			args.token = form.getToken();
		}
		if(StringUtils.isEmpty(form.getProvider())){
			errors.rejectValue("provider", ErrorCode.EMPTY_FIELD, "Provider cannot be empty");
		}
		else{
			args.provider = form.getProvider();
		}
		
		if(!errors.hasErrors()){
			form.setArguments(args);
		}
	}

}
