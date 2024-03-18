package com.cloud.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cloud.shop.service.CategoryService;
import com.cloud.shop.service.ProductService;
import com.cloud.shop.service.impl.CategoryServiceProcess;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/admin")
@Controller
public class AdminController {
	
	private final ProductService pService;
	private final CategoryService cService;
	
	@GetMapping("")
	public String index() {
		return "admin/index";
	}
	//카테고리등록 페이지이동
	@GetMapping("category/create")
	public String categoryCreate() {
		return "admin/category/create";
	}
	//카테고리수정 페이지이동
	@GetMapping("category/list")
	public String categoryList() {
		return "admin/category/list";
	}
	//비동기 카테고리 요청
	@GetMapping("category/list/show")
	public String categoryListShow(Model model) {
		cService.listPage(model);
		return "admin/category/list-show";
	}
	//카테고리등록시 비동기로 카테고리 리스트 불러오기
	@PostMapping("category/show")
	public String categoryList(@RequestParam("cVal") long cVal,Model model) {
		cService.listProcess(cVal,model);
		return "admin/category/show";
	}
	
	/////////////////////상품관련///////////////////////
	//상품등록 페이지이동
	@GetMapping("product/create")
	public String productCreate() {
		return "admin/product/create";
	}
	//상품조회수정 페이지이동
	@GetMapping("product/list")
	public String productList(Model model) {
		pService.listProcess(model);
		return "admin/product/list";
	}
	

}
