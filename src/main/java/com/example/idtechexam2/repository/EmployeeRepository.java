package com.example.idtechexam2.repository;

import com.example.idtechexam2.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	@Override
	Optional<Employee> findById(Long aLong);

	Optional<Employee> findByEmail(String email);

	@Override
	List<Employee> findAll();

	@Override
	<S extends Employee> S save(S entity);

	@Override
	void deleteById(Long aLong);

}
