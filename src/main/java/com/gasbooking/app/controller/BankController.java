package com.gasbooking.app.controller;

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

import com.gasbooking.app.entity.Bank;
import com.gasbooking.app.exception.BankException;
import com.gasbooking.app.exception.CustomerException;
import com.gasbooking.app.service.BankService;



@RestController
public class BankController {
    
	@Autowired
	private BankService bankService;
	
	@PostMapping("/bank/{customerId}")
	@ResponseStatus(value = HttpStatus.CREATED)
	public Bank addBankToCustomer(@Valid @RequestBody Bank bank,@PathVariable("customerId") Integer customerId)throws CustomerException {
		
		return this.bankService.addBankToCustomer(bank, customerId);
	}
	
	@GetMapping("/bank/{customerId}")
	@ResponseStatus(value = HttpStatus.CREATED)
	public Bank getBankByCustomerId(@PathVariable("customerId") Integer customerId)throws BankException,CustomerException{
		
		return this.bankService.getBankByCustomerId(customerId);
		
	}
	@PutMapping("/bank/{customerId}")
	@ResponseStatus(value = HttpStatus.CREATED)
	public Bank updateBankByCustomerId(@Valid @RequestBody Bank bank,@PathVariable("customerId") Integer customerId)throws BankException, CustomerException{
		return this.bankService.updateBankByCustomerId(bank, customerId);
	}
	@DeleteMapping("/bank/{customerId}")
	@ResponseStatus(value = HttpStatus.CREATED)
	public String deleteBankForCustomer(@PathVariable("customerId") Integer customerId)throws BankException, CustomerException{
		 this.bankService.deleteBankByCustomerId(customerId);
		 return "Bank Details Deleted Successfully";
	}
}
