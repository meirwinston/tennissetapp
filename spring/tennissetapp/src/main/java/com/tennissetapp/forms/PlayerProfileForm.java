package com.tennissetapp.forms;

import java.util.ArrayList;
import com.tennissetapp.args.PlayerProfileArgs;
import com.tennissetapp.persistence.entities.TennisPlayerProfile;

public class PlayerProfileForm extends AddressForm{
	protected String userAccountId;
	protected String levelOfPlay;
	protected String hand;
	protected String singlesCheck;
	protected String doublesCheck;
	protected String fullMatchCheck;
	protected String pointsCheck;
	protected String HittingAroundCheck;
	protected String weekendAvailabilityMorningCheck;
	protected String weekendAvailabilityAfternoonCheck;
	protected String weekendAvailabilityEveningCheck;
	protected String weekdayAvailabilityMorningCheck;
	protected String weekdayAvailabilityAfternoonCheck;
	protected String weekdayAvailabilityEveningCheck;
	protected String favouriteCourts; //comma separated values

	public PlayerProfileForm(){}
	public PlayerProfileForm(TennisPlayerProfile p){
		userAccountId = p.getUserAccountId().toString();
		levelOfPlay = p.getLevelOfPlay() != null ? p.getLevelOfPlay().toString() : null;
		hand = p.getHand() != null ? p.getHand().toString() : null;
		singlesCheck = p.getPlaySingles() ? Boolean.TRUE.toString() : null;
		doublesCheck = p.getPlayDoubles() ? Boolean.TRUE.toString() : null;
		fullMatchCheck = p.getPlayFullMatch() ? Boolean.TRUE.toString() : null;
		pointsCheck = p.getPlayPoints() ? Boolean.TRUE.toString() : null;
		HittingAroundCheck = p.getPlayHittingAround() ? Boolean.TRUE.toString() : null;
		weekendAvailabilityMorningCheck = p.getAvailableWeekendMorning() ? Boolean.TRUE.toString() : null;
		weekendAvailabilityAfternoonCheck = p.getAvailableWeekendAfternoon() ? Boolean.TRUE.toString() : null;
		weekendAvailabilityEveningCheck = p.getAvailableWeekendEvening() ? Boolean.TRUE.toString() : null;
		weekdayAvailabilityMorningCheck = p.getAvailableWeekdayMorning() ? Boolean.TRUE.toString() : null;
		weekdayAvailabilityAfternoonCheck = p.getAvailableWeekdayAfternoon() ? Boolean.TRUE.toString() : null;
		weekdayAvailabilityEveningCheck = p.getAvailableWeekdayEvening() ? Boolean.TRUE.toString() : null;
	}
	
//	public void initArgs(PlayerProfileArgs args){
//		super.initArgs(args);
//		
//		args.userAccountId = Long.valueOf(this.userAccountId);
//		args.levelOfPlay = Float.valueOf(this.levelOfPlay);
//		args.hand = TennisPlayerProfile.Hand.valueOf(this.hand);
//		
//		args.doublesCheck = (this.doublesCheck != null);
//		args.fullMatchCheck = (this.fullMatchCheck != null);
//		args.HittingAroundCheck = (this.HittingAroundCheck != null);
//		args.pointsCheck = (this.pointsCheck != null);
//		args.singlesCheck = (this.singlesCheck != null);
//		
//		args.weekdayAvailabilityAfternoonCheck = (this.weekdayAvailabilityAfternoonCheck != null);
//		args.weekdayAvailabilityEveningCheck = (this.weekdayAvailabilityEveningCheck != null);
//		args.weekdayAvailabilityMorningCheck = (this.weekdayAvailabilityMorningCheck != null);
//		args.weekendAvailabilityAfternoonCheck = (this.weekendAvailabilityAfternoonCheck != null);
//		args.weekendAvailabilityEveningCheck = (this.weekendAvailabilityEveningCheck != null);
//		args.weekendAvailabilityMorningCheck = (this.weekendAvailabilityMorningCheck != null);
//		
//		if(this.favouriteCourts != null){
//			String[] arr = this.favouriteCourts.split(",");
//			args.favouriteCourts = new ArrayList<Long>();
//			for(int i = 0 ; i < arr.length ; i++){
//				args.favouriteCourts.add(Long.valueOf(arr[i]));
//			}
//		}
//	}
	
