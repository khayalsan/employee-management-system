package com.example.idtechexam2.controller;

import com.example.idtechexam2.dto.EmployeeDto;
import com.example.idtechexam2.dto.EmployeeRequest;
import com.example.idtechexam2.entity.Employee;
import com.example.idtechexam2.enums.Status;
import com.example.idtechexam2.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

	private final EmployeeService employeeService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void createEmployee(@RequestBody EmployeeRequest employee) {
		employeeService.createEmployee(employee);
	}

	@GetMapping
	public List<EmployeeDto> getAllEmployees(
			@RequestParam(required = false) String firstName,
			@RequestParam(required = false) String lastName,
			@RequestParam(required = false) String position,
			@RequestParam(required = false) Status status,
			@RequestParam(required = false) Double minSalary,
			@RequestParam(required = false) Double maxSalary) {
		return employeeService.filterBy(firstName, lastName, position, status, minSalary, maxSalary);
	}

	@GetMapping("/{id}")
	public EmployeeDto getEmployee(@PathVariable Long id) {
		return employeeService.findById(id);
	}

	@PutMapping("/{id}")
	public void updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
		employeeService.update(id, employee);
	}

	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteEmployee(@PathVariable Long id) {
		employeeService.delete(id);
	}
}
