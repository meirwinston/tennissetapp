package com.tennissetapp.validation;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.Errors;

import com.google.common.collect.Lists;
import com.tennissetapp.args.CreateTennisCenterArgs;
import com.tennissetapp.args.SearchTennisCourtsArgs;
import com.tennissetapp.forms.CreateTennisCenterForm;
import com.tennissetapp.forms.FindByLocationForm;
import com.tennissetapp.forms.SearchTennisCourtsForm;
import com.tennissetapp.validation.Validator.ErrorCode;

public class TennisCourtServiceValidator extends Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return CreateTennisCenterForm.class.isAssignableFrom(clazz) || 
				FindByLocationForm.class.isAssignableFrom(clazz) ||
				SearchTennisCourtsForm.class.isAssignableFrom(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		logger.debug("validate " + target.getClass());
		
		if(target instanceof CreateTennisCenterForm){
			CreateTennisCenterForm f = (CreateTennisCenterForm)target;
			validate(f,errors);
		}
		else if(target instanceof FindByLocationForm){
			validate((FindByLocationForm)target,errors);
		}
		else if(target instanceof SearchTennisCourtsForm){
			validate((SearchTennisCourtsForm)target,errors);
		}
		
	}
	public void validate(SearchTennisCourtsForm f, Errors errors){
		SearchTennisCourtsArgs args = new SearchTennisCourtsArgs();
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
		if(StringUtils.isNotEmpty(f.getLatitude()) && StringUtils.isNotEmpty(f.getLongitude())){
			try {
				args.latitude = Double.parseDouble(f.getLatitude());
				args.longitude = Double.parseDouble(f.getLongitude());
			} catch (Exception exp) {
				errors.rejectValue("latitude",ErrorCode.INVALID_EXPRESSION,"Address was set incorrectly");
				logger.error("validate - latitude and longitude failed to parse " + f.getLatitude() + ", " + f.getLongitude() + ", is null? " + (f.getLongitude() == null) + ", " + (f.getLatitude() == null));
			}
		}
		if(StringUtils.isNotEmpty(f.getDistance())){
			try {
				args.distance = Double.parseDouble(f.getDistance());	
			} catch (Exception exp) {
				errors.rejectValue("latitude",ErrorCode.INVALID_EXPRESSION,"Address was set correctly");
			}
		}
		else{
			args.distance = 50d;
		}
		
		if(StringUtils.isNotEmpty(f.getCourtName())){
			args.courtName = f.getCourtName();
		}
		
		args.outdoor = f.getOutdoor() != null;
		args.indoor = f.getIndoor() != null;
		args.carpet = f.getCarpet() != null;
		args.clay = f.getClay() != null;
		args.concrete = f.getConcrete() != null;
		args.grass = f.getGrass() != null;
		args.hard = f.getHard() != null;
		args.synthetic = f.getSynthetic() != null;
		
		if(!errors.hasErrors()){
			f.setArguments(args);
		}
	}
	
	//TODO COMPLETE
	public void validate(CreateTennisCenterForm f, Errors errors) {
		CreateTennisCenterArgs args = new CreateTennisCenterArgs();
		validate(f,errors,args);
		//--court name
		if(StringUtils.isEmpty(f.getTennisCenterName())){
			errors.rejectValue("tennisCenterName",ErrorCode.EMPTY_FIELD,"Please insert tennis center name");
		}
		else if(f.getTennisCenterName().length() > 100){
			errors.rejectValue("tennisCenterName",ErrorCode.EXCEED_MAX,"Max 100 characters");
		}
		else{
			args.tennisCenterName = f.getTennisCenterName();
		}
		
		//--website
		if(StringUtils.isEmpty(f.getWebsite())){
//			errors.rejectValue("website",ErrorCode.EMPTY_FIELD,"Please insert official website address");
		}
		else if(!urlPattern.matcher(f.getWebsite()).matches()){
			errors.rejectValue("website",ErrorCode.INVALID_EXPRESSION,"Invalid website address");
		}
		else{
			args.website = f.getWebsite();
		}
		
		//--phone number
		if(StringUtils.isEmpty(f.getPhoneNumber())){
//			errors.rejectValue("phoneNumber",ErrorCode.EMPTY_FIELD,"Please insert a phone number");
		}
		else if(!phoneNumberPattern.matcher(f.getPhoneNumber()).matches()){
			errors.rejectValue("phoneNumber",ErrorCode.INVALID_EXPRESSION,"Invalid phone number");
		}
		else{
			args.phoneNumber = f.getPhoneNumber();
		}
//		logger.debug("NO CHOICE:: " + f.getIndoorSurface() + ", " + f.getNumberOfIndoorCourts() + ", " + f.getOutdoorSurface() + ", " + f.getNumberOfOutdoorCourts());
		//--
		if(f.getOutdoorSurface() != null && f.getNumberOfOutdoorCourts() != null){
			if(f.getNumberOfOutdoorCourts().length > 0){
				args.numberOfOutdoorCourts = Lists.newArrayList();
				args.outdoorSurface = Lists.newArrayList();
				try{
					for(int i = 0 ; i < f.getNumberOfOutdoorCourts().length ; i++){
						if(StringUtils.isNotEmpty(f.getNumberOfOutdoorCourts()[i]) && StringUtils.isNotEmpty(f.getOutdoorSurface()[i])){
							args.numberOfOutdoorCourts.add(Integer.valueOf(f.getNumberOfOutdoorCourts()[i]));
							args.outdoorSurface.add(f.getOutdoorSurface()[i].toUpperCase());
						}
					}
				}
				catch(Exception exp){
					errors.rejectValue("numberOfOutdoorCourts",ErrorCode.INVALID_EXPRESSION,"Invalid number");
				}
			}
		}
		
		if(f.getIndoorSurface() != null && f.getNumberOfIndoorCourts() != null){
			if(f.getNumberOfIndoorCourts().length > 0){
				args.numberOfIndoorCourts = Lists.newArrayList();
				args.indoorSurface = Lists.newArrayList();
				try{
					for(int i = 0 ; i < f.getNumberOfIndoorCourts().length ; i++){
						if(StringUtils.isNotEmpty(f.getNumberOfIndoorCourts()[i]) && StringUtils.isNotEmpty(f.getIndoorSurface()[i])){
							args.numberOfIndoorCourts.add(Integer.valueOf(f.getNumberOfIndoorCourts()[i]));
							args.indoorSurface.add(f.getIndoorSurface()[i].toUpperCase());
						}
					}
				}
				catch(Exception exp){
					errors.rejectValue("numberOfIndoorCourts",ErrorCode.INVALID_EXPRESSION,"Invalid number");
				}
			}
		}
		if(args.indoorSurface == null && args.outdoorSurface == null){
			errors.rejectValue("indoorSurface",ErrorCode.BELOW_MIN,"You must enter at least one court");
		}
		args.createdOn = new Date();
		
		if(!errors.hasErrors()){
			f.setArguments(args);
		}
	}

