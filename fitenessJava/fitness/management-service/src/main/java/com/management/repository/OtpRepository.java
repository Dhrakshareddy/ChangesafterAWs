package com.management.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.management.entity.OTPEntity;

import jakarta.transaction.Transactional;

@Repository
public interface OtpRepository extends JpaRepository<OTPEntity, Integer> {
	Optional<OTPEntity> findByEmail(String email);

	@Modifying
	@Transactional
	@Query("DELETE FROM OTPEntity o WHERE TIMESTAMPDIFF(MINUTE, o.expirationTime, CURRENT_TIMESTAMP()) <> 2 AND o.id > 0")
	void deleteExpiredOtps();
}