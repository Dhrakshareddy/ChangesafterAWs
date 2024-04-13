package com.management.serviceImpl;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender mailSender;

	public String sendOtpByEmail(String toEmail) {
		String subject = "Password Reset Request";
		String otp = generateOTP(4); // Generate a 4-digit OTP
		String body = "Your One-Time Password (OTP) is: " + otp;

		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("sarudr06@gmail.com");
		message.setTo(toEmail);
		message.setSubject(subject);
		message.setText(body);

		mailSender.send(message);

		System.out.println("OTP sent successfully to: " + toEmail);
		return otp;
	}

	private String generateOTP(int length) {
		String numbers = "0123456789";
		Random random = new Random();
		StringBuilder otp = new StringBuilder();

		for (int i = 0; i < length; i++) {
			int index = random.nextInt(numbers.length());
			otp.append(numbers.charAt(index));
		}

		return otp.toString();
	}
}
