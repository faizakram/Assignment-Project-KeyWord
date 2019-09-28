package com.assignment.web.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.web.dto.ResponseJson;
import com.assignment.web.dto.S3Bucket;
import com.assignment.web.utils.CommonConstant;
import com.assignment.web.utils.S3Utility;

/**
 * 
 * EnumItem Master Data
 *
 */
@RestController
@RequestMapping(CommonConstant.S3_URL)
public class S3BucketController {

	@Autowired
	private S3Utility s3Utility;

	@Autowired
	private ResponseJson response;

	@Resource(name = CommonConstant.COMMON_MAP_OBJECT)
	private Map<String, Object> model;

	/**
	 * Get EnumItem By EnumCode
	 * 
	 * @param id
	 * @return
	 */
	@PostMapping(CommonConstant.S3_CREATE_BUCKET)
	public ResponseJson createS3Bucket(S3Bucket s3Bucket) {
		if (s3Utility.createBucket(s3Bucket))
			response.setResponseDescription(CommonConstant.S0005_SUCCESS_DESCRIPTION);
		else
			response.setResponseDescription(CommonConstant.S0006_SUCCESS_DESCRIPTION);
		return response;
	}

	@PostMapping(CommonConstant.UPLOAD_FILE)
	public ResponseJson uploadFile(S3Bucket s3Bucket) {
		response.setResponseDescription(CommonConstant.S0002_SUCCESS_DESCRIPTION);
		s3Utility.uploadFile(s3Bucket);
		return response;
	}

}
