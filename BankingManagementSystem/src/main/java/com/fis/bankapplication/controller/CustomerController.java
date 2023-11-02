package com.fis.bankapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fis.bankapplication.exception.ResponseException;
import com.fis.bankapplication.model.Customer;
import com.fis.bankapplication.service.CustomerService;


//{
//    "accNo": 202301,
//    "custName": "Shruti",
//    "mobileNo": 9470718216,
//    "emailId": "shruti@gmail.com",
//    "aadharNo": 635645239812,
//    "dob": "2001/01/01",
//    "residentialAdd": "Bangalore",
//    "permanentAdd": "Patna",
//    "accType": "Savings"
//}
//
//{
//    "accNo": 202302,
//    "custName": "Sonal",
//    "mobileNo": 9625354621,
//    "emailId": "sonal@gmail.com",
//    "aadharNo": 986253423123,
//    "dob": "2002/05/05",
//    "residentialAdd": "Mumbai",
//    "permanentAdd": "Mumbai",
//    "accType": "Savings"
//}
//
//{
//    "accNo": 202303,
//    "custName": "Ankit",
//    "mobileNo": 9983477364,
//    "emailId": "ankit@gmail.com",
//    "aadharNo": 834836875234,
//    "dob": "2001/03/11",
//    "residentialAdd": "Bangalore",
//    "permanentAdd": "Puri",
//    "accType": "Current"
//}

/*
 * RestController: Use to create restful WebServices and also takes care of mapping request data 
 * to the defined request handler method. 
 */
@RestController
//RequestMapping: Used to map HTTP requests to handler methods
@RequestMapping("/customer")
public class CustomerController {
	@Autowired
	private CustomerService customerService;
	@Autowired
	private AccountController accountController;
	
    // this method is used for creating customer
	@PostMapping("/createCustomer") // http://localhost:8080/customer/createCustomer
	public void createCustomer(@RequestBody Customer customer) {
		customerService.createCustomer(customer);
		accountController.createAccount(customer.getAccNo(), 0, "Active");
	}

	// this method is used to get the customer info as per their account number and also handling the exception with a message
	@GetMapping("/getCustomerInfo/{accNo}") // http://localhost:8080/customer/getCustomerInfo/202301
	public ResponseEntity<?> getCustomerInfo(@PathVariable int accNo) {
        Customer user = customerService.getCustomerInfo(accNo);
        if (user == null) {
          ResponseException responseException = new ResponseException(); // if account number is not present in database then it will send error message
          responseException.setMessage("Invalid! Please enter correct Account Number.");
          return new ResponseEntity<>(responseException, HttpStatus.NOT_FOUND);
         }
       return new ResponseEntity<>(user, HttpStatus.OK);
    }    

	// this method is used to delete the customer as per the account number
	@DeleteMapping("/deleteCustomer/{accNo}") // http://localhost:8080/customer/deleteCustomer/202303
	public void deleteCustomer(@PathVariable int accNo) {
		customerService.deleteCustomer(accNo);
	}

}
