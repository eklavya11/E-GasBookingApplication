package com.gasbooking.app.service;

import java.util.List;

import com.gasbooking.app.entity.GasBooking;
import com.gasbooking.app.exception.GasBookingException;

public interface GasBookingService {

	// CRUD

	GasBooking addGasBookingToCustomer(Integer customerId,GasBooking gasBooking) throws GasBookingException;

	GasBooking updateGasBookingByCustomerId(Integer customerId,Integer gasBookingId,GasBooking updateGasBooking) throws GasBookingException;

	GasBooking deleteGasBookingByCustomerId(Integer customerId, Integer gasBookingId) throws GasBookingException;

	List<GasBooking> getGasBookingByCustomerId(Integer customerId) throws GasBookingException;

//	GasBooking getGasBookingBillByGasBookingId(Integer gasBookingId) throws GasBookingException;
}
