package com.tennissetapp.persistence.entities;

import java.util.Date;
import java.util.List;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@NamedQueries({
	@NamedQuery( 
		name="TennisPlayerProfile.findProfileImageFile",
		query="select p.profileImageFile from TennisPlayerProfile as p  " +
		"WHERE p.userAccountId = :userAccountId"
	),
	@NamedQuery( 
		name="TennisPlayerProfile.updateProfileImageFile",
		query="UPDATE TennisPlayerProfile AS ua " +
		"SET ua.profileImageFileId = :profileImageFileId " +
		"WHERE ua.userAccountId = :userAccountId"
	)
})
@Table(name="player_profiles",schema="tennissetapp")
@Entity
@Access(AccessType.FIELD)
//@DiscriminatorValue("TENNIS_PLAYER")
public class TennisPlayerProfile {
		
//	public enum ProfileType {
//		TENNIS_PLAYER,
//		TENNIS_TEACHER
//	}
	
	public enum Hand{
		RIGHT,
		LEFT
	}
	
//	@Id
//	@GeneratedValue(strategy=GenerationType.AUTO)
//	protected Long profileId;
	
	@Id
	protected Long userAccountId;
	
	protected Date createdOn;
	protected String createdByIP;
	protected String modifiedByIP;
	protected String aboutMe;
	
	protected Long profileImageFileId;
	
	@JoinColumn(name="profileImageFileId",referencedColumnName="imageFileId", updatable=false, insertable=false)
	@OneToOne(fetch=FetchType.LAZY)
	protected ImageFile profileImageFile;
	
//	@Enumerated(EnumType.STRING)
//	@Column(updatable=false,insertable=false)
//	protected ProfileType profileType;
	
	protected Float levelOfPlay;
	
