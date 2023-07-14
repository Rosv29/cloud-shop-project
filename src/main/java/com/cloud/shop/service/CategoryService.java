package com.cloud.shop.service;

import org.springframework.ui.Model;

import com.cloud.shop.domain.dto.CategorySaveDTO;

public interface CategoryService {

	void listProcess(long cVal, Model model);

	void saveProcess(CategorySaveDTO dto);

	void listPage(Model model);

	void updateProcess(String val, long val2);

	void deleteProcess(long cateno);





}
