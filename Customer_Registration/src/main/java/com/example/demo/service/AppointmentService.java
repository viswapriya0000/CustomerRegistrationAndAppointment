package com.example.demo.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exceptionhandler.NotBookedAppointment;
import com.example.demo.exceptionhandler.ProfileNotFoundException;
import com.example.demo.exceptionhandler.UsernameInvalidException;
import com.example.demo.model.Appointment;
import com.example.demo.repository.AppointmentRepository;

@Service
@Transactional
public class AppointmentService {
	
	@Autowired
	AppointmentRepository appoRepo;
	
	public String createAppointment(Appointment appointment) {
		
		@SuppressWarnings("deprecation")
		Appointment appointment1 = appoRepo.getById(appointment.getId());
		appoRepo.save(appointment);
		return "Appointment booked successfully";
	}
	
	public Appointment viewAppointment(String userName) {
		Appointment appointment=appoRepo.findByCustomerUserName(userName);
		if(appointment!=null) {
		    return appoRepo.findByCustomerUserName(userName);
		}
		else {
			throw new NotBookedAppointment(userName+" not booked appointment");
		}
	}
	
	public String modify(Appointment appointment) {

		@SuppressWarnings("deprecation")
		Appointment appointment1 = appoRepo.getById(appointment.getId());

		if (appointment1 != null) {
			appoRepo.save(appointment);
			return "updated successfully";
		} 
		else {
			throw new ProfileNotFoundException("NO SUCH APPOINTMENT WAS BOOKED WITH THIS ID.....CANT UPDATE");
		}
	}
	
	
	public String appointmentDelete(String userName) {

		Appointment appointment1 = (appoRepo.findByCustomerUserName(userName));

		if (appointment1 != null) {
			appoRepo.deleteByCustomerUserName(userName);
			return "Customer is deleted with username " + userName;
		} else {
			throw new UsernameInvalidException("APPOINTMENT WAS NOT BOOKED WITH THIS USERNAME " + userName +" TO DELETE");
		}

	}

}
