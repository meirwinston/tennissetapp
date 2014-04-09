package com.tennissetapp.persistence.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;

@NamedNativeQueries({
	@NamedNativeQuery(
		name="CourtSelect.count",
		query="CALL `countTennisCenters`(:latitude, :longitude, :maxDistance)"
	),
	@NamedNativeQuery(
		name="CourtSelect.scroll",
		query="CALL `nearbyTennisCenters`(:latitude, :longitude, :maxDistance, :firstResult, :maxResults)",
		resultClass=CourtSelect.class
	)
})
@Entity
public class CourtSelect {
	@Id
	protected Long tennisCenterId;
	
	protected String imageFile;
	protected String name;
	protected double distance;
	protected String locality;
	protected String route;
	protected String phoneNumber;
	protected String administrativeAreaLevel1;
	protected String postalCode;
	protected String website;
	protected double latitude,longitude;
	
	public Long getTennisCenterId() {
		return tennisCenterId;
	}
	public void setTennisCenterId(Long tennisCenterId) {
		this.tennisCenterId = tennisCenterId;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	public String getLocality() {
		return locality;
	}
	public void setLocality(String locality) {
		this.locality = locality;
	}
	
	public String getRoute() {
		return route;
	}
	public void setRoute(String route) {
		this.route = route;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public String getAdministrativeAreaLevel1() {
		return administrativeAreaLevel1;
	}
	public void setAdministrativeAreaLevel1(String administrativeAreaLevel1) {
		this.administrativeAreaLevel1 = administrativeAreaLevel1;
	}
	
	
	public String getImageFile() {
		return imageFile;
	}
	public void setImageFile(String imageFile) {
		this.imageFile = imageFile;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	
	
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	@Override
	public String toString() {
		return "CourtSelect [tennisCenterId=" + tennisCenterId + ", imageFile="
				+ imageFile + ", name=" + name + ", distance=" + distance
				+ ", locality=" + locality + ", route=" + route
				+ ", phoneNumber=" + phoneNumber
				+ ", administrativeAreaLevel1=" + administrativeAreaLevel1
				+ ", postalCode=" + postalCode + ", website=" + website
				+ ", latitude=" + latitude + ", longitude=" + longitude + "]";
	}
}
