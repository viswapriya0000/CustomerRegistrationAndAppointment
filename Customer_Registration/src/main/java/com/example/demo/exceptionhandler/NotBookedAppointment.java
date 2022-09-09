package com.example.demo.exceptionhandler;

public class NotBookedAppointment extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NotBookedAppointment(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	

}
