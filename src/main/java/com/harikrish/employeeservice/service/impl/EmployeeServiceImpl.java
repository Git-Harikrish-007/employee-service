package com.harikrish.employeeservice.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.harikrish.employeeservice.dto.EmployeeDto;
import com.harikrish.employeeservice.entity.Employee;
import com.harikrish.employeeservice.exception.ResourceNotFoundException;
import com.harikrish.employeeservice.repository.EmployeeRepository;
import com.harikrish.employeeservice.service.EmployeeService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepository employeeRepository;

	private ModelMapper modelMapper;

	@Override
	public EmployeeDto saveEmployee(EmployeeDto employeeDto) {

		Employee employee = modelMapper.map(employeeDto, Employee.class);

		Employee savedEmployee = employeeRepository.save(employee);

		return modelMapper.map(savedEmployee, EmployeeDto.class);
	}

	@Override
	public EmployeeDto getEmployeeById(Long employeeId) {

		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("EmployeeId not exists in database"));

		return modelMapper.map(employee, EmployeeDto.class);
	}

}
