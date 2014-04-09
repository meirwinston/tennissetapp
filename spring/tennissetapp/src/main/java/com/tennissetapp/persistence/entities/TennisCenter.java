package com.tennissetapp.persistence.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@NamedQueries({
	//SQRT(POW(111 * (latitude - :latitude), 2) + POW(111 * (:longitude - longitude) * COS(latitude / 57.29577951308232), 2)))
	//(SQRT(POW(111 * (c.address.latitude - :latitude), 2) + POW(111 * (:longitude - c.address.longitude) * COS(c.address.latitude / 57.29577951308232), 2))))
//	@NamedQuery(
//		name="TennisCourt.findNearby",
//		query="select c.address.locality, c.courtName, c.tennisCourtId " +
//				"from TennisCourt as c join c.address as a " +
//				"where (SQRT(POW(111 * (c.address.latitude - :latitude), 2) + POW(111 * (:longitude - c.address.longitude) * COS(c.address.latitude / 57.29577951308232), 2))) < :distance " +
//				"and :term is not null " + 
//				"and c.courtName like concat('%', :term, '%')"
//	)
	@NamedQuery(
		name="TennisCenter.count",
		query="SELECT COUNT(c.tennisCenterId) " +
				"FROM TennisCenter AS c "
	),
	@NamedQuery(
		name="TennisCenter.findByLatLng",
		query="SELECT t FROM TennisCenter AS t WHERE t.latitude = :latitude AND t.longitude = :longitude"
				
	)
})
@NamedNativeQueries({
////	@NamedNativeQuery(
////		name="TennisCourt.findNearby",
////		query="SELECT route, locality, SQRT(" +
////				"POW(111 * (latitude - :latitude), 2) + " +
////				"POW(111 * (:longitude - longitude) * COS(latitude / 57.29577951308232), 2)) AS distance " +
////				"FROM addresses AS a INNER JOIN TennisCourts AS c ON c.addressId = a.addressId " +
////				"WHERE c.courtName LIKE CONCAT('Ba','%') " +
////				"HAVING distance < :distance " +
////				"ORDER BY distance",
////		resultClass=CourtSelect.class
////	)
//	
//	@NamedNativeQuery(
//		name="TennisCourt.scroll",
//		query="SELECT c.tennisCourtId, c.courtName, a.route, a.locality, SQRT(" +
//				"POW(111 * (a.latitude - :latitude), 2) + " +
//				"POW(111 * (:longitude - a.longitude) * COS(a.latitude / 57.29577951308232), 2)) AS distance " +
//				"FROM addresses AS a INNER JOIN TennisCourts AS c ON c.latitude = a.latitude and c.longitude = a.longitude " +
//				"ORDER BY distance "+
//				"LIMIT :startIndex,:maxResults",
//		resultClass=CourtSelect.class
//	),
//	@NamedNativeQuery(
//		name="TennisCourt.findNearby",
//		query=	"SELECT * " +
//				"FROM TennisCourts AS c " +
//				"HAVING SQRT(" +
//				"POW(111 * (c.latitude - :latitude), 2) + " +
//				"POW(111 * (:longitude - c.longitude) * COS(c.latitude / 57.29577951308232), 2))  < :distance " +
//				"ORDER BY distance",
//		resultClass=TennisCourt.class
//	)
//	,
//	@NamedNativeQuery(
//		name="TennisCourt.findNearbyByTerm",
//		query="SELECT c.tennisCourtId, c.courtName, a.route, a.locality, SQRT(" +
//				"POW(111 * (a.latitude - :latitude), 2) + " +
//				"POW(111 * (:longitude - a.longitude) * COS(a.latitude / 57.29577951308232), 2)) AS distance " +
//				"FROM addresses AS a INNER JOIN TennisCourts AS c ON c.latitude = a.latitude and a.longitude = a.longitude " +
//				"WHERE c.courtName LIKE CONCAT(:term,'%') " +
//				"HAVING distance < :distance " +
//				"ORDER BY distance",
//		resultClass=CourtSelect.class
//	)
//	
////	@NamedNativeQuery(
////		name="TennisCourt.findNearby",
////		query="SELECT a.route, a.locality " +
////				"FROM addresses AS a " +
////				"WHERE :term is not null and :latitude is not null and :longitude is not null ",
////		resultClass=CourtSelect.class
////	)
	
})
@Table(name="tennis_centers",schema="tennissetapp")
@Entity
@Access(AccessType.FIELD)
public class TennisCenter {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	protected Long tennisCenterId;
	
	protected String name;
	protected Double latitude;
	protected Double longitude;
	protected String phoneNumber;
	protected String website;
	protected Date createdOn;
	protected Date modifiedOn;
	protected String imageFileId;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="latitude", referencedColumnName="latitude",updatable=false,insertable=false),
		@JoinColumn(name="longitude", referencedColumnName="longitude",updatable=false,insertable=false)
	})
	protected Address address;
	
	@OneToMany(mappedBy="tennisCenter",fetch=FetchType.LAZY)
	protected List<TennisCourt> tennisCourts;
	
	
	@JoinColumn(name="imageFileId",referencedColumnName="imageFileId",updatable=false,insertable=false)
	@OneToOne(fetch=FetchType.LAZY,optional=true)
	protected ImageFile image;
	
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
	
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
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
	
	public List<TennisCourt> getTennisCourts() {
		return tennisCourts;
	}
	public void setTennisCourts(List<TennisCourt> tennisCourts) {
		this.tennisCourts = tennisCourts;
	}
	
	
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
	
	public ImageFile getImage() {
		return image;
	}
	public void setImage(ImageFile image) {
		this.image = image;
	}
	
	public String getImageFileId() {
		return imageFileId;
	}
	public void setImageFileId(String imageFileId) {
		this.imageFileId = imageFileId;
	}
	@Override
	public String toString() {
		return "TennisCenter [tennisCenterId=" + tennisCenterId + ", name="
				+ name + ", latitude=" + latitude + ", longitude=" + longitude
				+ ", phoneNumber=" + phoneNumber + ", website=" + website
				+ ", createdOn=" + createdOn + ", modifiedOn=" + modifiedOn
				+ ", address=" + address + ", tennisCourts=" + tennisCourts
				+ ", image=" + image + "]";
	}
		
}


