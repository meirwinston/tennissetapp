package com.tennissetapp.validation;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;

import com.tennissetapp.forms.CreateProfileForm;


@Deprecated
public class CreateUserProfileFormVallidator extends Validator{
	@Override
	public boolean supports(Class<?> clazz) {
		return CreateProfileForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		CreateProfileForm form = (CreateProfileForm)target;
		
		if(StringUtils.isEmpty(form.getFirstName())){
			errors.rejectValue("firstName", ErrorCode.EMPTY_FIELD, "First name cannot be empty");
		}
		else if(form.getFirstName().length() > NAME_INPUT_MAX_LENGTH){
			errors.rejectValue("firstName", ErrorCode.EMPTY_FIELD, "Field must not exceed limit of " + NAME_INPUT_MAX_LENGTH + "characters");
		}
		
		if(StringUtils.isEmpty(form.getGender())){
			errors.rejectValue("gender", ErrorCode.EMPTY_FIELD, "Please specify your gender");
		}
		//--
		
		if(StringUtils.isEmpty(form.getLastName())){
			errors.rejectValue("lastName", ErrorCode.EMPTY_FIELD, "Last name cannot be empty");
		}
		else if(form.getLastName().length() > NAME_INPUT_MAX_LENGTH){
			errors.rejectValue("lastName", ErrorCode.EMPTY_FIELD, "Field must not exceed limit of " + NAME_INPUT_MAX_LENGTH + "characters");
		}
		
		//-- validate birthdate
		logger.debug("Validating Birthday Date: " + form.getBirthDay() + ", " + form.getBirthMonth() + ", " + form.getBirthYear());
		
		if(StringUtils.isEmpty(form.getBirthDay()) || StringUtils.isEmpty(form.getBirthMonth()) || StringUtils.isEmpty(form.getBirthYear())){
			errors.rejectValue("birthDay", ErrorCode.EMPTY_FIELD, "Date of birth cannot be empty");
		}
		else if("0".equals(form.getBirthDay()) || "0".equals(form.getBirthMonth()) || "0".equals(form.getBirthYear())){
			errors.rejectValue("birthDay", ErrorCode.EMPTY_FIELD, "Date of birth cannot be empty");
		}
		else{
			try{
				Integer.valueOf(form.getBirthDay());
				Integer.valueOf(form.getBirthMonth());
				Integer.valueOf(form.getBirthYear());
			}
			catch(Exception exp){
				errors.rejectValue("birthDay", ErrorCode.EMPTY_FIELD, "Date of birth cannot be empty");
			}
		}
		
		
		//--
		if(form.getAgreesToTerms() == null){
			errors.rejectValue("agreesToTerms", ErrorCode.ASSERT_TRUE, "You must agree to the terms and conditions");
		}
		
	}
//	protected String validateFirstName(CreateUserProfileForm form, Errors errors){
//		List<String> list = new ArrayList<String>();
//		if(StringUtils.isEmpty(form.getFirstName())){
//			errors.rejectValue("firstName", "1", "First name cannot be empty");
//		}
//	}

}
