package com.tennissetapp.forms;

public class UpdateTokenForm extends AbstractForm{
	protected String token;
	protected String provider;
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	@Override
	public String toString() {
		return "UpdateTokenForm [token=" + token + ", provider=" + provider
				+ "]";
	}
	
}
