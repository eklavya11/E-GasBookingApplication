package com.gasbooking.app.service;

import com.gasbooking.app.entity.Cylinder;
import com.gasbooking.app.exception.CylinderException;

public interface CylinderService {
	
	Cylinder addCylinderByGasBookingId(Integer gasBookingId,Cylinder cylinder)throws CylinderException;
	
	Cylinder getCylinderByGasBookingId(Integer cylinderId)throws CylinderException;
	
	Cylinder updateCylinderByGasBookingId(Integer gasBookingId,Cylinder updateCylinder)throws CylinderException;
	
	Cylinder deleteCylinderByGasBookingId(Integer gasBookingId)throws CylinderException;
	
}
