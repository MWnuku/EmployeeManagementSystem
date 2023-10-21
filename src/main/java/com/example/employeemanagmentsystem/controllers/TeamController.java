package com.example.employeemanagmentsystem.controllers;

import com.example.employeemanagmentsystem.models.Employee;
import com.example.employeemanagmentsystem.models.Team;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/team")
public class TeamController{
	private final TeamRepository teamRepository;

	public TeamController(TeamRepository teamRepository){
		this.teamRepository = teamRepository;
	}

	@PostMapping("/add")
	public Team addTeam(){
		return new Team();
	}

	@GetMapping("/getAll")
	public List<Team> getAllTeams(){
		return teamRepository.getAllTeams();
	}

	@GetMapping("/{id}")
	public Optional<Team> getTeamById(@PathVariable("id") Long id){
		if(teamRepository.existsTeamById(id))
			return teamRepository.getTeamById(id);
		else{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no team with this id.");
		}
	}

	@GetMapping("/{id}/employees")
	public List<Employee> getTeamsEmployeesById(@PathVariable("id") Long id){
		if(teamRepository.existsTeamById(id))
			return teamRepository.getTeamById(id).get().getEmployees();
		else{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no team with this id.");
		}
	}
}
