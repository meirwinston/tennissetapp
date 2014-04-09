package com.tennissetapp.persistence.entities;

import java.util.Date;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

//@NamedNativeQueries(
//	@NamedNativeQuery(
//		name="UserAccount.countSearchMates",
//		query="CALL `countSearchMates`(:latitude, :longitude, :distance)"
//	)
//)
@NamedQueries({
	@NamedQuery(
	    name="UserAccount.selectLatLng",
	    query="SELECT ua.address.latitude, ua.address.longitude FROM UserAccount AS ua WHERE ua.userAccountId = :userAccountId"
	),
	@NamedQuery(
	    name="UserAccount.findByActivationTocken",
	    query="SELECT ua FROM UserAccount AS ua WHERE ua.activationToken = :activationToken"
	),
	@NamedQuery(
	    name="UserAccount.selectPassword",
	    query="SELECT ua.password FROM UserAccount AS ua WHERE ua.userAccountId = :userAccountId"
	),
	@NamedQuery( //NEW
	    name="UserAccount.findByEmail",
	    query="SELECT u FROM UserAccount as u WHERE u.email = :email"
	),
	@NamedQuery( //NEW
	    name="UserAccount.findIdByEmail",
	    query="SELECT u.userAccountId FROM UserAccount as u WHERE u.email = :email"
	),
	@NamedQuery( //NEW
	    name="UserAccount.findByUsername",
	    query="SELECT u FROM UserAccount as u WHERE u.username = :username"
	),
	@NamedQuery( //0 or 1
	    name="UserAccount.countUsername",
	    query="SELECT COUNT(u.username) FROM UserAccount as u WHERE u.username = :username"
	),
	@NamedQuery( //NEW
	    name="UserAccount.updateVisibility",
	    query="UPDATE UserAccount AS ua SET ua.visibility = :visibility WHERE ua.userAccountId = :userAccountId"
	),
	@NamedQuery( //NEW
	    name="UserAccount.select",
	    query="SELECT u FROM UserAccount as u"
	),
//	@NamedQuery( 
//		name="UserAccount.findProfileImageFile",
//		query="select a.profileImageFile from UserAccount as a  " +
//		"WHERE a.userAccountId = :userAccountId"
//	),
	@NamedQuery( //^^p
		name="UserAccount.updatePassword",
		query="UPDATE UserAccount AS ua " +
		"SET ua.password = :password " +
		"WHERE ua.activationToken = :token"
	)
//	
	,
	@NamedQuery( 
		name="UserAccount.countNearBy",
		query="SELECT COUNT(u.userAccountId) " +
				"FROM UserAccount AS u " +
				"WHERE SQRT(POW(111 * (u.addressLatitude - :latitude), 2) + POW(111 * (:longitude - u.addressLongitude) * COS(u.addressLatitude / 57.29577951308232), 2)) <= :distance"
	)
//	,
//	@NamedQuery( 
//		name="UserAccount.searchNearBy",
//		query="SELECT SQRT(POW(111 * (u.addressLatitude - :latitude), 2) + POW(111 * (u.addressLongitude - :longitude) * COS(u.addressLatitude / 57.29577951308232), 2)) " +
//				"FROM UserAccount AS u"
////				"WHERE SQRT(POW(111 * (u.addressLatitude - :latitude), 2) + POW(111 * (:longitude - u.addressLongitude) * COS(u.addressLatitude / 57.29577951308232), 2)) <= :distance"
//	)
})
@Table(name="user_accounts",schema="tennissetapp")
@Entity
@Access(AccessType.FIELD)
//@JsonSerialize(using=UserAccountSerializer.class)
public class UserAccount{
	public interface Group{
		String USER = "ROLE_USER";
		String ADMIN = "admin";
	}
	
	public enum Visibility{
		HIDE_EMAIL,
		PUBLIC,
		PRIVATE
	}
	
	public enum Gender{
		MALE,
		FEMALE
	}
	
	public enum AccountStatus {
		ACTIVE,
		REMOVED, /*hardcoded in the DB deletePublication*/
		PENDING_ACTIVATION
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	protected Long userAccountId;
	
	@Column(name="email")
	protected String email;
	
	@Column(name="username")
	protected String username;
	
	@Column(name="password")
	protected String password;
	
	@Column(name="groupName")
	protected String groupName = Group.USER;
	
	@Column(name="createdOn")
	protected Date createdOn;
	
	@Column(name="createdByIP")
	protected String createdByIP;
	
	@Column(name="modifiedByIP")
	protected String modifiedByIP;
	
	@Enumerated(EnumType.STRING)
	@Column(name="visibility")
	protected Visibility visibility;
	
	@Enumerated(EnumType.STRING)
	@Column(name="status")
	protected AccountStatus status;
	
	@Column(name="lastLogIn")
	protected Date lastLogIn;
	
	@Column(name="loginCount")
	protected Integer loginCount;
	
