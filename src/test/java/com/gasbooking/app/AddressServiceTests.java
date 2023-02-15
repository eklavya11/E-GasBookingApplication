package com.gasbooking.app;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.gasbooking.app.entity.Address;
import com.gasbooking.app.exception.AddressException;
import com.gasbooking.app.service.AddressService;

@SpringBootTest
class AddressServiceTests {
	@Autowired
	private AddressService addressService;

	@Test
	void addAddresstoCustomerTest() throws AddressException {
		assertNotNull(addressService.addAddresstoCustomer(1,new Address(11, "Sindhiya society", 54, "kakinada", 533005)));
	}

	@Test
	void addAddresstoCustomerTest2() throws AddressException {
		assertNotNull(addressService.addAddresstoCustomer(2,new Address(12, "gateway society", 51, "vizag", 544003)));
	}

//	@Test
//	void addAddresstoCustomerTest1() throws AddressException {
//		assertNotNull(addressService.addAddresstoCustomer(3,new Address(13, "Indira society", 52, "vijayawada", 500554)));
//	}
//
//	@Test
//	void getAddressByCustomerIdTest() throws AddressException {
//		assertNotNull(addressService.getAddressByCustomerId(31));
//	}

//	@Test
//	void getAddressByCustomerIdTest1() throws AddressException {
//		assertNotNull(addressService.getAddressByCustomerId(19));
//	}

//	@Test
//	void getAddressByCustomerIdTest3() throws AddressException {
//		assertNotNull(addressService.getAddressByCustomerId(20));
//	}
//
//	@Test
//	void getAddressByCustomerIdExceptionTest() {
//
//		assertThrows(AddressException.class, () -> addressService.getAddressByCustomerId(2));
//	}
//
//	@Test
//	void deleteAddressByCustomerIdTest() {
//
//		String msg = null;
//		try {
//			addressService.deleteAddressByCustomerId(12);
//		} catch (AddressException e) {
//			msg = e.getMessage();
//		}
//
//		assertEquals("Address id not found :1", msg);
//	}

//	@Test
//	void deleteAddressByCustomerIdTest1() {
//
//		String msg = null;
//		try {
//			addressService.deleteAddressByCustomerId(13);
//		} catch (AddressException e) {
//			msg = e.getMessage();
//		}
//
//		assertEquals("Address id not found :2", msg);
//	}
//
//	@Test
//	void deleteAddressByCustomerIdTest3() {
//
//		String msg = null;
//		try {
//			addressService.deleteAddressByCustomerId(17);
//		} catch (AddressException e) {
//			msg = e.getMessage();
//		}
//
//		assertEquals("Address id not found :3", msg);
//	}

}

//@ParameterizedTest
//@ValueSource(strings = { "9999999999", "4567891111", "1234456789" })
//public void addPincodeTest1(String pincode) throws AddressException {
//	addressService.addAddresstoCustomer(new Address(4, "Indira colony", "53-6", "bengalore", "500005"));
//}
//
//@ParameterizedTest
//@ValueSource(strings = { "vandana3", "saraswathi", "rajeswari" })
//public void addPincodeTest(String password) throws AddressException {
//	addressService.addAddresstoCustomer(new Address(5, "A.P.S.P colony","50-3", "east godavari", "543006;"));
//}