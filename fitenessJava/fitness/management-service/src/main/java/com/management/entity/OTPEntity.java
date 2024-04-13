package com.management.entity;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "otp")
public class OTPEntity {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "email")
	private String email;
	@Column(name = "otp")
	private String otp;
	@Column(name = "date_time")
	private Timestamp expirationTime;

	public OTPEntity(int id, String email, String otp, Timestamp expirationTime) {
		super();
		this.id = id;
		this.email = email;
		this.otp = otp;
		this.expirationTime = expirationTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOtp() {
		return otp;
	}

	public OTPEntity() {
		super();
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public Timestamp getExpirationTime() {
		return expirationTime;
	}

	public void setExpirationTime(Timestamp expirationTime) {
		this.expirationTime = expirationTime;
	}

}