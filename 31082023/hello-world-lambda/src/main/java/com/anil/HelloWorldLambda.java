package com.anil;

public class HelloWorldLambda {
	public String handler(Object[] input) {
		String message= String.format("Hello, %s!", input);
		System.out.println(message);
		return message;
	}
}
