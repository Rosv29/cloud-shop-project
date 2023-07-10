package com.cloud.shop.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloud.shop.service.ProductService;
import com.cloud.shop.util.S3Util;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProductServiceProcess implements ProductService{

	private final S3Util s3Util;

	@Override
	public Map<String, String> tempImgUpload(MultipartFile temp) {
		
		return s3Util.tempImgUpload(temp);
	}
	
	
}
