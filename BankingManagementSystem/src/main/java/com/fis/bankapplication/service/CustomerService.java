package com.fis.bankapplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fis.bankapplication.model.Customer;
import com.fis.bankapplication.repo.CustomerRepo;

@Service
public class CustomerService {
	@Autowired
	private CustomerRepo customerRepo;

	public void createCustomer(Customer customer) {
		customerRepo.save(customer);
	}

	public Customer getCustomerInfo(int accNo) {
		return customerRepo.findById(accNo).orElse(null);
	}

	public void deleteCustomer(int accNo) {
		customerRepo.deleteById(accNo);
	}

}
