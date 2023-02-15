package com.gasbooking.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;



import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import com.gasbooking.app.entity.Customer;
import com.gasbooking.app.exception.CustomerException;
import com.gasbooking.app.exception.GasBookingException;
import com.gasbooking.app.exception.SurrenderCylinderException;
import com.gasbooking.app.service.CustomerService;



@SpringBootTest
public class CustomerServiceTests {
	
	@Autowired
	private CustomerService customerService;
	
	@Test
	void addCutomerTest() throws CustomerException{
		String msg = null;
		try {
		assertNotNull(customerService.addCustomer(new Customer(200,null,null,null,"Rohit Tiwari",
				"Rtwja289","7383873985","tiwari@gmail.com", null)));
		}
		catch(CustomerException e) {
			msg=e.getMessage();
		}
		assertEquals("Cusomer already exists for the given email : tiwari@gmail.com",msg);
	}
    
	
	
	@Test
	void updateCustomerByIdTest()throws CustomerException{
		Customer updateCustomer = new Customer(14,null,null,null,"KrEKLAVYA",
				"KJaja289","7383829285","keklavya@gmail.com", null);
		assertNotNull(customerService.updateCutomerById(updateCustomer, 14));
	}
	@Test
	void updateCustomerByIdExceptionTest()throws CustomerException{
		String msg = null;
		try {
		Customer updateCustomer = new Customer(91,null,null,null,"Riyaan",
				"KJaj9889","7388929285","riyan@gmail.com", null);
		assertNotNull(customerService.updateCutomerById(updateCustomer, 91));
		}
		catch(CustomerException e) {
			msg=e.getMessage();
		}
		assertEquals("Cusomer not found for the given customerId : 91",msg);
	}
	@Test
	void updateCustomerByIdEmailExceptionTest()throws CustomerException{
		String msg = null;
		try {
		Customer updateCustomer = new Customer(28,null,null,null,"Riyaan singh",
				"SJ39dksd9","7328392782","riyan@gmail.com", null);
		assertNotNull(customerService.updateCutomerById(updateCustomer, 28));
		}
		catch(CustomerException e) {
			msg=e.getMessage();
		}
		assertEquals("Customer already exists for the given email : riyan@gmail.com",msg);
	}
	
	@Test
	void deleteCustomerByIdTest()throws CustomerException{
		String msg = null;
		try {
			assertNotNull(customerService.deleteCutomerById(50));
		}
		catch(CustomerException e) {
			msg=e.getMessage();
		}
		assertEquals("Customer id does not exists to delete ! 50",msg);
		
	}

	@Test
	void getCutomerByIdTest() throws CustomerException {
		assertNotNull(customerService.getCutomerById(9));
	}
	@Test
	void getCutomerByIdExceptionTest() {
		
		assertThrows(CustomerException.class,()->customerService.getCutomerById(500));
	}
	@Test
	void getCutomerByIdExceptionMessageTest() {
		
		String msg=null;
		try {
			customerService.getCutomerById(100);
		} catch (CustomerException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			msg=e.getMessage();
		}
		
		assertEquals("Cusomer not found for the given customerId : 100", msg);
	}
	@Test
	void getAllCustomersTest() throws CustomerException{
		assertNotNull(customerService.getAllCustomers());
	}
	@Test
	void getSurrenderDetailsTest()throws CustomerException, SurrenderCylinderException{
		String msg = null;
		try {
			assertNotNull(customerService.getSurrenderCylinderDetailsByCustomerID(64));
		}
		catch(CustomerException e) {
			msg=e.getMessage();
		}
		assertEquals("Cusomer not found for the given customerId : 64", msg);
	}
	@Test
	void getSurrenderDetailsExceptionTest()throws CustomerException, SurrenderCylinderException{
		String msg = null;
		try {
			assertNotNull(customerService.getSurrenderCylinderDetailsByCustomerID(14));
		}
		catch(SurrenderCylinderException e) {
			msg=e.getMessage();
		}
		assertEquals("No surrender details for the given customerId : 14", msg);
	}
	@Test
	void getGasBookingDetailsTest() throws GasBookingException,CustomerException {
		String msg = null;
		try {
			assertNotNull(customerService.getAllGasBookingsByCustomerId(64));
		}
		catch(CustomerException e) {
			msg=e.getMessage();
		}
		assertEquals("Cusomer not found for the given customerId : 64", msg);
	}
	@Test
	void getGasBookingDetailsExceptionTest() throws GasBookingException,CustomerException {
		String msg = null;
		try {
			assertNotNull(customerService.getAllGasBookingsByCustomerId(14));
		}
		catch(GasBookingException e) {
			msg=e.getMessage();
		}
		assertEquals("Gas Bookings not found for the given customerId : 14", msg);
	}

}
