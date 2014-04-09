package com.tennissetapp.args;

import java.util.Date;

import org.apache.log4j.Logger;

public class CreateFavoriteTeacherArgs implements Arguments{
	public Logger logger = Logger.getLogger(getClass());
	
	public Long userAccountId;
	public Long teacherProfileId;
	public Date createdOn;
	
	@Override
	public String toString() {
		return "CreateFavoriteTeacherArgs [userAccountId=" + userAccountId
				+ ", teacherProfileId=" + teacherProfileId + ", createdOn="
				+ createdOn + "]";
	}
}
