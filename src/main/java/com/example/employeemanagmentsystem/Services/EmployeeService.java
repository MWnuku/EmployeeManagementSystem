package com.example.employeemanagmentsystem.Services;

import com.example.employeemanagmentsystem.Repositories.EmployeeRepository;
import com.example.employeemanagmentsystem.models.Employee;
import com.example.employeemanagmentsystem.models.Seniority;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

public class EmployeeService{
	private final EmployeeRepository employeeRepository;

	public EmployeeService(EmployeeRepository employeeRepository){
		this.employeeRepository = employeeRepository;
	}

	public Employee addEmployee(Employee employee){
		if(employeeRepository.existsByNameAndLastNameAndAddress(employee.getName(), employee.getLastName(), employee.getAddress()))
			return employeeRepository.save(employee);
		else{
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "There is already employee");
		}
	}

	public List<Employee> getAllEmployees(){
		return employeeRepository.getAllEmployees();
	}

	public Optional<Employee> getEmployeeById(Long id){
		if(employeeRepository.existsById(id))
			return employeeRepository.getEmployeeById(id);
		else{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no employee with this id");
		}
	}

	public Optional<Employee> getEmployeeByNameAndLastName(String name, String lastname){
		if(employeeRepository.existsByNameAndLastName(name, lastname))
			return employeeRepository.getEmployeeByNameAndLastName(name, lastname);
		else{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no employee with this name and lastname");
		}
	}

	public List<Employee> getEmployeesBySeniority(Seniority seniority){
		if(employeeRepository.getEmployeesBySeniority(seniority).isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no employees with that seniority");
		else{
			return employeeRepository.getEmployeesBySeniority(seniority);
		}
	}

	public void deleteEmployeeById(Long id){
		if(employeeRepository.existsById(id))
			employeeRepository.deleteEmployeeById(id);
		else{
			throw new ResponseStatusException((HttpStatus.NOT_FOUND), "There is no employee with that id");
		}
	}
}
