package com.anil.awslambda.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anil.awslambda.model.EmployeeEntity;
import com.anil.awslambda.service.EmpService;

@RestController
@RequestMapping("/v2/employees")
@CrossOrigin(origins = "*")
public class EmployeeControllerV2 {
	
	@Autowired
	private EmpService empServ;
	
	@PostMapping
	public EmployeeEntity save(@RequestBody EmployeeEntity employee) {
		return empServ.save(employee);
	}
	
	@GetMapping
	public List<EmployeeEntity> getAllEmployees(){
		return empServ.getAllEmployees();
	}
	
	@GetMapping("/{id}")
	public EmployeeEntity getEmpByEmployeeId(@PathVariable String id) {
		return empServ.getEmpByEmployeeId(id);
	}
	
	@DeleteMapping("/{id}")
	public String deleteEmployeeById(@PathVariable String id) {
		return empServ.deleteEmployeeById(id);
	}
}