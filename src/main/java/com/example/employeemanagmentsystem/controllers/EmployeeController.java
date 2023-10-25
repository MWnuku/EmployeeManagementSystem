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

	@GetMapping("{id}")
	public Optional<Employee> getEmployeeById(@PathVariable("id") Long id){
		return employeeService.getEmployeeById(id);
	}

	@GetMapping("/{name}/{lastname}")
	public Optional<Employee> getEmployeeByNameAndLastName(@PathVariable("name") String name, @PathVariable("lastname") String lastname){
		return employeeService.getEmployeeByNameAndLastName(name, lastname);
	}

	@GetMapping("{seniority}")
	public List<Employee> getEmployeesBySeniority(@PathVariable("seniority") Seniority seniority){
		return employeeService.getEmployeesBySeniority(seniority);
	}

	@DeleteMapping("{id}")
	public void deleteEmployeeById(@PathVariable("id") Long id){
		employeeService.deleteEmployeeById(id);
	}
}
