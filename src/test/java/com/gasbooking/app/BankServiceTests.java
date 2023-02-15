package com.gasbooking.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.gasbooking.app.entity.Bank;
import com.gasbooking.app.exception.BankException;
import com.gasbooking.app.exception.CustomerException;
import com.gasbooking.app.service.BankService;


@SpringBootTest
public class BankServiceTests {

	@Autowired BankService bankService;
	
	@Test
	void addCutomerToBankTest() throws CustomerException{
		String msg = null;
		try {
		assertNotNull(bankService.addBankToCustomer(new Bank(87,"HDFC","Pune",8392929,"jssk338SJSSD", "ADJPEjdj34"),99));
		}
		catch(CustomerException e) {
			msg=e.getMessage();
		}
		assertEquals("Customer not found for the given customerId : 99",msg);
	}
	@Test
	void getBankByCustomerIdTest()throws BankException ,CustomerException{
		assertNotNull(bankService.getBankByCustomerId(28));
	}
	@Test
	void getBankByCustomerIdExceptionTest() {
		
		assertThrows(CustomerException.class,()->bankService.getBankByCustomerId(500));
	}
	@Test
	void getBankByCustomerIdExceptionMessageTest1() throws CustomerException,BankException {
		
		String msg=null;
		try {
			bankService.getBankByCustomerId(14);
		} catch (BankException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			msg=e.getMessage();
		}
		
		assertEquals("Bank details do not exist for the given customerId : 14", msg);
	}
	@Test
	void getBankByCustomerIdExceptionMessageTest2() throws CustomerException,BankException {
		
		String msg=null;
		try {
			bankService.getBankByCustomerId(500);
		} catch (CustomerException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			msg=e.getMessage();
		}
		
		assertEquals("Customer not found for the given customerId : 500", msg);
	}
	@Test
	void updateBankByCustomerIdTest1() throws BankException ,CustomerException{
		String msg = null;
		try {
		assertNotNull(bankService.updateBankByCustomerId(new Bank(87,"HDFC","Pune",8392929,"jssk338SJSSD", "ADJPEjdj34"),99));
		}
		catch(CustomerException e) {
			msg=e.getMessage();
		}
		assertEquals("Customer not found for the given customerId : 99",msg);
	}
	@Test
	void updateBankByCustomerIdTest2() throws BankException ,CustomerException{
		String msg = null;
		try {
		assertNotNull(bankService.updateBankByCustomerId(new Bank(87,"HDFC","Pune",8392929,"jssk338SJSSD", "ADJPEjdj34"),14));
		}
		catch(BankException e) {
			msg=e.getMessage();
		}
		assertEquals("Bank not found for the given bankId to update : 87",msg);
	}
	@Test
	void updateBankByCustomerIdTest3() throws BankException ,CustomerException{
		String msg = null;
		try {
		assertNotNull(bankService.updateBankByCustomerId(new Bank(25,"HDFC","Pune",8392929,"jssk338SJSSD", "ADJPEjdj34"),10));
		}
		catch(BankException e) {
			msg=e.getMessage();
		}
		assertEquals("Bank does not exists for the given customer to update : 25",msg);
	}
	@Test
	void deleteBankByCustomerIdTest1() throws CustomerException, BankException {
		String msg = null;
		try {
		assertNotNull(bankService.deleteBankByCustomerId(600));
		}
		catch(CustomerException e) {
			msg=e.getMessage();
		}
		assertEquals("Customer not found for the given customerId : 600",msg);
	}
	@Test
	void deleteBankByCustomerIdTest2() throws CustomerException, BankException {
		String msg = null;
		try {
		assertNotNull(bankService.deleteBankByCustomerId(14));
		}
		catch(BankException e) {
			msg=e.getMessage();
		}
		assertEquals("Bank not found for the given customerId to delete : 14",msg);
	}
	@Test
	void deleteBankByCustomerIdExceptionTest1() {
		
		assertThrows(CustomerException.class,()->bankService.deleteBankByCustomerId(500));
	}
	@Test
	void deleteBankByCustomerIdExceptionTest2() {
		
		assertThrows(BankException.class,()->bankService.deleteBankByCustomerId(14));
	}
}
