package com.company.dao;

import com.company.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long>{

	RefreshToken findByStudentId(Long id);
	
}
