package com.cloud.shop.domain.entity;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
//@Entity
public class ProductEntity {

	private long no;
	
	private String name;
	
	private String price;
	
	private String content;
	
	
}
