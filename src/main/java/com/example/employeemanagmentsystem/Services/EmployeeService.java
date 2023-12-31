package com.example.employeemanagmentsystem.Services;

import com.example.employeemanagmentsystem.Repositories.AddressRepository;
import com.example.employeemanagmentsystem.Repositories.EmployeeRepository;
import com.example.employeemanagmentsystem.Repositories.TeamRepository;
import com.example.employeemanagmentsystem.models.Address;
import com.example.employeemanagmentsystem.models.Employee;
import com.example.employeemanagmentsystem.models.Seniority;
import com.example.employeemanagmentsystem.models.Team;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService{
	private final EmployeeRepository employeeRepository;

	private final AddressRepository addressRepository;
	private final TeamRepository teamRepository;

	public EmployeeService(EmployeeRepository employeeRepository, AddressRepository addressRepository, TeamRepository teamRepository){
		this.employeeRepository = employeeRepository;
		this.addressRepository = addressRepository;
		this.teamRepository = teamRepository;
	}

	public Employee addEmployee(Employee employee) {
		Address address = employee.getAddress();
		Address existingAddress = addressRepository.findByCityAndStreetAndNumberAndAdditionalInfo(
				address.getCity(), address.getStreet(), address.getNumber(), address.getAdditionalInfo());
		if(existingAddress != null)
			employee.setAddress(existingAddress);
		else{
			address = addressRepository.save(address);
			employee.setAddress(address);
		}
		if(employee.getTeam() != null){
			Team team = employee.getTeam();
			Team existingTeam = teamRepository.findTeamById(team.getId());
			if(existingTeam != null){
				employee.setTeam(existingTeam);
			} else{
				team = teamRepository.save(team);
				employee.setTeam(team);
			}
		}

		return employeeRepository.save(employee);
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

	public List<Employee> findEmployeesWithoutTeam(){
		List<Employee> employees = getAll();
		List<Employee> unemployed = new ArrayList<>();
		for(Employee employee : employees){
			if(employee.getTeam() == null)
				unemployed.add(employee);
		}
		return unemployed;
	}
}
