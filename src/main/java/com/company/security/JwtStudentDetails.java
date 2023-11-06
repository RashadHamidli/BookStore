package com.company.security;

import com.company.entity.Student;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
public class JwtStudentDetails implements UserDetails {

	public Long id;
	private String username;
	private String password;
	private Collection<? extends GrantedAuthority> authorities;

    private JwtStudentDetails(Long id, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    public static JwtStudentDetails create(Student student) {
        List<GrantedAuthority> authoritiesList = new ArrayList<>();
        authoritiesList.add(new SimpleGrantedAuthority("user"));
        return new JwtStudentDetails(student.getId(), student.getName(), student.getPassword(), authoritiesList);
    }

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}