package com.tennissetapp.forms;

import com.tennissetapp.args.SearchMatchesArgs;

public class SearchMatchForm {
	protected String date;
	protected String startHour;
	protected String startMinute;
	protected String startDayTime;
	protected String endHour;
	protected String endMinute;
	protected String endDayTime;
	protected String level1,level2,level3,level4,level5;
	protected String playSingles,playDoubles,playPoints,playHittingAround;
	protected String latitude;
	protected String longitude;
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getStartHour() {
		return startHour;
	}
	public void setStartHour(String startHour) {
		this.startHour = startHour;
	}
	public String getStartMinute() {
		return startMinute;
	}
	public void setStartMinute(String startMinute) {
		this.startMinute = startMinute;
	}
	public String getStartDayTime() {
		return startDayTime;
	}
	public void setStartDayTime(String startDayTime) {
		this.startDayTime = startDayTime;
	}
	public String getEndHour() {
		return endHour;
	}
	public void setEndHour(String endHour) {
		this.endHour = endHour;
	}
	public String getEndMinute() {
		return endMinute;
	}
	public void setEndMinute(String endMinute) {
		this.endMinute = endMinute;
	}
	public String getEndDayTime() {
		return endDayTime;
	}
	public void setEndDayTime(String endDayTime) {
		this.endDayTime = endDayTime;
	}
	public String getLevel1() {
		return level1;
	}
	public void setLevel1(String level1) {
		this.level1 = level1;
	}
	public String getLevel2() {
		return level2;
	}
	public void setLevel2(String level2) {
		this.level2 = level2;
	}
	public String getLevel3() {
		return level3;
	}
	public void setLevel3(String level3) {
		this.level3 = level3;
	}
	public String getLevel4() {
		return level4;
	}
	public void setLevel4(String level4) {
		this.level4 = level4;
	}
	public String getLevel5() {
		return level5;
	}
	public void setLevel5(String level5) {
		this.level5 = level5;
	}
	public String getPlaySingles() {
		return playSingles;
	}
	public void setPlaySingles(String playSingles) {
		this.playSingles = playSingles;
	}
	public String getPlayDoubles() {
		return playDoubles;
	}
	public void setPlayDoubles(String playDoubles) {
		this.playDoubles = playDoubles;
	}
	public String getPlayPoints() {
		return playPoints;
	}
	public void setPlayPoints(String playPoints) {
		this.playPoints = playPoints;
	}
	public String getPlayHittingAround() {
		return playHittingAround;
	}
	public void setPlayHittingAround(String playHittingAround) {
		this.playHittingAround = playHittingAround;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	
	public SearchMatchesArgs toArgs(){
		SearchMatchesArgs args = new SearchMatchesArgs();
		
		return args;
	}
}
