package com.cloud.shop.service;

import org.springframework.ui.Model;

import com.cloud.shop.domain.dto.CategorySaveDTO;

public interface CategoryService {

	void listProcess(long cVal, Model model);

	void saveProcess(CategorySaveDTO dto);





}
