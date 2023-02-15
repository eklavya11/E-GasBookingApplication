package com.gasbooking.app.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.gasbooking.app.entity.Admin;
import com.gasbooking.app.entity.GasBooking;
import com.gasbooking.app.entity.SurrenderCylinder;
import com.gasbooking.app.exception.AdminException;

public interface AdminService {
	Admin insertAdmin(Admin admin);
	Admin updateAdmin(Admin admin) ;
	Admin deleteAdminByAdminId(Integer adminId) throws AdminException;
	Admin getAdminByAdminId(Integer adminId) throws AdminException;
	List<GasBooking> getAllBookingsByCustomerId(Integer customerId) throws AdminException;
	List<GasBooking> getAllGasBookings();
	List<SurrenderCylinder> getAllSurrenderCylinders();
	List<GasBooking> getAllBookingsForBookingDate(LocalDate bookingDate) throws AdminException;
}
