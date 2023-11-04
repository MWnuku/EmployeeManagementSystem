package com.example.employeemanagmentsystem.Repositories;

import com.example.employeemanagmentsystem.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long>{
	Boolean existsAddressByCityAndStreetAndNumber(String city, String street, Integer number);
	Boolean existsAddressByCityAndStreetAndNumberAndAdditionalInfo(String city, String street, Integer number, String additionalInfo);

	Address findByCityAndStreetAndNumberAndAdditionalInfo(String city, String street, Integer number, String additionalInfo);
}
