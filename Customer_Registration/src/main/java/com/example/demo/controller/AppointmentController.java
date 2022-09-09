package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Appointment;
import com.example.demo.service.AppointmentService;

@RestController
@RequestMapping("/Appointment")
public class AppointmentController {
	
	@Autowired
	AppointmentService appoService;
	
	@PostMapping("/Booking")
	public ResponseEntity<String> addAppointmentDetails(@Validated @RequestBody Appointment appointment){
	    String response=appoService.createAppointment(appointment);
		return new ResponseEntity<String>(response,HttpStatus.CREATED);
	}
	
	@GetMapping("/view")
	public ResponseEntity<Appointment> fetchDetails(@RequestParam("username") String userName){
		Appointment appointments=appoService.viewAppointment(userName);
		return new ResponseEntity<Appointment>(appointments,HttpStatus.FOUND);
	}
	
	@PutMapping("/Update")
	public ResponseEntity<String> update(@Validated @RequestBody Appointment appointment){
		String response=appoService.modify(appointment);
		return new ResponseEntity<String>(response,HttpStatus.RESET_CONTENT);
	}
	
	@DeleteMapping("/Appointment_Cancel")
	public ResponseEntity<String> deleteProfile(@RequestParam("username") String userName) {
		String response = appoService.appointmentDelete(userName);
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}

}
