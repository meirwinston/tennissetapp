package com.tennissetapp.args;

import com.tennissetapp.persistence.entities.UserAccount;

public class UserAccountPrimaryArgs extends AddressArgs{
	public Long userAccountId;
	public Boolean agreesToTerms;
	public String firstName;
	public String lastName;
	public Integer birthDay;
	public Integer birthMonth;
	public Integer birthYear;
	public UserAccount.Gender gender;
	@Override
	public String toString() {
		return "UserAccountPrimaryArgs [userAccountId=" + userAccountId
				+ ", agreesToTerms=" + agreesToTerms + ", firstName="
				+ firstName + ", lastName=" + lastName + ", birthDay="
				+ birthDay + ", birthMonth=" + birthMonth + ", birthYear="
				+ birthYear + ", gender=" + gender + ", latitude=" + latitude
				+ ", longitude=" + longitude + ", streetNumber=" + streetNumber
				+ ", route=" + route + ", routeShortName=" + routeShortName
				+ ", locality=" + locality + ", localityShortName="
				+ localityShortName + ", sublocality=" + sublocality
				+ ", sublocalityShortName=" + sublocalityShortName
				+ ", neighborhood=" + neighborhood + ", neighborhoodShortName="
				+ neighborhoodShortName + ", political=" + political
				+ ", politicalShortName=" + politicalShortName
				+ ", administrativeAreaLevel2=" + administrativeAreaLevel2
				+ ", administrativeAreaLevel2ShortName="
				+ administrativeAreaLevel2ShortName
				+ ", administrativeAreaLevel1=" + administrativeAreaLevel1
				+ ", administrativeAreaLevel1ShortName="
				+ administrativeAreaLevel1ShortName + ", country=" + country
				+ ", countryShortName=" + countryShortName + ", postalCode="
				+ postalCode + ", addressTypes=" + addressTypes + "]";
	}
	
	
}
