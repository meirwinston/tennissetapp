package com.tennissetapp.args;

import java.util.Date;

public class ScrollByMateAccountIdArgs extends ScrollArgs{
	public Long userAccountId;
	public Long mateAccountId;
	public Date startDate;
	@Override
	public String toString() {
		return "ScrollByMateAccountIdArgs [userAccountId=" + userAccountId
				+ ", mateAccountId=" + mateAccountId + ", startDate="
				+ startDate + "]";
	}
	
	
}
