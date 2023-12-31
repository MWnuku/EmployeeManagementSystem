package com.example.employeemanagmentsystem.Services;

import com.example.employeemanagmentsystem.Repositories.EmployeeRepository;
import com.example.employeemanagmentsystem.Repositories.TeamRepository;
import com.example.employeemanagmentsystem.models.Employee;
import com.example.employeemanagmentsystem.models.Seniority;
import com.example.employeemanagmentsystem.models.Team;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.Null;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TeamService{
	private final TeamRepository teamRepository;
	private final EmployeeRepository employeeRepository;
	private final EmployeeService employeeService;

	public TeamService(TeamRepository teamRepository, EmployeeRepository employeeRepository, EmployeeService employeeService){
		this.teamRepository = teamRepository;
		this.employeeRepository = employeeRepository;
		this.employeeService = employeeService;
	}

	public Team addTeam(){
		return teamRepository.save(new Team());
	}

	public Team addTeam(List<Employee> employees){
		return teamRepository.save(new Team(employees));
	}

	public List<Team> findAll(){
		return teamRepository.findAll();
	}

	public Team findTeamById(Long id){
		if(teamRepository.existsTeamById(id))
			return teamRepository.findTeamById(id);
		else{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no team with this id.");
		}
	}

	public List<Employee> findTeamsEmployeesById(Long id){
		if(teamRepository.existsTeamById(id))
			return teamRepository.findTeamById(id).getEmployees();
		else{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no team with this id.");
		}
	}

	@Transactional
	public void addEmployeeByIdToTeamById(Long employeeId, Long teamId){
		Team team = teamRepository.findById(teamId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no team with this id."));
		Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no employee with this id."));

		employee.setTeam(team);
		employeeRepository.save(employee);

		team.getEmployees().add(employee);
		teamRepository.save(team);
	}

	@Transactional
	public void assignEmployeesWithoutTeams(){
		List<Employee> unemployed = employeeService.findEmployeesWithoutTeam();
		List<Team> teams = findAll();
		if(teams.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There are no teams.");

		Map<Seniority, List<Employee>> employeesBySeniority = sortEmployeesBySeniority(unemployed);

		for(Seniority seniority : Seniority.values()){
			List<Employee> seniorityList = employeesBySeniority.get(seniority);
			if(seniorityList != null)
				for(Employee employee : seniorityList){
					Team team = findTeamWithLowestNumberOfEmployeesBySeniority(seniority);
					addEmployeeByIdToTeamById(employee.getId(), team.getId());
				}
		}
	}

	public Map<Seniority, List<Employee>> sortEmployeesBySeniority(List<Employee> employees){

		return employees.stream()
				.collect(Collectors.groupingBy(Employee::getSeniority));
	}

	public Team findTeamWithLowestNumberOfEmployeesBySeniority(Seniority seniority){
		List<Team> teams = findAll();

		Optional<Team> teamWithFewestEmployees = teams.stream()
				.min(Comparator.comparingInt(team ->
						(int) team.getEmployees().stream()
								.filter(employee -> employee.getSeniority() == seniority)
								.count()));

		return teamWithFewestEmployees.get();
	}

	public void deleteTeamById(Long teamId){
		if(teamRepository.existsById(teamId))
			teamRepository.deleteById(teamId);
		else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no team with this id.");
		}
	}

	@Transactional
	public void deleteEmployeeByIdFromTeamByid(Long teamId, Long employeeId){
		if(!teamRepository.existsById(teamId))
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no team with this id.");

		if(!employeeRepository.existsById(employeeId))
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no employee with this id.");

		Team team = teamRepository.findTeamById(teamId);
		Employee employee = employeeService.findEmployeeById(employeeId).get();

		employee.setTeam(null);
		List<Employee> employees = team.getEmployees();
		employees.remove(employee);

		employeeRepository.save(employee);
		teamRepository.save(team);
	}
}
