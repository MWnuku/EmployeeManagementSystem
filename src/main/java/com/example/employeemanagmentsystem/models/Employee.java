package com.example.employeemanagmentsystem.models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "employee")
public class Employee{

	@Id
	@SequenceGenerator(name = "employee_id_sequence", sequenceName = "employee_id_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_id_sequence")
	@Column(name = "employee_id")
	private Long id;
	@Column
	private String name;
	@Column
	private String lastName;
//	@Column
	@OneToOne
	@PrimaryKeyJoinColumn(name = "address_id")
	private Address address;
	@Column
	private String email;
	@Column
	private Integer age;
	@Column
	private Seniority seniority;
//	@Column
	@ManyToOne
	@JoinColumn(name = "team_id")
	private Team team;

	public Employee(Long id, String name, String lastName, Address address, String email, Integer age, Seniority seniority){
		this.id = id;
		this.name = name;
		this.lastName = lastName;
		this.address = address;
		this.email = email;
		this.age = age;
		this.seniority = seniority;
	}

	public Employee(){
	}

	public Long getId(){
		return id;
	}

	public void setId(Long id){
		this.id = id;
	}

	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getLastName(){
		return lastName;
	}

	public void setLastName(String lastName){
		this.lastName = lastName;
	}

	public Address getAddress(){
		return address;
	}

	public void setAddress(Address address){
		this.address = address;
	}

	public String getEmail(){
		return email;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public Integer getAge(){
		return age;
	}

	public void setAge(Integer age){
		this.age = age;
	}

	public Seniority getSeniority(){
		return seniority;
	}

	public void setSeniority(Seniority seniority){
		this.seniority = seniority;
	}

	public Team getTeam(){
		return team;
	}

	public void setTeam(Team team){
		this.team = team;
	}

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
