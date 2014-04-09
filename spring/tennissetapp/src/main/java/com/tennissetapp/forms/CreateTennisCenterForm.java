package com.tennissetapp.forms;

import java.util.Arrays;

public class CreateTennisCenterForm extends AddressForm{
	protected String tennisCenterName;
	protected String[] numberOfOutdoorCourts;
	protected String[] numberOfIndoorCourts;
	protected String[] indoorSurface;
	protected String[] outdoorSurface;
	protected String phoneNumber;
	protected String website;

	
	
	public String getTennisCenterName() {
		return tennisCenterName;
	}
	public void setTennisCenterName(String tennisCenterName) {
		this.tennisCenterName = tennisCenterName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String[] getNumberOfOutdoorCourts() {
		return numberOfOutdoorCourts;
	}
	public void setNumberOfOutdoorCourts(String[] numberOfOutdoorCourts) {
		this.numberOfOutdoorCourts = numberOfOutdoorCourts;
	}
	public String[] getNumberOfIndoorCourts() {
		return numberOfIndoorCourts;
	}
	public void setNumberOfIndoorCourts(String[] numberOfIndoorCourts) {
		this.numberOfIndoorCourts = numberOfIndoorCourts;
	}
	public String[] getIndoorSurface() {
		return indoorSurface;
	}
	public void setIndoorSurface(String[] indoorSurface) {
		this.indoorSurface = indoorSurface;
	}
	public String[] getOutdoorSurface() {
		return outdoorSurface;
	}
	public void setOutdoorSurface(String[] outdoorSurface) {
		this.outdoorSurface = outdoorSurface;
	}
	
	
	@Override
	public String toString() {
		return "CreateTennisCourtForm [tennisCenterName=" + tennisCenterName + ", numberOfOutdoorCourts="
				+ Arrays.asList(numberOfOutdoorCourts != null ? numberOfOutdoorCourts : new String[]{}) + ", numberOfIndoorCourts="
				+ Arrays.asList(numberOfIndoorCourts != null ? numberOfIndoorCourts : new String[]{}) + ", indoorSurface=" + Arrays.asList(indoorSurface != null ? indoorSurface : new String[]{})
				+ ", outdoorSurface=" + Arrays.asList(outdoorSurface != null ? outdoorSurface : new String[]{}) + ", phoneNumber="
				+ phoneNumber + ", website=" + website + ", latitude="
				+ latitude + ", longitude=" + longitude + ", streetNumber="
				+ streetNumber + ", route=" + route + ", routeShortName="
				+ routeShortName + ", locality=" + locality
				+ ", localityShortName=" + localityShortName + ", sublocality="
				+ sublocality + ", sublocalityShortName="
				+ sublocalityShortName + ", administrativeAreaLevel2="
				+ administrativeAreaLevel2
				+ ", administrativeAreaLevel2ShortName="
				+ administrativeAreaLevel2ShortName
				+ ", administrativeAreaLevel1=" + administrativeAreaLevel1
				+ ", administrativeAreaLevel1ShortName="
				+ administrativeAreaLevel1ShortName + ", country=" + country
				+ ", countryShortName=" + countryShortName + ", postalCode="
				+ postalCode + ", addressTypes=" + addressTypes + ", logger="
				+ logger + "]";
	}
		
}
