package com.tennissetapp.forms;

public class CreateFavoriteTeacherForm extends AbstractForm{

	protected String teacherProfileId;

	public String getTeacherProfileId() {
		return teacherProfileId;
	}

	public void setTeacherProfileId(String teacherProfileId) {
		this.teacherProfileId = teacherProfileId;
	}

	@Override
	public String toString() {
		return "CreateFavoriteTeacherForm [teacherProfileId="
				+ teacherProfileId + "]";
	}
}
