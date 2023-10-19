package com.example.employeemanagmentsystem.controllers;

import com.example.employeemanagmentsystem.models.Employee;
import com.example.employeemanagmentsystem.models.Seniority;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
public class EmployeeController{
	private final EmployeeRepository employeeRepository;

	public EmployeeController(EmployeeRepository employeeRepository){
		this.employeeRepository = employeeRepository;
	}

	@PostMapping("/add")
	public Employee addEmployee(@RequestBody Employee employee){
		if(employeeRepository.existsByNameAndLastNameAndAddress(employee.getName(), employee.getLastName(), employee.getAddress()))
			return employeeRepository.save(employee);
		else{
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "There is already employee");
		}
	}

	@GetMapping
	public List<Employee> getAllEmployees(){
		return employeeRepository.getAllEmployees();
	}

	@GetMapping("{id}")
	public Optional<Employee> getEmployeeById(@PathVariable("id") Long id){
		if(employeeRepository.existsById(id))
			return employeeRepository.getEmployeeById(id);
		else{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no employee with this id");
		}
	}

	@GetMapping("/{name}/{lastname}")
	public Optional<Employee> getEmployeeByNameAndLastName(@PathVariable("name") String name, @PathVariable("lastname") String lastname){
		if(employeeRepository.existsByNameAndLastName(name, lastname))
			return employeeRepository.getEmployeeByNameAndLastName(name, lastname);
		else{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no employee with this name and lastname");
		}
	}

	@GetMapping("{seniority}")
	public List<Employee> getEmployeesBySeniority(@PathVariable("seniority") Seniority seniority){
		if(!employeeRepository.getEmployeesBySeniority(seniority).isEmpty())
			return employeeRepository.getEmployeesBySeniority(seniority);
		else{
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "There is no employees with that seniority");
		}
	}
}
