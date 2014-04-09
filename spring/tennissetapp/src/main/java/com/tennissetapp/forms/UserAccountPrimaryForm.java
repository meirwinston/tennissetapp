package com.tennissetapp.forms;

public class UserAccountPrimaryForm extends AddressForm {
	protected String userAccountId;
	protected String agreesToTerms;
	protected String firstName;
	protected String lastName;
	protected String birthDay;
	protected String birthMonth;
	protected String birthYear;
	protected String gender;
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
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	@Override
	public String toString() {
		return "UserAccountPrimaryForm [userAccountId=" + userAccountId
				+ ", agreesToTerms=" + agreesToTerms + ", firstName="
				+ firstName + ", lastName=" + lastName + ", birthDay="
				+ birthDay + ", birthMonth=" + birthMonth + ", birthYear="
				+ birthYear + ", gender=" + gender + ", latitude=" + latitude
				+ ", longitude=" + longitude + "]";
	}
	
}
