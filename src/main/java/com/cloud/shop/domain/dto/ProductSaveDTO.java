package com.cloud.shop.domain.dto;

import com.cloud.shop.domain.entity.ProductEntity;
import lombok.Data;

@Data
public class ProductSaveDTO {
	
	
	private String pName;

	private int pPrice;

	private int pStock;

	private String pContent;

	private boolean pStatus;

	public ProductEntity toProduct() {
		return ProductEntity.builder().pName(pName).pPrice(pPrice).pStock(pStock).pStatus(pStatus).pContent(pContent)
				.build();
	}

	/////////////////////
	private String[] tempKey;

	private String[] newName;
	
	private boolean[] def;

}
