package com.gasbooking.app.service;



import com.gasbooking.app.entity.Bank;
import com.gasbooking.app.exception.BankException;
import com.gasbooking.app.exception.CustomerException;


public interface BankService {
	Bank addBankToCustomer(Bank bank, Integer customerId)throws CustomerException;

	Bank getBankByCustomerId(Integer customerId) throws BankException,CustomerException;

	Bank updateBankByCustomerId(Bank bank, Integer customerId)throws BankException,CustomerException;

	Bank deleteBankByCustomerId(Integer customerId)throws BankException,CustomerException;
	
	
}
