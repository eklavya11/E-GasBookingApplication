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

import com.gasbooking.app.entity.Customer;
import com.gasbooking.app.entity.SurrenderCylinder;
import com.gasbooking.app.exception.CustomerException;
import com.gasbooking.app.exception.SurrenderCylinderException;
import com.gasbooking.app.repository.CustomerRepository;
import com.gasbooking.app.service.CustomerService;
import com.gasbooking.app.service.SurrenderCylinderService;

@RestController
public class SurrenderCylinderController {

	@Autowired
	private SurrenderCylinderService surrenderCylinderService;
	@Autowired
	private CustomerRepository customerRepository;

	@PostMapping("/surrendercustomer")
	public Customer addSurrenderCustomer(@Valid @RequestBody Customer customer) {
		return customerRepository.save(customer);
	}

	@PostMapping("/surrender-customer")
	public Customer insertCustomer(@Valid @RequestBody Customer customer) {
		return customerRepository.save(customer);
	}

	@PostMapping("/surrendercylinder/{id}")
	public SurrenderCylinder createSurrenderCylinder(@Valid @RequestBody SurrenderCylinder newSurrenderCylinder,
			@PathVariable("id") Integer customerId) throws SurrenderCylinderException, CustomerException {

		return surrenderCylinderService.addSurrenderCylinderToCustomerById(newSurrenderCylinder, customerId);

	}

	@PutMapping("/surrendercylindercylinder/{id}")
	public SurrenderCylinder updateSurrenderCylinder(@Valid @RequestBody SurrenderCylinder newSurrenderCylinder,
			@PathVariable("id") Integer customerId) throws SurrenderCylinderException, CustomerException {
		return surrenderCylinderService.updateSurrenderCylinderToCustomerById(newSurrenderCylinder, customerId);
	}

	@DeleteMapping("/surrendercylindercylinder/{id}")
	public SurrenderCylinder deleteSurrenderCylinder(@Valid @PathVariable("id") Integer SurrenderCylinderId)
			throws SurrenderCylinderException {
		return surrenderCylinderService.deleteSurrenderCylinderById(SurrenderCylinderId);
	}

}
