package com.tennissetapp.validation;

import java.util.Arrays;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.validation.Errors;

import com.tennissetapp.args.AddressArgs;
import com.tennissetapp.args.FindByLocationArgs;
import com.tennissetapp.args.ScrollByUserAccountIdArgs;
import com.tennissetapp.forms.AddressForm;
import com.tennissetapp.forms.FindByLocationForm;
import com.tennissetapp.forms.ScrollForm;

public abstract class Validator implements org.springframework.validation.Validator{
	public int NAME_INPUT_MAX_LENGTH = 50;
	public int PASSWORD_MIN_LENGTH = 6;
	protected Logger logger = Logger.getLogger(getClass());
	
	public interface ErrorCode{
		String EMPTY_FIELD = "EMPTY_FIELD";
		String EXCEED_MAX = "EXCEED_MAX_LENGTH";
		String BELOW_MIN = "BELOW_MIN_LENGTH";
		String ASSERT_TRUE = "ASSERT_TRUE";
		String NOT_EQUAL = "NOT_EQUAL";
		String INVALID_EXPRESSION = "INVALID_EXPRESSION";
	}
	protected static final String DATE_REGEX = "^\\d{4}-\\d{2}-\\d{2}$";
	protected static final String TIME_REGEX = "^\\d{2}:\\d{2}";
	protected static final String FLOATING_POINT_REGEX = "[-+]?[0-9]*\\.?[0-9]+";
	
	protected static final String EMAIL_REGEX = 
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	protected static final String URL_REGEX = "(@)?(href=')?(HREF=')?(HREF=\")?(href=\")?(http://)?[a-zA-Z_0-9\\-]+(\\.\\w[a-zA-Z_0-9\\-]+)+(/[#&\\n\\-=?\\+\\%/\\.\\w]+)?"; 
			//"^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
	
	protected static final String PHONE_NUMBER_REGEX = //"^(\\+\\d)*\\s*(\\(\\d{3}\\)\\s*)*\\d{3}(-{0,1}|\\s{0,1})\\d{2}(-{0,1}|\\s{0,1})\\d{2}$"; 
			"(\\d{10})" +
			"|" +
			"(\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4})" +
			"|" +
			"(\\d{3}-\\d{3}-\\d{4}\\s(x|(ext))\\d{3,5})" +
			"|" +
			"(\\(\\d{3}\\)-\\d{3}-\\d{4})";
//	
	
	// ^(\\+\\d)*\\s*(\\(\\d{3}\\)\\s*)*\\d{3}(-{0,1}|\\s{0,1})\\d{2}(-{0,1}|\\s{0,1})\\d{2}$
//	 //validate phone numbers of format "1234567890"
//	protected static final String TEN_DIGIT_PHONE_REGEX = "\\d{10}";
//    //validating phone number with -, . or spaces
//    else if(phoneNo.matches("\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}")) return true;
//    //validating phone number with extension length from 3 to 5
//    else if(phoneNo.matches("\\d{3}-\\d{3}-\\d{4}\\s(x|(ext))\\d{3,5}")) return true;
//    //validating phone number where area code is in braces ()
//    else if(phoneNo.matches("\\(\\d{3}\\)-\\d{3}-\\d{4}")) return true;
//    //return false if nothing matches the input
//    else return false;
	
