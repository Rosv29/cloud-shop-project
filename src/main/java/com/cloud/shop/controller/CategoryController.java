package com.cloud.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
		return "admin/category/pd-show";
	}
	@PostMapping("/admin/product/category2")
	public String product2(@RequestParam("tagVal")long tagVal ,Model model) {
		service.listProcess(tagVal,model);
		return "admin/category/pd-show2";
	}
	
	@PostMapping("admin/category/update")
	public String update(@RequestParam("val") String val,@RequestParam("val2") long val2) {
		service.updateProcess(val,val2);
		return "redirect:/admin/category/list";	
	}
	@PostMapping("admin/category/delete")
	public String update(@RequestParam("cateno") long cateno) {
		service.deleteProcess(cateno);
		return "redirect:/admin/category/list";	
	}
}
