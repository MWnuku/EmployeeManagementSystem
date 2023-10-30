package com.example.employeemanagmentsystem.Services;

import com.example.employeemanagmentsystem.Repositories.AddressRepository;
import com.example.employeemanagmentsystem.Repositories.EmployeeRepository;
import com.example.employeemanagmentsystem.models.Address;
import com.example.employeemanagmentsystem.models.Employee;
import com.example.employeemanagmentsystem.models.Seniority;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService{
	private final EmployeeRepository employeeRepository;
	private final AddressService addressService;

	public EmployeeService(EmployeeRepository employeeRepository, AddressRepository addressRepository, AddressService addressService){
		this.employeeRepository = employeeRepository;
		this.addressService = addressService;
	}

	public Employee addEmployee(Employee employee){
		if(!employeeRepository.existsByNameAndLastNameAndAddress(employee.getName(), employee.getLastName(), employee.getAddress())){
			if(!addressService.exists(employee.getAddress()))
				addressService.addAddress(employee.getAddress());
			return employeeRepository.save(employee);
		}
		else{
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "There is already that employee");
		}
	}

	public List<Employee> getAll(){
		return employeeRepository.findAll();
	}

	public Optional<Employee> findEmployeeById(Long id){
		if(employeeRepository.existsById(id))
			return employeeRepository.findEmployeeById(id);
		else{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no employee with this id");
		}
	}

	public Optional<Employee> findEmployeeByNameAndLastName(String name, String lastname){
		if(employeeRepository.existsByNameAndLastName(name, lastname))
			return employeeRepository.findEmployeeByNameAndLastName(name, lastname);
		else{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no employee with this name and lastname");
		}
	}

	public List<Employee> findEmployeesBySeniority(Seniority seniority){
		if(employeeRepository.findEmployeesBySeniority(seniority).isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no employees with that seniority");
		return employeeRepository.findEmployeesBySeniority(seniority);
	}

	public void deleteEmployeeById(Long id){
		if(employeeRepository.existsById(id))
			employeeRepository.deleteEmployeeById(id);
		else{
			throw new ResponseStatusException((HttpStatus.NOT_FOUND), "There is no employee with that id");
		}
	}
}
