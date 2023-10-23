package com.example.employeemanagmentsystem.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "team")
public class Team{

	@Id
	@SequenceGenerator(name = "team_id_sequence", sequenceName = "team_id_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "team_id_sequence")
	@Column(name = "team_id")
	private Long id;
	@OneToMany(mappedBy = "team")
	private List<Employee> employees = new ArrayList<>();

	public Team(List<Employee> employees){
		this.employees = employees;
	}

	public Team(){

	}

	public Long getId(){
		return id;
	}

	public void setId(Long id){
		this.id = id;
	}

	public List<Employee> getEmployees(){
		return employees;
	}

	public void setEmployees(List<Employee> employees){
		this.employees = employees;
	}

	@Override
	public boolean equals(Object o){
		if(this == o)
			return true;
		if(o == null || getClass() != o.getClass())
			return false;
		Team team = (Team) o;
		return Objects.equals(id, team.id) && Objects.equals(employees, team.employees);
	}

	@Override
	public int hashCode(){
		return Objects.hash(id, employees);
	}

	@Override
	public String toString(){
		return "Team{" + "id=" + id + ", employees=" + employees + '}';
	}
}
