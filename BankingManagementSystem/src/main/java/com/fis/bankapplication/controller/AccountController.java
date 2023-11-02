package com.fis.bankapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fis.bankapplication.model.Accounts;
import com.fis.bankapplication.model.Transaction;
import com.fis.bankapplication.service.AccountService;

/*
 * RestController: Use to create restful WebServices and also takes care of mapping request data 
 * to the defined request handler method. 
 */
@RestController
//RequestMapping: Used to map HTTP requests to handler methods
@RequestMapping("/account")
public class AccountController {
	@Autowired
	private AccountService accountService;
	@Autowired
	private TransactionController transactionController;

	// createAccount happens upon createCustomer
	public void createAccount(int accNo, int balance, String acctStatus) {
		Accounts acct = new Accounts(accNo, balance, acctStatus);
		accountService.createAccount(acct);
	}

	// this method is used for checking the Balance
	@GetMapping("/{accNo}/balance") // http://localhost:8080/account/202301/balance
	public int getBalance(@PathVariable int accNo) {
		return accountService.getBalance(accNo);
	}

	// this method is used for depositing Amount
	@PutMapping("/{accNo}/deposit/{amount}")  // http://localhost:8080/account/202301/deposit/90000
	public void depositAmount(@PathVariable int accNo, @PathVariable int amount) {
		int initBal = getBalance(accNo);
		accountService.depositAmount(accNo, amount);
		Transaction transaction = new Transaction(accNo, "Deposited", "Success", initBal, initBal + amount);
		transactionController.addLog(transaction);
	}

	// this method is used for withdrawing Amount
	@PutMapping("/{accNo}/withdraw/{amount}") // http://localhost:8080/account/202301/withdraw/10000
	public void withdrawAmount(@PathVariable int accNo, @PathVariable int amount) {
		int initBal = getBalance(accNo);
		accountService.withdrawAmount(accNo, amount);
		Transaction transaction = new Transaction(accNo, "Withdrawn", "Success", initBal, initBal - amount);
		transactionController.addLog(transaction);
	}

	// this method is used for transferring Amount
	@PutMapping("/{accNo}/transfer/{destAccNo}/{amount}") // http://localhost:8080/account/202301/transfer/202302/10000
	public void transferAmount(@PathVariable int accNo, @PathVariable int destAccNo, @PathVariable int amount) {
		int initBalSender = getBalance(accNo);
		int initBalReceiver = getBalance(destAccNo);
		accountService.transferAmount(accNo, destAccNo, amount);
		Transaction moneySender = new Transaction(accNo, "Transferred", "Success", initBalSender,
				initBalSender - amount);
		transactionController.addLog(moneySender);
		Transaction moneyReceiver = new Transaction(destAccNo, "Received", "Success", initBalReceiver,
				initBalReceiver + amount);
		transactionController.addLog(moneyReceiver);
	}

	// this method is used for deleting the Account
	@DeleteMapping("/deleteAccount/{accNo}") // http://localhost:8080/account/deleteAccount/202303
	public void deleteAccount(@PathVariable int accNo) {
		accountService.deleteAccount(accNo);
		transactionController.deleteLog(accNo);
	}

	// this method is used for getting AccountInfo
	@GetMapping("/getAccountInfo/{accNo}") // http://localhost:8080/account/getAccountInfo/202301
	public Accounts getAccountInfo(@PathVariable int accNo) {
		return accountService.getAccountInfo(accNo);
	}

}
