package com.tennissetapp.forms;

public class SearchTennisTeachersForm extends ScrollForm{

	protected String teacherCertified;
	protected String specialtyJuniors,specialtyAdults,specialtyTurnaments;
	
	protected String clinicRates;
	protected String currency;
	
	//location
	protected String latitude;
	protected String longitude;
	
	//availability
	protected String availableWeekendMorning;
	protected String availableWeekendAfternoon;
	protected String availableWeekendEvening;
	protected String availableWeekdayMorning;
	protected String availableWeekdayAfternoon;
	protected String availableWeekdayEvening;
	
	//distance
	protected String distance;

	public String getTeacherCertified() {
		return teacherCertified;
	}

	public void setTeacherCertified(String teacherCertified) {
		this.teacherCertified = teacherCertified;
	}

	public String getSpecialtyJuniors() {
		return specialtyJuniors;
	}

	public void setSpecialtyJuniors(String specialtyJuniors) {
		this.specialtyJuniors = specialtyJuniors;
	}

	public String getSpecialtyAdults() {
		return specialtyAdults;
	}

	public void setSpecialtyAdults(String specialtyAdults) {
		this.specialtyAdults = specialtyAdults;
	}

	public String getSpecialtyTurnaments() {
		return specialtyTurnaments;
	}

	public void setSpecialtyTurnaments(String specialtyTurnaments) {
		this.specialtyTurnaments = specialtyTurnaments;
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

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}
	
	
	public String getClinicRates() {
		return clinicRates;
	}

	public void setClinicRates(String clinicRates) {
		this.clinicRates = clinicRates;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	@Override
	public String toString() {
		return "SearchTennisTeachersForm [teacherCertified=" + teacherCertified
				+ ", specialtyJuniors=" + specialtyJuniors
				+ ", specialtyAdults=" + specialtyAdults
				+ ", specialtyTurnaments=" + specialtyTurnaments
				+ ", clinicRates=" + clinicRates + ", currency=" + currency
				+ ", latitude=" + latitude + ", longitude=" + longitude
				+ ", availableWeekendMorning=" + availableWeekendMorning
				+ ", availableWeekendAfternoon=" + availableWeekendAfternoon
				+ ", availableWeekendEvening=" + availableWeekendEvening
				+ ", availableWeekdayMorning=" + availableWeekdayMorning
				+ ", availableWeekdayAfternoon=" + availableWeekdayAfternoon
				+ ", availableWeekdayEvening=" + availableWeekdayEvening
				+ ", distance=" + distance + "]";
	}

	
}
