package com.tennissetapp.forms;

import com.tennissetapp.args.CreateProfileArgs;

public class CreateProfileForm extends AbstractForm {
	protected String userAccountId;
	protected String profileType;
	protected String agreesToTerms;
	protected String firstName;
	protected String lastName;
	protected String birthDay;
	protected String birthMonth;
	protected String birthYear;
	protected String gender;
	protected String profileFileItemId; //FileItemId
	
	public CreateProfileForm(){}
	
	public CreateProfileArgs toArgs(){
		CreateProfileArgs args = new CreateProfileArgs();
		args.userAccountId = Long.valueOf(this.userAccountId);
		args.profileType = this.profileType;
		args.agreesToTerms = (this.agreesToTerms != null);
		args.firstName = this.firstName;
		args.lastName = this.lastName;
		args.birthDay = Integer.valueOf(this.birthDay);
		args.birthMonth = Integer.valueOf(this.birthMonth);
		args.birthYear = Integer.valueOf(this.birthYear);
		args.gender = this.gender;
		
		return args;
	}
	
	public String getProfileFileItemId() {
		return profileFileItemId;
	}

	public void setProfileFileItemId(String profileFileItemId) {
		this.profileFileItemId = profileFileItemId;
	}

	public String getProfileType() {
		return profileType;
	}

	public void setProfileType(String profileType) {
		this.profileType = profileType;
		
//		Class<?>[] aa = new Class[]{};
	}

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

	@Override
	public String toString() {
		return "CreateProfileForm [userAccountId=" + userAccountId + ", profileType="
				+ profileType + ", agreesToTerms=" + agreesToTerms
				+ ", firstName=" + firstName + ", lastName=" + lastName
				+ ", birthDay=" + birthDay + ", birthMonth=" + birthMonth
				+ ", birthYear=" + birthYear + ", gender=" + gender
				+ "]";
	}
}
