package com.tennissetapp.args;

public class SearchByNameOrEmailArgs  extends ScrollArgs {
	public String nameOrEmail;
	public Double latitude,longitude;
	
	@Override
	public String toString() {
		return "SearchByNameOrEmailArgs [nameOrEmail=" + nameOrEmail
				+ ", latitude=" + latitude + ", longitude=" + longitude + "]";
	}
}
