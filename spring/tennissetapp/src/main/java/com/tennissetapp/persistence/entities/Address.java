package com.tennissetapp.persistence.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Degrees of latitude are parallel so the distance between each degree remains almost constant 
 * but since degrees of longitude are farthest apart at the equator and converge at the poles, 
 * their distance varies greatly.
 * Each degree of latitude is approximately 69 miles (111 kilometers) apart. 
 * The range varies (due to the earth's slightly ellipsoid shape) from 68.703 miles (110.567 km) 
 * at the equator to 69.407 (111.699 km) at the poles. This is convenient because each 
 * minute (1/60th of a degree) is approximately one mile.
 * A degree of longitude is widest at the equator at 69.172 miles (111.321) and gradually 
 * shrinks to zero at the poles. At 40° north or south the distance between a degree of 
 * longitude is 53 miles (85 km).
 *
 * @author Meir Winston
 *
 */

@NamedQueries({
	@NamedQuery(
		name="Address.select",
		query="select a from Address as a"
	),
	@NamedQuery(
		name="Address.findNearby",
		query="SELECT a " +
				"FROM Address AS a " +
				"WHERE SQRT(POW(111 * (a.latitude - :latitude), 2) + POW(111 * (:longitude - a.longitude) * COS(a.latitude / 57.29577951308232), 2)) < :distance "
	)
})
//@NamedNativeQueries({
//	@NamedNativeQuery(
//		name="Address.findNearby",
//		query="SELECT c.tennisCenterId, c.courtName, a.route, a.locality, SQRT(" +
//				"POW(111 * (a.latitude - :latitude), 2) + " +
//				"POW(111 * (:longitude - a.longitude) * COS(a.latitude / 57.29577951308232), 2)) AS distance " +
//				"FROM Addresses AS a " +
//				"HAVING distance < :distance " +
//				"ORDER BY distance",
//		resultClass=CourtSelect.class
//	),
//})
@Table(name="addresses",schema="tennissetapp")
@IdClass(LatitudeLongitude.class)
@Entity
@Access(AccessType.FIELD)
public class Address implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="latitude",nullable=false)
	protected Double latitude;
	
	@Id
	@Column(name="longitude",nullable=false)
	protected Double longitude;
	
	protected String streetNumber;
	protected String route;
	protected String routeShortName;
	protected String political;
	protected String politicalShortName;
	protected String locality; //home town
	protected String localityShortName;
	protected String sublocality;
	protected String sublocalityShortName;
	protected String neighborhood;
	protected String neighborhoodShortName;
	protected String administrativeAreaLevel2; //county
	protected String administrativeAreaLevel2ShortName;
	protected String administrativeAreaLevel1; //state
	protected String administrativeAreaLevel1ShortName;
	protected String country;
	protected String countryShortName;
	protected String postalCode;
	protected Date createdOn;
	protected Date modifiedOn;
	
	
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	public Date getModifiedOn() {
		return modifiedOn;
	}
	public void setModifiedOn(Date modifiedOn) {
		this.modifiedOn = modifiedOn;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((latitude == null) ? 0 : latitude.hashCode());
		result = prime * result
				+ ((longitude == null) ? 0 : longitude.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		if (latitude == null) {
			if (other.latitude != null)
				return false;
		} else if (!latitude.equals(other.latitude))
			return false;
		if (longitude == null) {
			if (other.longitude != null)
				return false;
		} else if (!longitude.equals(other.longitude))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Address [latitude=" + latitude + ", longitude=" + longitude
				+ ", streetNumber=" + streetNumber + ", route=" + route
				+ ", routeShortName=" + routeShortName + ", political="
				+ political + ", politicalShortName=" + politicalShortName
				+ ", locality=" + locality + ", localityShortName="
				+ localityShortName + ", sublocality=" + sublocality
				+ ", sublocalityShortName=" + sublocalityShortName
				+ ", neighborhood=" + neighborhood + ", neighborhoodShortName="
				+ neighborhoodShortName + ", administrativeAreaLevel2="
				+ administrativeAreaLevel2
				+ ", administrativeAreaLevel2ShortName="
				+ administrativeAreaLevel2ShortName
				+ ", administrativeAreaLevel1=" + administrativeAreaLevel1
				+ ", administrativeAreaLevel1ShortName="
				+ administrativeAreaLevel1ShortName + ", country=" + country
				+ ", countryShortName=" + countryShortName + ", postalCode="
				+ postalCode + ", createdOn=" + createdOn + ", modifiedOn="
				+ modifiedOn + "]";
	}
	
}
