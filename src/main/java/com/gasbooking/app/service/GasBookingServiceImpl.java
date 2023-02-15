package com.gasbooking.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gasbooking.app.entity.Customer;
import com.gasbooking.app.entity.GasBooking;
import com.gasbooking.app.exception.GasBookingException;
import com.gasbooking.app.repository.CustomerRepository;
import com.gasbooking.app.repository.GasBookingRepository;

/*******************************************************************************************************************
 * @author Sruthi Kannabathula Description It is a service class that provides
 *         the services for adding,obtaining,updating, deleting customer to the
 *         Gas Booking. These services are done by taking Customer ID as
 *         reference. Version 1.0 Created Date 10-FEB-2022
 *******************************************************************************************************************/

@Service
public class GasBookingServiceImpl implements GasBookingService {

	@Autowired
	private GasBookingRepository gasBookingRepository;

	@Autowired
	private CustomerRepository customerRepository;

	/********************************************************************************************************
	 * Method: - addGasBookingToCustomer Description: - To add a gasbooking to the
	 * Gas Booking if and only if gas booking was done for given Id.
	 * 
	 * @param customerId - Customer ID
	 * @param gasbooking - Object of a new cylinder
	 * @returns GasBooking - returns added GasBooking otherwise throws
	 *          GasBookingException
	 * @throws GasBookingException - It is raised if given Gas Booking ID does not
	 *                             exist or gasbooking already exists for given Gas
	 *                             Booking. Created By - Sruthi Kannabathula Created
	 *                             Date - 10-FEB-2022
	 * 
	 ********************************************************************************************************/

	@Override
	public GasBooking addGasBookingToCustomer(Integer customerId, GasBooking gasBooking) throws GasBookingException {
		Optional<Customer> optCustomer = this.customerRepository.findById(customerId);
		if (optCustomer.isEmpty()) {
			throw new GasBookingException("No customer done for the id:" + customerId);
		}
		Customer foundCustomer = optCustomer.get();
		GasBooking newGasBooking = this.gasBookingRepository.save(gasBooking);
		List<GasBooking> bookingsList = foundCustomer.getGasbookings();
		bookingsList.add(newGasBooking);
		foundCustomer.setGasbookings(bookingsList);
		this.customerRepository.save(foundCustomer);
		return newGasBooking;
	}

	/********************************************************************************************************
	 * Method: - updateGasBookingByCustomerId Description: - To update the details
	 * of gasbooking at the given Gas Booking ID and Customer ID
	 * 
	 * @param gasBookingId   - GasBooking ID
	 * @param customerId     - Customer ID
	 * @param updateGasBooking - Object of a gasbooking
	 * @returns GasBooking - returns updated GasBooking otherwise throws
	 *          GasBookingException
	 * @throws GasBookingException - It is raised if given Gas Booking ID does not
	 *                             exist or customer not found for given Gas
	 *                             Booking. Created By - Sruthi Kannabathula Created
	 *                             Date - 10-FEB-2022
	 * 
	 ********************************************************************************************************/

	@Override
	public GasBooking updateGasBookingByCustomerId(Integer customerId, Integer gasBookingId,
			GasBooking updateGasBooking) throws GasBookingException {

		Optional<Customer> optCustomer = customerRepository.findById(customerId);
		if (optCustomer.isEmpty()) {
			throw new GasBookingException("Customer not found for id:" + customerId);
		}
		Customer foundCustomer = optCustomer.get();
		List<GasBooking> bookingList = foundCustomer.getGasbookings();
		GasBooking updatedGasBooking = null;
		for (GasBooking foundGasBooking : bookingList) {
			if (foundGasBooking.getGasBookingId().equals(gasBookingId)) {
				updatedGasBooking = this.gasBookingRepository.save(updateGasBooking);
				int foundIndex = bookingList.indexOf(foundGasBooking);
				bookingList.set(foundIndex, updateGasBooking);
			}
		}
		foundCustomer.setGasbookings(bookingList);
		this.customerRepository.save(foundCustomer);
		return updatedGasBooking;
	}

	/********************************************************************************************************
	 * Method: - deleteGasBookingByCustomerId Description: - To delete the
	 * gasBookingId at at the given Customer ID
	 * 
	 * @param gasBookingId - GasBooking ID
	 * @param customerId  -  Customer ID
	 * @returns GasBooking - returns deleted GasBooking otherwise throws
	 *          GasBookingException
	 * @throws GasBookingException - It is raised if given Gas Booking ID does not
	 *                           exist or gasbooking not found for given Gas Booking.
	 *                           Created By - Sruthi Kannabathula Created Date -
	 *                           10-FEB-2022
	 * 
	 ********************************************************************************************************/

