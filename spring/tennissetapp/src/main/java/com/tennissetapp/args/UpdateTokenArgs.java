package com.tennissetapp.args;

public class UpdateTokenArgs implements Arguments{
	public String token;
	public String provider;
	public long userAccountId;
	
	
	@Override
	public String toString() {
		return "UpdateTokenArgs [token=" + token + ", provider=" + provider
				+ ", userAccountId=" + userAccountId + "]";
	}
	
		
}
