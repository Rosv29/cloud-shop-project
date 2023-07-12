package com.cloud.shop.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import com.cloud.shop.domain.dto.ProductSaveDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@DynamicUpdate
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product")
@Entity
public class ProductEntity extends BaseDateEntity{

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private long pNo;
	
	@Column(nullable = false)
	private String pName;
	
	@Column(nullable = false)
	private int pPrice;
	
	@Column(nullable = false)
	private int pStock;
	
	@Column(nullable = false)
	private String pContent;
	
	@Column(nullable = false)
	private boolean pStatus;

	public ProductEntity update(ProductSaveDTO dto) {
		if(!this.pName.equals(dto.getPName()))this.pName=dto.getPName();
		if(!(this.pPrice==dto.getPPrice()))this.pPrice=dto.getPPrice();
		if(!(this.pStock==dto.getPStock()))this.pStock=dto.getPStock();
		if(!this.pContent.equals(dto.getPContent()))this.pContent=dto.getPContent();
		if(this.pStatus!=dto.isPStatus())this.pStatus=dto.isPStatus();
		
		return this;
	}
	
}
