package com.example.employeemanagmentsystem.controllers;

import com.example.employeemanagmentsystem.Services.EmployeeService;
import com.example.employeemanagmentsystem.models.Employee;
import com.example.employeemanagmentsystem.models.Seniority;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
public class EmployeeController{
	private final EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService){
		this.employeeService = employeeService;
	}

	@PostMapping("/add")
	public Employee addEmployee(@RequestBody Employee employee){
		return employeeService.addEmployee(employee);
	}

	@GetMapping("/getAll")
	public List<Employee> findAll(){
		return employeeService.getAll();
	}

	@GetMapping("/get/{id}")
	public Optional<Employee> findEmployeeById(@PathVariable("id") Long id){
		return employeeService.findEmployeeById(id);
	}

	@GetMapping("/{name}/{lastname}")
	public Optional<Employee> findEmployeeByNameAndLastName(@PathVariable("name") String name, @PathVariable("lastname") String lastname){
		return employeeService.findEmployeeByNameAndLastName(name, lastname);
	}

	@GetMapping("{seniority}")
	public List<Employee> findEmployeesBySeniority(@PathVariable("seniority") Seniority seniority){
		return employeeService.findEmployeesBySeniority(seniority);
	}

	@DeleteMapping("{id}")
	public void deleteEmployeeById(@PathVariable("id") Long id){
		employeeService.deleteEmployeeById(id);
	}
}
