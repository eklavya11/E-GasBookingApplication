package com.gasbooking.app.controller;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gasbooking.app.entity.Customer;
import com.gasbooking.app.entity.GasBooking;
import com.gasbooking.app.entity.SurrenderCylinder;
import com.gasbooking.app.exception.CustomerException;
import com.gasbooking.app.exception.GasBookingException;
import com.gasbooking.app.exception.SurrenderCylinderException;
import com.gasbooking.app.service.CustomerService;




@RestController
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;

	@PostMapping("/customer")
	@ResponseStatus(value = HttpStatus.CREATED)
	public Customer addCustomer(@Valid @RequestBody Customer newCustomer) throws CustomerException{
       
		return customerService.addCustomer(newCustomer);
	}

	@GetMapping("/customer/{customerId}")
	@ResponseStatus(value = HttpStatus.CREATED)
	public Customer getCustomerById(@PathVariable("customerId") Integer customerId) throws CustomerException {

		return customerService.getCutomerById(customerId);
	}
	
	@PutMapping("/customer/{customerId}")
	@ResponseStatus(value = HttpStatus.CREATED)
	public Customer updateCustomerById(@Valid @RequestBody Customer updateCustomer,@PathVariable("customerId") Integer customerId) throws CustomerException{
		
		return customerService.updateCutomerById(updateCustomer,customerId);
	}
	
	@DeleteMapping("/customer/{customerId}") 
	@ResponseStatus(value = HttpStatus.CREATED)
	public String deleteCustomerById(@PathVariable("customerId") Integer customerId) throws CustomerException {
		
		this.customerService.deleteCutomerById(customerId);
		return "Customer Successfully deleted";
		
	}
	@GetMapping("/customers")
	@ResponseStatus(value = HttpStatus.CREATED)
	public List<Customer> getAllTheCustomers()throws CustomerException{
		return this.customerService.getAllCustomers();
	}
	
	@GetMapping("/customersurrenderdetails/{customerId}")
	@ResponseStatus(value = HttpStatus.CREATED)
	public SurrenderCylinder getSurrenderCylinderDetailsByCustomerID(@PathVariable("customerId") Integer customerId) throws CustomerException, SurrenderCylinderException{
		
		return this.customerService.getSurrenderCylinderDetailsByCustomerID(customerId);
		
	}
	
	@GetMapping("/customergasbookings/{customerId}")
	@ResponseStatus(value = HttpStatus.CREATED)
	public List<GasBooking> getAllGasBookingsByCustomerId(@PathVariable("customerId") Integer customerId) throws CustomerException, GasBookingException{
		return this.customerService.getAllGasBookingsByCustomerId(customerId);
	}


}
