package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString

@Entity
@Table(name="DOCTOR")
public @Data class Doctor {

	@Id
	@NotBlank(message="username should not be blank")
	private String doctorUserName;
}
