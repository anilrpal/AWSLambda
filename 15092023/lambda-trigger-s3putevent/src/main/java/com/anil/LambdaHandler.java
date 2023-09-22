package com.anil;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.util.IOUtils;

public class LambdaHandler implements RequestHandler<S3Event, String> {

	public static final String REGION="ap-south-1";
	
	AmazonS3 s3Client=AmazonS3ClientBuilder
			.standard()
			.withRegion(Regions.fromName(REGION))
			.withCredentials(new DefaultAWSCredentialsProviderChain())
			.build();
	
	@Override
	public String handleRequest(S3Event input, Context context) {
		String bucketName=input.getRecords().get(0).getS3().getBucket().getName();
		String fileName=input.getRecords().get(0).getS3().getObject().getKey();

		context.getLogger().log("Bucket Name : "+bucketName);
		context.getLogger().log("File Name : "+fileName);
		
		InputStream inputStream=s3Client.getObject(bucketName,fileName).getObjectContent();
		try {
			String content=IOUtils.toString(inputStream);
			
			context.getLogger().log("File Content : "+content);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
