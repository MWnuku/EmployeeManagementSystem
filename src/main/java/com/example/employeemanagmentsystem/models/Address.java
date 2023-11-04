package com.example.employeemanagmentsystem.models;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "addresses")
@Getter
@Setter
public class Address{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "address_id", unique = true, nullable = false)
	@Setter(AccessLevel.NONE)
	private Long id;
	@Column(nullable = false)
	private String country;
	@Column(nullable = false)
	private String city;
	@Column(nullable = false)
	private String street;
	private Integer number;
	@Column(nullable = true)
	private String additionalInfo;
	@Column(nullable = false)
	private String zipcode;
	public Address(String country, String city, String street, String zipcode, Integer number){
		this.country = country;
		this.city = city;
		this.street = street;
		this.zipcode = zipcode;
		this.number = number;
	}

	public Address(String country, String city, String street, Integer number, String optionalNumber, String zipcode){
		this.country = country;
		this.city = city;
		this.street = street;
		this.number = number;
		this.additionalInfo = optionalNumber;
		this.zipcode = zipcode;
	}

	public Address(){}

	@Override
	public boolean equals(Object o){
		if(this == o)
			return true;
		if(o == null || getClass() != o.getClass())
			return false;
		Address address = (Address) o;
		return Objects.equals(id, address.id) && Objects.equals(country, address.country) && Objects.equals(city, address.city) && Objects.equals(street, address.street) && Objects.equals(number, address.number) && Objects.equals(additionalInfo, address.additionalInfo) && Objects.equals(zipcode, address.zipcode);
	}

	@Override
	public int hashCode(){
		return Objects.hash(id, country, city, street, number, additionalInfo, zipcode);
	}

	@Override
	public String toString(){
		return "Address{" + "id=" + id + ", country='" + country + '\'' + ", city='" + city + '\'' + ", street='" + street + '\'' + ", number=" + number + ", additionalInfo='" + additionalInfo + '\'' + ", zipcode='" + zipcode + '\'' + '}';
	}
}
