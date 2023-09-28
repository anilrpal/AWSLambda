package com.anil.awslambda.service;

import java.util.List;

import com.anil.awslambda.model.EmployeeEntity;

public interface EmpService {
	EmployeeEntity save(EmployeeEntity employee);

	List<EmployeeEntity> getAllEmployees();

	EmployeeEntity getEmpByEmployeeId(String id);

	String deleteEmployeeById(String id);
}
