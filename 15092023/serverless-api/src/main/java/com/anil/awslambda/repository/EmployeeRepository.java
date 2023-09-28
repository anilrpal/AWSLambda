package com.anil.awslambda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anil.awslambda.model.EmployeeEntity;

public interface EmployeeRepository  extends JpaRepository<EmployeeEntity, String> {

}
