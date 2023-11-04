package com.example.employeemanagmentsystem.models;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import java.util.Objects;

@Entity
@Table(name = "employee")
@Getter
@Setter
public class Employee{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "employee_id")
	@Setter(AccessLevel.NONE)
	private Long id;
	private String name;
	private String lastName;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id", referencedColumnName = "address_id")
	private Address address;
	private String email;
	private Integer age;
	private Seniority seniority;
	@JoinColumn(name = "team_id")
	@ManyToOne(cascade = CascadeType.ALL)
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
