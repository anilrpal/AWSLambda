package com.anil.awslambda.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anil.awslambda.model.EmployeeEntity;
import com.anil.awslambda.repository.EmployeeRepository;

@Service
public class EmpServiceImpl implements EmpService {
	
	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public EmployeeEntity save(EmployeeEntity employee) {
		employeeRepository.save(employee);
		return employee;
	}

	@Override
	public List<EmployeeEntity> getAllEmployees() {
		return employeeRepository.findAll();
	}

	@Override
	public EmployeeEntity getEmpByEmployeeId(String id) {
		return employeeRepository.findById(id).get();
	}

	@Override
	public String deleteEmployeeById(String id) {
		employeeRepository.deleteById(id);
		return "Employee deleted succesfully with id : "+id;
	}

}
