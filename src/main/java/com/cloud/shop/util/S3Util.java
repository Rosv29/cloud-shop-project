package com.cloud.shop.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

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
	
	public Map<String, String> tempImgUpload(MultipartFile temp) {
		Map<String, String> resultMap=new HashMap<>();
		String newName=createNewFileName(temp.getOriginalFilename());
		String tempKey=tempPath+newName;
		try {
			InputStream is=temp.getInputStream();
			ObjectMetadata objectMetadata = new ObjectMetadata();
			objectMetadata.setContentLength(temp.getSize());
			objectMetadata.setContentType(temp.getContentType());
			PutObjectRequest putObjectRequest=new PutObjectRequest(bucketName, tempKey, is, objectMetadata);
			client.putObject(putObjectRequest.withCannedAcl(CannedAccessControlList.PublicRead));
			String imgUrl=client.getUrl(bucketName, tempKey).toString().substring(6);
			resultMap.put("imgUrl", imgUrl);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resultMap;
	}

	private String createNewFileName(String orgName) {
		int idx=orgName.lastIndexOf(".");
		return UUID.randomUUID().toString()
				+orgName.substring(idx); //확장자
	}

}
