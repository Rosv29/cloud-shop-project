package com.cloud.shop.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.CopyObjectRequest;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.cloud.shop.domain.entity.ProductImageEntity;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class S3Util {
	private final AmazonS3Client client;

	@Value("${cloud.aws.s3.bucket}")
	private String bucketName;
	@Value("${cloud.aws.s3.bucket.temp-path}")
	private String tempPath;
	@Value("${cloud.aws.s3.bucket.upload-path}")
	private String uploadPath;
	@Value("${cloud.aws.s3.domain}")
	private String domain;

	public Map<String, String> tempImgUpload(MultipartFile tempFile) {

		MultipartFile temp = tempFile;
		String newName = createNewFileName(temp.getOriginalFilename());
		String tempKey = tempPath + newName;
		Map<String, String> resultMap = new HashMap<>();
		try {
			InputStream is = temp.getInputStream();
			ObjectMetadata objectMetadata = new ObjectMetadata();
			objectMetadata.setContentLength(temp.getSize());
			objectMetadata.setContentType(temp.getContentType());
			PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, tempKey, is, objectMetadata);
			client.putObject(putObjectRequest.withCannedAcl(CannedAccessControlList.PublicRead));
			String imgUrl = client.getUrl(bucketName, tempKey).toString().substring(6);
			resultMap.put("imgUrl", imgUrl);
			resultMap.put("newName", newName);
			resultMap.put("tempKey", tempKey);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return resultMap;

	}

	private String createNewFileName(String orgName) {
		int idx = orgName.lastIndexOf(".");
		return UUID.randomUUID().toString() + orgName.substring(idx); // 확장자
	}

	public void tempToUpload(String newName) {
		String bucketKey = tempPath + newName;
		String uploadKey = uploadPath + newName;
		// System.out.println("##"+newName);
		// System.out.println("###"+bucketKey);
		// System.out.println("####"+uploadKey);
		CopyObjectRequest copyObjectRequest = new CopyObjectRequest(bucketName, bucketKey, bucketName, uploadKey);
		client.copyObject(copyObjectRequest.withCannedAccessControlList(CannedAccessControlList.PublicRead));
		client.deleteObject(bucketName, bucketKey);
	}

	public void updateImg(String bucketKey) {
		client.deleteObject(bucketName, bucketKey);
	}

	public void clearTemp() {
		// 템프 경로의 목록을 갖고와서 제거
		// 폴더 내의 모든 객체를 삭제
		ObjectListing objectListing = client.listObjects(bucketName, tempPath);
		while (true) {
			for (S3ObjectSummary objectSummary : objectListing.getObjectSummaries()) {
				client.deleteObject(bucketName, objectSummary.getKey());
			}

			if (objectListing.isTruncated()) {
				objectListing = client.listNextBatchOfObjects(objectListing);
			} else {
				break;
			}
		}

		// 폴더를 삭제
		client.deleteObject(bucketName, tempPath);

	}

}
