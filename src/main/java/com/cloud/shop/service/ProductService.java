package com.cloud.shop.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public interface ProductService {

	Map<String, String> tempImgUpload(MultipartFile temp);

}
