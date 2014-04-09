package com.tennissetapp.forms;

public class ScrollByMateAccountIdForm extends AbstractForm{
	protected String maxResults;
	protected String firstResult;
	protected String mateAccountId;
	protected String startDate;
	
	public String getMaxResults() {
		return maxResults;
	}
	public void setMaxResults(String maxResults) {
		this.maxResults = maxResults;
	}
	public String getFirstResult() {
		return firstResult;
	}
	public void setFirstResult(String firstResult) {
		this.firstResult = firstResult;
	}
	public String getMateAccountId() {
		return mateAccountId;
	}
	public void setMateAccountId(String mateAccountId) {
		this.mateAccountId = mateAccountId;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	@Override
	public String toString() {
		return "ScrollByMateAccountIdForm [maxResults=" + maxResults
				+ ", firstResult=" + firstResult + ", mateAccountId="
				+ mateAccountId + ", startDate=" + startDate + "]";
	}

}