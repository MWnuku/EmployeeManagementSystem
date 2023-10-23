package com.example.employeemanagmentsystem.Services;

import com.example.employeemanagmentsystem.Repositories.TeamRepository;
import com.example.employeemanagmentsystem.models.Employee;
import com.example.employeemanagmentsystem.models.Team;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class TeamService{
	private final TeamRepository teamRepository;

	public TeamService(TeamRepository teamRepository){
		this.teamRepository = teamRepository;
	}

	public Team addTeam(){
		return teamRepository.save(new Team());
	}

	public Team addTeam(List<Employee> employees){
		return teamRepository.save(new Team(employees));
	}

	public List<Team> getAllTeams(){
		return teamRepository.getAllTeams();
	}

	public Optional<Team> getTeamById(Long id){
		if(teamRepository.existsTeamById(id))
			return teamRepository.getTeamById(id);
		else{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no team with this id.");
		}
	}

	public List<Employee> getTeamsEmployeesById(Long id){
		if(teamRepository.existsTeamById(id))
			return teamRepository.getTeamById(id).get().getEmployees();
		else{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no team with this id.");
		}
	}
}
