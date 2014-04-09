package com.tennissetapp.forms;

import org.apache.log4j.Logger;

public class CreateMatchForm extends AbstractForm{
	protected Logger logger = Logger.getLogger(getClass());
	
	protected String playSingles;
	protected String playDoubles;
	protected String playFullMatch;
	protected String playPoints;
	protected String playHittingAround;
	
	//visibility
	protected String visibility;
	
	//location
//	protected String latitude;
//	protected String longitude;
//	protected String distance;
	protected String tennisCenterId;
	
	//time
	protected String startTime; //millis
	protected String endTime; //millis
	
	//level of play
	protected String levelOfPlayMin;
	protected String levelOfPlayMax;
	public Logger getLogger() {
		return logger;
	}
	public void setLogger(Logger logger) {
		this.logger = logger;
	}
	public String getPlaySingles() {
		return playSingles;
	}
	public void setPlaySingles(String playSingles) {
		this.playSingles = playSingles;
	}
	public String getPlayDoubles() {
		return playDoubles;
	}
	public void setPlayDoubles(String playDoubles) {
		this.playDoubles = playDoubles;
	}
	public String getPlayFullMatch() {
		return playFullMatch;
	}
	public void setPlayFullMatch(String playFullMatch) {
		this.playFullMatch = playFullMatch;
	}
	public String getPlayPoints() {
		return playPoints;
	}
	public void setPlayPoints(String playPoints) {
		this.playPoints = playPoints;
	}
	public String getPlayHittingAround() {
		return playHittingAround;
	}
	public void setPlayHittingAround(String playHittingAround) {
		this.playHittingAround = playHittingAround;
	}
	public String getVisibility() {
		return visibility;
	}
	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getLevelOfPlayMin() {
		return levelOfPlayMin;
	}
	public void setLevelOfPlayMin(String levelOfPlayMin) {
		this.levelOfPlayMin = levelOfPlayMin;
	}
	public String getLevelOfPlayMax() {
		return levelOfPlayMax;
	}
	public void setLevelOfPlayMax(String levelOfPlayMax) {
		this.levelOfPlayMax = levelOfPlayMax;
	}
	public String getTennisCenterId() {
		return tennisCenterId;
	}
	public void setTennisCenterId(String tennisCenterId) {
		this.tennisCenterId = tennisCenterId;
	}
	@Override
	public String toString() {
		return "CreateMatchForm [logger=" + logger + ", playSingles="
				+ playSingles + ", playDoubles=" + playDoubles
				+ ", playFullMatch=" + playFullMatch + ", playPoints="
				+ playPoints + ", playHittingAround=" + playHittingAround
				+ ", visibility=" + visibility + ", tennisCenterId="
				+ tennisCenterId + ", startTime=" + startTime + ", endTime="
				+ endTime + ", levelOfPlayMin=" + levelOfPlayMin
				+ ", levelOfPlayMax=" + levelOfPlayMax + "]";
	}
	
}
