package com.tennissetapp.persistence.entities;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Transient;

/**
 * games organized by users
 * 
 * @author Meir Winston
 */
@NamedQueries({
	@NamedQuery(
		name="MatchSelect.selectByUserId",
		query="select "
			+ "m.matchId as matchId,"
			+ "m.startDate AS startDate, m.endDate AS endDate, "
			+ "m.orgenizer.firstName as orgenizerFirstName, "
			+ "m.orgenizer.lastName as orgenizerLastName, "
			+ "m.playSingles as playSingles, "
			+ "m.playDoubles as playDoubles, "
			+ "m.playFullMatch as playFullMatch, "
			+ "m.playPoints as playPoints, "
			+ "m.playHittingAround as playHittingAround, "
			+ "m.visibility as visibility, "
			+ "m.tennisCenter.latitude as latitude, "
			+ "m.tennisCenter.longitude as longitude, "
			+ "m.tennisCenter.name as tennisCenterName, "
			+ "m.tennisCenter.address.localityShortName as locality,"
			+ "m.tennisCenter.address.administrativeAreaLevel1ShortName as administrativeAreaLevel1, "
			+ "m.tennisCenter.address.administrativeAreaLevel2ShortName as administrativeAreaLevel2, "
			+ "m.tennisCenter.address.sublocality as sublocality,"
			+ "m.tennisCenter.address.streetNumber as streetNumber,"
			+ "m.tennisCenter.address.route as route,"
			+ "m.tennisCenter.address.postalCode as postalCode "
			+ "from Match as m "
//			+ "where m.orgenizerId = :orgenizerId and m.startDate >= :fromDate"
+ "where m.orgenizerId = :orgenizerId "
			+ "order by m.startDate asc"
	),
	@NamedQuery(
		name="MatchSelect.countByUserId",
		query="select count(m.matchId) "
			+ "from Match as m "
			+ "where m.orgenizerId = :orgenizerId and m.startDate >= :fromDate"
	),
	@NamedQuery(
			name="MatchSelect.selectMembersByUserId",
			query="select "
				+ "m.match.matchId as matchId,"
				+ "m.match.startDate AS startDate, m.match.endDate AS endDate, "
				+ "m.match.orgenizer.firstName as orgenizerFirstName, "
				+ "m.match.orgenizer.lastName as orgenizerLastName, "
				+ "m.match.playSingles as playSingles, "
				+ "m.match.playDoubles as playDoubles, "
				+ "m.match.playFullMatch as playFullMatch, "
				+ "m.match.playPoints as playPoints, "
				+ "m.match.playHittingAround as playHittingAround, "
				+ "m.match.visibility as visibility, "
				+ "m.match.tennisCenter.latitude as latitude, "
				+ "m.match.tennisCenter.longitude as longitude, "
				+ "m.match.tennisCenter.name as tennisCenterName, "
				+ "m.match.tennisCenter.address.localityShortName as locality,"
				+ "m.match.tennisCenter.address.administrativeAreaLevel1ShortName as administrativeAreaLevel1, "
				+ "m.match.tennisCenter.address.administrativeAreaLevel2ShortName as administrativeAreaLevel2, "
				+ "m.match.tennisCenter.address.sublocality as sublocality,"
				+ "m.match.tennisCenter.address.streetNumber as streetNumber,"
				+ "m.match.tennisCenter.address.route as route,"
				+ "m.match.tennisCenter.address.postalCode as postalCode "
				+ "from MatchMember as m "
//				+ "where m.userAccountId = :userAccountId and m.match.startDate >= :fromDate"
+ "where m.userAccountId = :userAccountId "
				+ "order by m.match.startDate asc"
		),
		@NamedQuery(
			name="MatchSelect.countMembersByUserId",
			query="select count(m.matchId)"
				+ "from MatchMember as m "
				+ "where m.userAccountId = :userAccountId and m.match.startDate >= :fromDate"
		),
	@NamedQuery(
		name="MatchSelect.find",
			query="SELECT m.matchId AS matchId, m.playSingles AS playSingles, m.playDoubles AS playDoubles, m.playFullMatch AS playFullMatch, m.playPoints AS playPoints, m.playHittingAround AS playHittingAround, " 
			+ "m.startDate AS startDate, m.endDate AS endDate, "
			+ "m.visibility AS visibility, "
			+ "m.levelOfPlayMin AS levelOfPlayMin, m.levelOfPlayMax AS levelOfPlayMax, "
			+ "c.name AS tennisCenterName, "
			+ "i.fileName AS courtImage, "
			+ "u.firstName AS orgenizerFirstName, u.lastName AS orgenizerLastName, "
			+ "a.latitude AS latitude, a.longitude AS longitude, "
			+ "a.administrativeAreaLevel2 AS administrativeAreaLevel2, a.sublocality AS sublocality, a.streetNumber AS streetNumber, a.route AS route, a.postalCode AS postalCode, "
			+ "a.localityShortName AS locality, "
			+ "a.administrativeAreaLevel1ShortName AS administrativeAreaLevel1 "
			+ "FROM Match AS m "
			+ "INNER JOIN m.tennisCenter AS c "
			+ "INNER JOIN m.orgenizer AS u  "
			+ "INNER JOIN c.address AS a "
			+ "LEFT JOIN c.image AS i "
			+ "WHERE m.matchId = :matchId "
		)

})
@NamedNativeQueries({
	@NamedNativeQuery(
		name="MatchSelect.search",
		query="CALL searchMatches(:latitude,:longitude,:distance," +
				":playSingles,:playDoubles,:playPoints,:playFullMatch,:playHittingAround," +
				":levelOfPlayMin,:levelOfPlayMax," +
				":firstResult, :maxResults);",
		resultClass=MatchSelect.class
	)
	,
	@NamedNativeQuery(
		name="MatchSelect.countSearch",
		query="CALL countSearchMatches(:latitude,:longitude,:distance);",
		resultClass=MatchSelect.class
	)

})
@Entity
public class MatchSelect {
	@Id
	protected Long matchId;
	protected String orgenizerFirstName;
	protected String orgenizerLastName;
	
