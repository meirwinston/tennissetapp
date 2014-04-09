package com.tennissetapp.persistence.entities;

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

import com.tennissetapp.json.TennisCourtSerializer;

@NamedQueries(
	@NamedQuery(
		name="TennisCourt.selectByTennisCenterId",
		query="SELECT c FROM TennisCourt AS c WHERE c.tennisCenterId = :tennisCenterId"
	)	
)
@Table(name="tennis_courts",schema="tennissetapp")
@Entity
@JsonSerialize(using=TennisCourtSerializer.class)
@Access(AccessType.FIELD)
public class TennisCourt {

	public enum Surface{
		CARPET,
		CLAY,
		CONCRETE,
		GRASS,
		SYNTHETIC,
		HARD;
	}
	
	public enum Placement{
		OUTDOOR,
		INDOOR
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	protected Long tennisCourtId;
	
	protected Long tennisCenterId;
	
	protected Integer numberOfCourts;
	
	@Enumerated(EnumType.STRING)
	protected Surface surface;
	
	@Enumerated(EnumType.STRING)
	protected Placement placement;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "tennisCenterId",referencedColumnName="tennisCenterId",updatable=false,insertable=false)
	protected TennisCenter tennisCenter;
	
	public Long getTennisCourtId() {
		return tennisCourtId;
	}
	public void setTennisCourtId(Long tennisCourtId) {
		this.tennisCourtId = tennisCourtId;
	}
	public Integer getNumberOfCourts() {
		return numberOfCourts;
	}
	public void setNumberOfCourts(Integer numberOfCourts) {
		this.numberOfCourts = numberOfCourts;
	}
	public Surface getSurface() {
		return surface;
	}
	public void setSurface(Surface surface) {
		this.surface = surface;
	}
	
	public Placement getPlacement() {
		return placement;
	}
	public void setPlacement(Placement placement) {
		this.placement = placement;
	}
	public TennisCenter getTennisCenter() {
		return tennisCenter;
	}
	public void setTennisCenter(TennisCenter tennisCenter) {
		this.tennisCenter = tennisCenter;
	}
	
	public Long getTennisCenterId() {
		return tennisCenterId;
	}
	public void setTennisCenterId(Long tennisCenterId) {
		this.tennisCenterId = tennisCenterId;
	}
	@Override
	public String toString() {
		return "TennisCourt [tennisCourtId=" + tennisCourtId
				+ ", tennisCenterId=" + tennisCenterId + ", numberOfCourts="
				+ numberOfCourts + ", surface=" + surface + ", placement="
				+ placement + "]";
	}
	
}
