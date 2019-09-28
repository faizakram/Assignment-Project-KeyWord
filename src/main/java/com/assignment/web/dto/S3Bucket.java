package com.assignment.web.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.assignment.web.dto.enums.S3Region;

public class S3Bucket {
	private String bucketName;
	private S3Region s3Region;
	private String filePath;
	private List<MultipartFile> file;

	public S3Bucket() {
	}

	public S3Bucket(String bucketName, S3Region s3Region) {
		super();
		this.bucketName = bucketName;
		this.s3Region = s3Region;
	}

	public S3Bucket(String bucketName, S3Region s3Region, List<MultipartFile> file) {
		super();
		this.bucketName = bucketName;
		this.s3Region = s3Region;
		this.file = file;
	}

	public String getBucketName() {
		return bucketName;
	}

	public void setBucketName(String bucketName) {
		this.bucketName = bucketName;
	}

	public S3Region getS3Region() {
		return s3Region;
	}

	public void setS3Region(S3Region s3Region) {
		this.s3Region = s3Region;
	}

	public List<MultipartFile> getFile() {
		return file;
	}

	public void setFile(List<MultipartFile> file) {
		this.file = file;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

}
