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

import com.tennissetapp.json.UserPostSerializer;

@NamedQueries({
	@NamedQuery(
		name="UserPost.selectByRecipient",
		query="select p from UserPost as p where p.toUserAccountId=:userAccountId ORDER BY p.postedOn DESC"
	),
	@NamedQuery(
		name="UserPost.countByRecipient",
		query="select COUNT(p.userPostId) from UserPost as p where p.toUserAccountId=:userAccountId"
	),
	@NamedQuery(
		name="UserPost.selectConversation",
		query="select up "
				+ "from UserPost as up "
				+ "where ((up.userAccountId = :userAccountId AND up.toUserAccountId = :mateAccountId) OR "
				+ "(up.userAccountId = :mateAccountId AND up.toUserAccountId = :userAccountId)) "
				+ "ORDER BY up.postedOn DESC"
	),
	@NamedQuery(
		name="UserPost.selectConversationByDate",
		query="select up "
				+ "from UserPost as up "
				+ "where up.postedOn > :startDate "
				+ "AND ((up.userAccountId = :userAccountId AND up.toUserAccountId = :mateAccountId) OR "
				+ "(up.userAccountId = :mateAccountId AND up.toUserAccountId = :userAccountId)) "
				+ "ORDER BY up.postedOn DESC"
	)
})
@Table(name="user_posts",schema="tennissetapp")
@Entity
@Access(AccessType.FIELD)
@JsonSerialize(using=UserPostSerializer.class)
public class UserPost {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	protected Long userPostId;
	protected Long userAccountId;
	protected Long toUserAccountId;
	protected String content;
	protected Date postedOn;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="userAccountId",referencedColumnName="userAccountId",insertable=false,updatable=false)
	protected UserAccount userAccount;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="toUserAccountId",referencedColumnName="userAccountId",insertable=false,updatable=false)
	protected UserAccount toUserAccount;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="userAccountId",referencedColumnName="userAccountId",insertable=false,updatable=false)
	protected TennisPlayerProfile playerProfile;
	
	@Enumerated(EnumType.STRING)
	protected Status status;
	
	public enum Status{
		PENDING
	}

	public Long getUserPostId() {
		return userPostId;
	}

	public void setUserPostId(Long userPostId) {
		this.userPostId = userPostId;
	}

	public Long getUserAccountId() {
		return userAccountId;
	}

	public void setUserAccountId(Long userAccountId) {
		this.userAccountId = userAccountId;
	}

	public Long getToUserAccountId() {
		return toUserAccountId;
	}

	public void setToUserAccountId(Long toUserAccountId) {
		this.toUserAccountId = toUserAccountId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getPostedOn() {
		return postedOn;
	}

	public void setPostedOn(Date postedOn) {
		this.postedOn = postedOn;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	public UserAccount getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}

	public UserAccount getToUserAccount() {
		return toUserAccount;
	}

	public void setToUserAccount(UserAccount toUserAccount) {
		this.toUserAccount = toUserAccount;
	}
	

	public TennisPlayerProfile getPlayerProfile() {
		return playerProfile;
	}

	public void setPlayerProfile(TennisPlayerProfile playerProfile) {
		this.playerProfile = playerProfile;
	}

	@Override
	public String toString() {
		return "UserPost [userPostId=" + userPostId + ", userAccountId="
				+ userAccountId + ", toUserAccountId=" + toUserAccountId
				+ ", content=" + content + ", postedOn=" + postedOn
				+ ", status=" + status + "]";
	}
	
	
}
