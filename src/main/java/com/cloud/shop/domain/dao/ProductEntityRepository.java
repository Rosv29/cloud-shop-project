package com.cloud.shop.domain.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cloud.shop.domain.entity.CategoryEntity;
import com.cloud.shop.domain.entity.ProductEntity;

public interface ProductEntityRepository extends JpaRepository<ProductEntity, Long>{

	Optional<ProductEntity> findByCategory(Optional<CategoryEntity> findByNo);

}
