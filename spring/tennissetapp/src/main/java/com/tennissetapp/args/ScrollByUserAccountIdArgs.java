package com.tennissetapp.args;

public class ScrollByUserAccountIdArgs extends ScrollArgs{
	public Long userAccountId;

	@Override
	public String toString() {
		return "ScrollByUserAccountIdArgs [userAccountId=" + userAccountId
				+ ", firstResult=" + firstResult + ", maxResults=" + maxResults
				+ "]";
	}
	
}
