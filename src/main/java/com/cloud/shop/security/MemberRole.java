package com.cloud.shop.security;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MemberRole {
	
	USER("ROLE_USER","고객"),
	ADMIN("ROLE_ADMIN","관리자");
	
	private final String roleName;
	private final String korName;

}