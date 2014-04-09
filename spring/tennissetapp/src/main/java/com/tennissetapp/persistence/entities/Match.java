package com.tennissetapp.persistence.entities;

import java.util.Date;
import java.util.List;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@NamedQueries({
	@NamedQuery(
		name="Match.select",
		query="select a from Match as a"
	),
	@NamedQuery(
		name="Match.count",
		query="SELECT COUNT(m.matchId) " +
			"FROM Match AS m " +
			"INNER JOIN m.tennisCenter AS c " +
			"WHERE SQRT(" +
			"POW(111 * (c.latitude - :latitude), 2) + " +
			"POW(111 * (:longitude - c.longitude) * COS(c.latitude / 57.29577951308232), 2)) < :distance "
	)
})
@Table(name="matches",schema="tennissetapp")
@Entity
@Access(AccessType.FIELD)
public class Match {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	protected Long matchId;
	protected Date startDate;
	protected Date endDate;
	protected Long tennisCenterId;
	
	@ManyToOne
	@JoinColumn(name="tennisCenterId",referencedColumnName="tennisCenterId",insertable=false,updatable=false)
	protected TennisCenter tennisCenter;
	
//	@ManyToOne(fetch=FetchType.LAZY)
//	@JoinColumns({
//		@JoinColumn(name="addressLatitude", referencedColumnName="latitude",insertable=false,updatable=false),
//		@JoinColumn(name="addressLongitude", referencedColumnName="longitude",insertable=false,updatable=false)
//	})
//	protected Address address;
	
	protected Float levelOfPlayMin;
	protected Float levelOfPlayMax;
	protected boolean playSingles;
	protected boolean playDoubles;
	protected boolean playFullMatch;
	protected boolean playPoints;
	protected boolean playHittingAround;
	protected String visibility;
	protected Long orgenizerId;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="orgenizerId",referencedColumnName="userAccountId",insertable=false,updatable=false)
	protected UserAccount orgenizer;
	
	@OneToMany(mappedBy="match",fetch=FetchType.LAZY)
	protected List<MatchMember> matchMembers;

	public List<MatchMember> getMatchMembers() {
		return matchMembers;
	}
	public void setMatchMembers(List<MatchMember> matchMembers) {
		this.matchMembers = matchMembers;
	}
	public Long getMatchId() {
		return matchId;
	}
	public void setMatchId(Long matchId) {
		this.matchId = matchId;
	}
	
	public Long getTennisCenterId() {
		return tennisCenterId;
	}
	public void setTennisCenterId(Long tennisCenterId) {
		this.tennisCenterId = tennisCenterId;
	}
	public TennisCenter getTennisCenter() {
		return tennisCenter;
	}
	public void setTennisCenter(TennisCenter tennisCenter) {
		this.tennisCenter = tennisCenter;
	}
	public Float getLevelOfPlayMin() {
		return levelOfPlayMin;
	}
	public void setLevelOfPlayMin(Float levelOfPlayMin) {
		this.levelOfPlayMin = levelOfPlayMin;
	}
	public Float getLevelOfPlayMax() {
		return levelOfPlayMax;
	}
	public void setLevelOfPlayMax(Float levelOfPlayMax) {
		this.levelOfPlayMax = levelOfPlayMax;
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
	
	public String getVisibility() {
		return visibility;
	}
	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}
	
	public Long getOrgenizerId() {
		return orgenizerId;
	}
	public void setOrgenizerId(Long orgenizerId) {
		this.orgenizerId = orgenizerId;
	}
	
	public UserAccount getOrgenizer() {
		return orgenizer;
	}
	public void setOrgenizer(UserAccount orgenizer) {
		this.orgenizer = orgenizer;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	@Override
	public String toString() {
		return "Match [matchId=" + matchId + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", tennisCenterId=" + tennisCenterId
				+ ", tennisCenter=" + tennisCenter + ", levelOfPlayMin="
				+ levelOfPlayMin + ", levelOfPlayMax=" + levelOfPlayMax
				+ ", playSingles=" + playSingles + ", playDoubles="
				+ playDoubles + ", playFullMatch=" + playFullMatch
				+ ", playPoints=" + playPoints + ", playHittingAround="
				+ playHittingAround + ", visibility=" + visibility
				+ ", orgenizerId=" + orgenizerId + ", orgenizer=" + orgenizer
				+ ", matchMembers=" + matchMembers + "]";
	}
	
}
