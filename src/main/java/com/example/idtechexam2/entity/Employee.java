package com.example.idtechexam2.entity;

import com.example.idtechexam2.enums.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter @Setter
@Table(name = "employees")
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	@Column(name = "first_name")
	String firstName;
	@Column(name = "last_name")
	String lastName;
	@Column(name = "email", unique = true, nullable = false)
	String email;
	@Column(name = "phone_number")
	String phoneNumber;
	@Column(name = "position")
	String position;
	@Column(name = "salary")
	Double salary;
	@Column(name = "hire_date")
	LocalDate hireDate;
	@Enumerated(EnumType.STRING)
	Status status;


	@Override
	public String toString() {
		return "Employee{" +
				"id=" + id +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", email='" + email + '\'' +
				", phoneNumber='" + phoneNumber + '\'' +
				", position='" + position + '\'' +
				", salary=" + salary +
				", hireDate=" + hireDate +
				", status=" + status +
				'}';
	}
}
