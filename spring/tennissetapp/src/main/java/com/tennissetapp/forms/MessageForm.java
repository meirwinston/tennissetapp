package com.tennissetapp.forms;

public class MessageForm extends AbstractForm {
	protected String toUserAccountId;
	protected String message;
	
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
	@Override
	public String toString() {
		return "MessageForm [toUserAccountId=" + toUserAccountId + ", message="
				+ message + "]";
	}
}
