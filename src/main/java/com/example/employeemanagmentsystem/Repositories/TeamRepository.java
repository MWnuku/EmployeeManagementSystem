package com.example.employeemanagmentsystem.Repositories;

import com.example.employeemanagmentsystem.models.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long>{
	Boolean existsTeamById(Long id);
	Team findTeamById(Long id);
}
