package com.cloud.shop.domain.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cloud.shop.domain.entity.ProductEntity;
import com.cloud.shop.domain.entity.ProductImageEntity;

public interface ProductImageEntityRepository extends JpaRepository<ProductImageEntity, Long>{


	Optional<ProductImageEntity> findByProductAndDef(ProductEntity entity, boolean b);

	List<ProductImageEntity> findAllByProduct(ProductEntity result);

	List<ProductImageEntity> findAllByProductAndDef(ProductEntity result, boolean b);



}
