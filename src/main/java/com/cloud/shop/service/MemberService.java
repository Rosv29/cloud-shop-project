package com.cloud.shop.service;

import com.cloud.shop.domain.dto.MemberSignupDTO;

public interface MemberService {

	void saveProcess(MemberSignupDTO dto);

	boolean checkId(String id);

	boolean checkEmail(String email);

	String verifyEmail(String email);

}
