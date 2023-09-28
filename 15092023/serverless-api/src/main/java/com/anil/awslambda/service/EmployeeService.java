package com.anil.awslambda.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.anil.awslambda.model.Employee;
@Service
public class EmployeeService {
	
	private static final Logger log = LoggerFactory.getLogger(EmployeeService.class);
	
	@Autowired
	DynamoDBMapper dynamoDBMapper;
	
//	private void initDynamoDB() {
//		AmazonDynamoDB dynamoDB=AmazonDynamoDBClientBuilder.standard().build();
//		dynamoDBMapper=new DynamoDBMapper(dynamoDB);
//	}
	
	public Employee saveEmployee(Employee employee) {
		dynamoDBMapper.save(employee);
		log.info("Employee saved succesfully");
		return employee;
	}

	public Employee updateEmployee(Employee employee) {
		log.info("Employee update succesfull");
		dynamoDBMapper.save(employee, new DynamoDBSaveExpression().withExpectedEntry("empId", new ExpectedAttributeValue(new AttributeValue().withS(employee.getEmpId()))));
		return employee;
	}

	public Employee getEmployeeById(String empId) {
		log.info("Employee fetched "+empId);
		return dynamoDBMapper.load(Employee.class,empId);
	}

	public List<Employee> getEmployees() {
		log.info("Employees fetched ");
		return dynamoDBMapper.scan(Employee.class, new DynamoDBScanExpression());
	}

	public String deleteEmployeeById(String empId) {
		log.info("Employee delete succesfull");
		Employee employee=dynamoDBMapper.load(Employee.class,empId);
		dynamoDBMapper.delete(employee);
		return "Employee Delete!";
	}

}
