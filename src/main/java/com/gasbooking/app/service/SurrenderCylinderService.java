package com.gasbooking.app.service;

import java.util.List;

import com.gasbooking.app.entity.SurrenderCylinder;
import com.gasbooking.app.exception.CustomerException;
import com.gasbooking.app.exception.SurrenderCylinderException;

public interface SurrenderCylinderService {
	
	SurrenderCylinder addSurrenderCylinderToCustomerById(SurrenderCylinder surrenderCylinder, Integer customerId)throws SurrenderCylinderException,CustomerException;
	
	List<SurrenderCylinder> getAllSurrenderCylinder();
	
	public SurrenderCylinder updateSurrenderCylinderToCustomerById(SurrenderCylinder surrenderCylinder, Integer customerId )throws SurrenderCylinderException, CustomerException;
	
	public SurrenderCylinder deleteSurrenderCylinderById(Integer surrenderCylinderId)throws SurrenderCylinderException;
}
