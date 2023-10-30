package com.example.employeemanagmentsystem.models;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Entity
@Table(name = "address")
@Getter
@Setter
public class Address{
	@Id
	@SequenceGenerator(name = "address_id_sequence", sequenceName = "address_id_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_id_sequence")
	@Column(name = "address_id", unique = true, nullable = false)
	@Setter(AccessLevel.NONE)
	private Long id;
	private String country;
	private String city;
	private String street;
	private Integer number;
	private String additionalInfo;
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
