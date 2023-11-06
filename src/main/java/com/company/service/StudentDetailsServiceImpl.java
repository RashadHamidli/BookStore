package com.company.service;

import com.company.dao.StudentRepository;
import com.company.entity.Student;
import com.company.security.JwtStudentDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class StudentDetailsServiceImpl implements UserDetailsService {

	private final StudentRepository studentRepository;

    public StudentDetailsServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Student student = studentRepository.findByName(username);
		return JwtStudentDetails.create(student);
	}

	public UserDetails loadUserById(Long id) {
		Student user = studentRepository.findById(id).get();
		return JwtStudentDetails.create(user);
	}

}
