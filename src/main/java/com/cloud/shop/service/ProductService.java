package com.cloud.shop.service;

import java.util.Map;
import java.util.Optional;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.cloud.shop.domain.dto.ProductSaveDTO;

public interface ProductService {

	Map<String, String> tempImgUpload(MultipartFile temp);

	void saveProcess(ProductSaveDTO dto);

	void listProcess(Model model);

	void detailProcess(long no, Model model);

	void updateProcess(ProductSaveDTO dto);

	void deleteProcess(long pno);

	void findAllProcess(Model model);

	void detailProcess2(long no, Model model);

}
