package com.gasbooking.app.service;

import com.gasbooking.app.entity.Address;
import com.gasbooking.app.exception.AddressException;

public interface AddressService {

	Address addAddresstoCustomer(Integer customerId,Address address) throws AddressException;

	Address updateAddressByCustomerId(Integer customerId, Address updateAddress) throws AddressException;

	Address getAddressByCustomerId(Integer customerId) throws AddressException;

	Address deleteAddressByCustomerId(Integer customerId) throws AddressException;

}
