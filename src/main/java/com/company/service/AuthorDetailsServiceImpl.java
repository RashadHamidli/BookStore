package com.company.service;

import com.company.dao.AuthorRepository;
import com.company.entity.Author;
import com.company.security.JwtAuthorDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthorDetailsServiceImpl implements UserDetailsService {

	private AuthorRepository authorRepository;

    public AuthorDetailsServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Author author = authorRepository.findByName(username);
		return JwtAuthorDetails.create(author);
	}

	public UserDetails loadUserById(Long id) {
		Author author = authorRepository.findById(id).get();
		return JwtAuthorDetails.create(author);
	}

}