	@Enumerated(EnumType.STRING)
	protected Hand hand;
	
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
	
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="userAccountId", referencedColumnName="userAccountId",updatable=false,insertable=false)
	protected UserAccount userAccount;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
		name="playerprofile_tenniscenter",
		joinColumns={
			@JoinColumn(name="userAccountId",referencedColumnName="userAccountId")
		},
		inverseJoinColumns={
			@JoinColumn(name="tennisCenterId",referencedColumnName="tennisCenterId")	
		}
	)
	protected List<TennisCenter> favouriteCourts;
	
	protected Float attendance;
	protected Float punctuality;
	protected Float tennisLevel;
	
	
	public TennisPlayerProfile(){
		
	}
	
	public List<TennisCenter> getFavouriteCourts() {
		return favouriteCourts;
	}

	public void setFavouriteCourts(List<TennisCenter> favouriteCourts) {
		this.favouriteCourts = favouriteCourts;
	}


	public UserAccount getUserAccount() {
		return userAccount;
	}
	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}
	
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
	public String getCreatedByIP() {
		return createdByIP;
	}
	public void setCreatedByIP(String createdByIP) {
		this.createdByIP = createdByIP;
	}
	public String getModifiedByIP() {
		return modifiedByIP;
	}
	public void setModifiedByIP(String modifiedByIP) {
		this.modifiedByIP = modifiedByIP;
	}
	
	public Long getProfileImageFileId() {
		return profileImageFileId;
	}
	public void setProfileImageFileId(Long profileImageFileId) {
		this.profileImageFileId = profileImageFileId;
	}
	public ImageFile getProfileImageFile() {
		return profileImageFile;
	}
	public void setProfileImageFile(ImageFile profileImageFile) {
		this.profileImageFile = profileImageFile;
	}
	public Float getLevelOfPlay() {
		return levelOfPlay;
	}
	public void setLevelOfPlay(Float levelOfPlay) {
		this.levelOfPlay = levelOfPlay;
	}
	public Hand getHand() {
		return hand;
	}
	public void setHand(Hand hand) {
		this.hand = hand;
	}
	public boolean getPlaySingles() {
		return playSingles;
	}
	public void setPlaySingles(boolean playSingles) {
		this.playSingles = playSingles;
	}
	public boolean getPlayDoubles() {
		return playDoubles;
	}
	public void setPlayDoubles(boolean playDoubles) {
		this.playDoubles = playDoubles;
	}
	public boolean getPlayFullMatch() {
		return playFullMatch;
	}
	public void setPlayFullMatch(boolean playFullMatch) {
		this.playFullMatch = playFullMatch;
	}
	public boolean getPlayPoints() {
		return playPoints;
	}
	public void setPlayPoints(boolean playPoints) {
		this.playPoints = playPoints;
	}
	public boolean getPlayHittingAround() {
		return playHittingAround;
	}
	public void setPlayHittingAround(boolean playHittingAround) {
		this.playHittingAround = playHittingAround;
	}
	public boolean getAvailableWeekendMorning() {
		return availableWeekendMorning;
	}
	public void setAvailableWeekendMorning(boolean availableWeekendMorning) {
		this.availableWeekendMorning = availableWeekendMorning;
	}
	public boolean getAvailableWeekendAfternoon() {
		return availableWeekendAfternoon;
	}
	public void setAvailableWeekendAfternoon(boolean availableWeekendAfternoon) {
		this.availableWeekendAfternoon = availableWeekendAfternoon;
	}
	public boolean getAvailableWeekendEvening() {
		return availableWeekendEvening;
	}
	public void setAvailableWeekendEvening(boolean availableWeekendEvening) {
		this.availableWeekendEvening = availableWeekendEvening;
	}
	public boolean getAvailableWeekdayMorning() {
		return availableWeekdayMorning;
	}
	public void setAvailableWeekdayMorning(boolean availableWeekdayMorning) {
		this.availableWeekdayMorning = availableWeekdayMorning;
	}
	public boolean getAvailableWeekdayAfternoon() {
		return availableWeekdayAfternoon;
	}
	public void setAvailableWeekdayAfternoon(boolean availableWeekdayAfternoon) {
		this.availableWeekdayAfternoon = availableWeekdayAfternoon;
	}
	public boolean getAvailableWeekdayEvening() {
		return availableWeekdayEvening;
	}
	public void setAvailableWeekdayEvening(boolean availableWeekdayEvening) {
		this.availableWeekdayEvening = availableWeekdayEvening;
	}

	public Float getAttendance() {
		return attendance;
	}

	public void setAttendance(Float attendance) {
		this.attendance = attendance;
	}

	public Float getPunctuality() {
		return punctuality;
	}

	public void setPunctuality(Float punctuality) {
		this.punctuality = punctuality;
	}

	public Float getTennisLevel() {
		return tennisLevel;
	}

	public void setTennisLevel(Float tennisLevel) {
		this.tennisLevel = tennisLevel;
	}
	
	public String getAboutMe() {
		return aboutMe;
	}

	public void setAboutMe(String aboutMe) {
		this.aboutMe = aboutMe;
	}

	@Override
	public String toString() {
		return "TennisPlayerProfile [userAccountId=" + userAccountId
				+ ", createdOn=" + createdOn + ", createdByIP=" + createdByIP
				+ ", modifiedByIP=" + modifiedByIP + ", aboutMe=" + aboutMe
				+ ", profileImageFileId=" + profileImageFileId
				+ ", profileImageFile=" + profileImageFile + ", levelOfPlay="
				+ levelOfPlay + ", hand=" + hand + ", playSingles="
				+ playSingles + ", playDoubles=" + playDoubles
				+ ", playFullMatch=" + playFullMatch + ", playPoints="
				+ playPoints + ", playHittingAround=" + playHittingAround
				+ ", availableWeekendMorning=" + availableWeekendMorning
				+ ", availableWeekendAfternoon=" + availableWeekendAfternoon
				+ ", availableWeekendEvening=" + availableWeekendEvening
				+ ", availableWeekdayMorning=" + availableWeekdayMorning
				+ ", availableWeekdayAfternoon=" + availableWeekdayAfternoon
				+ ", availableWeekdayEvening=" + availableWeekdayEvening
				+ ", userAccount=" + userAccount + ", favouriteCourts="
				+ favouriteCourts + ", attendance=" + attendance
				+ ", punctuality=" + punctuality + ", tennisLevel="
				+ tennisLevel + "]";
	}
}
