package com.cloud.shop.domain.dto;

import com.cloud.shop.domain.entity.CategoryEntity;

import lombok.Data;

@Data
public class CategorySaveDTO {

	private String name;
	
	private long parentNo;

	
	
	public CategoryEntity toEntity(CategorySaveDTO dto, CategoryEntity entity) {
		
		return CategoryEntity.builder()
				.name(name)
				.parent(entity)
				.build();
	}
}
