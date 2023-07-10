package com.cloud.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/admin")
@Controller
public class AdminController {

	@GetMapping("index")
	public String index() {
		return "admin/index";
	}
	//카테고리등록 페이지이동
	@GetMapping("category/create")
	public String categoryCreate() {
		return "admin/category/create";
	}
	
	
	/////////////////////상품관련///////////////////////
	//상품등록 페이지이동
	@GetMapping("product/create")
	public String productCreate() {
		return "admin/product/create";
	}
	

}