	protected static Pattern emailPattern = Pattern.compile(EMAIL_REGEX);
	protected static Pattern urlPattern = Pattern.compile(URL_REGEX);
	protected static Pattern phoneNumberPattern = Pattern.compile(PHONE_NUMBER_REGEX);
	protected static Pattern datePattern = Pattern.compile(DATE_REGEX);
	protected static Pattern timePattern = Pattern.compile(TIME_REGEX);
//	protected static Pattern levelOfPlayPattern = Pattern.compile(FLOATING_POINT_REGEX + "|");
	
	
	/*
	 * 10-31 06:35:47.185: I/Tennis SetApp(885): CreatePlayerProfileForm [firstName=, lastName=, gender=male, userAccountId=172, levelOfPlay=1.0, hand=left, singlesCheck=null, doublesCheck=null, fullMatchCheck=null, pointsCheck=null, HittingAroundCheck=null, weekendAvailabilityMorningCheck=null, weekendAvailabilityAfternoonCheck=null, weekendAvailabilityEveningCheck=null, weekdayAvailabilityMorningCheck=on, weekdayAvailabilityAfternoonCheck=null, weekdayAvailabilityEveningCheck=null, favouriteCourts=null, latitude=40.4244493, longitude=-74.4221342, streetNumber=548, route=Ryders Lane, routeShortName=Ryders Ln, locality=East Brunswick, localityShortName=East Brunswick, sublocality=null, sublocalityShortName=null, administrativeAreaLevel2=null, administrativeAreaLevel2ShortName=null, administrativeAreaLevel1=New Jersey, administrativeAreaLevel1ShortName=NJ, country=United States, countryShortName=US, postalCode=08816, addressTypes=[street_address]]
	 */
	public static void validate(AddressForm f, Errors errors, AddressArgs args) {
		boolean valid = true;
		//latitude
		if(StringUtils.isEmpty(f.getLatitude())){
//			errors.rejectValue("latitude",ErrorCode.EMPTY_FIELD,"Invalid Address");
			valid = false;
		}
		else{
			try {
				args.latitude = Double.valueOf(f.getLatitude());
			} catch (Exception exp) {
//				errors.rejectValue("latitude",ErrorCode.INVALID_EXPRESSION,"Invalid Address");
				valid = false;
			}
			
		}
		
		//longitude
		if(StringUtils.isEmpty(f.getLatitude())){
//			errors.rejectValue("longitude",ErrorCode.EMPTY_FIELD,"Invalid Address");
			valid = false;
		}
		else{
			try {
				args.longitude = Double.valueOf(f.getLongitude());
			} catch (Exception exp) {
//				errors.rejectValue("longitude",ErrorCode.INVALID_EXPRESSION,"Invalid Address");
				valid = false;
			}
		}
		args.administrativeAreaLevel1 = f.getAdministrativeAreaLevel1(); //New Jersey
		args.administrativeAreaLevel1ShortName = f.getAdministrativeAreaLevel1ShortName();
		
		args.administrativeAreaLevel2 = f.getAdministrativeAreaLevel2();
		args.administrativeAreaLevel2ShortName = f.getAdministrativeAreaLevel2ShortName();
		
		if(StringUtils.isEmpty(f.getCountry())){
//			errors.rejectValue("country",ErrorCode.EMPTY_FIELD,"Incomplete address");
			valid = false;
		}
		else{
			args.country = f.getCountry();
		}
		
		if(StringUtils.isEmpty(f.getCountryShortName())){
//			errors.rejectValue("countryShortName",ErrorCode.EMPTY_FIELD,"Incomplete address");
			valid = false;
		}
		else{
			args.countryShortName = f.getCountryShortName();
		}
		if(StringUtils.isEmpty(f.getLocality())){ //Jerusalem, East Brunswick
//			errors.rejectValue("locality",ErrorCode.EMPTY_FIELD,"Incomplete address");
			valid = false;
		}
		else{
			args.locality = f.getLocality();
		}
		
		if(StringUtils.isEmpty(f.getLocalityShortName())){
//			errors.rejectValue("localityShortName",ErrorCode.EMPTY_FIELD,"Incomplete address");
			valid = false;
		}
		else{
			args.localityShortName = f.getLocalityShortName();
		}
		
		args.postalCode = f.getPostalCode();
		
		if(StringUtils.isEmpty(f.getRoute())){
//			errors.rejectValue("route",ErrorCode.EMPTY_FIELD,"Incomplete address");
			valid = false;
		}
		else{
			args.route = f.getRoute();
		}
		
		if(StringUtils.isEmpty(f.getRouteShortName())){
//			errors.rejectValue("routeShortName",ErrorCode.EMPTY_FIELD,"Incomplete address");
			valid = false;
		}
		else{
			args.routeShortName = f.getRouteShortName();
		}
		
		if(StringUtils.isEmpty(f.getStreetNumber())){
//			errors.rejectValue("streetNumber",ErrorCode.EMPTY_FIELD,"Incomplete address");
			valid = false;
		}
		else{
			args.streetNumber = f.getStreetNumber();
		}
		
		args.sublocality = f.getSublocality();
		args.sublocalityShortName = f.getSublocalityShortName();
		
		args.political = f.getPolitical();
		args.politicalShortName = f.getPoliticalShortName();
		args.neighborhood = f.getNeighborhood();
		args.neighborhoodShortName = f.getNeighborhoodShortName();
		
		if(f.getAddressTypes() != null){
			args.addressTypes = Arrays.asList(f.getAddressTypes());
		}
		
		if(!valid){
			errors.rejectValue("latitude",ErrorCode.INVALID_EXPRESSION,"Incomplete address, please select a full address from the list");
		}
		
//		logger.debug("ADDRESS:::::::::::::" + Arrays.asList(f.getAddressTypes()));
	} 
	
	
	public void validate(FindByLocationForm f, Errors errors) {
		FindByLocationArgs args = new FindByLocationArgs();
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
				args.maxDistance = Double.parseDouble(f.getDistance());	
			} catch (Exception exp) {
				errors.rejectValue("latitude",ErrorCode.INVALID_EXPRESSION,"Address was set correctly");
			}
		}
		else{
			args.maxDistance = 50d;
		}
		if(!errors.hasErrors()){
			f.setArguments(args);
		}
	}
	
	public void validate(ScrollForm form, Errors errors) {
		ScrollByUserAccountIdArgs args = new ScrollByUserAccountIdArgs();
		try {
			args.firstResult = Long.valueOf(form.getFirstResult());
			args.maxResults = Integer.valueOf(form.getMaxResults());
		} catch (Exception exp) {
			errors.rejectValue("firstResult", ErrorCode.INVALID_EXPRESSION, "Invalid request");
		}
		
		form.setArguments(args);
	}
}