//	public void validate(AddressForm f, Errors errors, AddressArgs args) {
//		//latitude
//		if(StringUtils.isEmpty(f.getLatitude())){
//			errors.rejectValue("latitude",ErrorCode.EMPTY_FIELD,"Invalid Address");
//		}
//		else{
//			try {
//				args.latitude = Double.valueOf(f.getLatitude());
//			} catch (Exception exp) {
//				errors.rejectValue("latitude",ErrorCode.INVALID_EXPRESSION,"Invalid Address");
//			}
//			
//		}
//		
//		//longitude
//		if(StringUtils.isEmpty(f.getLatitude())){
//			errors.rejectValue("longitude",ErrorCode.EMPTY_FIELD,"Invalid Address");
//		}
//		else{
//			try {
//				args.longitude = Double.valueOf(f.getLongitude());
//			} catch (Exception exp) {
//				errors.rejectValue("longitude",ErrorCode.INVALID_EXPRESSION,"Invalid Address");
//			}
//			
//		}
//		
//		args.administrativeAreaLevel1 = f.getAdministrativeAreaLevel1();
//		args.administrativeAreaLevel1ShortName = f.getAdministrativeAreaLevel1ShortName();
//		args.administrativeAreaLevel2 = f.getAdministrativeAreaLevel2();
//		args.administrativeAreaLevel2ShortName = f.getAdministrativeAreaLevel2ShortName();
//		args.country = f.getCountry();
//		args.countryShortName = f.getCountryShortName();
//		args.locality = f.getLocality();
//		args.localityShortName = f.getLocalityShortName();
//		args.postalCode = f.getPostalCode();
//		args.route = f.getRoute();
//		args.routeShortName = f.getRouteShortName();
//		
//		args.streetNumber = f.getStreetNumber();
//		args.sublocality = f.getSublocality();
//		args.sublocalityShortName = f.getSublocalityShortName();
//		args.addressTypes = Arrays.asList(f.getAddressTypes());
////		logger.debug("ADDRESS:::::::::::::" + Arrays.asList(f.getAddressTypes()));
//	}
}
