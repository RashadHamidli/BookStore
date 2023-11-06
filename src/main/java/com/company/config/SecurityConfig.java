//package com.company.config;
//
//import com.company.security.JwtAuthenticationEntryPoint;
//import com.company.security.JwtAuthenticationFilter;
//import com.company.security.JwtTokenProvider;
//import com.company.service.StudentDetailsServiceImpl;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//import org.springframework.web.filter.CorsFilter;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//    private final StudentDetailsServiceImpl studentDetailsService;
//    private final JwtTokenProvider handler;
//    private final JwtAuthenticationEntryPoint handler1;
//
//    public SecurityConfig(StudentDetailsServiceImpl studentDetailsService, JwtTokenProvider handler, JwtAuthenticationEntryPoint handler1) {
//        this.studentDetailsService = studentDetailsService;
//        this.handler1 = handler1;
//        this.handler = handler;
//    }
//
//    @Bean
//    public JwtAuthenticationFilter jwtAuthenticationFilter() {
//        return new JwtAuthenticationFilter(handler, studentDetailsService);
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
//        return authenticationConfiguration.getAuthenticationManager();
//    }
//
//    @Bean
//    public CorsFilter corsFilter() {
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        CorsConfiguration config = new CorsConfiguration();
//        config.setAllowCredentials(true);
//        config.addAllowedOrigin("*");
//        config.addAllowedHeader("*");
//        config.addAllowedMethod("OPTIONS");
//        config.addAllowedMethod("HEAD");
//        config.addAllowedMethod("GET");
//        config.addAllowedMethod("PUT");
//        config.addAllowedMethod("POST");
//        config.addAllowedMethod("DELETE");
//        config.addAllowedMethod("PATCH");
//        source.registerCorsConfiguration("/**", config);
//        return new CorsFilter(source);
//    }
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity
////                .cors(Object::notifyAll)
//                .csrf(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers(HttpMethod.GET, "/student")
//                        .permitAll()
//                        .requestMatchers(HttpMethod.POST, "/student")
//                        .permitAll()
//                        .requestMatchers(HttpMethod.GET, "/book")
//                        .permitAll()
//                        .requestMatchers(HttpMethod.POST, "/book")
//                        .permitAll()
//                        .requestMatchers("/author")
//                        .permitAll()
//                        .anyRequest().authenticated()
//                )
//                .sessionManagement(session -> session
//                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                )
//                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
////                .formLogin(Customizer.withDefaults());
//        return httpSecurity.build();
//    }
////    @Bean
////    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
////        http
//////              .cors().and()
////                .csrf(AbstractHttpConfigurer::disable)
////                .authorizeRequests(authorize -> authorize
////                        .antMatchers(HttpMethod.GET, "/user").permitAll()
////                        .antMatchers(HttpMethod.GET, "/student").permitAll()
////                        .antMatchers("/author/**").permitAll()
////                        .anyRequest().authenticated()
////                )
////                .exceptionHandling().authenticationEntryPoint(handler1)
////                .and()
////                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
////                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
////        // .formLogin(Customizer.withDefaults()); // Bu satırı eklerseniz giriş sayfasını etkinleştirebilirsiniz
////        return http.build();
////    }
//
//}
