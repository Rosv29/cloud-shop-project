package com.cloud.shop.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.cloud.shop.domain.dao.CategoryEntityRepository;
import com.cloud.shop.domain.dao.ProductEntityRepository;
import com.cloud.shop.domain.dao.ProductImageEntityRepository;
import com.cloud.shop.domain.dto.ProductListDTO;
import com.cloud.shop.domain.dto.ProductSaveDTO;
import com.cloud.shop.domain.entity.CategoryEntity;
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
	private final CategoryEntityRepository cRepo;

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
		CategoryEntity cate = cRepo.findById(dto.getCategoryNo()).orElseThrow();

		ProductEntity entity = pRepo.save(dto.toProduct(cate));

		for (int i = 0; i < dto.getTempKey().length; i++) {
			if (dto.getTempKey()[i] == null || dto.getTempKey()[i] == "")
				continue;
			iRepo.save(ProductImageEntity.builder().bucketKey(dto.url(uploadPath, i)).def(dto.getDef()[i])
					.product(entity).build());
			s3Util.tempToUpload(dto.getNewName()[i]);
		}
		s3Util.clearTemp();
	}

	@Override
	public void listProcess(Model model) {
		model.addAttribute("list", pRepo.findAll().stream()
				.map(entity -> new ProductListDTO(entity).defImg(iRepo.findByProductAndDef(entity, true)
						.orElse(ProductImageEntity.builder().bucketKey("/images/common/noimg.jpg").build()), domain))
				.collect(Collectors.toList()));

	}

	@Override
	public void detailProcess(long no, Model model) {
		ProductEntity result = pRepo.findById(no).orElseThrow();
		model.addAttribute("pd", result);

		Optional<ProductImageEntity> defImg = iRepo.findByProductAndDef(result, true);

		if (defImg.isPresent())
			model.addAttribute("defImg", defImg.get());
		List<ProductImageEntity> imgList = iRepo.findAllByProductAndDef(result, false);
		if (!imgList.isEmpty())
			model.addAttribute("imgList", imgList);

	}

	@Override
	public void detailProcess2(long no, Model model) {
		ProductEntity result = pRepo.findById(no).orElseThrow();
		model.addAttribute("pd", result);
		
		List<ProductImageEntity> imgs= iRepo.findAllByProduct(result);
		model.addAttribute("imgs",imgs);
	}
	
	@Override
	public void updateProcess(ProductSaveDTO dto) {
		CategoryEntity cate = cRepo.findById(dto.getCategoryNo()).orElseThrow();
		ProductEntity entity = pRepo.findById(dto.getPNo()).orElseThrow().update(dto, cate);
		pRepo.save(entity);
		List<ProductImageEntity> list = iRepo.findAllByProduct(entity);
		// 변경된이미지개수 세기
		int changeCount = 0;

		for (int i = 0; i < dto.getTempKey().length; i++) {
			if (dto.getTempKey()[i] != "")
				changeCount++;
		}

		// 기존 이미지 개수 >= 변경된 이미지 개수
		for (int i = 0; i < dto.getTempKey().length; i++) {
			if ((dto.getTempKey()[i] != "") && list.size() >= changeCount) {
				// 기존 이미지가 최대개수가 아니고 추가이미지가 생겼을때
				if (list.size() + 1 < dto.getTempKey().length) {
					iRepo.save(ProductImageEntity.builder().bucketKey(dto.url(uploadPath, i)).def(dto.getDef()[i])
							.product(entity).build());

					s3Util.tempToUpload(dto.getNewName()[i]);

				} else {
					s3Util.updateImg(list.get(i).getBucketKey());
					iRepo.save(list.get(i).update(dto.url(uploadPath, i), dto.getDef()[i]));
					s3Util.tempToUpload(dto.getNewName()[i]);
				}
				// 기존 이미지 개수 < 변경된 이미지 개수
			} else if ((dto.getTempKey()[i] != "") && list.size() < changeCount) {
				if (i < list.size()) {
					s3Util.updateImg(list.get(i).getBucketKey());
					iRepo.save(list.get(i).update(dto.url(uploadPath, i), dto.getDef()[i]));
					s3Util.tempToUpload(dto.getNewName()[i]);
				} else {
					iRepo.save(ProductImageEntity.builder().bucketKey(dto.url(uploadPath, i)).def(dto.getDef()[i])
							.product(entity).build());
					s3Util.tempToUpload(dto.getNewName()[i]);
				}
			}
		}
		s3Util.clearTemp();
	}

	@Override
	public void deleteProcess(long pno) {
		ProductEntity entity = pRepo.findById(pno).orElseThrow();
		List<ProductImageEntity> list = iRepo.findAllByProduct(entity);
		list.forEach(dto -> s3Util.updateImg(dto.getBucketKey()));
		list.forEach(dto -> iRepo.delete(dto));
		pRepo.delete(entity);

	}

	@Override
	public void findAllProcess(Model model) {
		model.addAttribute("list", pRepo.findAll().stream()
				.map(entity -> new ProductListDTO(entity).defImg(iRepo.findByProductAndDef(entity, true)
						.orElse(ProductImageEntity.builder().bucketKey("/images/common/noimg.jpg").build()), domain))
				.collect(Collectors.toList()));

	}


}
