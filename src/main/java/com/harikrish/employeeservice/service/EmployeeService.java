package com.harikrish.employeeservice.service;

import com.harikrish.employeeservice.dto.EmployeeDto;

public interface EmployeeService {

	EmployeeDto saveEmployee(EmployeeDto employeeDto);

	EmployeeDto getEmployeeById(Long employeeId);

}
