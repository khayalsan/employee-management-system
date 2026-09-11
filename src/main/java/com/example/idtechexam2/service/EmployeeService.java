package com.example.idtechexam2.service;

import com.example.idtechexam2.dto.EmployeeDto;
import com.example.idtechexam2.dto.EmployeeRequest;
import com.example.idtechexam2.entity.Employee;
import com.example.idtechexam2.enums.Status;
import com.example.idtechexam2.exception.EmployeeAlreadyExistsException;
import com.example.idtechexam2.exception.EmployeeNotFoundException;
import com.example.idtechexam2.mapper.EmployeeDtoMapper;
import com.example.idtechexam2.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class EmployeeService {
	private final EmployeeRepository employeeRepository;

	public void createEmployee(EmployeeRequest employeeRequest) {
		var sameEmailEmployeeBoxed = employeeRepository.findByEmail(employeeRequest.getEmail());
		if (sameEmailEmployeeBoxed.isPresent()) {
			throw new EmployeeAlreadyExistsException("Employee already exists");
		}
		var employee = EmployeeDtoMapper.mapRequestToEmployee(employeeRequest);
		employee.setStatus(Status.ACTIVE);
		employeeRepository.save(employee);
	}

	public EmployeeDto findById(Long aLong) {
		var employeeBoxed = employeeRepository.findById(aLong);

		if (employeeBoxed.isPresent()) {
			return EmployeeDtoMapper.mapToDto(employeeBoxed.get());
		}
		throw new EmployeeNotFoundException("Employee with id " + aLong + " not found");
	}

	public List<EmployeeDto> findAll() {
		return employeeRepository.findAll().stream().map(EmployeeDtoMapper::mapToDto).toList();
	}

	public List<EmployeeDto> filterBy(String firstName, String lastName, String position, Status status, Double minSalary, Double maxSalary) {
		var employeeStream = employeeRepository.findAll().stream();
		if (status != null) {
			employeeStream = employeeStream.filter(employee -> employee.getStatus().equals(status));
		}
		if (minSalary != null && minSalary >= 0) {
			employeeStream = employeeStream.filter(employee -> employee.getSalary() >= minSalary);
		}
		if (maxSalary != null && maxSalary >= 0) {
			employeeStream =  employeeStream.filter(employee -> employee.getSalary() <= maxSalary);
		}
		if (firstName != null && !firstName.isBlank()) {
			employeeStream = employeeStream.filter(employee -> employee.getFirstName().equalsIgnoreCase(firstName));
		}
		if (lastName != null && !lastName.isBlank()) {
			employeeStream = employeeStream.filter(employee -> employee.getLastName().equalsIgnoreCase(lastName));
		}
		if (position != null && !position.isBlank()) {
			employeeStream = employeeStream.filter(employee -> employee.getPosition().equalsIgnoreCase(position));
		}
		return employeeStream.map(EmployeeDtoMapper::mapToDto).toList();
	}

	private Stream<EmployeeDto> filterFirstName(Stream<EmployeeDto> stream, String firstName) {
		if (firstName.isEmpty()) { return stream; }

		return stream.filter(employee -> employee.getFirstName().contains(firstName));
	}

	private Stream<EmployeeDto> filterLastName(Stream<EmployeeDto> stream, String lastName) {
		if (lastName.isEmpty()) { return stream; }
		return stream.filter(employee -> employee.getLastName().contains(lastName));
	}



	public Employee update(Long id, Employee employee) {
		var toBeUpdated = employeeRepository.findById(id);
		if (toBeUpdated.isPresent()) {
			employee.setId(toBeUpdated.get().getId());
			var employeeWithRequestedMailBoxed = employeeRepository.findByEmail(employee.getEmail());
			if (employeeWithRequestedMailBoxed.isPresent()) {
				if (!employeeWithRequestedMailBoxed.get().getId().equals(employee.getId())) {
					throw new EmployeeAlreadyExistsException("Employee with that email already exists");
				}
			}
			return employeeRepository.save(employee);
		}
		throw new EmployeeNotFoundException("Employee with id " + id + " not found");
	}

	public void delete(Long aLong) {
		var toBeDeleted = employeeRepository.findById(aLong);

		if (toBeDeleted.isPresent()) {
			employeeRepository.deleteById(aLong);
			return;
		}
		throw new EmployeeNotFoundException("Employee with id " + aLong + " not found");
	}
}

