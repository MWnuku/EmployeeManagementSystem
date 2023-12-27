package com.example.employeemanagmentsystem.controllers;

import com.example.employeemanagmentsystem.Services.AddressService;
import com.example.employeemanagmentsystem.models.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController{

	private final AddressService addressService;

	public AddressController(AddressService addressService){
		this.addressService = addressService;
	}

	@PostMapping("/add")
	public Address addAddress(@RequestBody Address address){
		return addressService.addAddress(address);
	}

	@GetMapping("/getAll")
	public List<Address> findAllAddresses(){
		return addressService.findAllAddresses();
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteAddressById(@PathVariable("id") Long id){
		try{
			addressService.deleteAddressById(id);
			return new ResponseEntity<>("Address deleted successfully.", HttpStatus.OK);
		}catch(ResponseStatusException e){
			return new ResponseEntity<>(e.getReason(), e.getStatusCode());
		}catch(Error e){
			return new ResponseEntity<>("An error occurred while deleting an Address", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
