package com.cloud.shop.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class WebSecurityConfig {
	
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests(authorize -> authorize
				.antMatchers("/css/**","/js/**","/images/**","/webjars/**").permitAll()
				.antMatchers("/member/**","/common/**").permitAll()
				.antMatchers("/").permitAll()
				.antMatchers("/admin/**").hasRole("ADMIN")
				.anyRequest().authenticated()
			)
			.formLogin(formLogin -> formLogin
				.loginPage("/member/signin")
				.usernameParameter("id")
			
			).logout(logout -> logout
				.logoutSuccessUrl("/")
				.permitAll()
			)
			;
		return http.build();
	}
}
