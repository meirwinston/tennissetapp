package com.tennissetapp.persistence.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;


@NamedNativeQueries({
//	@NamedNativeQuery(
//		name="TeacherSelect.search",
//		query="CALL searchMates(:latitude,:longitude,:distance," +
//				":playSingles,:playDoubles,:playPoints,:playFullMatch,:playHittingAround," +
//				":levelOfPlayMin, :levelOfPlayMax, " +
//				":firstResult, :maxResults)",
//		resultClass=MateSelect.class
//	)
//	,
//	@NamedNativeQuery(
//		name="TeacherSelect.countMatesByNameOrEmail",
//		query="CALL `countMatesByNameOrEmail`(:nameOrEmail)",
//		resultClass=CountEntity.class
//	)
//	,
//	@NamedNativeQuery(
//		name="TeacherSelect.searchByNameOrEmail",
//		query="CALL `searchMatesByNameOrEmail`(:nameOrEmail,:firstResult,:maxResults)",
//		resultClass=MateSelect.class
//	),
	@NamedNativeQuery(
		name="TeacherSelect.nearby",
		query="CALL `tennissetapp`.`nearbyTennisTeachers`(:latitude,:longitude,:maxDistance,:firstResult, :maxResults)",
		resultClass=TeacherSelect.class
	),
	@NamedNativeQuery(
		name="TeacherSelect.count",
		query="CALL `tennissetapp`.`countNearbyTennisTeachers`(:latitude,:longitude,:maxDistance)"
	),
	@NamedNativeQuery(
		name="TeacherSelect.countByNameOrEmail",
		query="CALL `countTeachersByNameOrEmail`(:nameOrEmail)"
	)
	,
	@NamedNativeQuery(
		name="TeacherSelect.searchByNameOrEmail",
		query="CALL `searchTeachersByNameOrEmail`(:nameOrEmail,:firstResult,:maxResults)",
		resultClass=TeacherSelect.class
	),
})
@Entity
public class TeacherSelect {
	@Id
	protected Long userAccountId;
	protected String firstName;
	protected String lastName;
	protected Double latitude, longitude, distance;
	protected String route,locality,administrativeAreaLevel1,administrativeAreaLevel2;
	protected Boolean certified;
	protected Float yearsOfExperience;
	protected Boolean usptaCertified,usptrCertified;
	protected String certifyingOrganization;
	protected Boolean specialtyAdults,specialtyJuniors,specialtyTurnaments;
	protected String currency;
	protected Float clinicRates;
	protected Boolean availableWeekendMorning,availableWeekendAfternoon,availableWeekendEvening,availableWeekdayMorning,availableWeekdayAfternoon,availableWeekdayEvening;
	protected Date createdOn,modifiedOn;
	protected String profileImage;
	protected Long profileImageFileId;
	protected String club;
	
