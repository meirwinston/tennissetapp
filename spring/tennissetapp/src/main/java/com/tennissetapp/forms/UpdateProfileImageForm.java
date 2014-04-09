package com.tennissetapp.forms;

import com.tennissetapp.util.ImageMetaData;

public class UpdateProfileImageForm{
//	protected String email;
	protected Long userAccountId;
	protected ImageMetaData imageMetaData;
	protected ProfileType profileType;
	
	public enum ProfileType{
		TENNIS_TEACHER,
		TENNIS_PLAYER
	}
	
	public Long getUserAccountId() {
		return userAccountId;
	}
	public void setUserAccountId(Long userAccountId) {
		this.userAccountId = userAccountId;
	}
	public ImageMetaData getImageMetaData() {
		return imageMetaData;
	}
	public void setImageMetaData(ImageMetaData imageMetaData) {
		this.imageMetaData = imageMetaData;
	}
	public ProfileType getProfileType() {
		return profileType;
	}
	public void setProfileType(ProfileType profileType) {
		this.profileType = profileType;
	}
	
}
