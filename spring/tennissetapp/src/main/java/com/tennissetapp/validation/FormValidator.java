package com.tennissetapp.validation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import javax.validation.constraints.Min;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class FormValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		System.out.println("---->FormValidator.supports " + clazz);
		return true;
	}

	@Override
	public void validate(Object target, Errors errors) {
		System.out.println("---->FormValidator.validate");
		errors.rejectValue("firstName", "1", "This is wrong first name");
		Field[] fields = target.getClass().getDeclaredFields();
		for(Field field : fields){
			Annotation[] annotations = field.getDeclaredAnnotations();
			for(Annotation a : annotations){
				if(a.getClass().equals(Min.class)){
				}
			}
		}
	}

}

//public class CustomerValidator implements Validator {
//
//    private final Validator addressValidator;
//
//    public CustomerValidator(Validator addressValidator) {
//        if (addressValidator == null) {
//            throw new IllegalArgumentException(
//              "The supplied [Validator] is required and must not be null.");
//        }
//        if (!addressValidator.supports(Address.class)) {
//            throw new IllegalArgumentException(
//              "The supplied [Validator] must support the validation of [Address] instances.");
//        }
//        this.addressValidator = addressValidator;
//    }
//
//    /**
//    * This Validator validates Customer instances, and any subclasses of Customer too
//    */
//    public boolean supports(Class clazz) {
//        return Customer.class.isAssignableFrom(clazz);
//    }
//
//    public void validate(Object target, Errors errors) {
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "field.required");
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "surname", "field.required");
//        Customer customer = (Customer) target;
//        try {
//            errors.pushNestedPath("address");
//            ValidationUtils.invokeValidator(this.addressValidator, customer.getAddress(), errors);
//        } finally {
//            errors.popNestedPath();
//        }
//    }
//}


//public class PersonValidator implements Validator {
//
//    /**
//    * This Validator validates just Person instances
//    */
//    public boolean supports(Class clazz) {
//        return Person.class.equals(clazz);
//    }
//
//    public void validate(Object obj, Errors e) {
//        ValidationUtils.rejectIfEmpty(e, "name", "name.empty");
//        Person p = (Person) obj;
//        if (p.getAge() < 0) {
//            e.rejectValue("age", "negativevalue");
//        } else if (p.getAge() > 110) {
//            e.rejectValue("age", "too.darn.old");
//        }
//    }
//}
