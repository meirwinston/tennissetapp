package com.tennissetapp.forms;

public class SearchByNameOrEmailForm extends ScrollForm{

	protected String nameOrEmail;
	
	// location
	protected String latitude;
	protected String longitude;
	public String getNameOrEmail() {
		return nameOrEmail;
	}
	public void setNameOrEmail(String nameOrEmail) {
		this.nameOrEmail = nameOrEmail;
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
	@Override
	public String toString() {
		return "SearchByNameOrEmailForm [nameOrEmail=" + nameOrEmail
				+ ", latitude=" + latitude + ", longitude=" + longitude + "]";
	}
}
