package com.tennissetapp.persistence.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@NamedNativeQueries({
	@NamedNativeQuery(
		name="MateSelect.search",
		query="CALL searchMates(:latitude,:longitude,:distance," +
				":playSingles,:playDoubles,:playPoints,:playFullMatch,:playHittingAround," +
				":levelOfPlayMin, :levelOfPlayMax, " +
				":firstResult, :maxResults)",
		resultClass=MateSelect.class
	)
	,
	@NamedNativeQuery(
		name="MateSelect.countMatesByNameOrEmail",
		query="CALL `countMatesByNameOrEmail`(:nameOrEmail)",
		resultClass=CountEntity.class
	)
	,
	@NamedNativeQuery(
		name="MateSelect.searchMatesByNameOrEmail",
		query="CALL `searchMatesByNameOrEmail`(:nameOrEmail,:firstResult,:maxResults)",
		resultClass=MateSelect.class
	),
//	@NamedQuery(
//		name="Mate.select",
//		query="select m from Mate as m WHERE m.userAccountId = :userAccountId ORDER BY m.createdOn DESC"
//	),
//	@NamedQuery(
//		name="Mate.count",
//		query="select COUNT(m.mateAccountId) from Mate as m WHERE m.userAccountId = :userAccountId"
//	)
})
@Entity
public class MateSelect {
	
	@Id
	protected Long userAccountId;
	protected String firstName;
	protected String lastName;
	protected boolean playSingles;
	protected boolean playDoubles;
	protected boolean playFullMatch;
	protected boolean playPoints;
	protected boolean playHittingAround;
	protected boolean availableWeekendMorning;
	protected boolean availableWeekendAfternoon;
	protected boolean availableWeekendEvening;
	protected boolean availableWeekdayMorning;
	protected boolean availableWeekdayAfternoon;
	protected boolean availableWeekdayEvening;
	protected Float levelOfPlay;
	protected String locality;
	protected String administrativeAreaLevel1ShortName;
	protected String country;
	protected String profilePhoto;
	
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
	public boolean isPlaySingles() {
		return playSingles;
	}
	public void setPlaySingles(boolean playSingles) {
		this.playSingles = playSingles;
	}
	public boolean isPlayDoubles() {
		return playDoubles;
	}
	public void setPlayDoubles(boolean playDoubles) {
		this.playDoubles = playDoubles;
	}
	public boolean isPlayFullMatch() {
		return playFullMatch;
	}
	public void setPlayFullMatch(boolean playFullMatch) {
		this.playFullMatch = playFullMatch;
	}
	public boolean isPlayPoints() {
		return playPoints;
	}
	public void setPlayPoints(boolean playPoints) {
		this.playPoints = playPoints;
	}
	public boolean isPlayHittingAround() {
		return playHittingAround;
	}
	public void setPlayHittingAround(boolean playHittingAround) {
		this.playHittingAround = playHittingAround;
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
	public Float getLevelOfPlay() {
		return levelOfPlay;
	}
	public void setLevelOfPlay(Float levelOfPlay) {
		this.levelOfPlay = levelOfPlay;
	}
	
	public String getLocality() {
		return locality;
	}
	public void setLocality(String locality) {
		this.locality = locality;
	}
	public String getAdministrativeAreaLevel1ShortName() {
		return administrativeAreaLevel1ShortName;
	}
	public void setAdministrativeAreaLevel1ShortName(
			String administrativeAreaLevel1ShortName) {
		this.administrativeAreaLevel1ShortName = administrativeAreaLevel1ShortName;
	}
	public String getProfilePhoto() {
		return profilePhoto;
	}
	public void setProfilePhoto(String profilePhoto) {
		this.profilePhoto = profilePhoto;
	}
	
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	@Override
	public String toString() {
		return "MateSelect [userAccountId=" + userAccountId + ", firstName="
				+ firstName + ", lastName=" + lastName + ", playSingles="
				+ playSingles + ", playDoubles=" + playDoubles
				+ ", playFullMatch=" + playFullMatch + ", playPoints="
				+ playPoints + ", playHittingAround=" + playHittingAround
				+ ", availableWeekendMorning=" + availableWeekendMorning
				+ ", availableWeekendAfternoon=" + availableWeekendAfternoon
				+ ", availableWeekendEvening=" + availableWeekendEvening
				+ ", availableWeekdayMorning=" + availableWeekdayMorning
				+ ", availableWeekdayAfternoon=" + availableWeekdayAfternoon
				+ ", availableWeekdayEvening=" + availableWeekdayEvening
				+ ", levelOfPlay=" + levelOfPlay + ", locality=" + locality
				+ ", administrativeAreaLevel1ShortName="
				+ administrativeAreaLevel1ShortName + ", country=" + country
				+ ", profilePhoto=" + profilePhoto + "]";
	}
}
