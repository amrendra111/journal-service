package com.apica.journal.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.apica.journal.service.exception.JournalServiceApiException;

@Configuration
public class SecurityConfig {

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		try {
			return http.csrf().disable().authorizeHttpRequests().requestMatchers("/welcom/**").permitAll()
					.requestMatchers("/journal/**").hasAnyRole("ADMIN", "MANAGER")
					.requestMatchers(HttpMethod.GET, "/journal").hasAuthority("READ_PRIVILEGE").anyRequest()
					.authenticated().and().formLogin().and().build();
		} catch (Exception e) {
			throw new JournalServiceApiException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
