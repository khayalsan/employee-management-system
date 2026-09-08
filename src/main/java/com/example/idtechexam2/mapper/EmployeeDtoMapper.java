package com.example.idtechexam2.mapper;

import com.example.idtechexam2.dto.EmployeeDto;
import com.example.idtechexam2.dto.EmployeeRequest;
import com.example.idtechexam2.entity.Employee;

public interface EmployeeDtoMapper {
	static EmployeeDto mapToDto(Employee employee) {
		var employeeDto = new EmployeeDto();
		employeeDto.setId(employee.getId());
		employeeDto.setFirstName(employee.getFirstName());
		employeeDto.setLastName(employee.getLastName());
		employeeDto.setPosition(employee.getPosition());
		employeeDto.setSalary(employee.getSalary());
		employeeDto.setStatus(employee.getStatus());
		return employeeDto;
	}

	static Employee mapRequestToEmployee(EmployeeRequest employeeRequest) {
		var employee = new Employee();
		employee.setFirstName(employeeRequest.getFirstName());
		employee.setLastName(employeeRequest.getLastName());
		employee.setPosition(employeeRequest.getPosition());
		employee.setSalary(employeeRequest.getSalary());
		employee.setHireDate(employeeRequest.getHireDate());
		employee.setEmail(employeeRequest.getEmail());
		employee.setPhoneNumber(employeeRequest.getPhoneNumber());
		return employee;
	}
}
