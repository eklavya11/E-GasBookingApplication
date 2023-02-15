package com.gasbooking.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gasbooking.app.entity.Address;
import com.gasbooking.app.entity.Customer;
import com.gasbooking.app.exception.AddressException;
import com.gasbooking.app.repository.AddressRepository;
import com.gasbooking.app.repository.CustomerRepository;

/*******************************************************************************************************************
 * @author Sruthi Kannabathula Description It is a service class that provides
 *         the services for adding,obtaining,updating, deleting address to the
 *         Gas Booking. These services are done by taking Customer ID as
 *         reference. Version 1.0 Created Date 10-FEB-2022
 *******************************************************************************************************************/

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private CustomerRepository customerRepository;

	/********************************************************************************************************
	 * Method: - addAddresstoCustomer Description: - To add a address to the
	 * Gas Booking if and only if gas booking was done for given Id.
	 * 
	 * @param customerId - Customer ID
	 * @param address     - Object of a new address
	 * @returns Address - returns added Address otherwise throws AddressException
	 * @throws AddressException - It is raised if given Customer ID does not
	 *                           exist or Address already exists for given Gas
	 *                           Booking. Created By - Sruthi Kannabathula Created
	 *                           Date - 10-FEB-2022
	 * 
	 ********************************************************************************************************/

	@Override
	public Address addAddresstoCustomer(Integer customerId, Address address) throws AddressException {
		Optional<Customer> optCustomer = customerRepository.findById(customerId);
		if (optCustomer.isEmpty()) {
			throw new AddressException("No customer done for the id:" + customerId);
		}
		Customer foundCustomer = optCustomer.get();
		Address newAddress = addressRepository.save(address);
		foundCustomer.setAddress(newAddress);
		this.customerRepository.save(foundCustomer);
		return newAddress;
	}

	/********************************************************************************************************
	 * Method: - getAddressByCustomerId Description: - To get the details of a
	 * address at the given Customer ID.
	 * 
	 * @param customerId - customer ID
	 * @returns Address - returns obtained Address otherwise throws
	 *          AddressException
	 * @throws AddressException - It is raised if given Customer ID does not
	 *                           exist or address not found for given Gas Booking.
	 *                           Created By - Sruthi Kannabathula Created Date -
	 *                           10-FEB-2022
	 * 
	 ********************************************************************************************************/

	@Override
	public Address getAddressByCustomerId(Integer customerId) throws AddressException {

		Optional<Customer> optCustomer = customerRepository.findById(customerId);
		if (optCustomer.isEmpty())
			throw new AddressException("customer not found for id:" + customerId);
		Address foundAddress = optCustomer.get().getAddress();
		if (foundAddress == null) {
			throw new AddressException("Address not found");
		}
		return foundAddress;
	}

	/********************************************************************************************************
	 * Method: - updateAddressByCustomerId Description: - To update the details
	 * of address at the given Customer ID
	 * 
	 * @param customerId   - Customer ID
	 * @param updateAddress - Object of a address
	 * @returns Address - returns updated Address otherwise throws
	 *          AddressException
	 * @throws AddressException - It is raised if given Customer ID does not
	 *                           exist or address not found for given Gas Booking.
	 *                           Created By - Sruthi Kannabathula Created Date -
	 *                           10-FEB-2022
	 * 
	 ********************************************************************************************************/
	@Override
	public Address updateAddressByCustomerId(Integer customerId, Address updateAddress) throws AddressException {

		Optional<Customer> optCustomer = customerRepository.findById(customerId);
		if (optCustomer.isEmpty()) {
			throw new AddressException("Customer not found for id:" + customerId);
		}
		Customer foundCustomer = optCustomer.get();
		Address address = foundCustomer.getAddress();
		if (address == null) {
			throw new AddressException("Address not found");
		}
		Address updatedAddress = addressRepository.save(updateAddress);
		foundCustomer.setAddress(updatedAddress);
		this.customerRepository.save(foundCustomer);
		return updatedAddress;
	}

	/********************************************************************************************************
	 * Method: - deleteAddressByCustomerId Description: - To delete the address
	 * at the given customer ID
	 * 
	 * @param customer Id - Customer ID
	 * @returns Address - returns deleted Address otherwise throws
	 *          AddressException
	 * @throws AddressException - It is raised if given Customer ID does not
	 *                           exist or address not found for given Gas Booking.
	 *                           Created By - Sruthi Kannabathula Created Date -
	 *                           10-FEB-2022
	 * 
	 ********************************************************************************************************/

	@Override
	public Address deleteAddressByCustomerId(Integer customerId) throws AddressException {

		Optional<Customer> optCustomer = customerRepository.findById(customerId);
		if (optCustomer.isEmpty()) {
			throw new AddressException("customer does not exist for Id:" + customerId);
		}
		Customer foundCustomer = optCustomer.get();
		Address address = foundCustomer.getAddress();
		if (address == null) {
			throw new AddressException("address does not exists for customerId:" + customerId);
		}
		foundCustomer.setAddress(null);
		this.addressRepository.delete(address);
		return address;
	}

}

//public Address addAddresstoCustomer(Integer customerId, Address address) throws AddressException;
//
//{
//	Optional<Customer> optCustomer = customerRepository.findById(customerId);
//	if (optCustomer.isEmpty()) {
//		throw new AddressException("No customer done for the id:" + customerId);
//	}
//	Customer foundCustomer = optCustomer.get();
//	Address newAddress = addressRepository.save(address);
//	foundCustomer.setAddress(newAddress);
//	this.customerRepository.save(foundCustomer);
//	return newAddress;
//}
