package com.company.dao;

import com.company.entity.StudentRefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRefreshTokenRepository extends JpaRepository<StudentRefreshToken, Long>{

	StudentRefreshToken findByUserId(Long userId);
	
}
