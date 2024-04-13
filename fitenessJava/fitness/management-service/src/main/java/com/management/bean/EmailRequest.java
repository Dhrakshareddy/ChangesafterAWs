package com.management.bean;

public class EmailRequest {
	private String email;

	public EmailRequest(String email) {
		super();
		this.email = email;
	}
	
	public EmailRequest() {
		super();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
