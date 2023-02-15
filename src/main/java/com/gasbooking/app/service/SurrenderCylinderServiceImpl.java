package com.gasbooking.app.service;

import java.util.List;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gasbooking.app.entity.Customer;
import com.gasbooking.app.entity.SurrenderCylinder;
import com.gasbooking.app.exception.CustomerException;
import com.gasbooking.app.exception.SurrenderCylinderException;
import com.gasbooking.app.repository.CustomerRepository;
import com.gasbooking.app.repository.SurrenderCylinderRepository;

/************************************************************************************
 *          @author          Naveen Kumar
 *          Description      It is a service class that provides the services for adding a new surrenderCylinder, 
                               deleting a surrenderCylinder,fetching a surrenderCylinder. 
  *         Version             1.0
  *         Created Date    10-feb-2023
 ************************************************************************************/

@Service
public class SurrenderCylinderServiceImpl implements SurrenderCylinderService {
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private SurrenderCylinderRepository surrenderCylinderRepository;
	
	/************************************************************************************
	 * Method: 			-addSurrenderCylinderToCustomerById
     * Description: 	-Adding a new SurrenderCylinder
	 * @param surrenderCylinder - SurrenderCylinder type object
	 * @param customerId        - Id of Customer of type Integer
	 * @return surrenderCylinder - SurrenderCylinder type object
	 * @throws surrenderCylinderException - It is raised due to surrenderCylinderID NOT FOUND.
       @throws customerException - It is raised due to customerId NOT FOUND.                 
                *Created By        - Naveen Kumar                        
                *Created Date      - 10-feb-2023                         
	 
	 ************************************************************************************/


	@Override
	public SurrenderCylinder addSurrenderCylinderToCustomerById(SurrenderCylinder surrenderCylinder, Integer customerId)throws SurrenderCylinderException,CustomerException {
		Optional<Customer> optCustomer = this.customerRepository.findById(customerId);
		if(optCustomer.isEmpty()) {
			throw new CustomerException("Customer Id Not Found");
		}
//		Optional<SurrenderCylinder> getSurrenderCylinder=surrenderCylinderRepository.findById(surrenderCylinder.getSurrenderId());
//		if(getSurrenderCylinder.isPresent())
//		{
//			throw new SurrenderCylinderException("Surrender Cylinder Already Exists");
//		}
		Customer foundCustomer = optCustomer.get();
		SurrenderCylinder newSurrenderCylinder = this.surrenderCylinderRepository.save(surrenderCylinder);
		foundCustomer.setSurrender(newSurrenderCylinder);
		this.customerRepository.save(foundCustomer);
		return newSurrenderCylinder;
		
	}
	
	/************************************************************************************
	 * Method: 			-updateSurrenderCylinderToCustomerById
     * Description: 	-updating a new SurrenderCylinder
	 * @param surrenderCylinder - SurrenderCylinder type object
	 * @param customerId        - Id of Customer of type Integer
	 * @return surrenderCylinder - SurrenderCylinder type object
	 * @throws surrenderCylinderException - It is raised due to surrenderCylinderID NOT FOUND.
       @throws customerException - It is raised due to customerId NOT FOUND.                 
                *Created By        - Naveen Kumar                        
                *Created Date      - 10-feb-2023                         
	 
	 ************************************************************************************/
	
	@Override
	public SurrenderCylinder updateSurrenderCylinderToCustomerById(SurrenderCylinder updateSurrenderCylinders, Integer customerId)throws SurrenderCylinderException, CustomerException {
		Optional<Customer> optCustomer = this.customerRepository.findById(customerId);
		if(optCustomer.isEmpty()) {
			throw new CustomerException("Customer Id Not Found");
		}
		Optional<SurrenderCylinder> existingSurrenderCylinder = surrenderCylinderRepository.findById(updateSurrenderCylinders.getSurrenderId());
		if(existingSurrenderCylinder == null)
		{
			throw new SurrenderCylinderException("Cylinder could not be updated, id doesn't exist :"+ updateSurrenderCylinders.getSurrenderId());
		}
		return this.surrenderCylinderRepository.save(updateSurrenderCylinders);
		
	}
	
	/************************************************************************************
	 * Method: 			-deleteSurrenderCylinderToCustomerById
     * Description: 	-delete a new SurrenderCylinder
	 * 
	 * @param customerId        - Id of surrenderCylinder of type Integer
	 * @return surrenderCylinder - SurrenderCylinder type object
	 * @throws surrenderCylinderException - It is raised due to surrenderCylinderID NOT FOUND.               
                *Created By        - Naveen Kumar                        
                *Created Date      - 10-feb-2023                         
	 
	 ************************************************************************************/
    @Override
	public SurrenderCylinder deleteSurrenderCylinderById(Integer SurrenderCylinderId) throws SurrenderCylinderException {
	Optional<SurrenderCylinder> optSurrenderCylinder = this.surrenderCylinderRepository.findById(SurrenderCylinderId);
	if(optSurrenderCylinder.isEmpty())
		throw new SurrenderCylinderException("SurrenderCylinder id does not exists to delete !");
	SurrenderCylinder surrenderCylinder = optSurrenderCylinder.get();
	this.surrenderCylinderRepository.delete(surrenderCylinder);
	return surrenderCylinder;
	}
	

    
	@Override
	public List<SurrenderCylinder> getAllSurrenderCylinder() {
		// TODO Auto-generated method stub
		return this.surrenderCylinderRepository.findAll();
	}

//	public SurrenderCylinderRepository getSurrenderCylinderRepository() {
//		return surrenderCylinderRepository;
//	}
//
//	public void setSurrenderCylinderRepository(SurrenderCylinderRepository surrenderCylinderRepository) {
//		this.surrenderCylinderRepository = surrenderCylinderRepository;
//	}
}
