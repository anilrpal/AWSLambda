package com.anil.service;

import java.util.List;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.anil.entity.Employee;
import com.anil.utility.Utility;

public class EmployeeService {
	private DynamoDBMapper dynamoDBMapper;
	private String jsonBody=null;
	
	private void initDynamoDB() {
		AmazonDynamoDB dynamoDB=AmazonDynamoDBClientBuilder.standard().build();
		dynamoDBMapper=new DynamoDBMapper(dynamoDB);
	}
	
	public APIGatewayProxyResponseEvent saveEmployee(APIGatewayProxyRequestEvent requestEvent,Context context) {
		initDynamoDB();
		Employee employee=Utility.convertStringToObj(requestEvent.getBody(), context);
		dynamoDBMapper.save(employee);
		jsonBody=Utility.convertObjToString(employee, context);
		context.getLogger().log("Employee saved successfully "+jsonBody);
		return createApiResponse(jsonBody, 201, Utility.createHeaders());
	}
	
	public APIGatewayProxyResponseEvent getEmployeeById(APIGatewayProxyRequestEvent requestEvent,Context context) {
		initDynamoDB();
		String empId=requestEvent.getPathParameters().get("empId");
		Employee employee=dynamoDBMapper.load(Employee.class,empId);
		if(employee!=null) {
			jsonBody=Utility.convertObjToString(employee, context);
			context.getLogger().log("Fetched employee by id "+jsonBody);
			return createApiResponse(jsonBody, 200, Utility.createHeaders());
		}else {
			jsonBody="Employee Not Found.";
			return createApiResponse(jsonBody, 400, Utility.createHeaders());
		}
	}
	
	
	public APIGatewayProxyResponseEvent getEmployees(APIGatewayProxyRequestEvent requestEvent,Context context) {
		initDynamoDB();
		List<Employee> employees=dynamoDBMapper.scan(Employee.class, new DynamoDBScanExpression());
		jsonBody=Utility.convertListOfObjToString(employees, context);
		context.getLogger().log("Fetched Employees "+jsonBody);
		return createApiResponse(jsonBody, 201, Utility.createHeaders());
	}
	
	public APIGatewayProxyResponseEvent deleteEmployeeById(APIGatewayProxyRequestEvent requestEvent,Context context) {
		initDynamoDB();
		String empId=requestEvent.getPathParameters().get("empId");
		Employee employee=dynamoDBMapper.load(Employee.class,empId);
		if(employee!=null) {
			dynamoDBMapper.delete(employee);
			jsonBody="Data deleted succesfully";
			context.getLogger().log(jsonBody);
			return createApiResponse(jsonBody, 200, Utility.createHeaders());
		}else {
			jsonBody="Employee Not Found.";
			return createApiResponse(jsonBody, 400, Utility.createHeaders());
		}
	}
	
	//Not called from lambda handler
	
	public APIGatewayProxyResponseEvent updateEmployee(APIGatewayProxyRequestEvent requestEvent,Context context) {
		initDynamoDB();
		Employee employee=Utility.convertStringToObj(requestEvent.getBody(), context);
		dynamoDBMapper.save(employee, new DynamoDBSaveExpression().withExpectedEntry("empId", new ExpectedAttributeValue(new AttributeValue().withS(employee.getEmpid()))));
//		dynamoDBMapper.save(employee);
		jsonBody=Utility.convertObjToString(employee, context);
		context.getLogger().log("Employee saved successfully "+jsonBody);
		return createApiResponse(jsonBody, 201, Utility.createHeaders());
	}
	
	
	private APIGatewayProxyResponseEvent createApiResponse(String body,Integer statusCode,Map<String,String> headers) {
		APIGatewayProxyResponseEvent proxyResponseEvent=new APIGatewayProxyResponseEvent();
		proxyResponseEvent.setBody(body);
		proxyResponseEvent.setStatusCode(statusCode);
		proxyResponseEvent.setHeaders(headers);
		return proxyResponseEvent;
	}
}
