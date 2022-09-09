package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.demo.model.Appointment;
import com.example.demo.model.Customer;
import com.example.demo.model.Doctor;
import com.example.demo.repository.AppointmentRepository;
import com.example.demo.service.AppointmentService;


@SpringBootTest
public class AppointmentServicesTest {

	@MockBean
	AppointmentRepository appoRepo;
	
	@Autowired
	AppointmentService appoService;
	
	
	
	@Test
	public void createAppointment() {
		Appointment appointment=new Appointment();
		Customer customer=new Customer();
		Doctor doctor=new Doctor();
		appointment.setDate("2022-09-14");
		appointment.setAilment("fever");
		appointment.setDescription("cold and cough");
		appointment.setId(2);
		customer.setUserName("viswapriya@gmail.com");
		customer.setFullName("viswa priya");
		customer.setPassword("priya1234");
		customer.setGender("FEMALE");
		customer.setBloodGroup("O-");
		customer.setContactNo("1234567890");
		customer.setDateOfBirth("2000-08-11");
		appointment.setCustomer(customer);
		doctor.setDoctorUserName("prasad");
		appointment.setDoctor(doctor);
		String response="Appointment booked successfully";
		when(appoRepo.save(appointment)).thenReturn(appointment);
		assertEquals(response,appoService.createAppointment(appointment));
	}
	
	@Test
	public void viewAppointment() {
		Appointment appointment=new Appointment();
		Customer customer=new Customer();
		Doctor doctor=new Doctor();
		appointment.setDate("2022-09-14");
		appointment.setAilment("fever");
		appointment.setDescription("cold and cough");
		appointment.setId(2);
		customer.setUserName("viswapriya@gmail.com");
		customer.setFullName("viswa priya");
		customer.setPassword("priya1234");
		customer.setGender("FEMALE");
		customer.setBloodGroup("O-");
		customer.setContactNo("1234567890");
		customer.setDateOfBirth("2000-08-11");
		appointment.setCustomer(customer);
		doctor.setDoctorUserName("prasad");
		appointment.setDoctor(doctor);
		String userName="viswapriya@gmail.com";
		when(appoRepo.findByCustomerUserName(userName)).thenReturn(appointment);
		assertEquals(appointment,appoService.viewAppointment(userName));
	}
}
