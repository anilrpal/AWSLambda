package com.anil.awslambda.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anil.awslambda.model.Employee;
import com.anil.awslambda.service.EmployeeService;

@RestController
@RequestMapping("/employee")
@CrossOrigin(origins = "*")
public class EmployeeController {
	private static final Logger log = LoggerFactory.getLogger(EmployeeController.class);
	
	@Autowired
	EmployeeService employeeService;
	
	@PostMapping
	public Employee saveEmployee(@RequestBody Employee employee) {
		log.info("saveEmployee called");
		return employeeService.saveEmployee(employee);
	}
	
	@PutMapping
	public Employee updateEmployee(@RequestBody Employee employee) {
		log.info("updateEmployee called");
		return employeeService.updateEmployee(employee);
	}
	
	@GetMapping("/{empId}")
	public Employee getEmployeeById(@PathVariable String empId) {
		log.info("getEmployeeById called");
		return employeeService.getEmployeeById(empId);
	}
	
	@GetMapping("/employees")
	public List<Employee> getEmployees() {
		log.info("getEmployees called");
		return employeeService.getEmployees();
	}
	
	@DeleteMapping("/{empId}")
	public String deleteEmployeeById(@PathVariable String empId) {
		log.info("deleteEmployeeById called");
		return employeeService.deleteEmployeeById(empId);
	}
	
	
}
