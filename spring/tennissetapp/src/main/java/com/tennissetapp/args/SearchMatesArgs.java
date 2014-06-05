package com.tennissetapp.args;

public class SearchMatesArgs extends ScrollArgs{
	//type of play
	public boolean playSingles;
	public boolean playDoubles;
	public boolean playFullMatch;
	public boolean playPoints;
	public boolean playHittingAround;
	
	//location
	public Double latitude;
	public Double longitude;
	
	//level of play
	public Float levelOfPlayMin;
	public Float levelOfPlayMax;
	
	//availability
	public boolean availableWeekendMorning;
	public boolean availableWeekendAfternoon;
	public boolean availableWeekendEvening;
	public boolean availableWeekdayMorning;
	public boolean availableWeekdayAfternoon;
	public boolean availableWeekdayEvening;
	
	//distance
	public Double distance;

	@Override
	public String toString() {
		return "SearchMatesArgs [playSingles=" + playSingles + ", playDoubles="
				+ playDoubles + ", playFullMatch=" + playFullMatch
				+ ", playPoints=" + playPoints + ", playHittingAround="
				+ playHittingAround + ", latitude=" + latitude + ", longitude="
				+ longitude + ", levelOfPlayMin=" + levelOfPlayMin
				+ ", levelOfPlayMax=" + levelOfPlayMax
				+ ", availableWeekendMorning=" + availableWeekendMorning
				+ ", availableWeekendAfternoon=" + availableWeekendAfternoon
				+ ", availableWeekendEvening=" + availableWeekendEvening
				+ ", availableWeekdayMorning=" + availableWeekdayMorning
				+ ", availableWeekdayAfternoon=" + availableWeekdayAfternoon
				+ ", availableWeekdayEvening=" + availableWeekdayEvening
				+ ", distance=" + distance + "]";
	}
	
}
