package com.tennissetapp.args;

import org.apache.commons.fileupload.FileItem;

import com.tennissetapp.persistence.entities.TennisPlayerProfile;
import com.tennissetapp.persistence.entities.UserAccount;

public class UpdateTennisDetailsArgs implements Arguments {
	public Long userAccountId;
	public TennisPlayerProfile.Hand hand;
	public Float levelOfPlay;
	
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
	@Override
	public String toString() {
		return "UpdateTennisDetailsArgs [userAccountId=" + userAccountId
				+ ", hand=" + hand + ", levelOfPlay=" + levelOfPlay
				+ ", singlesCheck=" + singlesCheck + ", doublesCheck="
				+ doublesCheck + ", HittingAroundCheck=" + HittingAroundCheck
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
				+ weekendAvailabilityMorningCheck + "]";
	}
	
}
