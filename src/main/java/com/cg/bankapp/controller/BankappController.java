package com.cg.bankapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.bankapp.entity.Account;
import com.cg.bankapp.entity.Transaction;
import com.cg.bankapp.exception.AccountAlreadyExists;
import com.cg.bankapp.exception.AccountNotFoundException;
import com.cg.bankapp.service.BankappService;

/**
 * This class represents the RESTful controller for Bankapp application, which
 * handles HTTP requests related to account operations. It maps the incoming
 * HTTP requests to corresponding methods in the BankappService to perform
 * account-related operations.
 */
@RestController
public class BankappController {

	/**
	 * Autowired field for BankappService.
	 */
	@Autowired
	private BankappService bankappService;

	/**
	 * Adds a new account with the given account details.
	 *
	 * @param account the account object containing account details to be added
	 * @return ResponseEntity<String> indicating the success of the operation
	 */
	@PostMapping(value = "/addAccount/{accountId}/{accountBalance}/{customerFirstName}/{customerLastName}/{customerId}")
	public ResponseEntity<String> acceptAccountDetails(@ModelAttribute Account account) throws AccountNotFoundException, AccountAlreadyExists {
		int accountId = account.getAccountNo();
		double accountBalance = account.getAccountBalance();
		String customerFirstName = account.getCustomer().getFirstName();
		String customerLastName = account.getCustomer().getLastName();
		int customerId = account.getCustomer().getcId();
		account = bankappService.addAccount(accountId, accountBalance, customerFirstName, customerLastName, customerId);
		return new ResponseEntity<String>("Account details successfuly added id:= " + account.getAccountNo(),
				HttpStatus.CREATED);
	}

	/**
	 * Retrieves the balance of an account with the given account ID.
	 *
	 * @param acctID the account ID
	 * @return double representing the account balance
	 * @throws Exception if the account is not found
	 */
	@GetMapping("/account/{acctID}/balance")
	public double getBalance(@PathVariable int acctID) throws Exception {
		return bankappService.showBalance(acctID);
	}

	/**
	 * Deposits the given amount to the account with the given account ID.
	 *
	 * @param acctID the account ID
	 * @param amount the amount to be deposited
	 * @return ResponseEntity<String> indicating the success of the operation
	 * @throws Exception if the account is not found
	 */
	@PutMapping("/account/{acctID}/deposit/{amount}")
	public ResponseEntity<String> deposit(@PathVariable int acctID, @PathVariable double amount) throws Exception {
		bankappService.deposit(acctID, amount);
		return new ResponseEntity<String>(amount + " successfully deposited to account " + acctID, HttpStatus.OK);
	}

	/**
	 * Withdraws the given amount from the account with the given account ID.
	 *
	 * @param acctID the account ID
	 * @param amount the amount to be withdrawn
	 * @return ResponseEntity<String> indicating the success of the operation
	 * @throws Exception if the account is not found or insufficient balance
	 */
	@PutMapping("/account/{acctID}/withdraw/{amount}")
	public ResponseEntity<String> withdraw(@PathVariable int acctID, @PathVariable double amount) throws Exception {
		bankappService.withdraw(acctID, amount);
		return new ResponseEntity<String>(amount + " successfully credited from account " + acctID, HttpStatus.OK);
	}

	/**
	 * Transfers the given amount from the source account to the target account.
	 *
	 * @param sourceID the source account ID
	 * @param targetID the target account ID
	 * @param amount   the amount to be transferred
	 * @return ResponseEntity<String> indicating the success of the operation
	 * @throws Exception if the source or target account is not found or
	 *                   insufficient balance
	 */
	@PutMapping("/account/{sourceID}/transfer/{targetID}/{amount}")
	public ResponseEntity<String> fundTransfer(@PathVariable int sourceID, @PathVariable int targetID,
			@PathVariable double amount) throws Exception {
		bankappService.fundTransfer(sourceID, targetID, amount);
		return new ResponseEntity<String>(
				amount + " successfully transferred from account " + sourceID + " to account " + targetID,
				HttpStatus.OK);
	}

	/**
	 * Transfers the given amount from the source account to the target account.
	 *
	 * @param sourceID the source account ID
	 * @param targetID the target account ID
	 * @param amount   the amount to be transferred
	 * @return ResponseEntity<String> indicating the success of the operation
	 * @throws Exception if the source or target account is not found or
	 *                   insufficient balance
	 */
	@GetMapping(value = "/account/{acctID}/allTransactionDetails", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Transaction>> getAllTransactionDetails(@PathVariable int acctID) throws Exception {
		List<Transaction> transactionList = bankappService.getAllTransactionDetails(acctID);
		return new ResponseEntity<>(transactionList, HttpStatus.OK);
	}

}
