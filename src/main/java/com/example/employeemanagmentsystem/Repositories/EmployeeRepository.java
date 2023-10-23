package com.example.employeemanagmentsystem.Repositories;

import com.example.employeemanagmentsystem.models.Address;
import com.example.employeemanagmentsystem.models.Employee;
import com.example.employeemanagmentsystem.models.Seniority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{
	List<Employee> getAllEmployees();

	Boolean existsByNameAndLastName(String name, String lastname);

	Optional<Employee> getEmployeeByNameAndLastName(String name, String lastname);
	Boolean existsByNameAndLastNameAndAddress(String name, String lastname, Address address);

	List<Employee> getEmployeesBySeniority(Seniority seniority);

	Optional<Employee> getEmployeeById(Long id);

	void deleteEmployeeById(Long id);
}
