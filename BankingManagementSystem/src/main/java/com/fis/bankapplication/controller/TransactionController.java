package com.fis.bankapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fis.bankapplication.model.Transaction;
import com.fis.bankapplication.service.TransactionService;

/*
 * RestController: Use to create restful WebServices and also takes care of mapping request data 
 * to the defined request handler method. 
 */
@RestController

//RequestMapping: Used to map HTTP requests to handler methods
@RequestMapping("/account")
public class TransactionController {
	@Autowired
	private TransactionService transactionService;

	// this method is used to add Log
	public void addLog(Transaction transaction) {
		transactionService.addLog(transaction);
	}

	// this method is used to show Log
	@GetMapping("/{accNo}/logs") // http://localhost:8080/account/202301/logs
	public Transaction showLog(@PathVariable int accNo) {
		return transactionService.showLog(accNo);
	}
	 
	// this method is used to delete Log
	@DeleteMapping("/deleteLog/{accNo}/logs") // http://localhost:8080/account/deleteLog/202301/logs
	public void deleteLog(int accNo) {
		transactionService.deleteLog(accNo);
	}
}
