package com.example.employeemanagmentsystem;

import com.example.employeemanagmentsystem.Repositories.AddressRepository;
import com.example.employeemanagmentsystem.Repositories.EmployeeRepository;
import com.example.employeemanagmentsystem.Repositories.TeamRepository;
import com.example.employeemanagmentsystem.Services.TeamService;
import com.example.employeemanagmentsystem.models.Address;
import com.example.employeemanagmentsystem.models.Seniority;
import com.example.employeemanagmentsystem.models.Team;
import com.example.employeemanagmentsystem.models.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class DataLoader implements CommandLineRunner{
	private final TeamRepository teamRepository;
	private final TeamService teamService;
	private final AddressRepository addressRepository;
	private final EmployeeRepository employeeRepository;

	@Autowired
	public DataLoader(TeamRepository teamRepository, TeamService teamService, AddressRepository addressRepository, EmployeeRepository employeeRepository){
		this.teamRepository = teamRepository;
		this.teamService = teamService;
		this.addressRepository = addressRepository;
		this.employeeRepository = employeeRepository;
	}

	@Override
	@Transactional
	public void run(String... args) throws Exception{
		Team team1 = new Team();

		teamRepository.save(team1);

		Employee employee1 = new Employee("Mikolaj", "Kokoszka", new Address("Polska", "Warszawa", "Marszałkowska", 10, "informacje dodatkowe", "00-000"), "jan.auuu@gmail.com", 30, Seniority.Intern, team1);
		Employee employee2 = new Employee("Mikolaj", "Kokoszka", new Address("Polska", "Krakow", "Marszałkowska", 10, "informacje dodatkowe", "00-000"), "jan.w@gmail.com", 30, Seniority.Junior, team1);
		Employee employee3 = new Employee("Michal", "Kokoszka", new Address("Polska", "Gdansk", "Marszałkowska", 10, "informacje dodatkowe", "00-000"), "jan.g@gmail.com", 30, Seniority.Junior, team1);

		employeeRepository.saveAll(List.of(employee1, employee2, employee3));

		teamService.addEmployeeByIdToTeamById(1L, 1L);
		teamService.addEmployeeByIdToTeamById(1L, 2L);
		teamService.addEmployeeByIdToTeamById(1L, 3L);
	}
}
