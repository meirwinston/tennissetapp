package com.tennissetapp.persistence.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import com.tennissetapp.json.MateSerializer;

@JsonSerialize(using=MateSerializer.class)
@NamedQueries({
	@NamedQuery(
		name="Mate.select",
		query="select m from Mate as m WHERE m.userAccountId = :userAccountId OR m.mateAccountId = :userAccountId ORDER BY m.createdOn DESC"
	),
	@NamedQuery(
		name="Mate.count",
		query="select COUNT(m.mateAccountId) from Mate as m WHERE m.userAccountId = :userAccountId"
	),
	@NamedQuery(
		name="Mate.countByUserAndMate",
		query="select COUNT(m.mateAccountId) from Mate as m WHERE ((m.userAccountId = :userAccountId AND m.mateAccountId = :mateAccountId) OR (m.userAccountId = :mateAccountId AND m.mateAccountId = :userAccountId))"
	),
	@NamedQuery(
		name="Mate.findByUserAndMate",
		query="select m from Mate as m WHERE ((m.userAccountId = :userAccountId AND m.mateAccountId = :mateAccountId) OR (m.userAccountId = :mateAccountId AND m.mateAccountId = :userAccountId))"
	)
})
@Table(name="mates",schema="tennissetapp")
@Entity
@Access(AccessType.FIELD)
public class Mate implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	protected Long userAccountId;
	
	@Id
	protected Long mateAccountId;
	protected Date createdOn;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="userAccountId",referencedColumnName="userAccountId",insertable=false,updatable=false)
	protected UserAccount userAccount;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="mateAccountId",referencedColumnName="userAccountId",insertable=false,updatable=false)
	protected UserAccount mateAccount;
	
	public Long getUserAccountId() {
		return userAccountId;
	}
	public void setUserAccountId(Long userAccountId) {
		this.userAccountId = userAccountId;
	}
	public Long getMateAccountId() {
		return mateAccountId;
	}
	public void setMateAccountId(Long mateAccountId) {
		this.mateAccountId = mateAccountId;
	}
	
	public UserAccount getUserAccount() {
		return userAccount;
	}
	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}
	public UserAccount getMateAccount() {
		return mateAccount;
	}
	public void setMateAccount(UserAccount mateAccount) {
		this.mateAccount = mateAccount;
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
		result = prime * result
				+ ((mateAccountId == null) ? 0 : mateAccountId.hashCode());
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
		Mate other = (Mate) obj;
		if (mateAccountId == null) {
			if (other.mateAccountId != null)
				return false;
		} else if (!mateAccountId.equals(other.mateAccountId))
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
		return "Mate [userAccountId=" + userAccountId + ", mateAccountId="
				+ mateAccountId + ", createdOn=" + createdOn + "]";
	}
	
	
}
