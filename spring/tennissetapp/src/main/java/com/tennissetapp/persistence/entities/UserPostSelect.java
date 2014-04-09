package com.tennissetapp.persistence.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@NamedQueries({
//	@NamedQuery(
//		name="UserPostSelect.selectByRecipient",
//		query="select "
//				+ "p.userPostId AS userPostId, "
//				+ "p.userAccountId AS userAccountId, "
//				+ "p.toUserAccountId AS toUserAccountId, "
//				+ "u.firstName AS userFirstName, "
//				+ "u.lastName AS userLastName, "
//				+ "p.content AS content,"
//				+ "p.postedOn AS postedOn, "
//				+ "i.fileName AS userProfileImage "
//				+ "from UserPost as p join p.userAccount as u join p.playerProfile as pp join pp.playerProfile.profileImageFile as i "
//				+ "where p.toUserAccountId=:userAccountId"
//	)
//	,
//	@NamedQuery(
//		name="UserPostSelect.selectConversation",
//		query="select "
//				+ "p.userPostId AS userPostId, "
//				+ "p.userAccountId AS userAccountId, "
//				+ "p.toUserAccountId AS toUserAccountId, "
//				+ "u.firstName AS userFirstName, "
//				+ "u.lastName AS userLastName, "
//				+ "p.content AS content,"
//				+ "p.postedOn AS postedOn, "
//				+ "i.fileName AS userProfileImage "
//				+ "from UserPost as p join p.userAccount as u join p.playerProfile as pp join pp.playerProfile.profileImageFile as i "
//				+ "where (p.userAccountId = :userAccountId AND p.toUserAccountId = :mateAccountId) OR "
//				+ "(p.userAccountId = :mateAccountId AND p.toUserAccountId = :userAccountId) "
//				+ "ORDER BY p.postedOn ASC"
//	),
//	@NamedQuery(
//		name="UserPostSelect.selectConversationByDate",
//		query="select "
//				+ "p.userPostId AS userPostId, "
//				+ "p.userAccountId AS userAccountId, "
//				+ "p.toUserAccountId AS toUserAccountId, "
//				+ "u.firstName AS userFirstName, "
//				+ "u.lastName AS userLastName, "
//				+ "p.content AS content,"
//				+ "p.postedOn AS postedOn, "
//				+ "i.fileName AS userProfileImage "
//				+ "from UserPost as p join p.userAccount as u join p.playerProfile as pp join pp.playerProfile.profileImageFile as i "
//				+ "where (p.userAccountId = :userAccountId AND p.toUserAccountId = :mateAccountId) OR "
//				+ "(p.userAccountId = :mateAccountId AND p.toUserAccountId = :userAccountId) "
//				+ "AND p.postedOn > :startDate "
//				+ "ORDER BY p.postedOn ASC"
//	)
})
@Entity
public class UserPostSelect {
	@Id
	protected Long userPostId;
	protected Long userAccountId;
	protected Long toUserAccountId;
	protected String content;
	protected Date postedOn;
	protected String userProfileImage;
	protected String status;
	protected String userFirstName;
	protected String userLastName;
	
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getUserProfileImage() {
		return userProfileImage;
	}

	public void setUserProfileImage(String userProfileImage) {
		this.userProfileImage = userProfileImage;
	}

	public String getUserFirstName() {
		return userFirstName;
	}

	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}

	public String getUserLastName() {
		return userLastName;
	}

	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}

	@Override
	public String toString() {
		return "UserPostSelect [userPostId=" + userPostId + ", userAccountId="
				+ userAccountId + ", toUserAccountId=" + toUserAccountId
				+ ", content=" + content + ", postedOn=" + postedOn
				+ ", userProfileImage=" + userProfileImage + ", status="
				+ status + ", userFirstName=" + userFirstName
				+ ", userLastName=" + userLastName + "]";
	}


}
