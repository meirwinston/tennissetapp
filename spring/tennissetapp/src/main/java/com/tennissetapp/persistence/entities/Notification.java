package com.tennissetapp.persistence.entities;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import com.tennissetapp.json.NotificationSerializer;

@NamedQueries({
	@NamedQuery(
		name="Notification.selectByRecipient",
		query="select n from Notification as n where n.recipientId=:userAccountId ORDER BY n.createdOn DESC"
	),
//	@NamedQuery(
//		name="UserPost.countByRecipient",
//		query="select COUNT(p.userPostId) from UserPost as p where p.toUserAccountId=:userAccountId"
//	),
//	@NamedQuery(
//		name="UserPost.selectConversation",
//		query="select up "
//				+ "from UserPost as up "
//				+ "where ((up.userAccountId = :userAccountId AND up.toUserAccountId = :mateAccountId) OR "
//				+ "(up.userAccountId = :mateAccountId AND up.toUserAccountId = :userAccountId)) "
//				+ "ORDER BY up.postedOn DESC"
//	),
//	@NamedQuery(
//		name="UserPost.selectConversationByDate",
//		query="select up "
//				+ "from UserPost as up "
//				+ "where up.postedOn > :startDate "
//				+ "AND ((up.userAccountId = :userAccountId AND up.toUserAccountId = :mateAccountId) OR "
//				+ "(up.userAccountId = :mateAccountId AND up.toUserAccountId = :userAccountId)) "
//				+ "ORDER BY up.postedOn DESC"
//	)
})
@Table(name="notifications",schema="tennissetapp")
@Entity
@Access(AccessType.FIELD)
@JsonSerialize(using=NotificationSerializer.class)
public class Notification {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	protected Long notificationId;

	protected String message;
	protected Date createdOn;
	protected Long recipientId;
	protected Long originatorId;
//	protected Long originatedById; //matchId, UserId
	
	@Enumerated(EnumType.STRING)
	protected Status status;
	
	@Enumerated(EnumType.STRING)
	protected Origin origin;
	
//	@ManyToOne(fetch=FetchType.LAZY)
//	@JoinColumn(name="userAccountId",referencedColumnName="userAccountId",insertable=false,updatable=false)
//	protected UserAccount userAccount;
//	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="recipientId",referencedColumnName="userAccountId",insertable=false,updatable=false)
	protected UserAccount recipientUserAccount;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="recipientId",referencedColumnName="userAccountId",insertable=false,updatable=false)
	protected TennisPlayerProfile recipientProfile;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="originatorId",referencedColumnName="userAccountId",insertable=false,updatable=false)
	protected UserAccount originatorUserAccount;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="originatorId",referencedColumnName="userAccountId",insertable=false,updatable=false)
	protected TennisPlayerProfile originatorProfile;
	
	public enum Origin{
		MATCH
	}
	public enum Status{
		PENDING,
		CONSUMED
	}
	public Long getNotificationId() {
		return notificationId;
	}
	public void setNotificationId(Long notificationId) {
		this.notificationId = notificationId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	public Long getRecipientId() {
		return recipientId;
	}
	public void setRecipientId(Long recipientId) {
		this.recipientId = recipientId;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public UserAccount getRecipientUserAccount() {
		return recipientUserAccount;
	}
	public void setRecipientUserAccount(UserAccount recipientUserAccount) {
		this.recipientUserAccount = recipientUserAccount;
	}
	public TennisPlayerProfile getRecipientProfile() {
		return recipientProfile;
	}
	public void setRecipientProfile(TennisPlayerProfile recipientProfile) {
		this.recipientProfile = recipientProfile;
	}
	
	public Origin getOrigin() {
		return origin;
	}
	public void setOrigin(Origin origin) {
		this.origin = origin;
	}
	
	public Long getOriginatorId() {
		return originatorId;
	}
	public void setOriginatorId(Long originatorId) {
		this.originatorId = originatorId;
	}
	public UserAccount getOriginatorUserAccount() {
		return originatorUserAccount;
	}
	public TennisPlayerProfile getOriginatorProfile() {
		return originatorProfile;
	}
	@Override
	public String toString() {
		return "Notification [notificationId=" + notificationId + ", message="
				+ message + ", createdOn=" + createdOn + ", recipientId="
				+ recipientId + ", originatorId=" + originatorId + ", status="
				+ status + ", origin=" + origin + "]";
	}

}
