package com.example.web.util;

public class Constants {

	public static final String AWS_S3_BUCKET = "aws.s3.bucket";
	public static final String AWS_S3_BUCKET_ENV = "aws.s3.attachment.upload";
	public static final String AWS_ACCESS_KEY = "aws.accessKey";
	public static final String AWS_SECRET_KEY = "aws.secretKey";
	public static final String AWS_S3_BUCKET_URL = "aws.s3.bucket.endpoint.url";
	
	public enum Role{
		ADMIN(1), INTERNAL_USER(2), FIELD_USER(3);
		
		private Integer id;
		
		Role(Integer id){
			this.id = id;
		}
		
		public static Integer getIdByType(String type){
			for(Role role : Role.values()){
				if(type.equalsIgnoreCase(role.name())){
					return role.id;
				}
			}
			return null;
		}
	}
	
	public enum UserType{
		RYDEE, RYDER 
	}
	
	public enum PaymentType{
		NEW_USERS, PLAN_CHANGE 
	}
	
}
