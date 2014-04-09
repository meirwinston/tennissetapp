package com.tennissetapp.args;

public class SearchTennisTeachersArgs extends ScrollArgs{

	public Boolean teacherCertified;
	public boolean specialtyJuniors,specialtyAdults,specialtyTurnaments; //false ignore, true include
	public String currency;
	public Double clinicRates;
	//location
	public Double latitude;
	public Double longitude;
	
	//availability
	public boolean availableWeekendMorning;
	public boolean availableWeekendAfternoon;
	public boolean availableWeekendEvening;
	public boolean availableWeekdayMorning;
	public boolean availableWeekdayAfternoon;
	public boolean availableWeekdayEvening;
	
	//distance
	public Double maxDistance;

	@Override
	public String toString() {
		return "SearchTennisTeachersArgs [teacherCertified=" + teacherCertified
				+ ", specialtyJuniors=" + specialtyJuniors
				+ ", specialtyAdults=" + specialtyAdults
				+ ", specialtyTurnaments=" + specialtyTurnaments
				+ ", latitude=" + latitude + ", longitude=" + longitude
				+ ", availableWeekendMorning=" + availableWeekendMorning
				+ ", availableWeekendAfternoon=" + availableWeekendAfternoon
				+ ", availableWeekendEvening=" + availableWeekendEvening
				+ ", availableWeekdayMorning=" + availableWeekdayMorning
				+ ", availableWeekdayAfternoon=" + availableWeekdayAfternoon
				+ ", availableWeekdayEvening=" + availableWeekdayEvening
				+ ", maxDistance=" + maxDistance + "]";
	}
}
