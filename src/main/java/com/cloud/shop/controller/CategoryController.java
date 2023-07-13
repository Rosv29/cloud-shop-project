package com.cloud.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.cloud.shop.domain.dto.CategorySaveDTO;
import com.cloud.shop.service.CategoryService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class CategoryController {

	private final CategoryService service;
	
	@PostMapping("/admin/category/create")
	public String create(CategorySaveDTO dto) {
		service.saveProcess(dto);
		return "redirect:/admin/category/create";
		
	}
}
