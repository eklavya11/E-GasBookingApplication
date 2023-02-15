package com.gasbooking.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gasbooking.app.entity.Cylinder;
import com.gasbooking.app.entity.GasBooking;
import com.gasbooking.app.exception.CylinderException;
import com.gasbooking.app.repository.GasBookingRepository;
import com.gasbooking.app.service.CylinderService;


@RestController
public class CylinderController {

	@Autowired
	private CylinderService cylinderService;
	
	@Autowired
	private GasBookingRepository gasBookingRepository;
	
	@PostMapping("/cylinder-gasbooking")
	public GasBooking addGasBooking(@RequestBody GasBooking gasBooking) {
		
		return gasBookingRepository.save(gasBooking);
	}

	@PostMapping("/cylinder/{gasBookingId}")
	public Cylinder addCylinderByGasBookingId(@PathVariable("gasBookingId") Integer gasBookingId,@Valid @RequestBody Cylinder cylinder) throws CylinderException {
		
		return cylinderService.addCylinderByGasBookingId(gasBookingId,cylinder);
	}
	
	@GetMapping("/cylinder/{gasBookingId}")
	public Cylinder getCylinderByGasBookingId(@PathVariable("gasBookingId") Integer gasBookingId)throws CylinderException{
		
		return cylinderService.getCylinderByGasBookingId(gasBookingId);
	}
	
	@PutMapping("/cylinder/{gasBookingId}")
	public Cylinder updateCylinderByGasBookingId(@PathVariable("gasBookingId") Integer gasBookingId,@Valid @RequestBody Cylinder updateCylinder)throws CylinderException{
		
		return cylinderService.updateCylinderByGasBookingId(gasBookingId,updateCylinder);
	}
	
	@DeleteMapping("/cylinder/{gasBookingId}")
	public Cylinder deleteCylinderByGasBookingId(@PathVariable("gasBookingId") Integer gasBookingId)throws CylinderException{
		
		return cylinderService.deleteCylinderByGasBookingId(gasBookingId);
	}
	
}

