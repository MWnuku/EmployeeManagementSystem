package com.example.employeemanagmentsystem.controllers;

import com.example.employeemanagmentsystem.Services.TeamService;
import com.example.employeemanagmentsystem.models.Employee;
import com.example.employeemanagmentsystem.models.Team;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/team")
public class TeamController{
	private final TeamService teamService;

	public TeamController(TeamService teamService){
		this.teamService = teamService;
	}

	@PostMapping("/add")
	public Team addTeam(){
		return teamService.addTeam();
	}

	@PostMapping("/add/{employees}")
	public Team addTeam(@PathVariable("employees") List<Employee> employees){
		return teamService.addTeam(employees);
	}

	@GetMapping("/getAll")
	public List<Team> getAllTeams(){
		return teamService.getAllTeams();
	}

	@GetMapping("/{id}")
	public Optional<Team> getTeamById(@PathVariable("id") Long id){
		return teamService.getTeamById(id);
	}

	@GetMapping("/{id}/employees")
	public List<Employee> getTeamsEmployeesById(@PathVariable("id") Long id){
		return teamService.getTeamsEmployeesById(id);
	}
}
