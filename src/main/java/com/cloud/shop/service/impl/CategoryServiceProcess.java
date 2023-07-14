package com.cloud.shop.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.security.access.prepost.PrePostAdviceReactiveMethodInterceptor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.cloud.shop.domain.dao.CategoryEntityRepository;
import com.cloud.shop.domain.dao.ProductEntityRepository;
import com.cloud.shop.domain.dto.CategorySaveDTO;
import com.cloud.shop.domain.entity.CategoryEntity;
import com.cloud.shop.domain.entity.ProductEntity;
import com.cloud.shop.service.CategoryService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CategoryServiceProcess implements CategoryService {

	private final CategoryEntityRepository repo;
	private final ProductEntityRepository pRepo;

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

	@Override
	public void listPage(Model model) {

		List<CategoryEntity> list=repo.findAll();
		model.addAttribute("list", list);
		
	}

	@Override
	public void updateProcess(String val, long val2) {
		CategoryEntity entity =repo.findByNo(val2).orElseThrow();
		repo.save(entity.update(val));
		
	}

	@Override
	public void deleteProcess(long cateno) {
		
		repo.deleteById(cateno);
	}

	



}
