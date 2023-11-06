package com.company.dao;

import com.company.entity.AuthorRefreshToken;
import com.company.entity.StudentRefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRefreshTokenRepository extends JpaRepository<AuthorRefreshToken, Long>{

	AuthorRefreshToken findByUserId(Long userId);
	
}
