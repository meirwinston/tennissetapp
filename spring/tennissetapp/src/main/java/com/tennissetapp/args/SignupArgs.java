package com.tennissetapp.args;

public class SignupArgs implements Arguments{
	public Long userAccountId;
	public String email;
	public String username;
	public String password;
	public String ipAddress;
	@Override
	public String toString() {
		return "SignupArgs [userAccountId=" + userAccountId + ", email="
				+ email + ", username=" + username + ", password=" + password
				+ ", ipAddress=" + ipAddress + "]";
	}
	
	
}
