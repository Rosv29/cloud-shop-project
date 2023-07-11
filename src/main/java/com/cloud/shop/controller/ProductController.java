package com.cloud.shop.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
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
		return "/admin/product/list";
		
		
	}
	
	
}
