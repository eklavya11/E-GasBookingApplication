package com.gasbooking.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gasbooking.app.entity.Bank;
import com.gasbooking.app.entity.Customer;
import com.gasbooking.app.exception.BankException;
import com.gasbooking.app.exception.CustomerException;
import com.gasbooking.app.repository.BankRepository;
import com.gasbooking.app.repository.CustomerRepository;



/************************************************************************************
 *          @author          Kumar_Eklavya
 *          Description      It is a service class that provides the services for adding,updating,deleting
 *                           a bank details for a customer if the customer exists,we can also get the bank
 *                           details of a customer using his customer id.
                                          
  *         Version           1.0
  *         Created Date      11-FEB-2023
 ************************************************************************************/

@Service
public class BankServiceImpl implements BankService{
	
	@Autowired
	private BankRepository bankRepository;
	@Autowired
	private CustomerRepository customerRepository;
	
	/************************************************************************************
	 * Method: 			         - addBankToCustomer
     *Description: 		         - To add bank details of customer using customer's Id
	 * @param bank               - Object of bank to add to customer
	 * @param customerId         - Id of customer for whom we have to add bank details
	 * @returns Bank             - returns inserted bank otherwise throws CustomerException
	 * @throws CustomerException - It is raised if the customer does not exists for customer Id.
	                              
	                           
                *Created By                                - Kumar_Eklavya
                *Created Date                            -  11-FEB-2023                        
	 
	 ************************************************************************************/

	@Override
	public Bank addBankToCustomer(Bank bank, Integer customerId) throws CustomerException{
		Optional<Customer> optCustomer = this.customerRepository.findById(customerId);
		if(optCustomer.isEmpty()) {
			// handle exception
			throw new CustomerException("Customer not found for the given customerId : "+ customerId);
		}
		Customer foundCustomer = optCustomer.get();
		Bank foundBank = foundCustomer.getBank();
		if(foundBank!=null) {
			throw new CustomerException("Bank already exists for this customerId : "+customerId);
		}
		Bank newBank = this.bankRepository.save(bank);
		foundCustomer.setBank(newBank);
		this.customerRepository.save(foundCustomer ); 
		return bank;
	}
	
	/************************************************************************************
	 * Method: 			         - getBankByCustomerId
     *Description: 		         - To get bank details of customer using customer's Id
	 * @param customerId         - Id of customer for whom we have to get bank details
	 * @returns Bank             - returns  bank otherwise throws CustomerException or BankException
	 * @throws CustomerException - It is raised if the customer does not exists for customer Id.
	 * @throws BankException     - It is raised if the bank details do not exists for customer Id.                             
	                           
                *Created By                                - Kumar_Eklavya
                *Created Date                            -  11-FEB-2023                        
	 
	 ************************************************************************************/

	@Override
	public Bank getBankByCustomerId(Integer customerId) throws BankException ,CustomerException{
		// TODO Auto-generated method stub
		Optional<Customer> optCustomer = this.customerRepository.findById(customerId);
		if(optCustomer.isEmpty()) {
			// handle exception
			throw new CustomerException("Customer not found for the given customerId : "+ customerId);
		}
		Customer foundCustomer = optCustomer.get();
		Bank foundBank = foundCustomer.getBank();
		
		if(foundBank==null) {
			throw new BankException("Bank details do not exist for the given customerId : " + customerId);
		}
		
		
		return foundBank;
	}
    
	/************************************************************************************
	 * Method: 			         - updateBankByCustomerId
     *Description: 		         - To update bank details of customer using customer's Id
	 * @param bank               - Object of bank to update to customer's bank
	 * @param customerId         - Id of customer for whom we have to update bank details
	 * @returns Bank             - returns updated bank otherwise throws CustomerException or BankException
	 * @throws CustomerException - It is raised if the customer does not exists for customer Id.
	 * @throws BankException     - It is raised if the bank details do not exists for customer Id.  
	 *                              
	                           
                *Created By                                - Kumar_Eklavya
                *Created Date                            -  11-FEB-2023                        
	 
	 ************************************************************************************/
	
	@Override
	public Bank updateBankByCustomerId(Bank bank, Integer customerId) throws BankException,CustomerException{
		
		Optional<Customer> optCustomer = this.customerRepository.findById(customerId);
		if(optCustomer.isEmpty()) {
			// handle exception
			throw new CustomerException("Customer not found for the given customerId : "+ customerId);
		}
		Optional<Bank> optBank = this.bankRepository.findById(bank.getBankId());
		if(optBank.isEmpty()) {
			// handle exception
			throw new BankException("Bank not found for the given bankId to update : "+ bank.getBankId());
		}
		Customer foundCustomer = optCustomer.get();
		
		Bank custBank = getBankByCustomerId(customerId);
		if(custBank.getBankId()!= optBank.get().getBankId()) {
			throw new BankException("Bank does not exists for the given customer to update : "+ bank.getBankId());
		}
		
		
		Bank newBank = this.bankRepository.save(bank);
		foundCustomer.setBank(newBank);
		this.customerRepository.save(foundCustomer); 
		return bank;
	}
	
	/************************************************************************************
	 * Method: 			         - deleteBankByCustomerId
     *Description: 		         - To delete bank details of customer using customer's Id
	 * @param customerId         - Id of customer for whom we have to delete bank details
	 * @returns Bank             - returns deleted bank otherwise throws CustomerException or BankException
	 * @throws CustomerException - It is raised if the customer does not exists for customer Id.
	 * @throws BankException     - It is raised if the bank details do not exists for customer Id.                             
	                           
                *Created By                                - Kumar_Eklavya
                *Created Date                            -  11-FEB-2023                        
	 
	 ************************************************************************************/

	@Override
	public Bank deleteBankByCustomerId(Integer customerId) throws BankException,CustomerException {
		// TODO Auto-generated method stub
		Optional<Customer> optCustomer = this.customerRepository.findById(customerId);
		if(optCustomer.isEmpty()) {
			// handle exception
			throw new CustomerException("Customer not found for the given customerId : "+ customerId);
		}
		
		
		
		Customer foundCustomer = optCustomer.get();
		Bank bank = foundCustomer.getBank();
		if(bank==null) {
			throw new BankException("Bank not found for the given customerId to delete : "+ customerId);
		}
		foundCustomer.setBank(null);
		this.customerRepository.save(foundCustomer);
		this.bankRepository.delete(bank);
		return bank;
		
	}

}
