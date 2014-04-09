package com.tennissetapp.forms;

public class SearchTennisCourtsForm extends ScrollForm{

	protected String courtName;
	protected String outdoor, indoor;
	protected String hard,concrete,clay,grass,synthetic,carpet;
	
	protected String latitude,longitude,distance;

	public String getCourtName() {
		return courtName;
	}

	public void setCourtName(String courtName) {
		this.courtName = courtName;
	}

	public String getOutdoor() {
		return outdoor;
	}

	public void setOutdoor(String outdoor) {
		this.outdoor = outdoor;
	}

	public String getIndoor() {
		return indoor;
	}

	public void setIndoor(String indoor) {
		this.indoor = indoor;
	}

	public String getHard() {
		return hard;
	}

	public void setHard(String hard) {
		this.hard = hard;
	}

	public String getConcrete() {
		return concrete;
	}

	public void setConcrete(String concrete) {
		this.concrete = concrete;
	}

	public String getClay() {
		return clay;
	}

	public void setClay(String clay) {
		this.clay = clay;
	}

	public String getGrass() {
		return grass;
	}

	public void setGrass(String grass) {
		this.grass = grass;
	}

	public String getSynthetic() {
		return synthetic;
	}

	public void setSynthetic(String synthetic) {
		this.synthetic = synthetic;
	}

	public String getCarpet() {
		return carpet;
	}

	public void setCarpet(String carpet) {
		this.carpet = carpet;
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

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	@Override
	public String toString() {
		return "SearchTennisCourtsForm [courtName=" + courtName + ", outdoor="
				+ outdoor + ", indoor=" + indoor + ", hard=" + hard
				+ ", concrete=" + concrete + ", clay=" + clay + ", grass="
				+ grass + ", synthetic=" + synthetic + ", carpet=" + carpet
				+ ", latitude=" + latitude + ", longitude=" + longitude
				+ ", distance=" + distance + ", maxResults=" + maxResults
				+ ", firstResult=" + firstResult + ", logger=" + logger
				+ ", arguments=" + arguments + "]";
	}
	
	
	
}
