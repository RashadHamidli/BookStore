package com.company.config;

import com.company.security.JwtAuthenticationEntryPoint;
import com.company.security.JwtAuthorAuthenticationFilter;
import com.company.security.JwtStudentAuthenticationFilter;
import com.company.service.AuthorDetailsServiceImpl;
import com.company.service.StudentDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final StudentDetailsServiceImpl studentDetailsService;
    private final AuthorDetailsServiceImpl authorDetailsService;
    private final JwtAuthenticationEntryPoint handler;

    public SecurityConfig(StudentDetailsServiceImpl studentDetailsService, AuthorDetailsServiceImpl authorDetailsService, JwtAuthenticationEntryPoint handler) {
        this.studentDetailsService = studentDetailsService;
        this.authorDetailsService = authorDetailsService;
        this.handler = handler;
    }

    @Bean
    public JwtStudentAuthenticationFilter jwtStudentAuthenticationFilter() {
        return new JwtStudentAuthenticationFilter(handler, studentDetailsService);
    }
    @Bean
    public JwtAuthorAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtStudentAuthenticationFilter(handler,authorDetailsService);
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("OPTIONS");
        config.addAllowedMethod("HEAD");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("DELETE");
        config.addAllowedMethod("PATCH");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .cors(Object::notifyAll)
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.GET, "/posts")
                        .permitAll()
                        .requestMatchers(HttpMethod.GET, "/comments")
                        .permitAll()
                        .requestMatchers("/auth/**")
                        .permitAll()
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
//                .formLogin(Customizer.withDefaults());
        return httpSecurity.build();
    }

}
