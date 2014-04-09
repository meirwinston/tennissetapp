package com.tennissetapp.forms;

public class CreatePlayerProfileForm  extends AddressForm {
	protected String userAccountId;
//	protected String profileType;
	protected String agreesToTerms;
	protected String firstName;
	protected String lastName;
	protected String birthDay;
	protected String birthMonth;
	protected String birthYear;
	protected String gender;
	protected String profileFileItemId; //FileItemId
	protected String levelOfPlay;
	protected String hand;
	protected String aboutMe;
	
	protected String singlesCheck;
	protected String doublesCheck;
	protected String HittingAroundCheck;
	protected String pointsCheck;
	protected String fullMatchCheck;
	
	protected String weekdayAvailabilityAfternoonCheck;
	protected String weekdayAvailabilityEveningCheck;
	protected String weekdayAvailabilityMorningCheck;
	protected String weekendAvailabilityAfternoonCheck;
	protected String weekendAvailabilityEveningCheck;
	protected String weekendAvailabilityMorningCheck;
	
	protected String favoriteCourts;
	
	public String getHand() {
		return hand;
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

	public String getWeekdayAvailabilityMorningCheck() {
		return weekdayAvailabilityMorningCheck;
	}

	public void setWeekdayAvailabilityMorningCheck(
			String weekdayAvailabilityMorningCheck) {
		this.weekdayAvailabilityMorningCheck = weekdayAvailabilityMorningCheck;
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

	public String getWeekendAvailabilityMorningCheck() {
		return weekendAvailabilityMorningCheck;
	}

	public void setWeekendAvailabilityMorningCheck(
			String weekendAvailabilityMorningCheck) {
		this.weekendAvailabilityMorningCheck = weekendAvailabilityMorningCheck;
	}

	public void setHand(String hand) {
		this.hand = hand;
	}

	public String getLevelOfPlay() {
		return levelOfPlay;
	}

	public void setLevelOfPlay(String levelOfPlay) {
		this.levelOfPlay = levelOfPlay;
	}

	public CreatePlayerProfileForm(){}
	
//	public CreateProfileArgs toArgs(){
//		CreateProfileArgs args = new CreateProfileArgs();
//		args.userAccountId = Long.valueOf(this.userAccountId);
////		args.profileType = this.profileType;
//		args.agreesToTerms = (this.agreesToTerms != null);
//		args.firstName = this.firstName;
//		args.lastName = this.lastName;
//		args.birthDay = Integer.valueOf(this.birthDay);
//		args.birthMonth = Integer.valueOf(this.birthMonth);
//		args.birthYear = Integer.valueOf(this.birthYear);
//		args.gender = this.gender;
//		
//		return args;
//	}
	
	public String getProfileFileItemId() {
		return profileFileItemId;
	}

	public void setProfileFileItemId(String profileFileItemId) {
		this.profileFileItemId = profileFileItemId;
	}

//	public String getProfileType() {
//		return profileType;
//	}

//	public void setProfileType(String profileType) {
//		this.profileType = profileType;
//		
////		Class<?>[] aa = new Class[]{};
//	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public String getUserAccountId() {
		return userAccountId;
	}

	public void setUserAccountId(String userAccountId) {
		this.userAccountId = userAccountId;
	}

	public String getAgreesToTerms() {
		return agreesToTerms;
	}

	public void setAgreesToTerms(String agreesToTerms) {
		this.agreesToTerms = agreesToTerms;
	}

	public String getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}

	public String getBirthMonth() {
		return birthMonth;
	}

	public void setBirthMonth(String birthMonth) {
		this.birthMonth = birthMonth;
	}

	public String getBirthYear() {
		return birthYear;
	}

	public void setBirthYear(String birthYear) {
		this.birthYear = birthYear;
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

	public String getHittingAroundCheck() {
		return HittingAroundCheck;
	}

	public void setHittingAroundCheck(String HittingAroundCheck) {
		this.HittingAroundCheck = HittingAroundCheck;
	}

	public String getPointsCheck() {
		return pointsCheck;
	}

	public void setPointsCheck(String pointsCheck) {
		this.pointsCheck = pointsCheck;
	}

	public String getFullMatchCheck() {
		return fullMatchCheck;
	}

	public void setFullMatchCheck(String fullMatchCheck) {
		this.fullMatchCheck = fullMatchCheck;
	}
	
	public String getFavoriteCourts() {
		return favoriteCourts;
	}

	public void setFavoriteCourts(String favoriteCourts) {
		this.favoriteCourts = favoriteCourts;
	}

	public String getAboutMe() {
		return aboutMe;
	}

	public void setAboutMe(String aboutMe) {
		this.aboutMe = aboutMe;
	}

	@Override
	public String toString() {
		return "CreatePlayerProfileForm [userAccountId=" + userAccountId
				+ ", agreesToTerms=" + agreesToTerms + ", firstName="
				+ firstName + ", lastName=" + lastName + ", birthDay="
				+ birthDay + ", birthMonth=" + birthMonth + ", birthYear="
				+ birthYear + ", gender=" + gender + ", profileFileItemId="
				+ profileFileItemId + ", levelOfPlay=" + levelOfPlay
				+ ", hand=" + hand + ", aboutMe=" + aboutMe + ", singlesCheck="
				+ singlesCheck + ", doublesCheck=" + doublesCheck
				+ ", HittingAroundCheck=" + HittingAroundCheck
				+ ", pointsCheck=" + pointsCheck + ", fullMatchCheck="
				+ fullMatchCheck + ", weekdayAvailabilityAfternoonCheck="
				+ weekdayAvailabilityAfternoonCheck
				+ ", weekdayAvailabilityEveningCheck="
				+ weekdayAvailabilityEveningCheck
				+ ", weekdayAvailabilityMorningCheck="
				+ weekdayAvailabilityMorningCheck
				+ ", weekendAvailabilityAfternoonCheck="
				+ weekendAvailabilityAfternoonCheck
				+ ", weekendAvailabilityEveningCheck="
				+ weekendAvailabilityEveningCheck
				+ ", weekendAvailabilityMorningCheck="
				+ weekendAvailabilityMorningCheck + ", favoriteCourts="
				+ favoriteCourts + "]";
	}
}
