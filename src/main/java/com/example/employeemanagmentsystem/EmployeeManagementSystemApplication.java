package com.example.employeemanagmentsystem;

import com.example.employeemanagmentsystem.Services.EmployeeService;
import com.example.employeemanagmentsystem.models.Address;
import com.example.employeemanagmentsystem.models.Employee;
import com.example.employeemanagmentsystem.models.Seniority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RequestMapping("/emp")
public class EmployeeManagementSystemApplication{
	@Autowired
	private EmployeeService employeeService;

	public static void main(String[] args) {
		SpringApplication.run(EmployeeManagementSystemApplication.class, args);
	}

}
