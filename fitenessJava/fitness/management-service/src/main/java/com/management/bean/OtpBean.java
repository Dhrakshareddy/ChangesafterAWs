package com.management.bean;

public class OtpBean {
	private String email;
	private String otp;

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public String getEmail() {
		return email;
	}

	public OtpBean(String email, String otp) {
		super();
		this.email = email;
		this.otp = otp;
	}

	public OtpBean() {
		super();
	}

}