	protected boolean playSingles;
	protected boolean playDoubles;
	protected boolean playFullMatch;
	protected boolean playPoints;
	protected boolean playHittingAround;
	protected String visibility;
//	protected double distance;
	protected double latitude;
	protected double longitude;
	protected String tennisCenterName;
	
	protected String locality;
	protected String administrativeAreaLevel1;
	protected String administrativeAreaLevel2;
	protected String sublocality;
	protected String streetNumber;
	protected String route;
	protected String postalCode;
	
	protected Float levelOfPlayMin;
	protected Float levelOfPlayMax;
	protected Date startDate,endDate;
	protected String courtImage;
	
	@Transient
	protected List<MatchMember> members;
	
	public List<MatchMember> getMembers() {
		return members;
	}

	public void setMembers(List<MatchMember> members) {
		this.members = members;
	}

	public Long getMatchId() {
		return matchId;
	}
	
	public void setMatchId(Object matchId) {
		if(matchId instanceof BigInteger){
			this.matchId = ((BigInteger)matchId).longValue();
		}
		else{
			this.matchId = (Long)matchId;
		}
		
	}
	public String getOrgenizerFirstName() {
		return orgenizerFirstName;
	}
	public void setOrgenizerFirstName(String orgenizerFirstName) {
		this.orgenizerFirstName = orgenizerFirstName;
	}
	public String getOrgenizerLastName() {
		return orgenizerLastName;
	}
	public void setOrgenizerLastName(String orgenizerLastName) {
		this.orgenizerLastName = orgenizerLastName;
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
//	public double getDistance() {
//		return distance;
//	}
//	public void setDistance(double distance) {
//		this.distance = distance;
//	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	
	public String getTennisCenterName() {
		return tennisCenterName;
	}

	public void setTennisCenterName(String tennisCenterName) {
		this.tennisCenterName = tennisCenterName;
	}

	public String getLocality() {
		return locality;
	}

	public void setLocality(String locality) {
		this.locality = locality;
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

	public String getCourtImage() {
		return courtImage;
	}

	public void setCourtImage(String courtImage) {
		this.courtImage = courtImage;
	}
	
	public String getAdministrativeAreaLevel1() {
		return administrativeAreaLevel1;
	}

	public void setAdministrativeAreaLevel1(String administrativeAreaLevel1) {
		this.administrativeAreaLevel1 = administrativeAreaLevel1;
	}

	public String getAdministrativeAreaLevel2() {
		return administrativeAreaLevel2;
	}

	public void setAdministrativeAreaLevel2(String administrativeAreaLevel2) {
		this.administrativeAreaLevel2 = administrativeAreaLevel2;
	}

	public String getSublocality() {
		return sublocality;
	}

	public void setSublocality(String sublocality) {
		this.sublocality = sublocality;
	}

	public String getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}

	public String getRoute() {
		return route;
	}

	public void setRoute(String route) {
		this.route = route;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public void setMatchId(Long matchId) {
		this.matchId = matchId;
	}

	@Override
	public String toString() {
		return "MatchSelect [matchId=" + matchId + ", orgenizerFirstName="
				+ orgenizerFirstName + ", orgenizerLastName="
				+ orgenizerLastName + ", playSingles=" + playSingles
				+ ", playDoubles=" + playDoubles + ", playFullMatch="
				+ playFullMatch + ", playPoints=" + playPoints
				+ ", playHittingAround=" + playHittingAround + ", visibility="
				+ visibility + ", latitude=" + latitude + ", longitude="
				+ longitude + ", tennisCenterName=" + tennisCenterName
				+ ", locality=" + locality + ", administrativeAreaLevel1="
				+ administrativeAreaLevel1 + ", administrativeAreaLevel2="
				+ administrativeAreaLevel2 + ", sublocality=" + sublocality
				+ ", streetNumber=" + streetNumber + ", route=" + route
				+ ", postalCode=" + postalCode + ", levelOfPlayMin="
				+ levelOfPlayMin + ", levelOfPlayMax=" + levelOfPlayMax
				+ ", startDate=" + startDate + ", endDate=" + endDate
				+ ", courtImage=" + courtImage + "]";
	}

}
