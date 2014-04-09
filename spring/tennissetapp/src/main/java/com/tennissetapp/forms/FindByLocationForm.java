package com.tennissetapp.forms;

public class FindByLocationForm extends AbstractForm{
	protected String maxResults;
	protected String firstResult;
	protected String latitude;
	protected String longitude;
	protected String distance;
	
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
	public String getDistance() {
		return distance;
	}
	public void setDistance(String distance) {
		this.distance = distance;
	}
	
	public String getMaxResults() {
		return maxResults;
	}
	public void setMaxResults(String maxResults) {
		this.maxResults = maxResults;
	}
	public String getFirstResult() {
		return firstResult;
	}
	public void setFirstResult(String firstResult) {
		this.firstResult = firstResult;
	}
	@Override
	public String toString() {
		return "FindByLocationForm [maxResults=" + maxResults
				+ ", firstResult=" + firstResult + ", latitude=" + latitude
				+ ", longitude=" + longitude + ", distance=" + distance + "]";
	}
	
	
	
	
}
