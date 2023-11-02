package com.fis.bankapplication.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.fis.bankapplication.model.Accounts;

public interface AccountsRepo extends CrudRepository<Accounts, Integer>, JpaRepository<Accounts, Integer> {
	@Query("select balance from Accounts where accNo = ?1") // sql query to fetch the balance from account as per the
															// entered account number
	public int findBalanceByAccNo(int accNo);

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("update Accounts set balance = balance+?2 where accNo=?1") // sql query to update balance by depositing money
																		// as per the account number
	public void saveBalanceByAccNo(int accNo, int balance);

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("update Accounts set balance = balance-?2 where accNo=?1") // sql query to update balance by withdrawing
																		// money as per the account number
	public void withdrawAmountByAccNo(int accNo, int balance);

	/*
	 * @Query annotation is used to specify a query within a repository
	 * 
	 * @Modifying annotation is used to enhance the @Query
	 * 
	 * @Transactional annotation is used to mark a method or a class as
	 * transactional
	 */

}
