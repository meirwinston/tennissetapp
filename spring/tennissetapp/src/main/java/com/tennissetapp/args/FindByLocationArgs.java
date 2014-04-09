package com.tennissetapp.args;

public class FindByLocationArgs extends ScrollArgs{
	public double latitude;
	public double longitude;
	public double maxDistance;
	@Override
	public String toString() {
		return "FindByLocationArgs [latitude=" + latitude + ", longitude="
				+ longitude + ", distance=" + maxDistance + ", firstResult="
				+ firstResult + ", maxResults=" + maxResults + "]";
	}
	
	
}
