package com.anil.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Employee {
	private Integer id;
	private String name;
	private String email;
}
