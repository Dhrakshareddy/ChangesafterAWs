package com.management.service;

import java.sql.Timestamp;
import java.util.List;

import com.management.bean.LoginBean;
import com.management.bean.MedicalHistoryBean;
import com.management.entity.User;

public interface UserService {

	User update(User user);

	User saveUser(User user);

	public List<MedicalHistoryBean> getMedicalHistoryBean(String username);

	User getUserById(Long userId);

	List<User> getAll();

	User deleteById(Long userId);

	User validateLogin(LoginBean loginBean);

	List<User> getUsersByTrainerCode(String trainerCode);

	User getUserByName(String name);

	User updatePassword(String email, String password);

	

	User forgetPassword(String email);

	boolean verifyOtp(String email, String enteredOtp);

	void saveOtp(String email, String otp, Timestamp expirationTime);

	void sendOtpEmail(String toEmail, String otp);

	String generateOtp();

	
	
	


	

	

	

}
