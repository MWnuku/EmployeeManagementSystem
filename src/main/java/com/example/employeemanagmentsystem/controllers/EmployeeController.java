package com.example.employeemanagmentsystem.controllers;

import com.example.employeemanagmentsystem.Services.AddressService;
import com.example.employeemanagmentsystem.Services.EmployeeService;
import com.example.employeemanagmentsystem.models.Employee;
import com.example.employeemanagmentsystem.models.Seniority;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
public class EmployeeController{
	private final EmployeeService employeeService;
	private final AddressService addressService;

	public EmployeeController(EmployeeService employeeService, AddressService addressService){
		this.employeeService = employeeService;
		this.addressService = addressService;
	}

	@PostMapping("/add")
	public Employee addEmployee(@RequestBody Employee employee){
		if(!addressService.exists(employee.getAddress())) {
			addressService.addAddress(employee.getAddress());
		}
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
	public ResponseEntity<String> deleteEmployeeById(@PathVariable("id") Long id){
		try{
			employeeService.deleteEmployeeById(id);
			return new ResponseEntity<>("Employee deleted succesfully.", HttpStatus.OK);
		}catch(ResponseStatusException e){
			return new ResponseEntity<>(e.getReason(), e.getStatusCode());
		}catch(Error e){
			return new ResponseEntity<>("An error occurred while deleting an employee", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
