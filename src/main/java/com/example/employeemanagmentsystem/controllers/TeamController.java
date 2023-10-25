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
	public List<Team> findAll(){
		return teamService.findAll();
	}

	@GetMapping("/{id}")
	public Optional<Team> findTeamById(@PathVariable("id") Long id){
		return teamService.findTeamById(id);
	}

	@GetMapping("/{id}/employees")
	public List<Employee> findTeamsEmployeesById(@PathVariable("id") Long id){
		return teamService.findTeamsEmployeesById(id);
	}
}
