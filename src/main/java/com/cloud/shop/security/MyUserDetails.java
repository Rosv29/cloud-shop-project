package com.cloud.shop.security;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.cloud.shop.domain.entity.MemberEntity;

import lombok.Getter;

@Getter
public class MyUserDetails extends User {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long no;
	private String name;	
	private String email;
	private String phone;
	private String korName;
	
	public MyUserDetails(MemberEntity entity) {
		super(entity.getId(), entity.getPassword(), 
				entity.getRoles().stream()
				.map(role->new SimpleGrantedAuthority(role.getRoleName()))
				.collect(Collectors.toSet()));
		this.no=entity.getNo();
		this.name=entity.getName();
		this.email=entity.getEmail();
		this.phone=entity.getPhone();
		this.korName = entity.getRoles()
			    .stream()
			    .map(role -> role.getKorName())
			    .filter(name -> name.contains("관리자"))
			    .findFirst()
			    .orElse("고객");
		
		
	
	}


}