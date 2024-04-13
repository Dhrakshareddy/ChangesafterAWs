package com.management.serviceImpl;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.management.bean.LoginBean;
import com.management.bean.MedicalHistoryBean;
import com.management.constants.ManagementConstants;
import com.management.entity.OTPEntity;
import com.management.entity.User;
import com.management.exception.EmailNotFoundException;
import com.management.exception.InvalidOtpException;
import com.management.exception.PasswordMismatchException;
import com.management.exception.ResourceNotFoundException;
import com.management.repository.OtpRepository;
import com.management.repository.UserRepository;
import com.management.service.UserService;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private OtpRepository otpRepository;
	
	
//	@Resource
	@Autowired
    private JavaMailSender javaMailSender;
	
	
	
	
	

	/**
	 * Saves a user entity.
	 * 
	 * @param user The user entity to save.
	 * @return The saved user entity.
	 */

	@Override
	public User saveUser(User user) {
		user.setRole(ManagementConstants.USERROLE);
		return userRepository.save(user);
	}

	/**
	 * Retrieves a user entity by ID.
	 * 
	 * @param id The ID of the user entity to retrieve.
	 * @return The retrieved user entity.
	 * @throws ResourceNotFoundException If no user is found with the given ID.
	 */

	@Override
	public User getUserById(Long id) {
		Optional<User> optional = userRepository.findById(id);
		if (optional.isEmpty()) {
			try {
				optional.orElseThrow(
						() -> new ResourceNotFoundException("No user was found to fetch for the given ID:" + id));
			} catch (ResourceNotFoundException e) {
				log.error(e.getMessage());
				throw e;
			}
		}
		log.info("Fetched successfully");
		return optional.get();
	}

	/**
	 * Retrieves all user entities.
	 * 
	 * @return A list of all user entities.
	 */

	@Override
	public List<User> getAll() {
		log.info("Fetching all users");
		return userRepository.findAll();
	}

	/**
	 * Retrieves medical history data for a user by username.
	 * 
	 * @param username The username of the user.
	 * @return A list of medical history data for the user.
	 */

	@Override
	public List<MedicalHistoryBean> getMedicalHistoryBean(String username) {
		String url = "http://localhost:8086/medicalHistory/fetchbyName/{username}";
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> httpEntity = new HttpEntity<>(headers);

		ResponseEntity<List<MedicalHistoryBean>> responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity,
				new ParameterizedTypeReference<List<MedicalHistoryBean>>() {
				}, username);

		return responseEntity.getBody();
	}

	/**
	 * Validates the login credentials of a user.
	 * 
	 * @param loginBean The bean containing user login details.
	 * @return The authenticated user entity.
	 */

	@Override
	public User validateLogin(LoginBean loginBean) {
		User user = userRepository.findByName(loginBean.getName());
		log.info("User found: {}", user);

		if (user != null) {
			User registrationBean = new User();

			if (user.getPassword().equals(loginBean.getPassword())) {
				log.info("Login successful");
				return user;
			} else {
				try {
					throw new PasswordMismatchException("Incorrect password");
				} catch (Exception e) {
					log.error(e.getMessage());
				}
				return user;
			}
		} else {
			try {
				throw new UserNameNotFoundException("Incorrect EmailId");
			} catch (Exception e) {
				log.error(e.getMessage());
			}
		}
		return user;
	}

	/**
	 * Retrieves a user entity by name.
	 * 
	 * @param name The name of the user.
	 * @return The retrieved user entity.
	 */

	@Override
	public User getUserByName(String name) {
		return userRepository.findByName(name);
	}

	/**
	 * Updates a user entity.
	 * 
	 * @param user The user entity to update.
	 * @return The updated user entity.
	 * @throws ResourceNotFoundException If no user is found with the given ID.
	 */
	@Override
	public User update(User user) {
		log.info("Updating user: {}", user);
		Optional<User> optional = userRepository.findById(user.getUserId());
		if (optional.isEmpty()) {
			try {
				optional.orElseThrow(() -> new ResourceNotFoundException(
						"No user was found to update for the given ID:" + user.getUserId()));
			} catch (ResourceNotFoundException e) {
				log.error(e.getMessage());
				throw e;
			}
		}
		userRepository.save(user);
		log.info("Updated successfully");
		return user;
	}

	/**
	 * Retrieves users associated with a trainer code.
	 * 
	 * @param trainerCode The trainer code.
	 * @return A list of users associated with the trainer code.
	 */
	@Override
	public List<User> getUsersByTrainerCode(String trainerCode) {
		Optional<List<User>> optional = userRepository.findByTrainerCode(trainerCode);
		if (optional.isEmpty()) {
			try {
				optional.orElseThrow(() -> new ResourceNotFoundException(
						"No user was found to fetch for the given ID:" + trainerCode));
			} catch (ResourceNotFoundException e) {
				log.error(e.getMessage());
				throw e;
			}
		}
		log.info("Fetched successfully");
		return optional.get();
	}

	/**
	 * Updates the password for a user.
	 * 
	 * @param email    The email of the user.
	 * @param password The new password.
	 * @return The user entity with the updated password.
	 */

	@Override
	public User updatePassword(String email, String password) {
		User user = userRepository.findByEmail(email);
		if (user != null) {
			user.setPassword(password);
			userRepository.save(user);
		}
		return user;
	}

	/**
	 * Deletes a user entity by ID.
	 * 
	 * @param userId The ID of the user entity to delete.
	 * @return The deleted user entity.
	 * @throws ResourceNotFoundException If no user is found with the given ID.
	 */

	@Override
	public User deleteById(Long userId) {
		Optional<User> optional = userRepository.findById(userId);
		if (optional.isEmpty()) {
			try {
				optional.orElseThrow(
						() -> new ResourceNotFoundException("No user was found to delete for the given ID:" + userId));
			} catch (ResourceNotFoundException e) {
				log.error(e.getMessage());
				throw e;
			}
		}
		userRepository.deleteById(userId);
		log.info("Deleted successfully");
		return optional.get();
	}
	
	
	



	

	
	@Override
	public User forgetPassword(String email) {

		log.info("Checking if email is present or not");
		User user = userRepository.findByEmail(email);

		if (user != null) {
			log.info("Email is valid");
			String otp = generateOtp();
			System.out.println(otp);

			Timestamp expirationTime = Timestamp.from(Instant.now().plus(Duration.ofMinutes(5)));

			sendOtpEmail(email, otp);
			saveOtp(email, otp, expirationTime);
			return user;
		} else {
			log.info("Email is not valid");
			throw new EmailNotFoundException("Email not found");
		}

	}
	@Override
    public void sendOtpEmail(String toEmail, String otp) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("fingrow.hrservices@gmail.com");
        message.setTo(toEmail);
        message.setSubject("Your OTP");
        message.setText("Your OTP is: " + otp);
        javaMailSender.send(message);
    }
	@Override
	public String generateOtp() {
		Random random = new Random();
		
		System.out.println("hloooo");
		int otp = 100000 + random.nextInt(900000);
		System.out.println(otp);
		System.out.println("hiiiiii");
		return String.valueOf(otp);
	}

	

	

	@Override
	public boolean verifyOtp(String email, String enteredOtp) {

		OTPEntity otpEntity = otpRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("OTP not found"));

		Timestamp expirationTime = otpEntity.getExpirationTime();

		LocalDateTime expirationLocalDateTime = expirationTime.toInstant().atZone(ZoneId.systemDefault())
				.toLocalDateTime();

		if (expirationLocalDateTime.isBefore(LocalDateTime.now())) {
			return false;
		}

		else if (!enteredOtp.equals(otpEntity.getOtp())) {
			throw new InvalidOtpException("Entered Correct otp");
		} else {
			return true;
		}
	}

	@Override
	public void saveOtp(String email, String otp, Timestamp expirationTime) {
		System.out.println("srilathaaaaaa");
		Optional<OTPEntity> existingOtp = otpRepository.findByEmail(email);

		if (existingOtp.isPresent()) {
			existingOtp.get().setOtp(otp);
			existingOtp.get().setExpirationTime(expirationTime);
			otpRepository.save(existingOtp.get());
		} else {
			OTPEntity newOtp = new OTPEntity();
			newOtp.setEmail(email);
			newOtp.setOtp(otp);
			newOtp.setExpirationTime(expirationTime);
			otpRepository.save(newOtp);
		}
	}
	
}
