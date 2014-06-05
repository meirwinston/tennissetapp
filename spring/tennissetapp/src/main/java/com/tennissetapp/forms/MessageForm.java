package com.tennissetapp.forms;

public class MessageForm extends AbstractForm {
	protected String toUserAccountId;
	protected String message;
	protected String token; //registrationId
	
	public String getToUserAccountId() {
		return toUserAccountId;
	}
	public void setToUserAccountId(String toUserAccountId) {
		this.toUserAccountId = toUserAccountId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	@Override
	public String toString() {
		return "MessageForm [toUserAccountId=" + toUserAccountId + ", message="
				+ message + ", token=" + token + "]";
	}
}
