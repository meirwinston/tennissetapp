package com.tennissetapp.args;

public class SigninArgs implements Arguments{
	public String email;
	public String password;
	@Override
	public String toString() {
		return "SigninArgs [email=" + email + ", password=" + password + "]";
	}
	
	
}
