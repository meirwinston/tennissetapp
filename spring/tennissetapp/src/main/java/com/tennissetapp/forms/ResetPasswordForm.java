package com.tennissetapp.forms;

public class ResetPasswordForm {
	protected String password;
	protected String confirmPassword;
	protected String email;
	protected String token;
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	@Override
	public String toString() {
		return "ResetPasswordForm [password=" + password + ", confirmPassword="
				+ confirmPassword + ", email=" + email + ", token=" + token
				+ "]";
	}
}