	public Long getUserAccountId() {
		return userAccountId;
	}
	public void setUserAccountId(Long userAccountId) {
		this.userAccountId = userAccountId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
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
	public String getRoute() {
		return route;
	}
	public void setRoute(String route) {
		this.route = route;
	}
	public String getLocality() {
		return locality;
	}
	public void setLocality(String locality) {
		this.locality = locality;
	}
	public String getAdministrativeAreaLevel1() {
		return administrativeAreaLevel1;
	}
	public void setAdministrativeAreaLevel1(String administrativeAreaLevel1) {
		this.administrativeAreaLevel1 = administrativeAreaLevel1;
	}
	public String getAdministrativeAreaLevel2() {
		return administrativeAreaLevel2;
	}
	public void setAdministrativeAreaLevel2(String administrativeAreaLevel2) {
		this.administrativeAreaLevel2 = administrativeAreaLevel2;
	}
	public Boolean getCertified() {
		return certified;
	}
	public void setCertified(Boolean certified) {
		this.certified = certified;
	}
	public Float getYearsOfExperience() {
		return yearsOfExperience;
	}
	public void setYearsOfExperience(Float yearsOfExperience) {
		this.yearsOfExperience = yearsOfExperience;
	}
	public Boolean getUsptaCertified() {
		return usptaCertified;
	}
	public void setUsptaCertified(Boolean usptaCertified) {
		this.usptaCertified = usptaCertified;
	}
	public Boolean getUsptrCertified() {
		return usptrCertified;
	}
	public void setUsptrCertified(Boolean usptrCertified) {
		this.usptrCertified = usptrCertified;
	}
	public String getCertifyingOrganization() {
		return certifyingOrganization;
	}
	public void setCertifyingOrganization(String certifyingOrganization) {
		this.certifyingOrganization = certifyingOrganization;
	}
	public Boolean getSpecialtyAdults() {
		return specialtyAdults;
	}
	public void setSpecialtyAdults(Boolean specialtyAdults) {
		this.specialtyAdults = specialtyAdults;
	}
	public Boolean getSpecialtyJuniors() {
		return specialtyJuniors;
	}
	public void setSpecialtyJuniors(Boolean specialtyJuniors) {
		this.specialtyJuniors = specialtyJuniors;
	}
	public Boolean getSpecialtyTurnaments() {
		return specialtyTurnaments;
	}
	public void setSpecialtyTurnaments(Boolean specialtyTurnaments) {
		this.specialtyTurnaments = specialtyTurnaments;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public Float getClinicRates() {
		return clinicRates;
	}
	public void setClinicRates(Float clinicRates) {
		this.clinicRates = clinicRates;
	}
	public Boolean getAvailableWeekendMorning() {
		return availableWeekendMorning;
	}
	public void setAvailableWeekendMorning(Boolean availableWeekendMorning) {
		this.availableWeekendMorning = availableWeekendMorning;
	}
	public Boolean getAvailableWeekendAfternoon() {
		return availableWeekendAfternoon;
	}
	public void setAvailableWeekendAfternoon(Boolean availableWeekendAfternoon) {
		this.availableWeekendAfternoon = availableWeekendAfternoon;
	}
	public Boolean getAvailableWeekendEvening() {
		return availableWeekendEvening;
	}
	public void setAvailableWeekendEvening(Boolean availableWeekendEvening) {
		this.availableWeekendEvening = availableWeekendEvening;
	}
	public Boolean getAvailableWeekdayMorning() {
		return availableWeekdayMorning;
	}
	public void setAvailableWeekdayMorning(Boolean availableWeekdayMorning) {
		this.availableWeekdayMorning = availableWeekdayMorning;
	}
	public Boolean getAvailableWeekdayAfternoon() {
		return availableWeekdayAfternoon;
	}
	public void setAvailableWeekdayAfternoon(Boolean availableWeekdayAfternoon) {
		this.availableWeekdayAfternoon = availableWeekdayAfternoon;
	}
	public Boolean getAvailableWeekdayEvening() {
		return availableWeekdayEvening;
	}
	public void setAvailableWeekdayEvening(Boolean availableWeekdayEvening) {
		this.availableWeekdayEvening = availableWeekdayEvening;
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
	public String getProfileImage() {
		return profileImage;
	}
	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}
	public String getClub() {
		return club;
	}
	public void setClub(String club) {
		this.club = club;
	}
	
	
	public Long getProfileImageFileId() {
		return profileImageFileId;
	}
	public void setProfileImageFileId(Long profileImageFileId) {
		this.profileImageFileId = profileImageFileId;
	}
	
	public Double getDistance() {
		return distance;
	}
	public void setDistance(Double distance) {
		this.distance = distance;
	}
	@Override
	public String toString() {
		return "TeacherSelect [userAccountId=" + userAccountId + ", firstName="
				+ firstName + ", lastName=" + lastName + ", latitude="
				+ latitude + ", longitude=" + longitude + ", route=" + route
				+ ", locality=" + locality + ", administrativeAreaLevel1="
				+ administrativeAreaLevel1 + ", administrativeAreaLevel2="
				+ administrativeAreaLevel2 + ", certified=" + certified
				+ ", yearsOfExperience=" + yearsOfExperience
				+ ", usptaCertified=" + usptaCertified + ", usptrCertified="
				+ usptrCertified + ", certifyingOrganization="
				+ certifyingOrganization + ", specialtyAdults="
				+ specialtyAdults + ", specialtyJuniors=" + specialtyJuniors
				+ ", specialtyTurnaments=" + specialtyTurnaments
				+ ", currency=" + currency + ", clinicRates=" + clinicRates
				+ ", availableWeekendMorning=" + availableWeekendMorning
				+ ", availableWeekendAfternoon=" + availableWeekendAfternoon
				+ ", availableWeekendEvening=" + availableWeekendEvening
				+ ", availableWeekdayMorning=" + availableWeekdayMorning
				+ ", availableWeekdayAfternoon=" + availableWeekdayAfternoon
				+ ", availableWeekdayEvening=" + availableWeekdayEvening
				+ ", createdOn=" + createdOn + ", modifiedOn=" + modifiedOn
				+ ", profileImage=" + profileImage + ", profileImageFileId="
				+ profileImageFileId + ", club=" + club + "]";
	}
}
