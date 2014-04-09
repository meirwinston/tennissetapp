package com.tennissetapp.persistence.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.tennissetapp.json.MatchMemberSerializer;

@NamedQueries({
	@NamedQuery(
		name="MatchMember.countById",
		query="SELECT COUNT(m) FROM MatchMember AS m WHERE m.matchId = :matchId AND m.userAccountId = :userAccountId"
	),
	@NamedQuery(
		name="MatchMember.selectByMatchId",
		query="SELECT m FROM MatchMember AS m WHERE m.matchId = :matchId ORDER BY m.joinedOn DESC"
	),
	@NamedQuery(
		name="MatchMember.selectByUserAccountId",
		query="SELECT m FROM MatchMember AS m WHERE m.userAccountId = :userAccountId ORDER BY m.joinedOn DESC"
	),
	@NamedQuery(
		name="MatchMember.countByUserAccountId",
		query="SELECT COUNT(m) FROM MatchMember AS m WHERE m.userAccountId = :userAccountId"
	)
})
@JsonSerialize(using=MatchMemberSerializer.class)
@Table(name="match_members",schema="tennissetapp")
@Entity
@IdClass(MatchMember.Key.class)
@Access(AccessType.FIELD)
public class MatchMember implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	protected Long userAccountId;
	
	@Id
	protected Long matchId;
	
	protected Date joinedOn;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="userAccountId",referencedColumnName="userAccountId",insertable=false,updatable=false)
	protected UserAccount userAccount;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="matchId",referencedColumnName="matchId",insertable=false,updatable=false)
	protected Match match;
	
	public Long getUserAccountId() {
		return userAccountId;
	}
	public void setUserAccountId(Long userAccountId) {
		this.userAccountId = userAccountId;
	}
	public Long getMatchId() {
		return matchId;
	}
	public void setMatchId(Long matchId) {
		this.matchId = matchId;
	}
	public Date getJoinedOn() {
		return joinedOn;
	}
	public void setJoinedOn(Date joinedOn) {
		this.joinedOn = joinedOn;
	}
	public UserAccount getUserAccount() {
		return userAccount;
	}
	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}
	public Match getMatch() {
		return match;
	}
	public void setMatch(Match match) {
		this.match = match;
	}
	
	@Override
	public String toString() {
		return "MatchMember [userAccountId=" + userAccountId + ", matchId="
				+ matchId + ", joinedOn=" + joinedOn + ", userAccount="
				+ userAccount + ", match=" + match + "]";
	}
	
	public static class Key implements Serializable{
		private static final long serialVersionUID = 1L;

		@Id
		protected Long userAccountId;
		
		@Id
		protected Long matchId;
		
		public Key(){
		}
		
		public Key(Long matchId, Long userAccountId){
			this.userAccountId = userAccountId;
			this.matchId = matchId;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result
					+ ((matchId == null) ? 0 : matchId.hashCode());
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
			if (matchId == null) {
				if (other.matchId != null)
					return false;
			} else if (!matchId.equals(other.matchId))
				return false;
			if (userAccountId == null) {
				if (other.userAccountId != null)
					return false;
			} else if (!userAccountId.equals(other.userAccountId))
				return false;
			return true;
		}
	}

}
