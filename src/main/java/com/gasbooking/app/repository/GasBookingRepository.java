package com.gasbooking.app.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gasbooking.app.entity.GasBooking;

public interface GasBookingRepository extends JpaRepository<GasBooking, Integer>{

	List<GasBooking> findByBookingDate(LocalDate bookingDate);



}
