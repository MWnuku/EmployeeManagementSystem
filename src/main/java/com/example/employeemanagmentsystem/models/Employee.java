package com.example.employeemanagmentsystem.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.validation.constraints.Email;

import java.util.Objects;

@Entity
@Table(name = "employees")
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Employee{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "employee_id")
	@Setter(AccessLevel.NONE)
	private Long id;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String lastName;
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "address_id", referencedColumnName = "address_id", nullable = false)
	private Address address;
	@Column(name = "email", unique = true, nullable = false)
	@Email
	private String email;
	@Column(nullable = false)
	private Integer age;
	@Enumerated(value = EnumType.STRING)
	@Column(nullable = false)
	private Seniority seniority;
	@JoinColumn(name = "team_id")
	@ManyToOne(optional = true, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JsonIdentityReference(alwaysAsId = true)
	private Team team;
	public Employee(String name, String lastName, Address address, String email, Integer age, Seniority seniority){
		this.name = name;
		this.lastName = lastName;
		this.address = address;
		this.email = email;
		this.age = age;
		this.seniority = seniority;
	}

	public Employee(){}

	@Override
	public boolean equals(Object o){
		if(this == o)
			return true;
		if(o == null || getClass() != o.getClass())
			return false;
		Employee employee = (Employee) o;
		return Objects.equals(id, employee.id) && Objects.equals(name, employee.name) && Objects.equals(lastName, employee.lastName) && Objects.equals(address, employee.address) && Objects.equals(email, employee.email) && Objects.equals(age, employee.age) && seniority == employee.seniority && Objects.equals(team, employee.team);
	}

	@Override
	public int hashCode(){
		return Objects.hash(id, name, lastName, address, email, age, seniority, team);
	}

	@Override
	public String toString(){
		return "Employee{" + "id=" + id + ", name='" + name + '\'' + ", lastName='" + lastName + '\'' + ", address=" + address + ", email='" + email + '\'' + ", age=" + age + ", seniority=" + seniority + ", team=" + team + '}';
	}
}
