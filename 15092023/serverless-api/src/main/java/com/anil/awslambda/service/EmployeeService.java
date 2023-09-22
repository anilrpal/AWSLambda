package com.anil.awslambda.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.anil.awslambda.model.Employee;

public class EmployeeService {
	
	private static final Logger log = LoggerFactory.getLogger(EmployeeService.class);
	
	@Autowired
	DynamoDBMapper dynamoDBMapper;
	
	public Employee saveEmployee(Employee employee) {
		dynamoDBMapper.save(employee);
		log.info("Employee saved succesfully");
		return employee;
	}

	public Employee updateEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return null;
	}

	public Employee getEmployeeById(String empId) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Employee> getEmployees() {
		// TODO Auto-generated method stub
		return null;
	}

	public String deleteEmployeeById(String empId) {
		// TODO Auto-generated method stub
		return null;
	}

}
