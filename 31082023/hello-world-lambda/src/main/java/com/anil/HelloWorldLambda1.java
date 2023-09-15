package com.anil;

import java.util.HashMap;
import java.util.UUID;

import com.amazonaws.services.lambda.runtime.Context;

public class HelloWorldLambda1 {
	
	private String lambdaId=UUID.randomUUID().toString();
	
	public Response handler(Request input, Context context) throws InterruptedException {
		Thread.sleep(3000);
		System.out.println("Input : "+input.pathParameters);
		
		String greeting=System.getenv().getOrDefault("GREETING", "Hello");
		
//		String message= String.format("Hello, %s! ,  from Lambda Id %s", input.pathParameters.get("name"),lambdaId);
		
		String message= String.format("%s, %s!, from Lambda Id %s. time remaining in milisecond %d.", greeting,input.pathParameters.get("name"),lambdaId,context.getRemainingTimeInMillis());
		
		System.out.println(message);
		return new Response(200, message);
	}
	
	
	public static class Request{
		public HashMap<String,String> pathParameters=new HashMap<>();
	}
	
	public static class Response{
		public Integer statusCode;
		public String body;
		
		Response(Integer statusCode, String body) {
			super();
			this.statusCode = statusCode;
			this.body = body;
		}
	}
}
