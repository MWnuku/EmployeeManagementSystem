package com.example.employeemanagmentsystem.Services;

import com.example.employeemanagmentsystem.Repositories.EmployeeRepository;
import com.example.employeemanagmentsystem.Repositories.TeamRepository;
import com.example.employeemanagmentsystem.models.Employee;
import com.example.employeemanagmentsystem.models.Team;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class TeamService{
	private final TeamRepository teamRepository;
	private final EmployeeRepository employeeRepository;

	public TeamService(TeamRepository teamRepository, EmployeeRepository employeeRepository){
		this.teamRepository = teamRepository;
		this.employeeRepository = employeeRepository;
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

	public Optional<Team> findTeamById(Long id){
		if(teamRepository.existsTeamById(id))
			return teamRepository.findTeamById(id);
		else{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no team with this id.");
		}
	}

	public List<Employee> findTeamsEmployeesById(Long id){
		if(teamRepository.existsTeamById(id))
			return teamRepository.findTeamById(id).get().getEmployees();
		else{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no team with this id.");
		}
	}

	//TODO
	@Transactional
	public void addEmployeeByIdToTeamById(Long teamId, Long employeeId) {
		Team team = teamRepository.findById(teamId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no team with this id."));
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no employee with this id."));

		team.getEmployees().add(employee);
		teamRepository.save(team);
	}

}
