package com.cloud.shop.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.cloud.shop.domain.dao.CategoryEntityRepository;
import com.cloud.shop.service.CategoryService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CategoryServiceProcess implements CategoryService {

	private final CategoryEntityRepository repo;



}