	protected Integer timezone;
	protected String firstName;
	protected String middleName;
	protected String lastName;
	protected Double addressLatitude;
	protected Double addressLongitude;
	
//	protected Long profileImageFileId;
	
//	@JoinColumn(name="profileImageFileId",referencedColumnName="imageFileId", updatable=false, insertable=false)
//	@OneToOne(fetch=FetchType.LAZY)
//	protected ImageFile profileImageFile;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="addressLatitude", referencedColumnName="latitude",insertable=false,updatable=false),
		@JoinColumn(name="addressLongitude", referencedColumnName="longitude",insertable=false,updatable=false)
	})
	protected Address address;
	
	@Enumerated(value=EnumType.STRING)
	protected Gender gender; //0 complete feminine 1 complete masculine
	
	protected String birthDate;
	
	@OneToOne(fetch=FetchType.LAZY,mappedBy="userAccount")
//	@JoinColumn(name="userAccountId",referencedColumnName="userAccountId", insertable=false, updatable=false)
	protected TennisPlayerProfile playerProfile;
	
	@OneToOne(fetch=FetchType.LAZY,mappedBy="userAccount")
//	@JoinColumn(name="userAccountId",referencedColumnName="userAccountId", insertable=false, updatable=false)
	protected TennisTeacherProfile teacherProfile;
	
//	protected String aboutMe;
	
	protected String activationToken;
	protected Date activationTokenExpires;
	
	public UserAccount(){}
	
	public Long getUserAccountId() {
		return userAccountId;
	}

	public void setUserAccountId(Long userAccountId) {
		this.userAccountId = userAccountId;
	}



	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public void setPassword(String password) {
		this.password = password;
	}
	
	public Visibility getVisibility() {
		return visibility;
	}

	public void setVisibility(Visibility visibility) {
		this.visibility = visibility;
	}

	public AccountStatus getStatus() {
		return status;
	}

	public void setStatus(AccountStatus status) {
		this.status = status;
	}
	
	public Date getLastLogIn() {
		return lastLogIn;
	}

	public void setLastLogIn(Date lastLogIn) {
		this.lastLogIn = lastLogIn;
	}

	public Integer getLoginCount() {
		return loginCount;
	}

	public void setLoginCount(Integer loginCount) {
		this.loginCount = loginCount;
	}
	
	public Integer getTimezone() {
		return timezone;
	}
	public void setTimezone(Integer timezone) {
		this.timezone = timezone;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}

	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
//	public String getAboutMe() {
//		return aboutMe;
//	}
//	public void setAboutMe(String aboutMe) {
//		this.aboutMe = aboutMe;
//	}

	public TennisPlayerProfile getPlayerProfile() {
		return playerProfile;
	}

	public void setPlayerProfile(TennisPlayerProfile playerProfile) {
		this.playerProfile = playerProfile;
	}

	public TennisTeacherProfile getTeacherProfile() {
		return teacherProfile;
	}

	public void setTeacherProfile(TennisTeacherProfile teacherProfile) {
		this.teacherProfile = teacherProfile;
	}
	
//	public Long getProfileImageFileId() {
//		return profileImageFileId;
//	}
//
//	public void setProfileImageFileId(Long profileImageFileId) {
//		this.profileImageFileId = profileImageFileId;
//	}
//
//	public ImageFile getProfileImageFile() {
//		return profileImageFile;
//	}
//
//	public void setProfileImageFile(ImageFile profileImageFile) {
//		this.profileImageFile = profileImageFile;
//	}

	public String getActivationToken() {
		return activationToken;
	}

	public void setActivationToken(String activationToken) {
		this.activationToken = activationToken;
	}

	public Date getActivationTokenExpires() {
		return activationTokenExpires;
	}

	public void setActivationTokenExpires(Date activationTokenExpires) {
		this.activationTokenExpires = activationTokenExpires;
	}

	public Double getAddressLatitude() {
		return addressLatitude;
	}

	public void setAddressLatitude(Double addressLatitude) {
		this.addressLatitude = addressLatitude;
	}

	public Double getAddressLongitude() {
		return addressLongitude;
	}

	public void setAddressLongitude(Double addressLongitude) {
		this.addressLongitude = addressLongitude;
	}

//	@Override
//	public String toString() {
//		return "UserAccount [userAccountId=" + userAccountId + ", email="
//				+ email + ", username=" + username + ", password=" + password
//				+ ", groupName=" + groupName + ", createdOn=" + createdOn
//				+ ", createdByIP=" + createdByIP + ", modifiedByIP="
//				+ modifiedByIP + ", visibility=" + visibility + ", status="
//				+ status + ", lastLogIn=" + lastLogIn + ", loginCount="
//				+ loginCount + ", timezone=" + timezone + ", firstName="
//				+ firstName + ", middleName=" + middleName + ", lastName="
//				+ lastName + ", addressLatitude=" + addressLatitude
//				+ ", addressLongitude=" + addressLongitude
//				
//				+ ", address="
//				+ address + ", gender=" + gender + ", birthDate=" + birthDate
//				+ ", playerProfile=" + playerProfile + ", teacherProfile="
//				+ teacherProfile + ", aboutMe=" + aboutMe
//				+ ", activationToken=" + activationToken
//				+ ", activationTokenExpires=" + activationTokenExpires + "]";
//	}

}
