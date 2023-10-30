package com.example.employeemanagmentsystem.controllers;

import com.example.employeemanagmentsystem.Services.AddressService;
import com.example.employeemanagmentsystem.models.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
}