	@Override
	public GasBooking deleteGasBookingByCustomerId(Integer customerId, Integer gasBookingId)
			throws GasBookingException {

		Optional<Customer> optCustomer = this.customerRepository.findById(customerId);
		if (optCustomer.isEmpty()) {
			throw new GasBookingException("customer does not exist for Id:" + customerId);
		}
		Customer foundCustomer = optCustomer.get();
		List<GasBooking> bookingList = foundCustomer.getGasbookings();
		GasBooking deletedGasBooking = new GasBooking();
		for (GasBooking foundGasBooking : bookingList) {
			if (foundGasBooking.getGasBookingId().equals(gasBookingId)) {
				bookingList.remove(foundGasBooking);
				foundCustomer.setGasbookings(bookingList);
				this.customerRepository.save(foundCustomer);
				deletedGasBooking = foundGasBooking;
				this.gasBookingRepository.delete(foundGasBooking);
			}
		}
		return deletedGasBooking;
	}

	/********************************************************************************************************
	 * Method: - getGasBookingByCustomerId Description: - To get the details of a
	 * gasbooking at the given Gas Booking ID.
	 * 
	 * @param customerId - Customer ID
	 * @returns GasBooking - returns obtained GasBooking otherwise throws
	 *          GasBookingException
	 * @throws GasBookingException - It is raised if given Customer ID does not
	 *                           exist or gasbooking not found for given Gas Booking.
	 *                           Created By - Sruthi Kannabathula Created Date -
	 *                           10-FEB-2022
	 * 
	 ********************************************************************************************************/

	@Override
	public List<GasBooking> getGasBookingByCustomerId(Integer customerId) throws GasBookingException {

		Optional<Customer> optCustomer = customerRepository.findById(customerId);
		if (optCustomer.isEmpty())
			throw new GasBookingException("customer not found for id:" + customerId);
		List<GasBooking> foundGasBooking = optCustomer.get().getGasbookings();
		if (foundGasBooking == null) {
			throw new GasBookingException("Gasbooing is not found");
		}
		return foundGasBooking;
	}

}

//		if (gasBooking == null) {
//			throw new GasBookingException("gasBooking not found for the given Id to delete:" + customerId);
//		}
//		foundCustomer.setGasbooking(null);
//		this.customerRepository.save(foundCustomer);
//		this.gasBookingRepository.delete(gasBooking);
//		return gasBooking;
//	}

//	

////	@Override
////	public GasBooking getGasBookingBillByGasBookingId(Integer gasBookingId) throws GasBookingException {
////
////		Optional<Customer> optCustomer = customerRepository.findById(gasBookingId);
////		if (optCustomer.isEmpty())
////			throw new GasBookingException("customer not found for id:" + gasBookingId);
////		GasBooking foundGasBooking = optCustomer.get().getGasBooking();
////		if (foundGasBooking == null) {
////			throw new GasBookingException("Gasbooing is not found");
////		}
//		return foundGasBooking;
//	}

//	@Override
//	public GasBooking updateGasBookingByCustomerId(GasBooking updateGasBooking, Integer customerId)
//			throws GasBookingException {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public GasBooking addGasBookingToCustomer(GasBooking gasBooking, Integer customerId) throws GasBookingException {
//		// TODO Auto-generated method stub
//		return null;
//	}

//	@Override
//	public List<GasBooking> insertCustomerIdByGasBooking(GasBooking gasBooking, Integer customerId)
//			throws GasBookingException {
//		Optional<Customer> optCustomer = this.customerRepository.findById(customerId);
//		if (optCustomer.isEmpty()) {
//			throw new GasBookingException("No gasBooking done for the id:" + customerId);
//		}
//		Customer foundCustomer = optCustomer.get();
//		List<GasBooking> newGasBooking = foundCustomer.getGasbookings();
//		newGasBooking.add(gasBookingRepository.save(gasBooking));
//		foundCustomer.setGasbookings(newGasBooking);
//		this.customerRepository.save(foundCustomer);
//		return newGasBooking;
//	}
//
//	@Override
//	public GasBooking updateGasBookingById(GasBooking gasBooking) {
//
//		return gasBookingRepository.save(gasBooking);
//	}
//
//	@Override
//	public GasBooking deleteGasBookingById(Integer gasBookingId) throws GasBookingException {
//		Optional<GasBooking> optGasBooking = this.gasBookingRepository.findById(gasBookingId);
//		if (optGasBooking.isEmpty()) {
//			throw new GasBookingException("GasBooking id does not exists to delete !");
//		}
//		GasBooking gasBooking = optGasBooking.get();
//		this.gasBookingRepository.delete(gasBooking);
//
//		return gasBooking;
//
//	}
//
//	//@Override
//	//public Float BillByGasBookingId(Integer gasBookingId) throws GasBookingException {
//
//		Optional<GasBooking> foundGasBooking = gasBookingRepository.findById(gasBookingId);
//		if (foundGasBooking.isEmpty())
//			throw new GasBookingException("No gasBooking done for customer id :" + gasBookingId);
//
//		return foundGasBooking.get().getBill();
//
//	}
//
//}
