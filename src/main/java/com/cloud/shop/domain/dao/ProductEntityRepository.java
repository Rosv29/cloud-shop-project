package com.cloud.shop.domain.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cloud.shop.domain.entity.ProductEntity;

public interface ProductEntityRepository extends JpaRepository<ProductEntity, Long>{

}
