package com.cloud.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	@PostMapping("/admin/product/category")
	public String product(@RequestParam("tagVal")long tagVal ,Model model) {
		service.listProcess(tagVal,model);
		return "admin/category/list";
	}
}
