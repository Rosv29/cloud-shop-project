package com.cloud.shop.domain.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cloud.shop.domain.entity.ProductEntity;
import com.cloud.shop.domain.entity.ProductImageEntity;

public interface ProductImageEntityRepository extends JpaRepository<ProductImageEntity, Long>{


	Optional<ProductImageEntity> findByProductAndDef(ProductEntity entity, boolean b);



}
