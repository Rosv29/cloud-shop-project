package com.cloud.shop.domain.dto;

import com.cloud.shop.domain.entity.CategoryEntity;
import com.cloud.shop.domain.entity.ProductEntity;
import com.cloud.shop.domain.entity.ProductImageEntity;

import lombok.Data;

@Data
public class ProductSaveDTO {
	
	private long pNo;
	
	private String pName;

	private int pPrice;

	private int pStock;

	private String pContent;

	private boolean pStatus;

	private long categoryNo;
	
	public ProductEntity toProduct(CategoryEntity cate) {
		return ProductEntity.builder().pName(pName).pPrice(pPrice).pStock(pStock).pStatus(pStatus).pContent(pContent)
				.category(cate)
				.build();
	}

	/////////////////////
	private String[] tempKey;

	private String[] newName;
	
	private boolean[] def;

	public String url(String path,int i) {
		return path+newName[i];
	}
}
