package com.cg.bankapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cg.bankapp.entity.Account;
import com.cg.bankapp.entity.Transaction;
import com.cg.bankapp.exception.AccountAlreadyExists;
import com.cg.bankapp.exception.AccountNotFoundException;

/**
 * BankappService is an interface that defines the contract for the banking application service.
 * It provides methods to perform various banking operations such as checking balance, depositing,
 * withdrawing, transferring funds, adding accounts, and retrieving transaction details.
 * 
 * @see Service
 */
@Service
public interface BankappService {
	
	/**
	 * Retrieves the current balance of the specified account.
	 * 
	 * @param accountNo The account number for which balance needs to be retrieved.
	 * @return The current balance of the specified account.
	 * @throws Exception If any error occurs during the operation.
	 */
	public double showBalance(int accountNo) throws AccountNotFoundException;
	
	/**
	 * Deposits the specified amount into the specified account.
	 * 
	 * @param accountNo The account number into which amount needs to be deposited.
	 * @param amount The amount to be deposited.
	 * @return The updated balance after the deposit.
	 * @throws Exception If any error occurs during the operation.
	 */
	public double deposit(int accountNo, double amount) throws Exception;
	
	/**
	 * Withdraws the specified amount from the specified account.
	 * 
	 * @param accountNo The account number from which amount needs to be withdrawn.
	 * @param amount The amount to be withdrawn.
	 * @return The updated balance after the withdrawal.
	 * @throws Exception If any error occurs during the operation.
	 */
	public double withdraw(int accountNo, double amount) throws Exception;
	
	/**
	 * Transfers the specified amount from the source account to the target account.
	 * 
	 * @param sourceAccountNo The account number from which amount needs to be transferred.
	 * @param targetAccountNo The account number to which amount needs to be transferred.
	 * @param amount The amount to be transferred.
	 * @return The updated balance of the source account after the transfer.
	 * @throws Exception If any error occurs during the operation.
	 */
	public double fundTransfer(int sourceAccountNo, int targetAccountNo, double amount) throws Exception;
	
	/**
	 * Adds a new account with the specified details.
	 * 
	 * @param accountNumber The account number of the new account.
	 * @param accountBalance The initial balance of the new account.
	 * @param customerFirstName The first name of the customer associated with the new account.
	 * @param customerLastName The last name of the customer associated with the new account.
	 * @param customerId The ID of the customer associated with the new account.
	 * @return The newly created Account object.
	 * @throws AccountAlreadyExists 
	 * @throws AccountNotFoundException 
	 */
	public Account addAccount(int accountNumber, double accountBalance, String customerFirstName, String customerLastName, int customerId) throws AccountNotFoundException, AccountAlreadyExists;
	
	/**
	 * Retrieves all the transaction details for the specified account.
	 * 
	 * @param accountNo The account number for which transaction details need to be retrieved.
	 * @return A list of Transaction objects representing the transaction details.
	 * @throws Exception If any error occurs during the operation.
	 */
	List<Transaction> getAllTransactionDetails(int accountNo) throws Exception;

}
