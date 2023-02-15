package com.gasbooking.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.gasbooking.app.entity.Admin;
import com.gasbooking.app.entity.GasBooking;
import com.gasbooking.app.exception.AdminException;
import com.gasbooking.app.service.AdminService;
import com.gasbooking.app.service.GasBookingService;

@SpringBootTest
class AdminServiceTests {
	@Autowired 
	private AdminService adminService;
	@Test
	@DisplayName("Admin is not created when Username is Null ")
	public void runtimeExceptionUsernameTest(){
		Assertions.assertThrows(AdminException.class,()->{
			adminService.insertAdmin(new Admin(6,null,"vandana9","7878787878","vandana@gmail.com"));
	});
		
	}
	@Test
	public void insertAdminTest(){
		assertNotNull(adminService.insertAdmin(new Admin(10,"suhasini","suhasini9","7878797878","suhasini@gmail.com")));
	}
	@Test
	@DisplayName("Admin is not created when Password is Null ")
	public void runtimeExceptionEmailTest(){
		Assertions.assertThrows(AdminException.class,()->{
			adminService.insertAdmin(new Admin(18,"niharika","niharika12","9898989898",null));
	});
		
	}
	@ParameterizedTest
	@ValueSource(strings= {"9999999999","4567891111","1234456789"}) 
	public void insertPhonenumberTest(String mobileNumber){
		adminService.insertAdmin(new Admin(89,"Ajay2", "ajay87877", mobileNumber,"ajay@gmail.com"));
	}
	@ParameterizedTest
	@ValueSource(strings= {"vandana3","saraswathi","rajeswari"})
	public void insertPasswordTest(String password){
		adminService.insertAdmin(new Admin(20,"Swapna",password,"7676767676","swapna@gmail.com"));
	}
	@Test
	public void getAdminByIdTest() throws AdminException{
		assertNotNull(adminService.getAdminByAdminId(31));
	}
	@Test
	public void getAdminByIdTest1() throws AdminException{
		assertNotNull(adminService.getAdminByAdminId(19));
	} 
	@Test
	public void getAdminByIdExceptionTest() {
		
		assertThrows(AdminException.class,()->adminService.getAdminByAdminId(2));
	}
	@Test
	public void getAllGasBookingsTest() {
		assertNotNull(adminService.getAllGasBookings());
		
	}
	@Test
	public void getCustomerByCustomerIdTest() throws AdminException{
		assertThrows(AdminException.class,()->adminService.getAllBookingsByCustomerId(66));
	}
	@Test 
	public void deleteAdminByIdTest() {
		
		String msg=null;
		try {
			adminService.deleteAdminByAdminId(74);
		} catch (AdminException e) {
			msg=e.getMessage();
		}
		
		assertEquals("Admin id not found :74", msg);
	}
	@Test 
	public void deleteAdminByIdTest1() {
		
		String msg=null;
		try {
			adminService.deleteAdminByAdminId(13);
		} catch (AdminException e) {
			msg=e.getMessage(); 
		}
		
		assertEquals("Admin id not found :13", msg);   
	}
	@Test 
	public void getCustomerByIdTest() {
		
		String msg=null;
		try { 
			adminService.getAllBookingsByCustomerId(69);
		} catch (AdminException e) {
			msg=e.getMessage();
		}
		
		
		assertEquals("Customer id not found :69", msg);
	}
	@Test 
	public void getCustomerByIdTest1() {
		
		String msg=null;
		try { 
			adminService.getAllBookingsByCustomerId(2);
		} catch (AdminException e) {
			msg=e.getMessage();
		}
		
		assertEquals("Customer id not found :2", msg);
	}
	@Test
	public void getAllSurrenderCylinderTest() {
		assertNotNull(adminService.getAllSurrenderCylinders());
		
	}
	@Test 
	public void getGasBookingByBookingDateTest() throws ParseException {
		String inputDate="2023-02-13";
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Date date=null;
		String msg=null;
		try { 
			date=sdf.parse(inputDate);
		} catch (ParseException e) {
			msg=e.getMessage();
		}
		assertNotNull(date);
	} 
	@Test
	public void getGasBookingByBookingDateTest1() throws AdminException{

		assertThrows(AdminException.class,() -> {
			List<GasBooking> bookings=adminService.getAllBookingsForBookingDate(null);
		});
	}
	
	
}
