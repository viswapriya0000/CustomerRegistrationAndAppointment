package com.example.demo.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString

@Entity
@Table(name="APPOINTMENT")
public @Data class Appointment {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@NotBlank(message="Date should not be blank")
	@Pattern(regexp="^[0-9]{4}-[0-9]{2}-[0-9]{2}$",message="Enter date  in this format yyyy-mm-dd")
	private String date;
	
	@NotBlank(message="Description should not be blank")
	private String description;
	
	@OneToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name="CUSTOMER_USERNAME")
	private Customer customer;
	
	@OneToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name="DOCTOR_USERNAME")
	private Doctor doctor;
	
	@NotBlank(message="Ailment should not be blank")
	private String ailment;

}
