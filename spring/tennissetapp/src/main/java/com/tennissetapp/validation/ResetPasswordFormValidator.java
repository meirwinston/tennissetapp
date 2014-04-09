package com.tennissetapp.validation;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.Errors;
import com.tennissetapp.forms.ResetPasswordForm;

public class ResetPasswordFormValidator extends Validator{
	@Override
	public boolean supports(Class<?> clazz) {
		return ResetPasswordForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ResetPasswordForm form = (ResetPasswordForm)target;
		
		
		if(StringUtils.isEmpty(form.getPassword())){
			errors.rejectValue("password", ErrorCode.EMPTY_FIELD, "Password cannot be empty");
		}
		else if(form.getPassword().length() < PASSWORD_MIN_LENGTH){
			errors.rejectValue("password", ErrorCode.BELOW_MIN, "Password must contain at least " + PASSWORD_MIN_LENGTH + " characters");
		}
		if(StringUtils.isEmpty(form.getConfirmPassword())){
			errors.rejectValue("confirmPassword", ErrorCode.EMPTY_FIELD, "Please re-type password");
		}
//		else if(form.getConfirmPassword().length() < PASSWORD_MIN_LENGTH){
//			errors.rejectValue("confirmPassword", ErrorCode.BELOW_MIN_LENGTH, "Password must contain at least " + PASSWORD_MIN_LENGTH + " characters");
//		}
		if(StringUtils.isNotEmpty(form.getPassword()) && !form.getPassword().equals(form.getConfirmPassword())){
			errors.rejectValue("password", ErrorCode.NOT_EQUAL, "Passwords do not match");
		}
	}
}
