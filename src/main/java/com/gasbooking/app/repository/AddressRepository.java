package com.gasbooking.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gasbooking.app.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {

}
