package com.assessment.contactmanager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	 @SuppressWarnings("removal")
	@Bean
	    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	        http
	            .csrf(csrf -> csrf.disable())
	            .headers(headers -> headers
	                .frameOptions().sameOrigin() // 👈 Allows H2 console to load
	            )
	            .authorizeHttpRequests(auth -> auth
	                .requestMatchers("/h2-console/**").permitAll() // 👈 allow H2 paths
	                .anyRequest().authenticated()
	            )
	            .httpBasic();
	        return http.build();
	    }
	 
	 @Bean
	 public InMemoryUserDetailsManager userDetailsService() {
	     UserDetails user = User.withUsername("user")
	             .password(passwordEncoder().encode("user123"))
	             .roles("USER")
	             .build();
	     UserDetails admin = User.withUsername("admin")
	             .password(passwordEncoder().encode("admin123"))
	             .roles("ADMIN")
	             .build();
	     return new InMemoryUserDetailsManager(user, admin);
	 }
	 
	 @Bean
	 public PasswordEncoder passwordEncoder() {
	     return new BCryptPasswordEncoder();
	 }

}
