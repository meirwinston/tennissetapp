package com.tennissetapp.persistence.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;

@NamedNativeQueries({
	@NamedNativeQuery(
		name="CalendarEventSelect.selectByUserAccountId",
		query="(SELECT matchId AS eventId, orgenizerId AS initiatedByAccountId, matchId,startDate, endDate, 'MATCH' AS eventType "
			+ "FROM matches "
			+ "WHERE orgenizerId = :userAccountId "
			+ "AND startDate >= :fromDate AND endDate <= :toDate) "
			+ "UNION "
			+ "(SELECT m1.matchId AS eventId, m1.orgenizerId AS initiatedByAccountId, m1.matchId,m1.startDate,m1.endDate, 'MATCH_MEMBER' AS eventType "
			+ "FROM match_members AS m2 "
			+ "INNER JOIN matches AS m1 ON m1.matchId = m2.matchId "
			+ "WHERE m2.userAccountId = :userAccountId "
			+ "AND m1.startDate >= :fromDate AND m1.endDate <= :toDate) "
			+ "ORDER BY startDate",
			resultClass=CalendarEventSelect.class
	),
	@NamedNativeQuery(
		name="CalendarEventSelect.selectNextEvent",
		query="(SELECT matchId AS eventId, orgenizerId AS initiatedByAccountId, matchId,startDate, endDate, 'MATCH' AS eventType "
				+ "FROM matches "
				+ "WHERE orgenizerId = :userAccountId "
				+ "AND startDate >= :fromDate) "
				+ "UNION "
				+ "(SELECT m1.matchId AS eventId, m1.orgenizerId AS initiatedByAccountId, m1.matchId,m1.startDate,m1.endDate, 'MATCH_MEMBER' AS eventType "
				+ "FROM match_members AS m2 "
				+ "INNER JOIN matches AS m1 ON m1.matchId = m2.matchId "
				+ "WHERE m2.userAccountId = :userAccountId "
				+ "AND m1.startDate >= :fromDate) "
				+ "ORDER BY startDate "
				+ "LIMIT 1",
		resultClass=CalendarEventSelect.class
	)
	
})
@Entity
public class CalendarEventSelect implements Serializable{
	private static final long serialVersionUID = 1L;

	public enum Event{
		MATCH,
		MATCH_MEMBER,
		CLINIC
	}
	protected Long eventId;
	
	@Id
	protected Long initiatedByAccountId;
	
	@Id
	protected Date startDate;
	
	@Id
	protected Date endDate;
	
	@Id
	@Enumerated(EnumType.STRING)
	@Column(name="eventType")
	protected Event eventType;

	public Long getEventId() {
		return eventId;
	}

	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}

	public Long getInitiatedByAccountId() {
		return initiatedByAccountId;
	}

	public void setInitiatedByAccountId(Long initiatedByAccountId) {
		this.initiatedByAccountId = initiatedByAccountId;
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

	public Event getEventType() {
		return eventType;
	}

	public void setEventType(Event eventType) {
		this.eventType = eventType;
	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result
				+ ((eventType == null) ? 0 : eventType.hashCode());
		result = prime * result
				+ ((startDate == null) ? 0 : startDate.hashCode());
		result = prime * result
				+ ((initiatedByAccountId == null) ? 0 : initiatedByAccountId.hashCode());
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
		CalendarEventSelect other = (CalendarEventSelect) obj;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (eventType != other.eventType)
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		if (initiatedByAccountId == null) {
			if (other.initiatedByAccountId != null)
				return false;
		} else if (!initiatedByAccountId.equals(other.initiatedByAccountId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CalendarEventSelect [eventId=" + eventId
				+ ", initiatedByAccountId=" + initiatedByAccountId
				+ ", startDate=" + startDate + ", endDate=" + endDate
				+ ", eventType=" + eventType + "]";
	}
}
