package com.tennissetapp.validation;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.Errors;

import com.tennissetapp.args.SearchMatesArgs;
import com.tennissetapp.args.SearchMatesByNameArgs;
import com.tennissetapp.forms.FindByLocationForm;
import com.tennissetapp.forms.ScrollForm;
import com.tennissetapp.forms.SearchByNameOrEmailForm;
import com.tennissetapp.forms.SearchMatesForm;

public class TennisMatesServiceValidator extends Validator{
	@Override
	public boolean supports(Class<?> clazz) {
		return FindByLocationForm.class.isAssignableFrom(clazz) ||
				SearchMatesForm.class.isAssignableFrom(clazz) ||
				SearchByNameOrEmailForm.class.isAssignableFrom(clazz) ||
				ScrollForm.class.equals(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		if(target instanceof FindByLocationForm){
			FindByLocationForm f = (FindByLocationForm)target;
			validate(f, errors);
		}
		else if(target instanceof SearchMatesForm){
			SearchMatesForm f = (SearchMatesForm)target;
			validate(f, errors);
		}
		
		else if(target instanceof SearchByNameOrEmailForm){
			SearchByNameOrEmailForm f = (SearchByNameOrEmailForm)target;
			validate(f, errors);
		}
		
		else if(target instanceof ScrollForm){
			ScrollForm f = (ScrollForm)target;
			super.validate(f,errors);
					
		}
	}
	
	private void validate(SearchByNameOrEmailForm f, Errors errors){
		SearchMatesByNameArgs args = new SearchMatesByNameArgs();
		
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
		
//		//location not required
//		if(StringUtils.isNotEmpty(f.getLatitude()) && StringUtils.isNotEmpty(f.getLongitude())){
//			try {
//				args.latitude = Double.parseDouble(f.getLatitude());
//				args.longitude = Double.parseDouble(f.getLongitude());
//			} catch (Exception exp) {
//			}
//		}
		
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
	
	public void validate(SearchMatesForm f, Errors errors) {
		SearchMatesArgs args = new SearchMatesArgs();
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
				args.distance = Double.parseDouble(f.getLatitude());
			} catch (Exception exp) {
				args.distance = 50d;
			}
		}
		else{
			args.distance = 50d;
		}
		
		//type of play
		if(StringUtils.isNotEmpty(f.getPlayDoubles())){
			args.playDoubles = true;
		}
		if(StringUtils.isNotEmpty(f.getPlayFullMatch())){
			args.playFullMatch = true;
		}
		if(StringUtils.isNotEmpty(f.getPlayHittingAround())){
			args.playHittingAround = true;
		}
		if(StringUtils.isNotEmpty(f.getPlayPoints())){
			args.playPoints = true;
		}
		if(StringUtils.isNotEmpty(f.getPlaySingles())){
			args.playSingles = true;
		}
		
		//level of play
		if(StringUtils.isNotEmpty(f.getLevelOfPlayMin())){
			try {
				args.levelOfPlayMin = Float.valueOf(f.getLevelOfPlayMin());
			} catch (Exception e) {
				logger.debug("Invalid level of play Min");
				errors.rejectValue("levelOfPlayMin", ErrorCode.INVALID_EXPRESSION,"Invalid minimum level of play ");
			}
		}
		
		if(StringUtils.isNotEmpty(f.getLevelOfPlayMax())){
			try {
				args.levelOfPlayMax = Float.valueOf(f.getLevelOfPlayMax());
			} catch (Exception e) {
				logger.debug("Invalid level of play Max");
				errors.rejectValue("levelOfPlayMax", ErrorCode.INVALID_EXPRESSION,"Invalid maximum level of play ");
			}
		}
		
		//availability
		args.availableWeekdayAfternoon = f.getAvailableWeekdayAfternoon() != null;
		args.availableWeekdayEvening = f.getAvailableWeekdayEvening() != null;
		args.availableWeekdayMorning = f.getAvailableWeekdayMorning() != null;
		args.availableWeekendAfternoon = f.getAvailableWeekendAfternoon() != null;
		args.availableWeekendEvening = f.getAvailableWeekendEvening() != null;
		args.availableWeekendMorning = f.getAvailableWeekendMorning() != null;
		
		if(!errors.hasErrors()){
			f.setArguments(args);
		}
	}

}
