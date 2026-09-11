package com.example.idtechexam2.dto;

import com.example.idtechexam2.enums.Status;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class EmployeeDto {
	private Long id;
    private String firstName;
    private String lastName;
	private String position;
	private Status status;
	private Double salary;
}
