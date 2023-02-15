package com.gasbooking.app.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.gasbooking.app.entity.Customer;
import com.gasbooking.app.entity.GasBooking;
import com.gasbooking.app.entity.SurrenderCylinder;

import com.gasbooking.app.exception.CustomerException;
import com.gasbooking.app.exception.GasBookingException;
import com.gasbooking.app.exception.SurrenderCylinderException;
import com.gasbooking.app.repository.CustomerRepository;

/************************************************************************************
 *          @author          Kumar_Eklavya
 *          Description      It is a service class that provides the services for adding,updating,deleting
 *                           a new customer, and also we can get to see all the gas bookings of a customer
 *                           and his/her surrender cylinder details as well.If we want we can also see all
 *                           customers at once.
                                          
  *         Version           1.0
  *         Created Date      10-FEB-2023
 ************************************************************************************/

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	/************************************************************************************
	 * Method: 			         - addCustomer
     *Description: 		         - To add a customer For gasBooking
	 * @param newCustomer        - Object of a new customer
	 * @returns Customer         - returns added Customer otherwise throws CustomerException
	 * @throws CustomerException - It is raised if the customer for given email already exists
	                               or the data given is invalid or email already exists.
	                               
                *Created By                                - Kumar_Eklavya
                *Created Date                            -  10-FEB-2023                        
	 
	 ************************************************************************************/



	@Override
	public Customer addCustomer(Customer newCustomer)throws CustomerException {
		

		try {
		String email = newCustomer.getEmail();
		String query = "SELECT customer FROM Customer customer WHERE customer.email = ?1";
		TypedQuery<Customer> typedQuery = entityManager.createQuery(query, Customer.class);
		typedQuery.setParameter(1, email);
		
		Customer foundCustomer = typedQuery.getSingleResult();
		
		if(foundCustomer!=null) {
			throw new CustomerException("Cusomer already exists for the given email : " + email);
		}

		}
		catch(NoResultException e) {
			return customerRepository.save(newCustomer);
		}
		
		return newCustomer;
	}
	
	/************************************************************************************
	 * Method: 			         - getCutomerById
     *Description: 			     - To get customer details for a given Id
	 * @param customerId         - Id of a customer
	 * @returns Customer         - returns Customer for the given Id otherwise throws CustomerException
	 * @throws CustomerException - It is raised if the customer for given id does not exists.
	 *                           
                *Created By                                - Kumar_Eklavya
                *Created Date                            -  10-FEB-2023                        
	 
	 ************************************************************************************/

	@Override
	public Customer getCutomerById(Integer customerId) throws CustomerException {

		Optional<Customer> optCustomer = customerRepository.findById(customerId);
		if (optCustomer.isEmpty())
			throw new CustomerException("Cusomer not found for the given customerId : " + customerId);

		return optCustomer.get();
	}
	
	/************************************************************************************
	 * Method: 			         - updateCutomerById
     *Description: 		         - To update a customer using customer's Id
	 * @param updateCustomer     - Object of customer to update
	 * @param customerId         - Id of customer for whom we have to update
	 * @returns Customer         - returns updated Customer otherwise throws CustomerException
	 * @throws CustomerException - It is raised if the customer does not exists for customer Id or
	                               the data given is invalid or email already exists in database.
	                           
                *Created By                                - Kumar_Eklavya
                *Created Date                            -  10-FEB-2023                        
	 
	 ************************************************************************************/


	@Override
	public Customer updateCutomerById(Customer updateCustomer,Integer customerId)throws CustomerException {
		
		Optional<Customer> optCustomer = customerRepository.findById(customerId);
		if (optCustomer.isEmpty())
			throw new CustomerException("Cusomer not found for the given customerId : " + customerId);
		
		Customer foundCustomer = optCustomer.get();
		
		if(!updateCustomer.getEmail().equals(foundCustomer.getEmail())) {
			try {
				String email = updateCustomer.getEmail();
				String query = "SELECT customer FROM Customer customer WHERE customer.email = ?1";
				TypedQuery<Customer> typedQuery = entityManager.createQuery(query, Customer.class);
				typedQuery.setParameter(1, email);
				
				Customer customer = typedQuery.getSingleResult();
				
				if(customer!=null) {
					throw new CustomerException("Customer already exists for the given email : " + email);
				}

				}
				catch(NoResultException e) {
					return this.customerRepository.save(updateCustomer);
				}
		}
		
		this.customerRepository.save(updateCustomer);
			
		return updateCustomer;

		
	}
	
	/************************************************************************************
	 * Method: 			         - deleteCutomerById
     *Description: 		         - To delete a customer using customer's Id
	 * @param customerId         - Id of customer to delete
	 * @returns Customer         - returns details of deleted Customer otherwise throws CustomerException
	 * @throws CustomerException - It is raised if the customer does not exists for customer Id.
	                              
	                           
                *Created By                                - Kumar_Eklavya
                *Created Date                            -  10-FEB-2023                        
	 
	 ************************************************************************************/

	@Override
	public Customer deleteCutomerById(Integer customerId) throws CustomerException {

		Optional<Customer> optCustomer = this.customerRepository.findById(customerId);
		if (optCustomer.isEmpty())
			throw new CustomerException("Customer id does not exists to delete ! " + customerId);
		Customer customer = optCustomer.get();
		
		this.customerRepository.delete(customer);
		return customer;
	}
	
	/************************************************************************************
	 * Method: 			         - getAllCustomers
     *Description: 		         - To get all customers at once.
	 * @returns List<Customer>   - returns List of all the customers
	 * @throws CustomerException - It is raised if the there is no single customer.
	                              
	                           
                *Created By                                - Kumar_Eklavya
                *Created Date                            -  10-FEB-2023                        
	 
	 ************************************************************************************/
	
	@Override
	public List<Customer> getAllCustomers() throws CustomerException{
		
		List<Customer> customers=  this.customerRepository.findAll();
		if(customers.isEmpty()) {
			throw new CustomerException("No customers found in the Database");
		}
		
		return customers;
		
	}
	
	/************************************************************************************
	 * Method: 			                  - getSurrenderCylinderDetailsByCustomerID
     *Description: 		                  - To get surrender cylinder details using customer's Id
	 * @param customerId                  - Id of customer
	 * @returns SurrenderCylinder         - returns surrender details of given customer id
	 * @throws CustomerException          - It is raised if the customer does not exists for customer Id.
	 * @throws SurrenderCylinderException - It is raised if surrender details do not exists for customer Id.
	                              
	                           
                *Created By                                - Kumar_Eklavya
                *Created Date                            -  10-FEB-2023                        
	 
	 ************************************************************************************/

	@Override
	public SurrenderCylinder getSurrenderCylinderDetailsByCustomerID(Integer customerId) throws CustomerException, SurrenderCylinderException {
		Optional<Customer> optCustomer = customerRepository.findById(customerId);
		if (optCustomer.isEmpty())
			throw new CustomerException("Cusomer not found for the given customerId : " + customerId);
		Customer foundCustomer = optCustomer.get();
		SurrenderCylinder foundSurrenderCylinder = foundCustomer.getSurrender();
		
		if(foundSurrenderCylinder==null) {
			throw new SurrenderCylinderException("No surrender details for the given customerId : " + customerId);
		}
		
		
		return foundSurrenderCylinder;
	}
	
	/************************************************************************************
	 * Method: 			           - getAllGasBookingsByCustomerId
     *Description: 		           - To get all gas bookings for one customer.
	 * @param customerId           - Id of customer
	 * @returns List<GasBooking>   - returns gas bookings for a customer
	 * @throws CustomerException   - It is raised if the customer does not exists for customer Id.
	 * @throws GasBookingException - It is raised if gas bookings do not exists for customer Id.
	                              
	                           
                *Created By                                - Kumar_Eklavya
                *Created Date                            -  10-FEB-2023                        
	 
	 ************************************************************************************/

	@Override
	public List<GasBooking> getAllGasBookingsByCustomerId(Integer customerId) throws CustomerException , GasBookingException {
		Optional<Customer> optCustomer = customerRepository.findById(customerId);
		if (optCustomer.isEmpty())
			throw new CustomerException("Cusomer not found for the given customerId : " + customerId);
		Customer foundCustomer = optCustomer.get();
		List<GasBooking> foundGasBookings = foundCustomer.getGasbookings();
		if(foundGasBookings.isEmpty()) {
			throw new GasBookingException("Gas Bookings not found for the given customerId : " + customerId);
		}
		return foundGasBookings;
	}
	

}
