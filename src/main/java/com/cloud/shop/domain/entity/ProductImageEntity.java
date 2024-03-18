package com.cloud.shop.domain.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "productImage")
@Entity
public class ProductImageEntity {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private long iNo;
	
	private String bucketKey;
	
	private boolean def;
	
	@ManyToOne
	private ProductEntity product;
	
	public ProductImageEntity update(String bucket, boolean tf) {
		
		this.bucketKey=bucket;
		this.def=tf;
		return this;	
	}
	
}
