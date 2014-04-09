package com.tennissetapp.validation;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import com.tennissetapp.forms.TeacherProfileForm;

public class TeacherProfileFormValidator extends Validator{
	protected Logger logger = Logger.getLogger(getClass());
	@Override
	public boolean supports(Class<?> clazz) {
		return TeacherProfileForm.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
//		
		TeacherProfileForm form = (TeacherProfileForm)target;
		logger.debug("---->validate " + form);
		if(StringUtils.isEmpty(form.getStreetNumber())){
			errors.rejectValue("streetNumber", ErrorCode.EMPTY_FIELD, "Your address is incomplete, please make sure to put an accurate address");
		}
		if(StringUtils.isEmpty(form.getAdministrativeAreaLevel1())){
			errors.rejectValue("administrativeAreaLevel1", ErrorCode.EMPTY_FIELD, "Your address is incomplete, please make sure to put an accurate address");
		}
		if(StringUtils.isEmpty(form.getAdministrativeAreaLevel2())){
			errors.rejectValue("administrativeAreaLevel2", ErrorCode.EMPTY_FIELD, "Your address is incomplete, please make sure to put an accurate address");
		}
		if(StringUtils.isEmpty(form.getCountry())){
			errors.rejectValue("country", ErrorCode.EMPTY_FIELD, "Your address is incomplete, please make sure to put an accurate address");
		}
		if(StringUtils.isEmpty(form.getLocality())){
			errors.rejectValue("locality", ErrorCode.EMPTY_FIELD, "Your address is incomplete, please make sure to put an accurate address");
		}
		if(StringUtils.isEmpty(form.getRoute())){
			errors.rejectValue("route", ErrorCode.EMPTY_FIELD, "Your address is incomplete, please make sure to put an accurate address");
		}
		//years of experience
		if(StringUtils.isEmpty(form.getYearsOfExperience())){
			errors.rejectValue("yearsOfExperience", ErrorCode.EMPTY_FIELD, "Please specify your experience");
		}
		else{
			try {
				Float.valueOf(form.getYearsOfExperience());
			} 
			catch (Exception exp) {
				errors.rejectValue("yearsOfExperience", ErrorCode.EMPTY_FIELD, "Must be a number");
			}
		}
		//clinic rates
		if(StringUtils.isEmpty(form.getClinicRates())){
			errors.rejectValue("clinicRates", ErrorCode.EMPTY_FIELD, "Please specify Lesson/Clinic rates");
		}
		else{
			try {
				Float.valueOf(form.getClinicRates());
			} 
			catch (Exception exp) {
				errors.rejectValue("clinicRates", ErrorCode.EMPTY_FIELD, "Must be a number");
			}
		}
		
		//club
		if(StringUtils.isEmpty(form.getClub())){
			errors.rejectValue("club", ErrorCode.EMPTY_FIELD, "Please specify a club");
		}
		if(form.getUsptaCertified() == null && 
			form.getUsptrCertified() == null &&
			StringUtils.isEmpty(form.getCertifyingOrganization())){
			
			errors.rejectValue("certifyingOrganization", ErrorCode.EMPTY_FIELD, "Please specify a certifying organization");
		}
		if(form.getWeekdayAvailabilityAfternoonCheck() == null &&
				form.getWeekdayAvailabilityEveningCheck() == null &&
				form.getWeekdayAvailabilityMorningCheck() == null &&
				form.getWeekendAvailabilityAfternoonCheck() == null &&
				form.getWeekendAvailabilityEveningCheck() == null &&
				form.getWeekendAvailabilityMorningCheck() == null){
			errors.rejectValue("weekdayAvailabilityAfternoonCheck", ErrorCode.EMPTY_FIELD, "Please specify your availability");
		}
		
		if(form.getSpecialtyAdults() == null && 
				form.getSpecialtyJuniors() == null && 
				form.getSpecialtyTurnaments() == null){
			errors.rejectValue("specialtyAdults",ErrorCode.EMPTY_FIELD, "Please specify your specialty");
			
		}
	}

}
