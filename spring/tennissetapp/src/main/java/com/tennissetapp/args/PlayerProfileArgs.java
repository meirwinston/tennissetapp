package com.tennissetapp.args;

import java.util.List;

import com.tennissetapp.persistence.entities.TennisPlayerProfile;

public class PlayerProfileArgs extends AddressArgs{
	public Long userAccountId;
	public Float levelOfPlay;
	public TennisPlayerProfile.Hand hand;
	public boolean singlesCheck;
	public boolean doublesCheck;
	public boolean fullMatchCheck;
	public boolean pointsCheck;
	public boolean HittingAroundCheck;
	public boolean weekendAvailabilityMorningCheck;
	public boolean weekendAvailabilityAfternoonCheck;
	public boolean weekendAvailabilityEveningCheck;
	public boolean weekdayAvailabilityMorningCheck;
	public boolean weekdayAvailabilityAfternoonCheck;
	public boolean weekdayAvailabilityEveningCheck;
	public List<Long> favouriteCourts;
	@Override
	public String toString() {
		return "PlayerProfileArgs [userAccountId=" + userAccountId
				+ ", levelOfPlay=" + levelOfPlay + ", hand=" + hand
				+ ", singlesCheck=" + singlesCheck + ", doublesCheck="
				+ doublesCheck + ", fullMatchCheck=" + fullMatchCheck
				+ ", pointsCheck=" + pointsCheck + ", HittingAroundCheck="
				+ HittingAroundCheck + ", weekendAvailabilityMorningCheck="
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
				+ favouriteCourts + "]";
	}
	
}
