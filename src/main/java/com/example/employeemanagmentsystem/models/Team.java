package com.example.employeemanagmentsystem.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "teams")
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Team{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "team_id")
	@Setter(AccessLevel.NONE)
	private Long id;
	@OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private List<Employee> employees = new ArrayList<>();

	public Team(List<Employee> employees){
		this.employees = employees;
	}

	public Team(){}

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
