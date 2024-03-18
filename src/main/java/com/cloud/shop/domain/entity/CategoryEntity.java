package com.cloud.shop.domain.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="productCategory")
@Entity
public class CategoryEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long no;//카테고리 번호
	
	private String name; //카테고리 이름
	
	//셀프조인 하기위한 관계설정 FK(parent_no) 생성
	@ManyToOne(fetch = FetchType.LAZY)//default: eager
	private CategoryEntity parent;//상위카테고리

	public CategoryEntity update(String val) {
		this.name=val;
		return this;
		
	}
	
}
