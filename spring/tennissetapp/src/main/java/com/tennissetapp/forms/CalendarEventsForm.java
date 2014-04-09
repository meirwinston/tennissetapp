package com.tennissetapp.forms;

public class CalendarEventsForm extends AbstractForm{
	protected String fromDate;
	protected String toDate;
	
	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	@Override
	public String toString() {
		return "CalendarEventsForm [fromDate=" + fromDate + ", toDate="
				+ toDate + "]";
	}

	
}
