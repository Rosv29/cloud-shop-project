package com.cloud.shop.domain.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.cloud.shop.domain.entity.CategoryEntity;
import com.cloud.shop.domain.entity.ProductEntity;
import com.cloud.shop.domain.entity.ProductImageEntity;

import lombok.Data;

@Data
public class ProductListDTO {

	private long no;
	
	private CategoryEntity category;
	
	private String name;

	private int price;

	private int stock;

	private boolean status;

	private LocalDateTime createdDate;
	
	private String bucketKey;
	
	
	
	public ProductListDTO defImg(ProductImageEntity img,String domain) {
		if(img.getBucketKey()=="/images/common/noimg.jpg") {
			this.bucketKey="/images/common/noimg.jpg";
			return this;
		}else {
			this.bucketKey= domain+img.getBucketKey();
			return this;			
		}
		
	}


	public ProductListDTO(ProductEntity entity) {
		this.no=entity.getPNo();
		this.category=entity.getCategory();
		this.name=entity.getPName();
		this.price=entity.getPPrice();
		this.stock=entity.getPStock();
		this.status=entity.isPStatus();
		this.createdDate=entity.getCreatedDate();
	}
	

}
