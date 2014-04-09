package com.tennissetapp.validation;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.Errors;
import com.tennissetapp.args.CreateMatchArgs;
import com.tennissetapp.args.SearchMatchesArgs;
import com.tennissetapp.forms.CreateMatchForm;
import com.tennissetapp.forms.FindByLocationForm;
import com.tennissetapp.forms.CalendarEventsForm;
import com.tennissetapp.forms.ScrollForm;
import com.tennissetapp.forms.SearchMatchesForm;

public class MatchesServiceValidator  extends Validator{
	@Override
	public boolean supports(Class<?> clazz) {
		return FindByLocationForm.class.equals(clazz) ||
				CreateMatchForm.class.equals(clazz) || 
				SearchMatchesForm.class.equals(clazz) ||
				ScrollForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if(target instanceof FindByLocationForm){
			FindByLocationForm f = (FindByLocationForm)target;
			validate(f, errors);
		}
		else if(target instanceof CreateMatchForm){
			CreateMatchForm f = (CreateMatchForm)target;
			validate(f, errors);
		}
		else if(target instanceof SearchMatchesForm){
			SearchMatchesForm f = (SearchMatchesForm)target;
			validateSearchMatchesForm(f, errors);
		}
		else if(target instanceof CalendarEventsForm){
			
		}
		
	}
	
	private void validateSearchMatchesForm(SearchMatchesForm f, Errors errors){
		//no validation this is a search, just ignore wrong values
		SearchMatchesArgs args = new SearchMatchesArgs();
		
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
				args.distance = Double.parseDouble(f.getDistance());
			} catch (Exception exp) {
				args.distance = 50d;
			}
		}
		else{
			args.distance = 50d;
		}
		
		//visibility
//		if(StringUtils.isEmpty(f.getVisibility())){
//			errors.rejectValue("visibility",ErrorCode.EMPTY_FIELD,"Please specify visibility");
//		}
//		else if(!"CYCLE".equals(f.getVisibility()) && !"PUBLIC".equals(f.getVisibility())){
//			errors.rejectValue("visibility",ErrorCode.INVALID_EXPRESSION,"Invalid input");
//		}
//		else{
//			args.visibility = f.getVisibility();
//		}
		
		//type of play
		args.playDoubles = (f.getPlayDoubles() != null);
		args.playFullMatch = (f.getPlayFullMatch() != null);
		args.playHittingAround = (f.getPlayHittingAround() != null);
		args.playPoints = (f.getPlayPoints() != null);
		args.playSingles = (f.getPlaySingles() != null);
		
		//time
		if(StringUtils.isNotEmpty(f.getStartTime())){
			try {
				args.startTime = new Date(Long.valueOf(f.getStartTime()));
			} catch (Exception exp) {
				logger.error(exp.getMessage(),exp);
				errors.rejectValue("startTime", ErrorCode.INVALID_EXPRESSION,"Invalid start time");
			}
		}
		else{
			errors.rejectValue("startTime", ErrorCode.EMPTY_FIELD,"Please specify start time");
		}
		
		if(StringUtils.isNotEmpty(f.getEndTime())){
			try {
				args.endTime = new Date(Long.valueOf(f.getEndTime()));
			} catch (Exception exp) {
				errors.rejectValue("endTime", ErrorCode.INVALID_EXPRESSION,"Invalid end time");
			}
		}
		else{
			errors.rejectValue("endTime", ErrorCode.EMPTY_FIELD,"Please specify end time");
		}
		
		//level of play
		if(StringUtils.isNotEmpty(f.getLevelOfPlayMin())){
			try {
				args.levelOfPlayMin = Float.valueOf(f.getLevelOfPlayMin());
			} catch (Exception exp) {
				errors.rejectValue("levelOfPlayMin", ErrorCode.INVALID_EXPRESSION,"Invalid min level");
			}
		}
		if(StringUtils.isNotEmpty(f.getLevelOfPlayMax())){
			try {
				args.levelOfPlayMax = Float.valueOf(f.getLevelOfPlayMax());
			} catch (Exception exp) {
				errors.rejectValue("levelOfPlayMax", ErrorCode.INVALID_EXPRESSION,"Invalid max level");
			}
		}
		
		if(!errors.hasErrors()){
			f.setArguments(args);
		}
	}

	
	private void validate(CreateMatchForm f, Errors errors){
		//no validation this is a search, just ignore wrong values
		CreateMatchArgs args = new CreateMatchArgs();

		if(StringUtils.isNotEmpty(f.getTennisCenterId())){
			try {
				args.tennisCenterId = Long.valueOf(f.getTennisCenterId());
			} catch (Exception exp) {
				errors.rejectValue("tennisCenterId",ErrorCode.INVALID_EXPRESSION,"Invalid tennis center");
			}
		}
		else{
			errors.rejectValue("tennisCenterId",ErrorCode.EMPTY_FIELD,"Please specify tennis center");
		}
		//visibility
		if(StringUtils.isEmpty(f.getVisibility())){
			errors.rejectValue("visibility",ErrorCode.EMPTY_FIELD,"Please specify visibility");
		}
		else if(!"CYCLE".equals(f.getVisibility()) && !"PUBLIC".equals(f.getVisibility())){
			errors.rejectValue("visibility",ErrorCode.INVALID_EXPRESSION,"Invalid input");
		}
		else{
			args.visibility = f.getVisibility();
		}

		//type of play
		args.playDoubles = (f.getPlayDoubles() != null);
		args.playFullMatch = (f.getPlayFullMatch() != null);
		args.playHittingAround = (f.getPlayHittingAround() != null);
		args.playPoints = (f.getPlayPoints() != null);
		args.playSingles = (f.getPlaySingles() != null);

		//time
		if(StringUtils.isNotEmpty(f.getStartTime())){
			try {
				args.startTime = new Date(Long.valueOf(f.getStartTime()));
			} catch (Exception exp) {
				logger.error(exp.getMessage(),exp);
				errors.rejectValue("startTime", ErrorCode.INVALID_EXPRESSION,"Invalid start time");
			}
		}
		else{
			errors.rejectValue("startTime", ErrorCode.EMPTY_FIELD,"Please specify start time");
		}

		if(StringUtils.isNotEmpty(f.getEndTime())){
			try {
				args.endTime = new Date(Long.valueOf(f.getEndTime()));
			} catch (Exception exp) {
				errors.rejectValue("endTime", ErrorCode.INVALID_EXPRESSION,"Invalid end time");
			}
		}
		else{
			errors.rejectValue("endTime", ErrorCode.EMPTY_FIELD,"Please specify end time");
		}

		//level of play
		if(StringUtils.isNotEmpty(f.getLevelOfPlayMin())){
			try {
				args.levelOfPlayMin = Float.valueOf(f.getLevelOfPlayMin());
			} catch (Exception exp) {
				errors.rejectValue("levelOfPlayMin", ErrorCode.INVALID_EXPRESSION,"Invalid min level");
			}
		}
		else{
			errors.rejectValue("levelOfPlayMin", ErrorCode.EMPTY_FIELD,"Please specify level");
		}
		if(StringUtils.isNotEmpty(f.getLevelOfPlayMax())){
			try {
				args.levelOfPlayMax = Float.valueOf(f.getLevelOfPlayMax());
			} catch (Exception exp) {
				errors.rejectValue("levelOfPlayMax", ErrorCode.INVALID_EXPRESSION,"Invalid max level");
			}
		}
		else{
			errors.rejectValue("levelOfPlayMax", ErrorCode.EMPTY_FIELD,"Please specify level");
		}

		if(!errors.hasErrors()){
			f.setArguments(args);
		}
	}

}
