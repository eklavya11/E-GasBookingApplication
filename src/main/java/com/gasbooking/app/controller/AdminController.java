package com.gasbooking.app.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gasbooking.app.entity.Admin;
import com.gasbooking.app.entity.Customer;
import com.gasbooking.app.entity.GasBooking;
import com.gasbooking.app.entity.SurrenderCylinder;
import com.gasbooking.app.exception.AdminException;
import com.gasbooking.app.repository.CustomerRepository;
import com.gasbooking.app.repository.GasBookingRepository;
import com.gasbooking.app.repository.SurrenderCylinderRepository;
import com.gasbooking.app.service.AdminService;
import com.gasbooking.app.service.CustomerService;

@RestController
public class AdminController { 
	@Autowired 
	private AdminService adminService;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private GasBookingRepository gasBookingRepository;
	@Autowired
	private SurrenderCylinderRepository surrenderCylinderRepository; 
	@PostMapping("/admin")
	public Admin registerAdmin(@Valid @RequestBody Admin admin) {
			return adminService.insertAdmin(admin);	
	}
	@PostMapping("/admin-customer")
	public 	Customer insertCustomer(@Valid @RequestBody Customer customer){
		return customerRepository.save(customer);
	}
	@PostMapping("/admin-gasbooking")
	public 	GasBooking insertGasBooking(@Valid @RequestBody GasBooking gasBooking){
		return gasBookingRepository.save(gasBooking);
	}
	@PostMapping("/admin-surrenderCylinder")
	public SurrenderCylinder insertSurrenderCylinder(@Valid @RequestBody SurrenderCylinder surrenderCylinder) {
		return surrenderCylinderRepository.save(surrenderCylinder);
	}
	@PutMapping("/admin")
	public Admin adminUpdate(@Valid @RequestBody Admin admin) {
		return adminService.updateAdmin(admin);
	}
	@DeleteMapping("/admin{adminId}") 
	public String adminDelete(@PathVariable("adminId") Integer adminId) throws AdminException{
		adminService.deleteAdminByAdminId(adminId);
		return "Admin with Id "+adminId+" deleted Successfully";
	
	}
	@GetMapping("/admin{adminId}")
	public Admin getadmin(@PathVariable("adminId") Integer adminId) throws AdminException{
		return adminService.getAdminByAdminId(adminId);	
	}
	@GetMapping("/admin-customer{customerId}")
	public List<GasBooking> getListofBookingsByCustomerId(@PathVariable("customerId") Integer customerId) throws AdminException{
		return adminService.getAllBookingsByCustomerId(customerId);
		
	}
	@GetMapping("/admin-gasBookings")
	public List<GasBooking> getAllGasBookings(){
		return adminService.getAllGasBookings(); 
	} 
	@GetMapping("/admin-surrenderCylinders")
	public List<SurrenderCylinder> getAllSurrenderCylinders(){
		return adminService.getAllSurrenderCylinders();
	}
	@GetMapping("/gasbookings{bookingDate}")
	public List<GasBooking> getAllBookingsForBookingDate(@RequestParam("bookingDate") @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) LocalDate bookingDate) throws AdminException{
		return adminService.getAllBookingsForBookingDate(bookingDate);
	}
}
