package com.gasbooking.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gasbooking.app.entity.Customer;
import com.gasbooking.app.entity.GasBooking;
import com.gasbooking.app.exception.GasBookingException;
import com.gasbooking.app.repository.CustomerRepository;
import com.gasbooking.app.service.GasBookingService;

@RestController
public class GasBookingController {

	@Autowired
	private GasBookingService gasBookingService;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	
	@PostMapping("/gasbooking-customer")
	public Customer addCustomer(@RequestBody Customer customer) {
		
		return customerRepository.save(customer);
	}
	

	@PostMapping("/gasBooking/{customerId}")
	public GasBooking addGasBooking(@PathVariable("customerId") Integer customerId, @RequestBody GasBooking gasBooking)
			throws GasBookingException {

		return this.gasBookingService.addGasBookingToCustomer(customerId, gasBooking);
	}

	@PutMapping("/gasBooking/{customerId}/{gasBookingId}")
	public GasBooking updateGasBookingDetails(@PathVariable("customerId") Integer customerId,@PathVariable("gasBookingId") Integer gasBookingId,
			@RequestBody GasBooking gasBooking) throws GasBookingException {
		return this.gasBookingService.updateGasBookingByCustomerId(customerId,gasBookingId,gasBooking);
	}

	@DeleteMapping("/gasBooking/{customerId}/{gasBookingId}")
	public GasBooking deleteGasBookingByCustomerId(@PathVariable("customerId") Integer customerId,@PathVariable("gasBookingId") Integer gasBookingId)
			throws GasBookingException {

		return gasBookingService.deleteGasBookingByCustomerId(customerId,gasBookingId);
	}
	
	@GetMapping("/gasBooking/{customerId}")
	public List<GasBooking> getGasBookingByCustomerId(@PathVariable("customerId") Integer customerId)
			throws GasBookingException {
		List<GasBooking> gasBooking = this.gasBookingService.getGasBookingByCustomerId(customerId);
		if (gasBooking == null) {
			throw new GasBookingException("GasBooking details do not exict for the id" + customerId);
		}
		return gasBookingService.getGasBookingByCustomerId(customerId);
	}
}
//
//	@GetMapping("/gasBooking/{gasBookingId}")
//	public GasBooking getGasBookingBillByGasBookingId(@PathVariable("gasBookingId") Integer gasBookingId)
//			throws GasBookingException {
//
//		return gasBookingService.getGasBookingBillByGasBookingId(gasBookingId);
//	}
//
