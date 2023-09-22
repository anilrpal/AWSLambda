package com.anil;

import java.io.IOException;
import java.io.InputStream;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.util.IOUtils;
import com.anil.entity.Employee;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class LambdaHandler implements RequestHandler<S3Event, String> {
	
	AmazonS3 amazonS3;
	DynamoDBMapper dbMapper;

	@Override
	public String handleRequest(S3Event input, Context context) {
		String bucketName=input.getRecords().get(0).getS3().getBucket().getName();
		String fileName=input.getRecords().get(0).getS3().getObject().getKey();
		context.getLogger().log("Bucket Name : "+bucketName);
		context.getLogger().log("File Name : "+fileName);
		
		initAmazonS3client();
		InputStream inputStream=amazonS3.getObject(bucketName, fileName).getObjectContent();
		
		String content=null;
		try {
			content=IOUtils.toString(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			Employee employee= new ObjectMapper().readValue(content, Employee.class);
			initDynamoDb();
			
			dbMapper.save(employee);
			
			context.getLogger().log("successfully saved data to dynamo DB.");
			
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "successfully saved data to dynamo DB.";
	}
	
	private void initAmazonS3client() {
		amazonS3=AmazonS3ClientBuilder.standard().build();
	}
	
	private void initDynamoDb() {
		AmazonDynamoDB amazonDynamoDB=AmazonDynamoDBClientBuilder.standard().build();
		dbMapper=new DynamoDBMapper(amazonDynamoDB);
	}

}
