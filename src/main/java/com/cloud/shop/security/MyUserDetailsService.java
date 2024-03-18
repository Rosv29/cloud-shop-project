package com.cloud.shop.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.cloud.shop.domain.dao.MemberEntityRepository;
import com.cloud.shop.domain.entity.MemberEntity;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class MyUserDetailsService implements UserDetailsService{

	private final MemberEntityRepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
		
		MemberEntity entity=repo.findById(id).orElseThrow();
		
		return new MyUserDetails(entity);
	}

	
}
