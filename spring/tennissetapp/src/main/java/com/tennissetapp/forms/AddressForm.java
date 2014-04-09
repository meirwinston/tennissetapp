package com.tennissetapp.forms;

import java.util.Arrays;

import com.tennissetapp.args.AddressArgs;
import com.tennissetapp.persistence.entities.Address;

public class AddressForm extends AbstractForm{
	protected String latitude;
	protected String longitude;
	protected String streetNumber;
	protected String route;
	protected String routeShortName;
	protected String locality;
	protected String localityShortName;
	protected String sublocality;
	protected String sublocalityShortName;
	protected String neighborhood;
	protected String neighborhoodShortName;
	protected String political;
	protected String politicalShortName;
	protected String administrativeAreaLevel2;
	protected String administrativeAreaLevel2ShortName;
	protected String administrativeAreaLevel1;
	protected String administrativeAreaLevel1ShortName;
	protected String country;
	protected String countryShortName;
	protected String postalCode;
	protected String[] addressTypes;
	
	public AddressForm(){}
	public AddressForm(Address d){
		if(d == null) return;
		
		latitude = d.getLatitude().toString();
		longitude = d.getLongitude().toString();
		streetNumber = d.getStreetNumber();
		route = d.getRoute();
		routeShortName = d.getRouteShortName();
		locality = d.getLocality();
		localityShortName = d.getLocalityShortName();
		political = d.getPolitical();
		politicalShortName = d.getPoliticalShortName();
		neighborhood = d.getNeighborhood();
		neighborhoodShortName = d.getNeighborhoodShortName();
		administrativeAreaLevel2 = d.getAdministrativeAreaLevel2();
		administrativeAreaLevel2ShortName = d.getAdministrativeAreaLevel2ShortName();
		administrativeAreaLevel1 = d.getAdministrativeAreaLevel1();
		administrativeAreaLevel1ShortName = d.getAdministrativeAreaLevel1ShortName();
		country = d.getCountry();
		countryShortName = d.getCountryShortName();
		postalCode = d.getPostalCode();
	}
	
	public void initArgs(AddressArgs args){
		args.latitude = Double.valueOf(this.latitude);
		args.longitude = Double.valueOf(this.longitude);
		
		args.administrativeAreaLevel1 = this.administrativeAreaLevel1;
		args.administrativeAreaLevel1ShortName = this.administrativeAreaLevel1ShortName;
		args.administrativeAreaLevel2 = this.administrativeAreaLevel2;
		args.administrativeAreaLevel2ShortName = this.administrativeAreaLevel2ShortName;
		args.country = this.country;
		args.countryShortName = this.countryShortName;
		args.locality = this.locality;
		args.localityShortName = this.localityShortName;
		args.postalCode = this.postalCode;
		args.route = this.route;
		args.routeShortName = this.routeShortName;
		args.streetNumber = this.streetNumber;
		args.sublocality = this.sublocality;
		args.sublocalityShortName = this.sublocalityShortName;
		args.political = this.political;
		args.politicalShortName = this.politicalShortName;
		args.neighborhood = this.neighborhood;
		args.neighborhoodShortName = this.neighborhoodShortName;
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
	public String getStreetNumber() {
		return streetNumber;
	}
	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}
	public String getRoute() {
		return route;
	}
	public void setRoute(String route) {
		this.route = route;
	}
	public String getRouteShortName() {
		return routeShortName;
	}
	public void setRouteShortName(String routeShortName) {
		this.routeShortName = routeShortName;
	}
	public String getLocality() {
		return locality;
	}
	public void setLocality(String locality) {
		this.locality = locality;
	}
	public String getLocalityShortName() {
		return localityShortName;
	}
	public void setLocalityShortName(String localityShortName) {
		this.localityShortName = localityShortName;
	}
	public String getAdministrativeAreaLevel2() {
		return administrativeAreaLevel2;
	}
	public void setAdministrativeAreaLevel2(String administrativeAreaLevel2) {
		this.administrativeAreaLevel2 = administrativeAreaLevel2;
	}
	public String getAdministrativeAreaLevel2ShortName() {
		return administrativeAreaLevel2ShortName;
	}
	public void setAdministrativeAreaLevel2ShortName(
			String administrativeAreaLevel2ShortName) {
		this.administrativeAreaLevel2ShortName = administrativeAreaLevel2ShortName;
	}
	public String getAdministrativeAreaLevel1() {
		return administrativeAreaLevel1;
	}
	public void setAdministrativeAreaLevel1(String administrativeAreaLevel1) {
		this.administrativeAreaLevel1 = administrativeAreaLevel1;
	}
	public String getAdministrativeAreaLevel1ShortName() {
		return administrativeAreaLevel1ShortName;
	}
	public void setAdministrativeAreaLevel1ShortName(
			String administrativeAreaLevel1ShortName) {
		this.administrativeAreaLevel1ShortName = administrativeAreaLevel1ShortName;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCountryShortName() {
		return countryShortName;
	}
	public void setCountryShortName(String countryShortName) {
		this.countryShortName = countryShortName;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	
	
	public String[] getAddressTypes() {
		return addressTypes;
	}
	public void setAddressTypes(String[] addressTypes) {
		this.addressTypes = addressTypes;
	}
	
	public String getSublocality() {
		return sublocality;
	}
	public void setSublocality(String sublocality) {
		this.sublocality = sublocality;
	}
	public String getSublocalityShortName() {
		return sublocalityShortName;
	}
	public void setSublocalityShortName(String sublocalityShortName) {
		this.sublocalityShortName = sublocalityShortName;
	}
	
	public String getNeighborhood() {
		return neighborhood;
	}
	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}
	public String getNeighborhoodShortName() {
		return neighborhoodShortName;
	}
	public void setNeighborhoodShortName(String neighborhoodShortName) {
		this.neighborhoodShortName = neighborhoodShortName;
	}
	public String getPolitical() {
		return political;
	}
	public void setPolitical(String political) {
		this.political = political;
	}
	public String getPoliticalShortName() {
		return politicalShortName;
	}
	public void setPoliticalShortName(String politicalShortName) {
		this.politicalShortName = politicalShortName;
	}
	@Override
	public String toString() {
		return "AddressForm [latitude=" + latitude + ", longitude=" + longitude
				+ ", streetNumber=" + streetNumber + ", route=" + route
				+ ", routeShortName=" + routeShortName + ", locality="
				+ locality + ", localityShortName=" + localityShortName
				+ ", sublocality=" + sublocality + ", sublocalityShortName="
				+ sublocalityShortName + ", neighborhood=" + neighborhood
				+ ", neighborhoodShortName=" + neighborhoodShortName
				+ ", political=" + political + ", politicalShortName="
				+ politicalShortName + ", administrativeAreaLevel2="
				+ administrativeAreaLevel2
				+ ", administrativeAreaLevel2ShortName="
				+ administrativeAreaLevel2ShortName
				+ ", administrativeAreaLevel1=" + administrativeAreaLevel1
				+ ", administrativeAreaLevel1ShortName="
				+ administrativeAreaLevel1ShortName + ", country=" + country
				+ ", countryShortName=" + countryShortName + ", postalCode="
				+ postalCode + ", addressTypes="
				+ Arrays.toString(addressTypes) + "]";
	}
	
}
