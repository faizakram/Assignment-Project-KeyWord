package com.assignment.web.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CreateBucketRequest;
import com.amazonaws.services.s3.model.GetBucketLocationRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.assignment.web.dto.S3Bucket;
import com.assignment.web.exception.ErrorCodeHelper;
import com.assignment.web.exception.ErrorInfo;
import com.assignment.web.exception.ServiceException;

@Component
public class S3Utility {

	@Resource(name = CommonConstant.S3_BUCKET_OBJECT)
	private AmazonS3 amazonS3;

	@Autowired
	private ErrorCodeHelper errorCodeHelper;

	

	/**
	 * Upload On S3
	 * 
	 * @param bucketName
	 * @param fileName
	 * @param file
	 * @return
	 */
	public PutObjectResult uploadOnS3(String bucketName, String fileName, InputStream file) {
		return amazonS3.putObject(bucketName, fileName, file, null);
	}

	public PutObjectResult uploadOnS3(String bucketName, String fileName, InputStream file, String region) {
		
		return amazonS3.putObject(bucketName, fileName, file, null);
	}

	public void uploadOnS3All(String bucketName, Map<String, MultipartFile> map) {
		for (Map.Entry<String, MultipartFile> entry : map.entrySet()) {
			try {
				uploadOnS3(bucketName, entry.getKey(), entry.getValue().getInputStream());
			} catch (Exception e) {
				ErrorInfo errorInfo = errorCodeHelper.getErrorInfo(CommonConstant.E1011_ERROR_CODE,
						CommonConstant.E1011_ERROR_DESCRIPTION);
				throw new ServiceException(errorInfo, HttpStatus.EXPECTATION_FAILED);
			}
		}
	}

	public S3Object downloadObject(String bucketName, String fileName) {
		return amazonS3.getObject(bucketName, fileName);
	}

	public byte[] downloadS3Byte(String bucketName, String fileName) {
		S3Object s3object = amazonS3.getObject(bucketName, fileName);
		S3ObjectInputStream inputStream = s3object.getObjectContent();
		try {
			return FileCopyUtils.copyToByteArray(inputStream);
		} catch (Exception e) {
			ErrorInfo errorInfo = errorCodeHelper.getErrorInfo(CommonConstant.E1010_ERROR_CODE,
					CommonConstant.E1010_ERROR_DESCRIPTION);
			throw new ServiceException(errorInfo, HttpStatus.EXPECTATION_FAILED);
		}
	}

	public boolean createBucket(S3Bucket s3Bucket) {
		if (!amazonS3.doesBucketExist(s3Bucket.getBucketName())) {
			amazonS3.createBucket(
					new CreateBucketRequest(s3Bucket.getBucketName()));
			amazonS3.getBucketLocation(new GetBucketLocationRequest(s3Bucket.getBucketName()));
			return true;
		} else {
			ErrorInfo errorInfo = errorCodeHelper.getErrorInfo(CommonConstant.E1012_ERROR_CODE,
					CommonConstant.E1012_ERROR_DESCRIPTION);
			throw new ServiceException(errorInfo, HttpStatus.EXPECTATION_FAILED);
		}
	}

	public void uploadFile(S3Bucket s3Bucket) {
		s3Bucket.getFile().forEach(file -> {
			String filePath =  s3Bucket.getFilePath()+ CommonConstant.SLASH + UUID.randomUUID() + file.getOriginalFilename();
			try {
				uploadOnS3(s3Bucket.getBucketName(), filePath, file.getInputStream(),
						s3Bucket.getS3Region().getRegion());
			} catch (IOException e) {
				ErrorInfo errorInfo = errorCodeHelper.getErrorInfo(CommonConstant.E1011_ERROR_CODE,
						CommonConstant.E1011_ERROR_DESCRIPTION);
				throw new ServiceException(errorInfo, HttpStatus.EXPECTATION_FAILED);
			}
		});
	}

}
