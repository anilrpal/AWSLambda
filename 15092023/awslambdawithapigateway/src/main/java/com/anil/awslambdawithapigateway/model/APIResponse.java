package com.anil.awslambdawithapigateway.model;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class APIResponse {
	private Integer statusCode;
	private String body;
	private Map<String,String> headers;
}
