package com.tennissetapp.args;

import java.util.List;

public class AddressArgs implements Arguments{
	public Double latitude;
	public Double longitude;
	public String streetNumber;
	public String route;
	public String routeShortName;
	public String locality;
	public String localityShortName;
	public String sublocality;
	public String sublocalityShortName;
	public String neighborhood;
	public String neighborhoodShortName;
	public String political;
	public String politicalShortName;
	public String administrativeAreaLevel2;
	public String administrativeAreaLevel2ShortName;
	public String administrativeAreaLevel1;
	public String administrativeAreaLevel1ShortName;
	public String country;
	public String countryShortName;
	public String postalCode;
	public List<String> addressTypes;
	@Override
	public String toString() {
		return "AddressArgs [latitude=" + latitude + ", longitude=" + longitude
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
				+ postalCode + ", addressTypes=" + addressTypes + "]";
	}
}
