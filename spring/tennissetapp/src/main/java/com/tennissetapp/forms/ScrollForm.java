package com.tennissetapp.forms;

/**
 * must have setters and getters to work in a controller
 * 
 * @author meir
 */
public class ScrollForm extends AbstractForm{
	protected String maxResults;
	protected String firstResult;
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
	@Override
	public String toString() {
		return "ScrollForm [maxResults=" + maxResults + ", firstResult="
				+ firstResult + "]";
	}
}