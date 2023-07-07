package com.cloud.shop.domain.dto;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.cloud.shop.domain.entity.MemberEntity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MemberSignupDTO {

	private String id;
	
	private String password;
	
	private String name;
	
	private String email;
	
	private String phone;
	
	public MemberEntity toEntity(PasswordEncoder encoder) {
		return MemberEntity.builder()
				.id(id).password(encoder.encode(password)).name(name)
				.email(email).phone(phone).build();
	}
}
