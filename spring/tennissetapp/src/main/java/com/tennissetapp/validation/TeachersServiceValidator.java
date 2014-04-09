package com.tennissetapp.validation;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.Errors;

import com.tennissetapp.args.CreateFavoriteTeacherArgs;
import com.tennissetapp.args.SearchByNameOrEmailArgs;
import com.tennissetapp.args.SearchTennisTeachersArgs;
import com.tennissetapp.forms.CreateFavoriteTeacherForm;
import com.tennissetapp.forms.FindByLocationForm;
import com.tennissetapp.forms.ScrollForm;
import com.tennissetapp.forms.SearchByNameOrEmailForm;
import com.tennissetapp.forms.SearchTennisTeachersForm;

public class TeachersServiceValidator extends Validator{
	@Override
	public boolean supports(Class<?> clazz) {
		return FindByLocationForm.class.equals(clazz) ||
				SearchTennisTeachersForm.class.equals(clazz) ||
				SearchByNameOrEmailForm.class.equals(clazz) ||
				CreateFavoriteTeacherForm.class.equals(clazz) ||
				ScrollForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if(target instanceof FindByLocationForm){
			FindByLocationForm f = (FindByLocationForm)target;
			validate(f, errors);
		}
		else if(target instanceof SearchTennisTeachersForm){
			SearchTennisTeachersForm f = (SearchTennisTeachersForm)target;
			validate(f, errors);
		}
		
		else if(target instanceof SearchByNameOrEmailForm){
			SearchByNameOrEmailForm f = (SearchByNameOrEmailForm)target;
			validate(f, errors);
		}
	
		else if(target instanceof CreateFavoriteTeacherForm){
			CreateFavoriteTeacherForm f = (CreateFavoriteTeacherForm)target;
			validate(f, errors);
		}
		
		else if(target instanceof ScrollForm){
			ScrollForm f = (ScrollForm)target;
			validate(f, errors);
		}
	}

	private void validate(CreateFavoriteTeacherForm f, Errors errors){
		CreateFavoriteTeacherArgs args = new CreateFavoriteTeacherArgs();
		if(StringUtils.isNotEmpty(f.getTeacherProfileId())){
			try {
				args.teacherProfileId = Long.valueOf(f.getTeacherProfileId());
			} 
			catch (Exception exp) {
				errors.rejectValue("teacherProfileId",ErrorCode.INVALID_EXPRESSION,"Invalid ID");
			}
		}
		else{
			errors.rejectValue("teacherProfileId",ErrorCode.EMPTY_FIELD,"Blank ID");
		}
		if(!errors.hasErrors()){
			f.setArguments(args);
		}
	}
	
	
	private void validate(SearchTennisTeachersForm f, Errors errors){
		//no validation this is a search, just ignore wrong values
		SearchTennisTeachersArgs args = new SearchTennisTeachersArgs();
		
		if(StringUtils.isNotEmpty(f.getFirstResult())){
			try {
				args.firstResult = Integer.valueOf(f.getFirstResult());
				
			} catch (Exception exp) {
				errors.rejectValue("firstResult",ErrorCode.INVALID_EXPRESSION,"Invalid first result");
			}
		}
		else{
			args.firstResult = 0;
		}
		if(StringUtils.isNotEmpty(f.getMaxResults())){
			try {
				args.maxResults = Integer.valueOf(f.getMaxResults());
			} catch (Exception exp) {
				errors.rejectValue("maxResults",ErrorCode.INVALID_EXPRESSION,"Invalid max results");
			}
		}
		else{
			args.maxResults = 10;
		}
		
		//location
		if(StringUtils.isNotEmpty(f.getLatitude()) && StringUtils.isNotEmpty(f.getLongitude())){
			try {
				args.latitude = Double.parseDouble(f.getLatitude());
				args.longitude = Double.parseDouble(f.getLongitude());
			} catch (Exception exp) {
				errors.rejectValue("latitude",ErrorCode.INVALID_EXPRESSION,"Incomplete address");
				logger.error("validate - latitude and longitude failed to parse " + f.getLatitude() + ", " + f.getLongitude() + ", is null? " + (f.getLongitude() == null) + ", " + (f.getLatitude() == null));
			}
		}
		else{
			errors.rejectValue("latitude",ErrorCode.EMPTY_FIELD,"Incomplete address");
		}
		
		if(StringUtils.isNotEmpty(f.getDistance())){
			try {
				args.maxDistance = Double.parseDouble(f.getDistance());
			} catch (Exception exp) {
				args.maxDistance = 50d;
			}
		}
		else{
			args.maxDistance = 50d;
		}
		
		//specialty
		args.specialtyAdults = (f.getSpecialtyAdults() != null);
		args.specialtyJuniors = (f.getSpecialtyJuniors() != null);
		args.specialtyTurnaments = (f.getSpecialtyTurnaments() != null);
		args.teacherCertified = (f.getTeacherCertified() != null);
		
		//availability
		args.availableWeekdayAfternoon = (f.getAvailableWeekdayAfternoon() != null);
		args.availableWeekdayEvening = (f.getAvailableWeekdayEvening() != null);
		args.availableWeekdayMorning = (f.getAvailableWeekdayMorning() != null);
		args.availableWeekendAfternoon = (f.getAvailableWeekendAfternoon() != null);
		args.availableWeekendEvening = (f.getAvailableWeekendEvening() != null);
		args.availableWeekendMorning = (f.getAvailableWeekendMorning() != null);
		
		if(!errors.hasErrors()){
			f.setArguments(args);
		}
	}
	
	private void validate(SearchByNameOrEmailForm f, Errors errors){
		//no validation this is a search, just ignore wrong values
		SearchByNameOrEmailArgs args = new SearchByNameOrEmailArgs();
		
		if(StringUtils.isNotEmpty(f.getFirstResult())){
			try {
				args.firstResult = Integer.valueOf(f.getFirstResult());
				
			} catch (Exception exp) {
				errors.rejectValue("firstResult",ErrorCode.INVALID_EXPRESSION,"Invalid first result");
			}
		}
		else{
			args.firstResult = 0;
		}
		if(StringUtils.isNotEmpty(f.getMaxResults())){
			try {
				args.maxResults = Integer.valueOf(f.getMaxResults());
			} catch (Exception exp) {
				errors.rejectValue("maxResults",ErrorCode.INVALID_EXPRESSION,"Invalid max results");
			}
		}
		else{
			args.maxResults = 10;
		}
		
		//location
		if(StringUtils.isNotEmpty(f.getLatitude()) && StringUtils.isNotEmpty(f.getLongitude())){
			try {
				args.latitude = Double.parseDouble(f.getLatitude());
				args.longitude = Double.parseDouble(f.getLongitude());
			} catch (Exception exp) {
//				errors.rejectValue("latitude",ErrorCode.INVALID_EXPRESSION,"Incomplete address");
				logger.error("validate - latitude and longitude failed to parse " + f.getLatitude() + ", " + f.getLongitude() + ", is null? " + (f.getLongitude() == null) + ", " + (f.getLatitude() == null));
			}
		}
		else{
			errors.rejectValue("latitude",ErrorCode.EMPTY_FIELD,"Incomplete address");
		}
		
		if(StringUtils.isNotEmpty(f.getNameOrEmail())){
			args.nameOrEmail = f.getNameOrEmail();
		}
		else{
			args.nameOrEmail = "";
		}
		
		if(!errors.hasErrors()){
			f.setArguments(args);
		}
	}
}
