package com.gasbooking.app.service;



import java.util.List;

import com.gasbooking.app.entity.Customer;
import com.gasbooking.app.entity.GasBooking;
import com.gasbooking.app.entity.SurrenderCylinder;
import com.gasbooking.app.exception.CustomerException;
import com.gasbooking.app.exception.GasBookingException;
import com.gasbooking.app.exception.SurrenderCylinderException;



public interface CustomerService {
	Customer addCustomer(Customer newCustomer)throws CustomerException;

	Customer getCutomerById(Integer customerId) throws CustomerException;

	Customer updateCutomerById(Customer updateCustomer,Integer customerId)throws CustomerException;

	Customer deleteCutomerById(Integer customerId)throws CustomerException;
	
	List<Customer> getAllCustomers()throws CustomerException;
	
	SurrenderCylinder getSurrenderCylinderDetailsByCustomerID(Integer customerId)throws CustomerException,SurrenderCylinderException;
	
	List<GasBooking> getAllGasBookingsByCustomerId(Integer customerId)throws CustomerException,GasBookingException;
	
}
