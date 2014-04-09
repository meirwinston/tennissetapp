package com.tennissetapp.args;

import java.util.List;

import org.apache.commons.fileupload.FileItem;

import com.tennissetapp.persistence.entities.TennisPlayerProfile;
import com.tennissetapp.persistence.entities.UserAccount;

public class CreatePlayerProfileArgs extends AddressArgs{
	public Long userAccountId;
	public Boolean agreesToTerms;
	public String firstName;
	public String lastName;
	public Integer birthDay;
	public Integer birthMonth;
	public Integer birthYear;
	public UserAccount.Gender gender;
	public TennisPlayerProfile.Hand hand;
	public Float levelOfPlay;
	public String aboutMe;
	
	public boolean singlesCheck;
	public boolean doublesCheck;
	public boolean HittingAroundCheck;
	public boolean pointsCheck;
	public boolean fullMatchCheck;
	
	public boolean weekdayAvailabilityAfternoonCheck;
	public boolean weekdayAvailabilityEveningCheck;
	public boolean weekdayAvailabilityMorningCheck;
	public boolean weekendAvailabilityAfternoonCheck;
	public boolean weekendAvailabilityEveningCheck;
	public boolean weekendAvailabilityMorningCheck;
	
	public String photoItemId;
	public String profilePhotoUrl;
	public String profileFileItemId; //FileItemId
	public FileItem fileItem;
	
	public List<Long> favoriteCourts;

	@Override
	public String toString() {
		return "CreatePlayerProfileArgs [userAccountId=" + userAccountId
				+ ", agreesToTerms=" + agreesToTerms + ", firstName="
				+ firstName + ", lastName=" + lastName + ", birthDay="
				+ birthDay + ", birthMonth=" + birthMonth + ", birthYear="
				+ birthYear + ", gender=" + gender + ", hand=" + hand
				+ ", levelOfPlay=" + levelOfPlay + ", singlesCheck="
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
				+ weekendAvailabilityMorningCheck + ", photoItemId="
				+ photoItemId + ", profilePhotoUrl=" + profilePhotoUrl
				+ ", profileFileItemId=" + profileFileItemId + ", fileItem="
				+ fileItem + ", favoriteCourts=" + favoriteCourts + "]";
	}
	
}
