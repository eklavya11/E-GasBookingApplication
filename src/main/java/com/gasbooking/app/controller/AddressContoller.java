package com.gasbooking.app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gasbooking.app.entity.Address;
import com.gasbooking.app.entity.Customer;
import com.gasbooking.app.exception.AddressException;
import com.gasbooking.app.repository.CustomerRepository;
import com.gasbooking.app.service.AddressService;

@RestController
public class AddressContoller {

	@Autowired
	private AddressService addressService;

	@Autowired
	private CustomerRepository customerRepository;

	@PostMapping("/address-gasbooking")
	public Customer addCustomer(@Valid @RequestBody Customer customer) {

		return customerRepository.save(customer);
	}

	@PostMapping("/address/{customerId}")

	public Address addAddresstoCustomer(@PathVariable("customerId") Integer customerId, @RequestBody Address address)
			throws AddressException {

		return addressService.addAddresstoCustomer(customerId, address);
	}

	@GetMapping("/address/{customerId}")
	public Address getAddressByCustomerId(@PathVariable("customerId") Integer customerId) throws AddressException {

		return addressService.getAddressByCustomerId(customerId);
	}

	@PutMapping("/address/{customerId}")
	public Address updateAddressByCustomerId(@PathVariable("customerId") Integer customerId,
			@RequestBody Address updateAddress) throws AddressException {

		return addressService.updateAddressByCustomerId(customerId, updateAddress);
	}

	@DeleteMapping("/address/{customerId}")
	public Address deleteAddressByCustomerId(@PathVariable("customerId") Integer customerId) throws AddressException {

		return addressService.deleteAddressByCustomerId(customerId);
	}

}
