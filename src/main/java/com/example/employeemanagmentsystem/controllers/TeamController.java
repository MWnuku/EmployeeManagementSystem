package com.example.employeemanagmentsystem.controllers;

import com.example.employeemanagmentsystem.Services.TeamService;
import com.example.employeemanagmentsystem.models.Employee;
import com.example.employeemanagmentsystem.models.Team;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
	public Team findTeamById(@PathVariable("id") Long id){
		return teamService.findTeamById(id);
	}

	@GetMapping("/{id}/employees")
	public List<Employee> findTeamsEmployeesById(@PathVariable("id") Long id){
		return teamService.findTeamsEmployeesById(id);
	}

	@PostMapping("/{id}/{employee_id}")
	public ResponseEntity<String> addEmployeeByIdToTeamById(@PathVariable("id") Long id, @PathVariable("employee_id") Long employeeId) {
		try {
			teamService.addEmployeeByIdToTeamById(id, employeeId);
			return new ResponseEntity<>("Employee added to the team successfully", HttpStatus.OK);
		} catch (ResponseStatusException e) {
			return new ResponseEntity<>(e.getReason(), e.getStatusCode());
		} catch (Exception e) {
			return new ResponseEntity<>("An error occurred while adding the employee to the team", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
