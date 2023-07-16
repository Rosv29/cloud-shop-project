package com.cloud.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cloud.shop.service.ProductService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class CommonController {

	private final ProductService pService;
	
	@GetMapping("/")
	public String index(Model model) {
		pService.findAllProcess(model);
		return "index";
	}
	
	@GetMapping("/common/product/{no}")
	public String detailPage(@PathVariable long no,Model model) {
		pService.detailProcess2(no, model);
		return "common/product/detail";
	}
}
