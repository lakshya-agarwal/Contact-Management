package com.assessment.contactmanager.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.assessment.contactmanager.dao.UserDao;
import com.assessment.contactmanager.entity.UserEntity;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

	@Autowired
	private UserDao userRepository;

	@SuppressWarnings("removal")
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable()).headers(headers -> headers.frameOptions().sameOrigin() // 👈 Allows H2 console
																									// to load
		).authorizeHttpRequests(auth -> auth.requestMatchers("/h2-console/**").permitAll() // 👈 allow H2 paths
				.anyRequest().authenticated()).httpBasic();
		return http.build();
	}

	
	@Bean
	CommandLineRunner seedUsers(UserDao userDao, PasswordEncoder encoder) {
	    return args -> {
	        if (userDao.findByUsername("admin").isEmpty()) {
	            userDao.save(UserEntity.builder()
	                .username("admin")
	                .password(encoder.encode("admin123")) // correct encoding
	                .role("ROLE_ADMIN")
	                .build());
	        }

	        if (userDao.findByUsername("user").isEmpty()) {
	            userDao.save(UserEntity.builder()
	                .username("user")
	                .password(encoder.encode("user123"))
	                .role("ROLE_USER")
	                .build());
	        }
	    };
	}
	@Bean
	public UserDetailsService userDetailsService() {
		return username -> {
			UserEntity user = userRepository.findByUsername(username)
					.orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
			return User.withUsername(user.getUsername()).password(user.getPassword())
					.roles(user.getRole().replace("ROLE_", "")) // remove prefix
					.build();
		};
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
