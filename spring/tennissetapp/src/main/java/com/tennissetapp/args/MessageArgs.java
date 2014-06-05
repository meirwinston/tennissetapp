package com.tennissetapp.args;

import java.util.Date;

public class MessageArgs implements Arguments{
	public Long userAccountId;
	public Long toUserAccountId;
	public String message;
	public Date createdOn;
	public String token;
	@Override
	public String toString() {
		return "MessageArgs [userAccountId=" + userAccountId
				+ ", toUserAccountId=" + toUserAccountId + ", message="
				+ message + ", createdOn=" + createdOn + ", token=" + token
				+ "]";
	}
}
