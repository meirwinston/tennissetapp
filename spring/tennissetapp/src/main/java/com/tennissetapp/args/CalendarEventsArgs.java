package com.tennissetapp.args;

import java.util.Date;

public class CalendarEventsArgs implements Arguments{
	public Date fromDate;
	public Date toDate;
	public Long userAccountId;
	@Override
	public String toString() {
		return "CalendarEventsArgs [fromDate=" + fromDate + ", toDate="
				+ toDate + ", userAccountId=" + userAccountId + "]";
	}
	
		
}
