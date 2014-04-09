package com.tennissetapp.validation;

import java.lang.annotation.Annotation;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.tennissetapp.forms.CreateProfileForm;

public class TennisSetAppValidator implements ConstraintValidator<Annotation, CreateProfileForm>{

	@Override
	public void initialize(Annotation annotation) {
		System.out.println("TennisSetAppValidator.initialize");
	}

	@Override
	public boolean isValid(CreateProfileForm form, ConstraintValidatorContext context) {
		System.out.println("TennisSetAppValidator.isValid");
		return true;
	}
	
}
