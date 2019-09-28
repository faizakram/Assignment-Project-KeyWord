package com.assignment.web.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
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

	@Value("${cloud.aws.credentials.accessKey}")
	private String accessKey;

	@Value("${cloud.aws.credentials.secretKey}")
	private String secretkey;

	@Value("${cloud.aws.region.static}")
	private String regions;

	@Autowired
	private ErrorCodeHelper errorCodeHelper;

	/**
	 * Get Basic AWS Credentials
	 * 
	 * @param accesskey
	 * @param secretkey
	 * @return
	 */
	private AWSCredentials getBasicAWSCredentials(String accesskey, String secretkey) {
		return new BasicAWSCredentials(accesskey, secretkey);
	}

	/**
	 * Get AmazonS3
	 * 
	 * @return
	 */
	private AmazonS3 getAmazonS3() {
		return AmazonS3ClientBuilder.standard()
				.withCredentials(new AWSStaticCredentialsProvider(getBasicAWSCredentials(accessKey, secretkey)))
				.withRegion(regions).build();
	}

	private AmazonS3 getAmazonS3(String region) {
		return AmazonS3ClientBuilder.standard()
				.withCredentials(new AWSStaticCredentialsProvider(getBasicAWSCredentials(accessKey, secretkey)))
				.withRegion(region).build();
	}

	/**
	 * Upload On S3
	 * 
	 * @param bucketName
	 * @param fileName
	 * @param file
	 * @return
	 */
	public PutObjectResult uploadOnS3(String bucketName, String fileName, InputStream file) {
		return getAmazonS3().putObject(bucketName, fileName, file, null);
	}

	public PutObjectResult uploadOnS3(String bucketName, String fileName, InputStream file, String region) {
		return getAmazonS3(region).putObject(bucketName, fileName, file, null);
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
		return getAmazonS3().getObject(bucketName, fileName);
	}

	public byte[] downloadS3Byte(String bucketName, String fileName) {
		S3Object s3object = getAmazonS3().getObject(bucketName, fileName);
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
		AmazonS3 s3Client = getAmazonS3(s3Bucket.getS3Region().getRegion());
		if (!s3Client.doesBucketExist(s3Bucket.getBucketName())) {
			s3Client.createBucket(
					new CreateBucketRequest(s3Bucket.getBucketName(), s3Bucket.getS3Region().getRegion()));
			s3Client.getBucketLocation(new GetBucketLocationRequest(s3Bucket.getBucketName()));
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
