package com.tennissetapp.persistence.entities;

import java.util.Date;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@NamedQueries({
	@NamedQuery( 
		name="TennisTeacherProfile.findProfileImageFile",
		query="select p.profileImageFile from TennisTeacherProfile as p  " +
		"WHERE p.userAccountId = :userAccountId"
	),
	@NamedQuery( 
		name="TennisTeacherProfile.updateProfileImageFile",
		query="UPDATE TennisTeacherProfile AS ua " +
		"SET ua.profileImageFileId = :profileImageFileId " +
		"WHERE ua.userAccountId = :userAccountId"
	)
})
@Table(name="teacher_profiles",schema="tennissetapp")
@Entity
@Access(AccessType.FIELD)
public class TennisTeacherProfile{
	@Id
	protected Long userAccountId;
	protected Date createdOn;
	protected Date modifiedOn;
	protected Boolean certified;
	protected Float yearsOfExperience;
	protected Boolean usptaCertified;
	protected Boolean usptrCertified;
	protected String certifyingOrganization;
	protected Boolean specialtyAdults;
	protected Boolean specialtyJuniors;
	protected Boolean specialtyTurnaments;
	protected String currency;
	protected boolean availableWeekendMorning;
	protected boolean availableWeekendAfternoon;
	protected boolean availableWeekendEvening;
	protected boolean availableWeekdayMorning;
	protected boolean availableWeekdayAfternoon;
	protected boolean availableWeekdayEvening;
	protected String club;
	protected Float clinicRates;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="userAccountId", referencedColumnName="userAccountId",updatable=false,insertable=false)
	protected UserAccount userAccount;
	
	protected Long profileImageFileId;
	
	@JoinColumn(name="profileImageFileId",referencedColumnName="imageFileId", updatable=false, insertable=false)
	@OneToOne(fetch=FetchType.LAZY)
	protected ImageFile profileImageFile;
	
	public Long getUserAccountId() {
		return userAccountId;
	}
	public void setUserAccountId(Long userAccountId) {
		this.userAccountId = userAccountId;
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
	public boolean isAvailableWeekendMorning() {
		return availableWeekendMorning;
	}
	public void setAvailableWeekendMorning(boolean availableWeekendMorning) {
		this.availableWeekendMorning = availableWeekendMorning;
	}
	public boolean isAvailableWeekendAfternoon() {
		return availableWeekendAfternoon;
	}
	public void setAvailableWeekendAfternoon(boolean availableWeekendAfternoon) {
		this.availableWeekendAfternoon = availableWeekendAfternoon;
	}
	public boolean isAvailableWeekendEvening() {
		return availableWeekendEvening;
	}
	public void setAvailableWeekendEvening(boolean availableWeekendEvening) {
		this.availableWeekendEvening = availableWeekendEvening;
	}
	public boolean isAvailableWeekdayMorning() {
		return availableWeekdayMorning;
	}
	public void setAvailableWeekdayMorning(boolean availableWeekdayMorning) {
		this.availableWeekdayMorning = availableWeekdayMorning;
	}
	public boolean isAvailableWeekdayAfternoon() {
		return availableWeekdayAfternoon;
	}
	public void setAvailableWeekdayAfternoon(boolean availableWeekdayAfternoon) {
		this.availableWeekdayAfternoon = availableWeekdayAfternoon;
	}
	public boolean isAvailableWeekdayEvening() {
		return availableWeekdayEvening;
	}
	public void setAvailableWeekdayEvening(boolean availableWeekdayEvening) {
		this.availableWeekdayEvening = availableWeekdayEvening;
	}
	public UserAccount getUserAccount() {
		return userAccount;
	}
	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}
	public ImageFile getProfileImageFile() {
		return profileImageFile;
	}
	public void setProfileImageFile(ImageFile profileImageFile) {
		this.profileImageFile = profileImageFile;
	}
	public Long getProfileImageFileId() {
		return profileImageFileId;
	}
	public void setProfileImageFileId(Long profileImageFileId) {
		this.profileImageFileId = profileImageFileId;
	}
	public String getClub() {
		return club;
	}
	public void setClub(String club) {
		this.club = club;
	}
	
	public Boolean getCertified() {
		return certified;
	}
	public void setCertified(Boolean certified) {
		this.certified = certified;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
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
	
	
	public Float getClinicRates() {
		return clinicRates;
	}
	public void setClinicRates(Float clinicRates) {
		this.clinicRates = clinicRates;
	}
	@Override
	public String toString() {
		return "TennisTeacherProfile [createdOn="
				+ createdOn + ", modifiedOn=" + modifiedOn + ", userAccountId=" + userAccountId
				+ ", certified=" + certified + ", yearsOfExperience="
				+ yearsOfExperience + ", usptaCertified=" + usptaCertified
				+ ", usptrCertified=" + usptrCertified
				+ ", certifyingOrganization=" + certifyingOrganization
				+ ", specialtyAdults=" + specialtyAdults
				+ ", specialtyJuniors=" + specialtyJuniors
				+ ", specialtyTurnaments=" + specialtyTurnaments
				+ ", currency=" + currency + ", availableWeekendMorning="
				+ availableWeekendMorning + ", availableWeekendAfternoon="
				+ availableWeekendAfternoon + ", availableWeekendEvening="
				+ availableWeekendEvening + ", availableWeekdayMorning="
				+ availableWeekdayMorning + ", availableWeekdayAfternoon="
				+ availableWeekdayAfternoon + ", availableWeekdayEvening="
				+ availableWeekdayEvening + ", club=" + club + ", clinicRates="
				+ clinicRates + ", userAccount=" + userAccount
				+ ", profileImageFileId=" + profileImageFileId
				+ ", profileImageFile=" + profileImageFile + "]";
	}

}
