package com.tennissetapp.args;

public class SearchTennisCourtsArgs extends ScrollArgs{
	public double latitude;
	public double longitude;
	public double distance;
	
	public String courtName;
	public boolean outdoor, indoor;
	public boolean hard,concrete,clay,grass,synthetic,carpet;
	@Override
	public String toString() {
		return "SearchTennisCourtsArgs [latitude=" + latitude + ", longitude="
				+ longitude + ", distance=" + distance + ", courtName="
				+ courtName + ", outdoor=" + outdoor + ", indoor=" + indoor
				+ ", hard=" + hard + ", concrete=" + concrete + ", clay="
				+ clay + ", grass=" + grass + ", synthetic=" + synthetic
				+ ", carpet=" + carpet + ", firstResult=" + firstResult
				+ ", maxResults=" + maxResults + "]";
	}
}
