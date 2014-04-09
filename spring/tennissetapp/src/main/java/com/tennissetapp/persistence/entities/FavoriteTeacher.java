package com.tennissetapp.persistence.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@NamedQueries({
	@NamedQuery(
		name="FavoriteTeacher.selectByUserAccountId",
		query="select t from FavoriteTeacher as t where t.userAccountId = :userAccountId"
	),
	@NamedQuery(
		name="FavoriteTeacher.deleteByUserAccountId",
		query="delete from FavoriteTeacher as t where t.userAccountId = :userAccountId"
	)
})
@Table(name="favorite_teachers",schema="tennissetapp")
@Entity
@IdClass(FavoriteTeacher.Key.class)
@Access(AccessType.FIELD)
public class FavoriteTeacher implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	protected Long userAccountId;
	
	@Id
	protected Long teacherProfileId;
	
	protected Date createdOn;
	
	public static class Key implements Serializable{
		private static final long serialVersionUID = 1L;

		@Id
		protected Long userAccountId;
		
		@Id
		protected Long teacherProfileId;
		
		public Key(){}
		public Key(Long userAccountId,Long teacherProfileId){
			this.userAccountId = userAccountId;
			this.teacherProfileId = teacherProfileId;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime
					* result
					+ ((teacherProfileId == null) ? 0 : teacherProfileId
							.hashCode());
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
			Key other = (Key) obj;
			if (teacherProfileId == null) {
				if (other.teacherProfileId != null)
					return false;
			} else if (!teacherProfileId.equals(other.teacherProfileId))
				return false;
			if (userAccountId == null) {
				if (other.userAccountId != null)
					return false;
			} else if (!userAccountId.equals(other.userAccountId))
				return false;
			return true;
		}
		
		
	}
	
	@ManyToOne
	@JoinColumn(name="userAccountId",referencedColumnName="userAccountId",insertable=false,updatable=false)
	protected UserAccount userAccount;
	
	@ManyToOne
	@JoinColumn(name="teacherProfileId",referencedColumnName="userAccountId",insertable=false,updatable=false)
	protected TennisTeacherProfile teacherProfile;
	
	public Long getUserAccountId() {
		return userAccountId;
	}

	public void setUserAccountId(Long userAccountId) {
		this.userAccountId = userAccountId;
	}

	public Long getTeacherProfileId() {
		return teacherProfileId;
	}

	public void setTeacherProfileId(Long teacherProfileId) {
		this.teacherProfileId = teacherProfileId;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	
	public UserAccount getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}

	public TennisTeacherProfile getTeacherProfile() {
		return teacherProfile;
	}

	public void setTeacherProfile(TennisTeacherProfile teacherProfile) {
		this.teacherProfile = teacherProfile;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((createdOn == null) ? 0 : createdOn.hashCode());
		result = prime
				* result
				+ ((teacherProfileId == null) ? 0 : teacherProfileId.hashCode());
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
		FavoriteTeacher other = (FavoriteTeacher) obj;
		if (createdOn == null) {
			if (other.createdOn != null)
				return false;
		} else if (!createdOn.equals(other.createdOn))
			return false;
		if (teacherProfileId == null) {
			if (other.teacherProfileId != null)
				return false;
		} else if (!teacherProfileId.equals(other.teacherProfileId))
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
		return "FavoriteTeacher [userAccountId=" + userAccountId
				+ ", teacherProfileId=" + teacherProfileId + ", createdOn="
				+ createdOn + "]";
	}
	
	

}
