package com.example.employeemanagmentsystem.models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "address")
public class Address{
	@Id
	@SequenceGenerator(name = "address_id_sequence", sequenceName = "address_id_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_id_sequence")
//	@Column(name = "address_id")
	@PrimaryKeyJoinColumn
	private Long id;
	private String country;
	private String city;
	private String street;
	private String zipcode;

	public Address(Long id, String country, String city, String street, String zipcode){
		this.id = id;
		this.country = country;
		this.city = city;
		this.street = street;
		this.zipcode = zipcode;
	}

	public Address(){

	}

	public Long getId(){
		return id;
	}

	public void setId(Long id){
		this.id = id;
	}

	public String getCountry(){
		return country;
	}

	public void setCountry(String country){
		this.country = country;
	}

	public String getCity(){
		return city;
	}

	public void setCity(String city){
		this.city = city;
	}

	public String getStreet(){
		return street;
	}

	public void setStreet(String street){
		this.street = street;
	}

	public String getZipcode(){
		return zipcode;
	}

	public void setZipcode(String zipcode){
		this.zipcode = zipcode;
	}

	@Override
	public boolean equals(Object o){
		if(this == o)
			return true;
		if(o == null || getClass() != o.getClass())
			return false;
		Address address = (Address) o;
		return Objects.equals(id, address.id) && Objects.equals(country, address.country) && Objects.equals(city, address.city) && Objects.equals(street, address.street) && Objects.equals(zipcode, address.zipcode);
	}

	@Override
	public int hashCode(){
		return Objects.hash(id, country, city, street, zipcode);
	}

	@Override
	public String toString(){
		return "Address{" + "id=" + id + ", country='" + country + '\'' + ", city='" + city + '\'' + ", street='" + street + '\'' + ", zipcode='" + zipcode + '\'' + '}';
	}
}
