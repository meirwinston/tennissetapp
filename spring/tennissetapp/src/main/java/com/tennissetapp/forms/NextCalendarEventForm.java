package com.tennissetapp.forms;

public class NextCalendarEventForm extends AbstractForm{
	protected String fromDate;
	
	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	@Override
	public String toString() {
		return "NextCalendarEventForm [fromDate=" + fromDate + "]";
	}
}
