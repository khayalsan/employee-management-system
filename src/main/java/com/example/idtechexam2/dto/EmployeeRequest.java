package com.example.idtechexam2.dto;

import com.example.idtechexam2.enums.Status;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EmployeeRequest {
	String firstName;
	String lastName;
	String email;
	String phoneNumber;
	String position;
	Double salary;
	LocalDate hireDate;
	Status status;
}
