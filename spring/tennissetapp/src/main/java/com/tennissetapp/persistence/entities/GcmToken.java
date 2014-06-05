package com.tennissetapp.persistence.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@NamedQueries({
	@NamedQuery(
		name="GcmToken.selectByUserId",
		query="select a from GcmToken as a where a.userAccountId = :userAccountId"
	),
	@NamedQuery(
		name="GcmToken.find",
		query="select a from GcmToken as a where a.userAccountId = :userAccountId and a.token = :token"
	)
})
@Table(name="gcm_tokens",schema="tennissetapp")
@Entity
@Access(AccessType.FIELD)
public class GcmToken implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	private Long userAccountId;
	
	private Date createdOn;
	
	@Id
	private String token;
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
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
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((token == null) ? 0 : token.hashCode());
		result = prime * result
				+ ((userAccountId == null) ? 0 : userAccountId.hashCode());
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
		GcmToken other = (GcmToken) obj;
		if (token == null) {
			if (other.token != null)
				return false;
		} else if (!token.equals(other.token))
			return false;
		if (userAccountId == null) {
			if (other.userAccountId != null)
				return false;
		} else if (!userAccountId.equals(other.userAccountId))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "GcmToken [userAccountId=" + userAccountId + ", createdOn="
				+ createdOn + ", token=" + token + "]";
	}
	
	
}
