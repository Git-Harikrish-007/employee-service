package com.harikrish.employeeservice.service.impl;

import org.jspecify.annotations.Nullable;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.harikrish.employeeservice.dto.APIResponseDto;
import com.harikrish.employeeservice.dto.DepartmentDto;
import com.harikrish.employeeservice.dto.EmployeeDto;
import com.harikrish.employeeservice.entity.Employee;
import com.harikrish.employeeservice.exception.ResourceNotFoundException;
import com.harikrish.employeeservice.repository.EmployeeRepository;
import com.harikrish.employeeservice.service.APIClient;
import com.harikrish.employeeservice.service.EmployeeService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepository employeeRepository;

	private ModelMapper modelMapper;

//	private RestTemplate restTemplate;
	
//	private WebClient webClient;
	
	private APIClient apiClient;

	@Override
	public EmployeeDto saveEmployee(EmployeeDto employeeDto) {

		Employee employee = modelMapper.map(employeeDto, Employee.class);

		Employee savedEmployee = employeeRepository.save(employee);

		return modelMapper.map(savedEmployee, EmployeeDto.class);
	}

	@Override
	public APIResponseDto getEmployeeById(Long employeeId) {

		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("EmployeeId not exists in database"));

//		ResponseEntity<DepartmentDto> responseEntity = restTemplate.getForEntity(
//				"http://localhost:8080/api/departments/" + employee.getDepartmentCode(), DepartmentDto.class);
//
//		DepartmentDto departmentDto = responseEntity.getBody();

//		DepartmentDto departmentDto = webClient.get()
//				.uri("http://localhost:8080/api/departments/" + employee.getDepartmentCode()).retrieve()
//				.bodyToMono(DepartmentDto.class).block();
		
		DepartmentDto departmentDto = apiClient.getDepartment(employee.getDepartmentCode());
		
		APIResponseDto apiResponseDto = new APIResponseDto();

		apiResponseDto.setDepartment(departmentDto);

		EmployeeDto employeeDto = modelMapper.map(employee, EmployeeDto.class);

		apiResponseDto.setEmployee(employeeDto);

		return modelMapper.map(apiResponseDto, APIResponseDto.class);
	}

}
