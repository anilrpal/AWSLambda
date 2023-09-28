package com.anil.awslambda;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.internal.StaticCredentialsProvider;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

@Configuration
public class DynamoDbConfiguration {

	@Bean
	public DynamoDBMapper dynamoDBMapper() {
		return new DynamoDBMapper(buildAmazonBuilder());
	}

	private AmazonDynamoDB buildAmazonBuilder() {
		// TODO Auto-generated method stub
//		return AmazonDynamoDBClientBuilder.standard().build();
		return AmazonDynamoDBClientBuilder.standard().withEndpointConfiguration(
				new AwsClientBuilder.EndpointConfiguration("dynamodb.ap-south-1.amazonaws.com", "ap-south-1"))
				.withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials("AKIA3MHAPCXUV36WFR6R", "Bsy+H8EhzT1fVef5pQyGNE+SMEkSjIGF2zpR1KWz")))
				.build();
	}
}
