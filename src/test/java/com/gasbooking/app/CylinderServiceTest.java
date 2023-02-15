package com.gasbooking.app;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.gasbooking.app.entity.Cylinder;
import com.gasbooking.app.entity.CylinderType;
import com.gasbooking.app.exception.CylinderException;
import com.gasbooking.app.service.CylinderService;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class CylinderServiceTests {
	@Autowired
	private CylinderService cylinderService;

	@Test
	void addCylinderExceptionTest1() throws CylinderException {
		assertThrows(CylinderException.class,()->cylinderService.addCylinderByGasBookingId(1,new Cylinder(5,CylinderType.DOMESTIC,500,5)));
	}
	@Test
	void addCylinderExceptionTest2() throws CylinderException {
		assertThrows(CylinderException.class,()->cylinderService.addCylinderByGasBookingId(100,new Cylinder(6,CylinderType.DOMESTIC,500,5)));
	}
	@Test
	void addCylinderExceptionMessageTest1() {
		String msg=null;
		try {
			cylinderService.addCylinderByGasBookingId(100,new Cylinder(5,CylinderType.DOMESTIC,500,5));
		} 
		catch (CylinderException e) {
			msg=e.getMessage();
		}
		assertEquals("Gasbooking not found for id:100", msg);
	}
	@Test
	void addCylinderExceptionMessageTest2() {
		String msg=null;
		try {
			cylinderService.addCylinderByGasBookingId(1,new Cylinder(5,CylinderType.DOMESTIC,500,5));
		} 
		catch (CylinderException e) {
			msg=e.getMessage();
		}
		assertEquals("Cylinder already exists for the gasBookingId:1", msg);
	}
	@Test
	void getCylinderByIdTest() throws CylinderException {
		assertNotNull(cylinderService.getCylinderByGasBookingId(1));
	}
	@Test
	void getCylinderByIdExceptionTest() {
		assertThrows(CylinderException.class,()->cylinderService.getCylinderByGasBookingId(100));
	}
	@Test
	void getCylinderByIdExceptionMessageTest1() {
		String msg=null;
		try {
			cylinderService.getCylinderByGasBookingId(500);
		} 
		catch (CylinderException e) {
			msg=e.getMessage();
		}
		assertEquals("GasBooking not found for id:500", msg);
	}
	@Test
	void getCylinderByIdExceptionMessageTest2() {
		String msg=null;
		try {
			cylinderService.getCylinderByGasBookingId(2);
		} 
		catch (CylinderException e) {
			msg=e.getMessage();
		}
		assertEquals("Cylinder not found for gasBookingId:2", msg);
	}
	@Test
	void updateCylinderExceptionTest1() throws CylinderException {
		assertThrows(CylinderException.class,()->cylinderService.updateCylinderByGasBookingId(100,new Cylinder(5,CylinderType.DOMESTIC,500,5)));
	}
	@Test
	void updateCylinderExceptionTest2() throws CylinderException {
		assertThrows(CylinderException.class,()->cylinderService.updateCylinderByGasBookingId(2,new Cylinder(5,CylinderType.DOMESTIC,500,5)));
	}
	@Test 
	void updateCylinderExceptionMessageTest1() {
		
		String msg=null;
		try {
			cylinderService.updateCylinderByGasBookingId(100,new Cylinder(5,CylinderType.DOMESTIC,500,5));
		}
		catch (CylinderException e) {
			msg=e.getMessage();
		}
		assertEquals("GasBooking not found for id:100", msg);
	}
	@Test 
	void updateCylinderExceptionMessageTest2() {
		
		String msg=null;
		try {
			cylinderService.updateCylinderByGasBookingId(100,new Cylinder(5,CylinderType.DOMESTIC,500,5));
		}
		catch (CylinderException e) {
			msg=e.getMessage();
		}
		assertEquals("Cylinder not found for gasBookingId:100 to update!", msg);
	}
	@Test
	void deleteCylinderTest() throws CylinderException {
		assertNotNull(cylinderService.deleteCylinderByGasBookingId(1));
	}
	@Test
	void deleteCylinderExceptionTest1() {
		assertThrows(CylinderException.class,()->cylinderService.deleteCylinderByGasBookingId(100));
	}
	@Test
	void deleteCylinderExceptionTest2() {
		assertThrows(CylinderException.class,()->cylinderService.deleteCylinderByGasBookingId(2));
	}
	@Test 
	void deleteCylinderExceptionMessageTest1() {
		
		String msg=null;
		try {
			cylinderService.deleteCylinderByGasBookingId(100);
		}
		catch (CylinderException e) {
			msg=e.getMessage();
		}
		assertEquals("GasBooking not found for Id:100", msg);
	}
	@Test 
	void deleteCylinderExceptionMessageTest2() {
		
		String msg=null;
		try {
			cylinderService.deleteCylinderByGasBookingId(2);
		}
		catch (CylinderException e) {
			msg=e.getMessage();
		}
		assertEquals("Cylinder not found for gasbookingId:2 to delete!", msg);
	}
}
		
