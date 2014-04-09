package com.tennissetapp.args;

import java.util.Date;

import org.apache.log4j.Logger;

public class CreateMatchArgs implements Arguments{
	public Logger logger = Logger.getLogger(getClass());
	
	public Long orgenizerId;
//	public String name;
	public Long tennisCenterId;
	public Date date;
	public Date startTime;
	public Date endTime;
	public Float levelOfPlayMin;
	public Float levelOfPlayMax;
	public boolean playSingles;
	public boolean playDoubles;
	public boolean playFullMatch;
	public boolean playPoints;
	public boolean playHittingAround;
	public String visibility;
	@Override
	public String toString() {
		return "CreateMatchArgs [logger=" + logger + ", orgenizerId="
				+ orgenizerId + ", tennisCenterId="
				+ tennisCenterId + ", date=" + date + ", startTime="
				+ startTime + ", endTime=" + endTime + ", levelOfPlayMin="
				+ levelOfPlayMin + ", levelOfPlayMax=" + levelOfPlayMax
				+ ", playSingles=" + playSingles + ", playDoubles="
				+ playDoubles + ", playFullMatch=" + playFullMatch
				+ ", playPoints=" + playPoints + ", playHittingAround="
				+ playHittingAround + ", visibility=" + visibility + "]";
	}
}
