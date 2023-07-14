package com.cloud.shop.domain.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cloud.shop.domain.entity.CategoryEntity;

public interface CategoryEntityRepository extends JpaRepository<CategoryEntity, Long>{

	List<CategoryEntity> findAllByParentIsNull();


	List<CategoryEntity> findAllByParent(CategoryEntity entity);


	Optional<CategoryEntity> findByNo(long no);


	List<CategoryEntity> findAllByParentIsNotNull();




}
