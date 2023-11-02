package com.fis.bankapplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fis.bankapplication.model.Transaction;
import com.fis.bankapplication.repo.TransactionRepo;

@Service
public class TransactionService {
	@Autowired
	TransactionRepo transactionRepo;

	public void addLog(Transaction transaction) {
		transactionRepo.save(transaction);
	}

	public Transaction showLog(int accNo) {
		return transactionRepo.findById(accNo).orElse(null);
	}

	public void deleteLog(int accNo) {
		transactionRepo.deleteById(accNo);
	}
}
