package com.tennissetapp.validation;

import org.springframework.validation.Errors;

public class SubmitPostFormValidator extends Validator{
	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}

	@Override
	public void validate(Object target, Errors errors) {
	}
}
