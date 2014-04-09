package com.tennissetapp.args;

import java.util.Date;

public class SearchMatchesArgs extends ScrollArgs{
	public boolean playSingles;
	public boolean playDoubles;
	public boolean playFullMatch;
	public boolean playPoints;
	public boolean playHittingAround;
	public Double latitude;
	public Double longitude;
	public Date startTime;
	public Date endTime;
	
	public Float levelOfPlayMin;
	public Float levelOfPlayMax;
	
	public Double distance;
	@Override
	public String toString() {
		return "SearchMatchesArgs [playSingles=" + playSingles
				+ ", playDoubles=" + playDoubles + ", playFullMatch="
				+ playFullMatch + ", playPoints=" + playPoints
				+ ", playHittingAround=" + playHittingAround + ", latitude=" + latitude + ", longitude="
				+ longitude + ", startTime=" + startTime
				+ ", endTime=" + endTime + ", levelOfPlayMin=" + levelOfPlayMin
				+ ", levelOfPlayMax=" + levelOfPlayMax + ", distance="
				+ distance + ", offset=" + firstResult + ", maxResults="
				+ maxResults + "]";
	}
	
	
}
