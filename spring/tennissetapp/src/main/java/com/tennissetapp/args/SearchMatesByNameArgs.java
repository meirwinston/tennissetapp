package com.tennissetapp.args;

/**
 * no need for distance
 * 
 * @author meir
 * 
 */
public class SearchMatesByNameArgs extends ScrollArgs {
	public String nameOrEmail;

	@Override
	public String toString() {
		return "SearchMatesByNameArgs [nameOrEmail=" + nameOrEmail
				+ ", firstResult=" + firstResult + ", maxResults=" + maxResults
				+ "]";
	}
}
