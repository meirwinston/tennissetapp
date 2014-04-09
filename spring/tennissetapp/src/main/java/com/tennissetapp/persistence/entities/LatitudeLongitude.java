package com.tennissetapp.persistence.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@NamedQueries({
	@NamedQuery(
	    name="LatitudeLongitude.findByUserEmail",
	    query="SELECT new LatitudeLongitude(ua.address.latitude, ua.address.longitude) " +
	    		"FROM UserAccount AS ua " +
	    		"WHERE ua.email = :email"
	),
	@NamedQuery(
	    name="LatitudeLongitude.findByUserAccountId",
	    query="SELECT new LatitudeLongitude(ua.address.latitude, ua.address.longitude) " +
	    		"FROM UserAccount AS ua " +
	    		"WHERE ua.userAccountId = :userAccountId"
	)
})
@Entity
public class LatitudeLongitude implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	protected Double latitude;
	
	@Id
	protected Double longitude;
	
	public LatitudeLongitude(){}
	public LatitudeLongitude(double lat, double lng){
		this.latitude = lat;
		this.longitude = lng;
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
		LatitudeLongitude other = (LatitudeLongitude) obj;
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
	
	
}
