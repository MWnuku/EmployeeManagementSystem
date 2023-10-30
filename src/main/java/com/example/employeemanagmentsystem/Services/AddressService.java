package com.example.employeemanagmentsystem.Services;

import com.example.employeemanagmentsystem.Repositories.AddressRepository;
import com.example.employeemanagmentsystem.models.Address;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AddressService{
	private final AddressRepository addressRepository;

	public AddressService(AddressRepository addressRepository){
		this.addressRepository = addressRepository;
	}

	public Boolean exists(Address address){
		boolean addressExists;
		if(address.getAdditionalInfo().isBlank())
			addressExists = addressRepository.existsAddressByCityAndStreetAndNumber(address.getCity(), address.getStreet(), address.getNumber());
		else
			addressExists = addressRepository.existsAddressByCityAndStreetAndNumberAndAdditionalInfo(address.getCity(), address.getStreet(), address.getNumber(), address.getAdditionalInfo());
		return addressExists;
	}

	public Address addAddress(Address address){
		if(exists(address) || addressRepository.existsById(address.getId()))
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "This address already exists.");
		return addressRepository.save(address);
	}

	public List<Address> findAllAddresses(){
		if(addressRepository.findAll().isEmpty())
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "There are no addresses.");
		return addressRepository.findAll();
	}
}
