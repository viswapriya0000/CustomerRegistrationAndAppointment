package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.ForgotPasswordRequest;
import com.example.demo.dto.LoginRequest;
import com.example.demo.exceptionhandler.KeyViolationException;
import com.example.demo.exceptionhandler.PasswordNotMatchException;
import com.example.demo.exceptionhandler.ProfileNotFoundException;
import com.example.demo.exceptionhandler.UsernameInvalidException;
import com.example.demo.exceptionhandler.WrongPasswordException;
import com.example.demo.exceptionhandler.WrongUsernameAndPassword;
import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;

@Service
@Transactional
public class CustomerService {
	
	@Autowired
	CustomerRepository cusRepo;
	
	public String customerMembership(Customer customer) {
		
		Customer customer1=cusRepo.findByUserName(customer.getUserName());
		
		if(customer1!=null) {
			throw new KeyViolationException("Membership is already exsited");
		}
		else {
			cusRepo.save(customer);
			return ("profile created successfully");
		}
		
	}
	
	public Optional<Customer> customerView(String userName) {
		try {
		     return Optional.of(cusRepo.findByUserName(userName));
		}
		catch(Exception e) {
		 			throw new ProfileNotFoundException("NO SUCH USERNAME");
 		}
		
	}
	
	public String loginPage(LoginRequest login) {
		
		Customer customer=cusRepo.findByUserName(login.getUserName());
		
		if(customer!=null) {
			if(customer.getPassword().equals(login.getPassword())) {
			    return "welcome to HOME page";
			}
			else {
				throw new WrongPasswordException("Enter correct password");
			}
		}
		else {
		     throw new WrongUsernameAndPassword("Enter correct username and password");
		
		}

	}
	
	public String resetPassword(ForgotPasswordRequest forgotPassword) {
		
		Customer customer=cusRepo.findByUserName(forgotPassword.getUserName());
		
	  if(customer!=null) {
		  if (forgotPassword.getPassword().equals(forgotPassword.getConfirmPassword())) {
			  cusRepo.updatePassword(forgotPassword.getPassword(), forgotPassword.getUserName());
			  return "Successfully reset the password";
		  } 
		  else {
			  throw new PasswordNotMatchException("confirm password doesnot matches with password");
		  }
	  }
	  else {
		     throw new ProfileNotFoundException("NO SUCH USERNAME WAS REGISTERED");
	  }
	}
	
	public List<Customer> getAllCustomers(){
		return cusRepo.findAll();
	}
	
	public String modify(Customer customer) {
		
		Customer customer1 = cusRepo.findByUserName(customer.getUserName());
		
		 if(customer1!=null) {
		     cusRepo.save(customer);
		     return "updated successfully";
		}
		 else {
			 throw new ProfileNotFoundException("NO SUCH USERNAME WAS REGISTERED");
		 }
	}
	
	
	public String customerDelete(String userName) {
		
		Customer customer = (cusRepo.findByUserName(userName));
		
		if (customer!=null) {
			cusRepo.deleteByUserName(userName);
			return "Customer is deleted with username " + userName;
		} 
		else {
			throw new UsernameInvalidException("Customer is not found for this username " + userName);
		}

	}
	 

}
