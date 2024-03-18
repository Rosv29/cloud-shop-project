package com.cloud.shop.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cloud.shop.domain.dto.ProductSaveDTO;
import com.cloud.shop.service.ProductService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class ProductController {

	private final ProductService service;
	
	@ResponseBody
	@PostMapping("/admin/product/temp-img")
	public Map<String,String> tempImg(MultipartFile temp) {
		return service.tempImgUpload(temp);
		
	}
	
	@PostMapping("/admin/product/create")
	public String productCreate(ProductSaveDTO dto) {
		service.saveProcess(dto);
		return "redirect:/admin/product/list";	
	}
	
	@GetMapping("/admin/product/update/{no}")
	public String productUpdate(@PathVariable long no,Model model) {
		service.detailProcess(no,model);
		return "admin/product/update";
	}
	
	@PostMapping("/admin/product/update")
	public String productUpdate(ProductSaveDTO dto) {
		service.updateProcess(dto);
		return "redirect:/admin/product/list";	
	}
	
	@GetMapping("/admin/product/delete/{no}")
	public String productDelete(@PathVariable("no") long pno) {
		service.deleteProcess(pno);
		return "redirect:/admin/product/list";
	}
	
	@PostMapping("/common/search")
	public String search(String searchName,Model model) {
		
		return "common/search";
	}
	
}
