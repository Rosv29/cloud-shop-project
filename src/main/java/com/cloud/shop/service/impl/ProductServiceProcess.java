package com.cloud.shop.service.impl;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.cloud.shop.domain.dao.ProductEntityRepository;
import com.cloud.shop.domain.dao.ProductImageEntityRepository;
import com.cloud.shop.domain.dto.ProductListDTO;
import com.cloud.shop.domain.dto.ProductSaveDTO;
import com.cloud.shop.domain.entity.ProductEntity;
import com.cloud.shop.domain.entity.ProductImageEntity;
import com.cloud.shop.service.ProductService;
import com.cloud.shop.util.S3Util;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProductServiceProcess implements ProductService {

	private final S3Util s3Util;

	private final ProductEntityRepository pRepo;

	private final ProductImageEntityRepository iRepo;

	@Value("${cloud.aws.s3.bucket.upload-path}")
	private String uploadPath;
	@Value("${cloud.aws.s3.domain}")
	private String domain;
	

	@Override
	public Map<String, String> tempImgUpload(MultipartFile temp) {

		return s3Util.tempImgUpload(temp);
	}

	@Override
	public void saveProcess(ProductSaveDTO dto) {
		ProductEntity entity = pRepo.save(dto.toProduct());
		for (int i = 0; i < dto.getTempKey().length; i++) {
			if (dto.getTempKey()[i] == null || dto.getTempKey()[i] == "")continue;
			iRepo.save(ProductImageEntity.builder()
					.bucketKey(uploadPath+dto.getNewName()[i])
					.def(dto.getDef()[i])
					.product(entity).build());
			s3Util.tempToUpload(dto.getNewName()[i]);
		}
		s3Util.clearTemp();
	}

	@Override
	public void listProcess(Model model) {
		model.addAttribute("list",
				pRepo.findAll().stream()
						.map(entity -> new ProductListDTO(entity).defImg(iRepo.findByProductAndDef(entity, true)
								.orElse(ProductImageEntity.builder().bucketKey("/image/common/noimg.jpg").build()),domain))
						.collect(Collectors.toList()));

	}

}
