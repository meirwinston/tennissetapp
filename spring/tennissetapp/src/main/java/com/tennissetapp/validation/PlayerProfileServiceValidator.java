package com.tennissetapp.validation;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.validation.Errors;

import com.google.common.collect.Lists;
import com.tennissetapp.args.CreatePlayerProfileArgs;
import com.tennissetapp.args.UpdateTennisDetailsArgs;
import com.tennissetapp.forms.CreatePlayerProfileForm;
import com.tennissetapp.forms.ScrollForm;
import com.tennissetapp.forms.UpdateTennisDetailsForm;
import com.tennissetapp.persistence.entities.TennisPlayerProfile;
import com.tennissetapp.persistence.entities.UserAccount;

public class PlayerProfileServiceValidator extends Validator{
	protected Logger logger = Logger.getLogger(getClass());
	@Override
	public boolean supports(Class<?> clazz) {
		return 
			ScrollForm.class.equals(clazz) || 
			CreatePlayerProfileForm.class.equals(clazz) ||
			UpdateTennisDetailsForm.class.equals(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		if(target instanceof ScrollForm){
			validate((ScrollForm)target,errors);
		}
		else if(target instanceof CreatePlayerProfileForm){
			validate((CreatePlayerProfileForm)target,errors);
		}
		
		else if(target instanceof UpdateTennisDetailsForm){
			validate((UpdateTennisDetailsForm)target,errors);
		}
	}
	
	public void validate(CreatePlayerProfileForm form, Errors errors) {
		CreatePlayerProfileArgs args = new CreatePlayerProfileArgs();

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
//		logger.debug("Validating Birthday Date: " + form.getBirthDay() + ", " + form.getBirthMonth() + ", " + form.getBirthYear());
		
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
		
		//--level of play
		try {
			args.levelOfPlay = Float.valueOf(form.getLevelOfPlay());	
		} catch (Exception exp) {
			errors.rejectValue("levelOfPlay", ErrorCode.INVALID_EXPRESSION, "Invalid level of play");
		}

		//-- hand
		
		try {
			args.hand = TennisPlayerProfile.Hand.valueOf(form.getHand());	
		} catch (Exception exp) {
			errors.rejectValue("hand", ErrorCode.INVALID_EXPRESSION, "Invalid hand input");
		}
		
		//-- play
		if(form.getSinglesCheck() == null && 
				form.getDoublesCheck() == null && 
				form.getFullMatchCheck() == null &&
				form.getPointsCheck() == null && 
				form.getHittingAroundCheck() == null
		){
			errors.rejectValue("singlesCheck", ErrorCode.EMPTY_FIELD, "Please specify your type of play");
		}
		else{
			args.doublesCheck = (form.getDoublesCheck() != null);
			args.fullMatchCheck = (form.getFullMatchCheck() != null);
			args.HittingAroundCheck = (form.getHittingAroundCheck() != null);
			args.pointsCheck = (form.getPointsCheck() != null);
			args.singlesCheck = (form.getSinglesCheck() != null);
		}
		
		//-- availability
		
		if(form.getWeekdayAvailabilityAfternoonCheck() == null &&
				form.getWeekdayAvailabilityEveningCheck() == null &&
				form.getWeekdayAvailabilityMorningCheck() == null &&
				form.getWeekendAvailabilityAfternoonCheck() == null &&
				form.getWeekendAvailabilityEveningCheck() == null &&
				form.getWeekendAvailabilityMorningCheck() == null){
			errors.rejectValue("weekdayAvailabilityAfternoonCheck", ErrorCode.EMPTY_FIELD, "Please specify your availability");
		}
		else{
			args.weekdayAvailabilityAfternoonCheck = (form.getWeekdayAvailabilityAfternoonCheck() != null);
			args.weekdayAvailabilityEveningCheck = (form.getWeekdayAvailabilityEveningCheck() != null);
			args.weekdayAvailabilityMorningCheck = (form.getWeekdayAvailabilityMorningCheck() != null);
			args.weekendAvailabilityAfternoonCheck = (form.getWeekendAvailabilityAfternoonCheck() != null);
			args.weekendAvailabilityEveningCheck = (form.getWeekendAvailabilityEveningCheck() != null);
			args.weekendAvailabilityMorningCheck = (form.getWeekendAvailabilityMorningCheck() != null);
		}
		
		//--
		if(StringUtils.isEmpty(form.getAgreesToTerms())){
			errors.rejectValue("agreesToTerms", ErrorCode.ASSERT_TRUE, "You must agree to the terms and conditions");
			args.agreesToTerms = Boolean.FALSE;
		}
		else{
			args.agreesToTerms = Boolean.TRUE;
		}
		if(StringUtils.isNotEmpty(form.getAboutMe())){
			args.aboutMe = form.getAboutMe();
		}
		
		if(StringUtils.isNotEmpty(form.getFavoriteCourts())){
			try {
				String[] arr = StringUtils.split(form.getFavoriteCourts(), ",");
				args.favoriteCourts = Lists.newArrayList();
				for(String s : arr){
					args.favoriteCourts.add(Long.valueOf(s.trim()));
				}
			} catch (Exception exp) {
				errors.rejectValue("favoriteCourts", ErrorCode.INVALID_EXPRESSION, "Selected courts are invalid");
			}
			
		}
		
		super.validate(form, errors, args);
		
		if(!errors.hasErrors()){
			form.setArguments(args);
		}
	}
	
	public void validate(UpdateTennisDetailsForm form, Errors errors) {
		UpdateTennisDetailsArgs args = new UpdateTennisDetailsArgs();

		//--level of play
		try {
			args.levelOfPlay = Float.valueOf(form.getLevelOfPlay());	
		} catch (Exception exp) {
			errors.rejectValue("levelOfPlay", ErrorCode.INVALID_EXPRESSION, "Invalid level of play");
		}

		//-- hand
		
		try {
			args.hand = TennisPlayerProfile.Hand.valueOf(form.getHand());	
		} catch (Exception exp) {
			errors.rejectValue("hand", ErrorCode.INVALID_EXPRESSION, "Invalid hand input");
		}
		
		//-- play
		if(form.getSinglesCheck() == null && 
				form.getDoublesCheck() == null && 
				form.getFullMatchCheck() == null &&
				form.getPointsCheck() == null && 
				form.getHittingAroundCheck() == null
		){
			errors.rejectValue("singlesCheck", ErrorCode.EMPTY_FIELD, "Please specify your type of play");
		}
		else{
			args.doublesCheck = (form.getDoublesCheck() != null);
			args.fullMatchCheck = (form.getFullMatchCheck() != null);
			args.HittingAroundCheck = (form.getHittingAroundCheck() != null);
			args.pointsCheck = (form.getPointsCheck() != null);
			args.singlesCheck = (form.getSinglesCheck() != null);
		}
		
		//-- availability
		
		if(form.getWeekdayAvailabilityAfternoonCheck() == null &&
				form.getWeekdayAvailabilityEveningCheck() == null &&
				form.getWeekdayAvailabilityMorningCheck() == null &&
				form.getWeekendAvailabilityAfternoonCheck() == null &&
				form.getWeekendAvailabilityEveningCheck() == null &&
				form.getWeekendAvailabilityMorningCheck() == null){
			errors.rejectValue("weekdayAvailabilityAfternoonCheck", ErrorCode.EMPTY_FIELD, "Please specify your availability");
		}
		else{
			args.weekdayAvailabilityAfternoonCheck = (form.getWeekdayAvailabilityAfternoonCheck() != null);
			args.weekdayAvailabilityEveningCheck = (form.getWeekdayAvailabilityEveningCheck() != null);
			args.weekdayAvailabilityMorningCheck = (form.getWeekdayAvailabilityMorningCheck() != null);
			args.weekendAvailabilityAfternoonCheck = (form.getWeekendAvailabilityAfternoonCheck() != null);
			args.weekendAvailabilityEveningCheck = (form.getWeekendAvailabilityEveningCheck() != null);
			args.weekendAvailabilityMorningCheck = (form.getWeekendAvailabilityMorningCheck() != null);
		}
		
		if(!errors.hasErrors()){
			form.setArguments(args);
		}
	}
}
