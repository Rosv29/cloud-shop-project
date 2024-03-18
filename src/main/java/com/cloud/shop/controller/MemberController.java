package com.cloud.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cloud.shop.domain.dto.MemberSignupDTO;
import com.cloud.shop.service.MemberService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/member")
@RequiredArgsConstructor
@Controller
public class MemberController {

	private final MemberService service;
	
	//로그인페이지이동
	@GetMapping("signin")
	public String signin() {
		return "member/signin";
	}
	//회원가입페이지이동
	@GetMapping("signup")
	public String signup() {
		return "member/signup";
	}
	//회원가입
	@PostMapping("signup")
	public String signup(MemberSignupDTO dto) {
		service.saveProcess(dto);
		return "redirect:/member/signin";
	}
	//아이디중복체크
	@PostMapping("checkId")
	@ResponseBody
	public boolean checkId(@RequestParam String id) {
		System.out.println(id);
		return service.checkId(id);
	}
	//이메일중복체크
	@PostMapping("checkEmail")
	@ResponseBody
	public boolean checkEmail(@RequestParam String email) {
		return service.checkEmail(email);
	}
	//이메일로 인증번호 전송
	@ResponseBody
	@PostMapping("verifyEmail")
	public void verifyEmail(String email) {
		service.verifyEmail(email);
	}
	@ResponseBody
	@PostMapping("verifyCode")
	public String verifyCode(String email) {
		return service.verifyCode(email);
	}
}
