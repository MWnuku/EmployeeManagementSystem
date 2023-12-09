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
	public void run(String... args){
		Team team1 = new Team();
		Team team2 = new Team();
		Team team3 = new Team();

		teamRepository.saveAll(List.of(team1, team2, team3));

		Employee employee1 = new Employee("Mikolaj", "Kokoszka", new Address("Polska", "Warszawa", "Marszałkowska", 10, "informacje dodatkowe", "00-000"), "jan.auuu@gmail.com", 30, Seniority.Intern, team1);
		Employee employee2 = new Employee("Mikolaj", "Kokosz", new Address("Polska", "Krakow", "Marszałkowska", 10, "informacje dodatkowe", "00-000"), "jan.w@gmail.com", 21, Seniority.Junior, team2);
		Employee employee3 = new Employee("Michal", "Kokoszka", new Address("Polska", "Gdansk", "Marszałkowska", 10, "informacje dodatkowe", "00-000"), "jan.g@gmail.com", 22, Seniority.Junior, team3);
		Employee employeeWithoutTeam1 = new Employee("Jan", "Nowak", new Address("Polska", "Wroclaw", "Krakowska", 5, "informacje dodatkowe", "00-000"), "jan.nowak@gmail.com", 31, Seniority.Senior);
		Employee employeeWithoutTeam2 = new Employee("Mateusz", "Nowak", new Address("Polska", "Tarnow", "Miejska", 7, "informacje dodatkowe", "00-000"), "mateusz.nowak@gmail.com", 32, Seniority.Senior);
		Employee employeeWithoutTeam3 = new Employee("Szczepan", "Kurek", new Address("Polska", "Katowice", "Trzecia", 8, "informacje dodatkowe", "00-000"), "szczepan.kurek@gmail.com", 25, Seniority.Mid);
		Employee employeeWithoutTeam4 = new Employee("Daniel", "Kowalczyk", new Address("Polska", "Gdynia", "Druga", 23, "informacje dodatkowe", "00-000"), "daniel.kowalczyk@gmail.com", 26, Seniority.Mid);
		Employee employeeWithoutTeam5 = new Employee("Dawid", "Kowal", new Address("Polska", "Poznac", "Pierwsza", 56, "informacje dodatkowe", "00-000"), "dawid.kowal@gmail.com", 27, Seniority.Mid);

		employeeRepository.saveAll(List.of(employee1, employee2, employee3, employeeWithoutTeam1, employeeWithoutTeam2, employeeWithoutTeam3, employeeWithoutTeam4, employeeWithoutTeam5));
		teamService.addEmployeeByIdToTeamById(1L,1L);
		teamService.addEmployeeByIdToTeamById(2L,2L);
		teamService.addEmployeeByIdToTeamById(3L,3L);

		teamService.assignEmployeesWithoutTeams();
	}
}
