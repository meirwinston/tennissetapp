package com.tennissetapp.forms;

@Deprecated //use message form
public class SubmitPostForm extends AbstractForm{
	protected String email;
	protected String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
