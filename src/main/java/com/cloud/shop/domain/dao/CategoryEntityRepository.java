package com.cloud.shop.domain.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cloud.shop.domain.entity.CategoryEntity;

public interface CategoryEntityRepository extends JpaRepository<CategoryEntity, Long>{


}