	public String getUserAccountId() {
		return userAccountId;
	}
	public void setUserAccountId(String userAccountId) {
		this.userAccountId = userAccountId;
	}
	public String getStreetNumber() {
		return streetNumber;
	}
	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}
	public String getLevelOfPlay() {
		return levelOfPlay;
	}
	public void setLevelOfPlay(String levelOfPlay) {
		this.levelOfPlay = levelOfPlay;
	}
	public String getHand() {
		return hand;
	}
	public void setHand(String hand) {
		this.hand = hand;
	}
	
	public String getSinglesCheck() {
		return singlesCheck;
	}
	public void setSinglesCheck(String singlesCheck) {
		this.singlesCheck = singlesCheck;
	}
	public String getDoublesCheck() {
		return doublesCheck;
	}
	public void setDoublesCheck(String doublesCheck) {
		this.doublesCheck = doublesCheck;
	}
	public String getFullMatchCheck() {
		return fullMatchCheck;
	}
	public void setFullMatchCheck(String fullMatchCheck) {
		this.fullMatchCheck = fullMatchCheck;
	}
	public String getPointsCheck() {
		return pointsCheck;
	}
	public void setPointsCheck(String pointsCheck) {
		this.pointsCheck = pointsCheck;
	}
	public String getHittingAroundCheck() {
		return HittingAroundCheck;
	}
	public void setHittingAroundCheck(String HittingAroundCheck) {
		this.HittingAroundCheck = HittingAroundCheck;
	}
	public String getWeekendAvailabilityMorningCheck() {
		return weekendAvailabilityMorningCheck;
	}
	public void setWeekendAvailabilityMorningCheck(
			String weekendAvailabilityMorningCheck) {
		this.weekendAvailabilityMorningCheck = weekendAvailabilityMorningCheck;
	}
	public String getWeekendAvailabilityAfternoonCheck() {
		return weekendAvailabilityAfternoonCheck;
	}
	public void setWeekendAvailabilityAfternoonCheck(
			String weekendAvailabilityAfternoonCheck) {
		this.weekendAvailabilityAfternoonCheck = weekendAvailabilityAfternoonCheck;
	}
	public String getWeekendAvailabilityEveningCheck() {
		return weekendAvailabilityEveningCheck;
	}
	public void setWeekendAvailabilityEveningCheck(
			String weekendAvailabilityEveningCheck) {
		this.weekendAvailabilityEveningCheck = weekendAvailabilityEveningCheck;
	}
	public String getWeekdayAvailabilityMorningCheck() {
		return weekdayAvailabilityMorningCheck;
	}
	public void setWeekdayAvailabilityMorningCheck(
			String weekdayAvailabilityMorningCheck) {
		this.weekdayAvailabilityMorningCheck = weekdayAvailabilityMorningCheck;
	}
	public String getWeekdayAvailabilityAfternoonCheck() {
		return weekdayAvailabilityAfternoonCheck;
	}
	public void setWeekdayAvailabilityAfternoonCheck(
			String weekdayAvailabilityAfternoonCheck) {
		this.weekdayAvailabilityAfternoonCheck = weekdayAvailabilityAfternoonCheck;
	}
	public String getWeekdayAvailabilityEveningCheck() {
		return weekdayAvailabilityEveningCheck;
	}
	public void setWeekdayAvailabilityEveningCheck(
			String weekdayAvailabilityEveningCheck) {
		this.weekdayAvailabilityEveningCheck = weekdayAvailabilityEveningCheck;
	}
	
	public String getFavouriteCourts() {
		return favouriteCourts;
	}
	public void setFavouriteCourts(String favouriteCourts) {
		this.favouriteCourts = favouriteCourts;
	}
	@Override
	public String toString() {
		return "PlayerProfileForm [userAccountId=" + userAccountId + ", levelOfPlay="
				+ levelOfPlay + ", hand=" + hand + ", singlesCheck="
				+ singlesCheck + ", doublesCheck=" + doublesCheck
				+ ", fullMatchCheck=" + fullMatchCheck + ", pointsCheck="
				+ pointsCheck + ", HittingAroundCheck=" + HittingAroundCheck
				+ ", weekendAvailabilityMorningCheck="
				+ weekendAvailabilityMorningCheck
				+ ", weekendAvailabilityAfternoonCheck="
				+ weekendAvailabilityAfternoonCheck
				+ ", weekendAvailabilityEveningCheck="
				+ weekendAvailabilityEveningCheck
				+ ", weekdayAvailabilityMorningCheck="
				+ weekdayAvailabilityMorningCheck
				+ ", weekdayAvailabilityAfternoonCheck="
				+ weekdayAvailabilityAfternoonCheck
				+ ", weekdayAvailabilityEveningCheck="
				+ weekdayAvailabilityEveningCheck + ", favouriteCourts="
				+ favouriteCourts + ", latitude=" + latitude
				+ ", longitude=" + longitude + ", streetNumber=" + streetNumber
				+ ", route=" + route + ", routeShortName=" + routeShortName
				+ ", locality=" + locality + ", localityShortName="
				+ localityShortName + ", administrativeAreaLevel2="
				+ administrativeAreaLevel2
				+ ", administrativeAreaLevel2ShortName="
				+ administrativeAreaLevel2ShortName
				+ ", administrativeAreaLevel1=" + administrativeAreaLevel1
				+ ", administrativeAreaLevel1ShortName="
				+ administrativeAreaLevel1ShortName + ", country=" + country
				+ ", countryShortName=" + countryShortName + ", postalCode="
				+ postalCode + ", addressTypes=" + addressTypes + "]";
	}
}
