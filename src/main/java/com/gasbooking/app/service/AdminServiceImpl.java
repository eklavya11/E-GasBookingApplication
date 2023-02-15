package com.gasbooking.app.service;

import java.time.LocalDate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gasbooking.app.entity.Admin;
import com.gasbooking.app.entity.Customer;
import com.gasbooking.app.entity.GasBooking;
import com.gasbooking.app.entity.SurrenderCylinder;
import com.gasbooking.app.exception.AdminException;
import com.gasbooking.app.repository.AdminRepository;
import com.gasbooking.app.repository.CustomerRepository;
import com.gasbooking.app.repository.GasBookingRepository;
import com.gasbooking.app.repository.SurrenderCylinderRepository;
/******************************************************************************************************************
 *         @author          Thotly Vandana
 *         Description      It is a service class that provides the services for adding,updating,deleting,obtaining
          					and also admin can get to see all Bookings by CustomerId and by BookingDate,Admin can 
          					also see all Gasbookings and SurrenderCylinders.
 *         Version          1.0
 *         Created Date     10-FEB-2023
 ******************************************************************************************************************/

@Service
public class AdminServiceImpl implements AdminService{
	@Autowired
	private AdminRepository adminRepository;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private GasBookingRepository gasBookingRepository;
	@Autowired
	private SurrenderCylinderRepository surrenderCylinderRepository;
	
	/************************************************************************************
	 * Method: 			 	-  insertAdmin
     * Description: 		-  To add Admin
	 * @returns Admin       -  returns new Admin
     * Created By           -  Thotly Vandana
     * Created Date         -  10-FEB-2023                           
	 
	 ************************************************************************************/
	@Override
	public Admin insertAdmin(Admin admin){
		return adminRepository.save(admin);
	} 
	
	
	/************************************************************************************
	 * Method: 			 	-  updateAdmin
     * Description: 		-  To update Admin
	 * @returns Admin       -  returns updated Admin
     * Created By           -  Thotly Vandana
     * Created Date         -  10-FEB-2023                           
	 
	 ************************************************************************************/
	@Override
	public Admin updateAdmin(Admin admin){
		return adminRepository.save(admin);
		
	}
	
	
	/********************************************************************************************************
	 * Method: 			         -  deleteAdminbyAdminId
     * Description: 			 -  To delete the admin at the given Admin ID
	 * @param adminId       	 -  Admin ID
	 * @returns Cylinder         -  returns deleted Admin otherwise throws AdminException
	 * @throws AdminException    -  It is raised if given Admin ID does not exist 
	 * Created By                -  Thotly Vandana
     * Created Date              -  10-FEB-2022                           
	 
	 ********************************************************************************************************/

	@Override
	public Admin deleteAdminByAdminId(Integer adminId) throws AdminException{
		Optional<Admin> optAdmin=adminRepository.findById(adminId);
		if(!optAdmin.isPresent()) {
			throw new AdminException("Admin with id "+ adminId+" not found");
		}
		Admin admin=optAdmin.get();
		adminRepository.delete(admin); 
		return admin;
	} 
	
	
	/********************************************************************************************************
	 * Method: 			         -  getAdminByAdminId
     * Description: 			 -  To get the details of a admin by the given Admin ID. 
	 * @param adminId       	 -  Admin ID
	 * @returns Admin         	 -  returns obtained Admin otherwise throws AdminException
	 * @throws AdminException 	 -  It is raised if given Admin ID does not exist 
	 * Created By                -  Thotly Vandana
     * Created Date              -  10-FEB-2022                           
	 
	 ********************************************************************************************************/
	@Override
	public Admin getAdminByAdminId(Integer adminId) throws AdminException {
		Optional<Admin> optAdmin=adminRepository.findById(adminId);
		if(optAdmin.isEmpty())
			throw new AdminException("Admin with Id "+adminId+" not found");
		return optAdmin.get();
	}
	
	
	
	/********************************************************************************************************
	 * Method: 			         -  getAllBookingsByCustomerId
     * Description: 			 -  To get the details of all GasBookings by the given Customer ID. 
	 * @param gasBookingId       -  Customer ID
	 * @returns GasBookings      -  returns obtained GasBookings otherwise throws AdminException
	 * @throws AdminException 	 -  It is raised if given CustomerId does not exist 
	 * Created By                -  Thotly Vandana
     * Created Date              -  10-FEB-2022                           
	 
	 ********************************************************************************************************/
	@Override
	public List<GasBooking> getAllBookingsByCustomerId(Integer customerId) throws AdminException{
		Optional<Customer> optCustomer=customerRepository.findById(customerId);
		if(optCustomer.isEmpty())
			throw new AdminException("Customer with Id "+customerId+" not found");
		return optCustomer.get().getGasbookings();
	}
	
	
	/************************************************************************************
	 * Method: 			 			-  getAllGasBookings
     * Description: 				-  To get the list of all GasBooking Details.
	 * @returns List<GasBooking>    -  returns list of all GasBookings
     * Created By           		-  Thotly Vandana
     * Created Date         		-  10-FEB-2023                           
	 
	 ************************************************************************************/
	@Override
	public List<GasBooking> getAllGasBookings(){
		return gasBookingRepository.findAll();
	}
	
	
	
	/****************************************************************************************
	 * Method: 			 				 -  getAllSurrenderCylinders
     * Description: 					 -  To get the list of all SurrenderCylinder Details.
	 * @returns List<SurrenderCylinder>  -  returns list of all SurrenderCylinders
     * Created By          				 -  Thotly Vandana
     * Created Date        				 -  10-FEB-2023                           
	 
	 *****************************************************************************************/
	@Override
	public List<SurrenderCylinder> getAllSurrenderCylinders(){
		return surrenderCylinderRepository.findAll();
	}
	
	
	/********************************************************************************************************
	 * Method: 			         -  getAllBookingsForBookingDate
     * Description: 			 -  To get the details of all GasBookings by the Booking Date. 
	 * @param bookingDate        -  Booking Date
	 * @returns GasBookings      -  returns obtained GasBookings otherwise throws AdminException
	 * @throws AdminException 	 -  It is raised if given bookingDate does not exist 
	 * Created By                -  Thotly Vandana
     * Created Date              -  10-FEB-2022                           
	 
	 ********************************************************************************************************/
	@Override 
	public List<GasBooking> getAllBookingsForBookingDate(LocalDate bookingDate) throws AdminException{
		List<GasBooking> optGasBooking=gasBookingRepository.findByBookingDate(bookingDate);
	 	if(optGasBooking.isEmpty())
			throw new AdminException("There are no GasBookings with particular date "+ bookingDate);
		else
			return gasBookingRepository.findByBookingDate(bookingDate);
	} 
}
