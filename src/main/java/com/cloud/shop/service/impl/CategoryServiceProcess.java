package com.cloud.shop.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.cloud.shop.domain.dao.CategoryEntityRepository;
import com.cloud.shop.domain.dto.CategorySaveDTO;
import com.cloud.shop.domain.entity.CategoryEntity;
import com.cloud.shop.service.CategoryService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CategoryServiceProcess implements CategoryService {

	private final CategoryEntityRepository repo;

	@Override
	public void listProcess(long cVal, Model model) {
		if(cVal==0) {
			model.addAttribute("list", repo.findAllByParentIsNull());
		}else {
			System.out.println(cVal);
			model.addAttribute("list", repo.findAllByParent(repo.findById(cVal).orElseThrow()));
		}
	}

	@Override
	public void saveProcess(CategorySaveDTO dto) {
		Optional<CategoryEntity> entity=repo.findByNo(dto.getParentNo());
		repo.save(dto.toEntity(dto,entity.orElse(null)));
		
	}

	



}
