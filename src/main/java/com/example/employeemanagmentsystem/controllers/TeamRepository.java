package com.example.employeemanagmentsystem.controllers;

import com.example.employeemanagmentsystem.models.Employee;
import com.example.employeemanagmentsystem.models.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long>{
	List<Team> getAllTeams();
	Boolean existsTeamById(Long id);
	Optional<Team> getTeamById(Long id);
}
