package com.tennissetapp.forms;

public class SearchMatesForm extends ScrollForm{
	
	//name or email
//	protected String nameOrEmail;
	
	//type of play
	protected String playSingles;
	protected String playDoubles;
	protected String playFullMatch;
	protected String playPoints;
	protected String playHittingAround;
	
	//location
	protected String latitude;
	protected String longitude;
	
	//level of play
	protected String levelOfPlayMin;
	protected String levelOfPlayMax;
	
	//availability
	protected String availableWeekendMorning;
	protected String availableWeekendAfternoon;
	protected String availableWeekendEvening;
	protected String availableWeekdayMorning;
	protected String availableWeekdayAfternoon;
	protected String availableWeekdayEvening;
	
	protected String distance;
	
	public String getDistance() {
		return distance;
	}
	public void setDistance(String distance) {
		this.distance = distance;
	}
	public String getLevelOfPlayMin() {
		return levelOfPlayMin;
	}
	public void setLevelOfPlayMin(String levelOfPlayMin) {
		this.levelOfPlayMin = levelOfPlayMin;
	}
	public String getLevelOfPlayMax() {
		return levelOfPlayMax;
	}
	public void setLevelOfPlayMax(String levelOfPlayMax) {
		this.levelOfPlayMax = levelOfPlayMax;
	}
	
	public String getAvailableWeekendMorning() {
		return availableWeekendMorning;
	}
	public void setAvailableWeekendMorning(String availableWeekendMorning) {
		this.availableWeekendMorning = availableWeekendMorning;
	}
	public String getAvailableWeekendAfternoon() {
		return availableWeekendAfternoon;
	}
	public void setAvailableWeekendAfternoon(String availableWeekendAfternoon) {
		this.availableWeekendAfternoon = availableWeekendAfternoon;
	}
	public String getAvailableWeekendEvening() {
		return availableWeekendEvening;
	}
	public void setAvailableWeekendEvening(String availableWeekendEvening) {
		this.availableWeekendEvening = availableWeekendEvening;
	}
	public String getAvailableWeekdayMorning() {
		return availableWeekdayMorning;
	}
	public void setAvailableWeekdayMorning(String availableWeekdayMorning) {
		this.availableWeekdayMorning = availableWeekdayMorning;
	}
	public String getAvailableWeekdayAfternoon() {
		return availableWeekdayAfternoon;
	}
	public void setAvailableWeekdayAfternoon(String availableWeekdayAfternoon) {
		this.availableWeekdayAfternoon = availableWeekdayAfternoon;
	}
	public String getAvailableWeekdayEvening() {
		return availableWeekdayEvening;
	}
	public void setAvailableWeekdayEvening(String availableWeekdayEvening) {
		this.availableWeekdayEvening = availableWeekdayEvening;
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
	public String getPlayFullMatch() {
		return playFullMatch;
	}
	public void setPlayFullMatch(String playFullMatch) {
		this.playFullMatch = playFullMatch;
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
	
//	public String getNameOrEmail() {
//		return nameOrEmail;
//	}
//	public void setNameOrEmail(String nameOrEmail) {
//		this.nameOrEmail = nameOrEmail;
//	}
	
//	public SearchMatesByNameArgs toSearchMatesByUserArgs(UserAccount userAccount){
//		SearchMatesByNameArgs args = new SearchMatesByNameArgs();
////		args.nameOrEmail = this.getNameOrEmail();
//		args.maxResults = Integer.valueOf(this.getMaxResults());
//		args.firstResult = Long.valueOf(this.getFirstResult());
//		args.distance = 50d;
//		args.latitude = userAccount.getAddressLatitude();
//		args.longitude = userAccount.getAddressLongitude();
//		args.playDoubles = userAccount.getPlayerProfile().getPlayDoubles();
//		args.playFullMatch = userAccount.getPlayerProfile().getPlayFullMatch();
//		args.playHittingAround = userAccount.getPlayerProfile().getPlayHittingAround();
//		args.playPoints = userAccount.getPlayerProfile().getPlayPoints();
//		args.playSingles = userAccount.getPlayerProfile().getPlaySingles();
//		args.levelOfPlayMin = userAccount.getPlayerProfile().getLevelOfPlay();
//		args.levelOfPlayMax = userAccount.getPlayerProfile().getLevelOfPlay();
//		args.availableWeekdayAfternoon = userAccount.getPlayerProfile().getAvailableWeekdayAfternoon();
//		args.availableWeekdayEvening = userAccount.getPlayerProfile().getAvailableWeekdayEvening();
//		args.availableWeekdayMorning = userAccount.getPlayerProfile().getAvailableWeekdayMorning();
//		args.availableWeekendAfternoon = userAccount.getPlayerProfile().getAvailableWeekendAfternoon();
//		args.availableWeekendEvening = userAccount.getPlayerProfile().getAvailableWeekendEvening();
//		args.availableWeekendMorning = userAccount.getPlayerProfile().getAvailableWeekendMorning();
//		return args;
//	}
	@Override
	public String toString() {
		return "SearchMatesForm [playSingles="
				+ playSingles + ", playDoubles=" + playDoubles
				+ ", playFullMatch=" + playFullMatch + ", playPoints="
				+ playPoints + ", playHittingAround=" + playHittingAround
				+ ", latitude=" + latitude + ", longitude=" + longitude
				+ ", levelOfPlayMin=" + levelOfPlayMin + ", levelOfPlayMax="
				+ levelOfPlayMax + ", availableWeekendMorning="
				+ availableWeekendMorning + ", availableWeekendAfternoon="
				+ availableWeekendAfternoon + ", availableWeekendEvening="
				+ availableWeekendEvening + ", availableWeekdayMorning="
				+ availableWeekdayMorning + ", availableWeekdayAfternoon="
				+ availableWeekdayAfternoon + ", availableWeekdayEvening="
				+ availableWeekdayEvening + ", distance=" + distance + "]";
	}
	
	
}
