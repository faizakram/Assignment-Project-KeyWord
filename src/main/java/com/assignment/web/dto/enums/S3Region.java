package com.assignment.web.dto.enums;

public enum S3Region {
	USEAST1("us-east-1"), 
	USEAST2("us-east-2"), 
	USWEST1("us-west-1"), 
	USWEST2("us-west-2"), 
	APEAST1("ap-east-1"),
	APSOUTH1("ap-south-1");
	
	private String region;

	public String getRegion() {
		return region;
	}

	private S3Region(String region) {
		this.region = region;
	}
}
