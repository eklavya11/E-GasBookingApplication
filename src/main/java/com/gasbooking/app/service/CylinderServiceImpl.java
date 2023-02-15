package com.gasbooking.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gasbooking.app.entity.Cylinder;
import com.gasbooking.app.entity.GasBooking;
import com.gasbooking.app.exception.CylinderException;
import com.gasbooking.app.repository.CylinderRepository;
import com.gasbooking.app.repository.GasBookingRepository;

/*******************************************************************************************************************
 *        @author          Nagakanakadurga Srirajini Kaki
 *        Description      It is a service class that provides the services for adding,obtaining,updating,
         				   deleting cylinder to the Gas Booking. These services are done by taking Gas Booking ID
         				   as reference.
 *        Version          1.0
 *        Created Date     10-FEB-2022
 *******************************************************************************************************************/
@Service
public class CylinderServiceImpl implements CylinderService {
	
	@Autowired
	private CylinderRepository cylinderRepository;
	
	@Autowired
	private GasBookingRepository gasBookingRepository;
	
	/********************************************************************************************************
	 * Method: 			         -  addCylinderByGasBookingId
     * Description: 			 -  To add a cylinder to the Gas Booking if and only if gas booking 
     							    was done for given Id.
	 * @param gasBookingId       -  GasBooking ID
	 * @param cylinder           -  Object of a new cylinder
	 * @returns Cylinder         -  returns added Cylinder otherwise throws CylinderException
	 * @throws CylinderException -  It is raised if given Gas Booking ID does not exist or cylinder already
	  								exists for given Gas Booking.
	 * Created By                -  Nagakanakadurga Srirajini Kaki
     * Created Date              -  10-FEB-2022                           
	 
	 ********************************************************************************************************/

	@Override
	public Cylinder addCylinderByGasBookingId(Integer gasBookingId, Cylinder cylinder) throws CylinderException {
		Optional<GasBooking> optGasBooking = gasBookingRepository.findById(gasBookingId);
		if (optGasBooking.isEmpty()) {
			throw new CylinderException("Gasbooking not found for id:" + gasBookingId);
		}
		GasBooking foundGasBooking = optGasBooking.get();
		Cylinder existingCylinder = foundGasBooking.getCylinder();
		if (existingCylinder != null) {
			throw new CylinderException("Cylinder already exists for the gasBookingId:" + gasBookingId);
		}
		Cylinder newCylinder = cylinderRepository.save(cylinder);
		foundGasBooking.setCylinder(newCylinder);
		this.gasBookingRepository.save(foundGasBooking);
		return newCylinder;
	}
	
	/********************************************************************************************************
	 * Method: 			         -  getCylinderByGasBookingId
     * Description: 			 -  To get the details of a cylinder at the given Gas Booking ID. 
	 * @param gasBookingId       -  GasBooking ID
	 * @returns Cylinder         -  returns obtained Cylinder otherwise throws CylinderException
	 * @throws CylinderException -  It is raised if given Gas Booking ID does not exist or cylinder not found
	  							    for given Gas Booking.
	 * Created By                -  Nagakanakadurga Srirajini Kaki
     * Created Date              -  10-FEB-2022                           
	 
	 ********************************************************************************************************/
	
	@Override
	public Cylinder getCylinderByGasBookingId(Integer gasBookingId) throws CylinderException {

		Optional<GasBooking> optGasBooking = gasBookingRepository.findById(gasBookingId);
		if (optGasBooking.isEmpty())
			throw new CylinderException("GasBooking not found for id:" +gasBookingId);
		Cylinder foundCylinder = optGasBooking.get().getCylinder();
		if (foundCylinder == null) {
			throw new CylinderException("Cylinder not found for gasBookingId:" +gasBookingId);
		}
		return foundCylinder;
	}
	
	/********************************************************************************************************
	 * Method: 			         -  updateCylinderByGasBookingId
     * Description: 			 -  To update the details of cylinder at the given Gas Booking ID
	 * @param gasBookingId       -  GasBooking ID
	 * @param updateCylinder     -  Object of a cylinder
	 * @returns Cylinder         -  returns updated Cylinder otherwise throws CylinderException
	 * @throws CylinderException -  It is raised if given Gas Booking ID does not exist or cylinder not found
	  							    for given Gas Booking.
	 * Created By                -  Nagakanakadurga Srirajini Kaki
     * Created Date              -  10-FEB-2022                           
	 
	 ********************************************************************************************************/
	
	@Override
	public Cylinder updateCylinderByGasBookingId(Integer gasBookingId,Cylinder updateCylinder) throws CylinderException {
		
		Optional<GasBooking> optGasBooking = gasBookingRepository.findById(gasBookingId);
		if(optGasBooking.isEmpty()) {
			throw new CylinderException("GasBooking not found for id:"+gasBookingId);
		}
		GasBooking foundGasBooking= optGasBooking.get();
		Cylinder cylinder= foundGasBooking.getCylinder();
		if(cylinder == null) {
			throw new CylinderException("Cylinder not found for gasBookingId:" + gasBookingId + " to update!");
		}
		Cylinder updatedCylinder= cylinderRepository.save(updateCylinder);
		foundGasBooking.setCylinder(updatedCylinder);
		this.gasBookingRepository.save(foundGasBooking);
		return updatedCylinder;
	}
	
	/********************************************************************************************************
	 * Method: 			         -  deleteCylinderByGasBookingId
     * Description: 			 -  To delete the cylinder at the given Gas Booking ID
	 * @param gasBookingId       -  GasBooking ID
	 * @returns Cylinder         -  returns deleted Cylinder otherwise throws CylinderException
	 * @throws CylinderException -  It is raised if given Gas Booking ID does not exist or cylinder not found
	  							    for given Gas Booking.
	 * Created By                -  Nagakanakadurga Srirajini Kaki
     * Created Date              -  10-FEB-2022                           
	 
	 ********************************************************************************************************/
	
	@Override
	public Cylinder deleteCylinderByGasBookingId(Integer gasBookingId) throws CylinderException {

		Optional<GasBooking> optGasBooking = gasBookingRepository.findById(gasBookingId);
		if (optGasBooking.isEmpty()) {
			throw new CylinderException("GasBooking not found for Id:" + gasBookingId);
		}
		GasBooking foundGasBooking = optGasBooking.get();
		Cylinder cylinder = foundGasBooking.getCylinder();
		if (cylinder == null) {
			throw new CylinderException("Cylinder not found for gasbookingId:" + gasBookingId + " to delete!");
		}
		foundGasBooking.setCylinder(null);
		this.cylinderRepository.delete(cylinder);
		this.gasBookingRepository.save(foundGasBooking);
		return cylinder;
	}

}
