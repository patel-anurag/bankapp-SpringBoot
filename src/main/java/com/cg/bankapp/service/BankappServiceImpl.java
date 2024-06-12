package com.cg.bankapp.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

import com.cg.bankapp.dao.BankappDao;
import com.cg.bankapp.entity.*;
import com.cg.bankapp.exception.AccountAlreadyExists;
import com.cg.bankapp.exception.AccountNotFoundException;
import com.cg.bankapp.exception.IncorrectAmountException;
import com.cg.bankapp.exception.InsufficientBalanceException;
import com.cg.bankapp.exception.SameAccountException;
import com.cg.bankapp.exception.TransactionListEmpty;

/**
 * This class is an implementation of the BankappService interface, providing
 * implementation for various banking operations such as checking balance,
 * depositing money, withdrawing money, transferring funds, adding an account,
 * and getting transaction details.
 * 
 * @author [Anurag Patel]
 *
 */
@Component(value = "bankappService")
@Service
public class BankappServiceImpl implements BankappService {

	@Autowired
	private BankappDao bankappDao;
	private int transactionId;

	public BankappServiceImpl(BankappDao daoMock) {
		this.bankappDao = daoMock;
	}

	/**
	 * Sets the transaction details for a given transaction list, balance, and type.
	 * 
	 * @param t       the transaction list
	 * @param balance the transaction balance
	 * @param type    the transaction type
	 */
	private void setTransaction(List<Transaction> t, double balance, String type) {
		Transaction transaction = new Transaction(transactionId, balance, type);
		try {
			t.add(transaction);
			
		} catch (NullPointerException e) {
		}
	}

	/**
	 * Retrieves the account balance for a given account number.
	 * 
	 * @param accountNo the account number
	 * @return the account balance
	 * @throws Exception if the account is not found
	 */
	@Override
	public double showBalance(int accountNo) throws AccountNotFoundException {
		Account account = bankappDao.findById(accountNo)
				.orElseThrow(() -> new AccountNotFoundException("Account with account number " + accountNo + " not found."));
		return account.getAccountBalance();
	}

	/**
	 * Deposits the specified amount into the account associated with the given
	 * account number.
	 * 
	 * @param accountNo the account number
	 * @param amount    the amount to be deposited
	 * @return the updated account balance
	 * @throws Exception if the account is not found
	 */
	@Override
	public double deposit(int accountNo, double amount) throws Exception {
		Account account = bankappDao.findById(accountNo)
				.orElseThrow(() -> new AccountNotFoundException("Account with account number " + accountNo + " not found."));
		if (amount <= 0)
			throw new IncorrectAmountException(amount + " is invalid input. Please provide valid amount to deposit");
		List<Transaction> transactionList = account.getTransaction();
		double accountBalance = account.getAccountBalance();
		account.setAccountBalance(accountBalance + amount);
		setTransaction(transactionList, amount, "Deposit");
		bankappDao.save(account);
		return 0;
	}

	/**
	 * Withdraws the specified amount from the account associated with the given
	 * account number.
	 * 
	 * @param accountNo the account number
	 * @param amount    the amount to be withdrawn
	 * @return the updated account balance
	 * @throws Exception if the account is not found or insufficient balance
	 */
	@Override
	public double withdraw(int accountNo, double amount) throws Exception {
		Account account = bankappDao.findById(accountNo)
				.orElseThrow(() -> new AccountNotFoundException("Account with account number " + accountNo + " not found."));
		if (amount <= 0)
			throw new IncorrectAmountException(amount + " is invalid input. Please provide valid amount to withdraw");
		if (amount > account.getAccountBalance())
			throw new InsufficientBalanceException(accountNo + " does not have sufficient balance.");
		List<Transaction> transactionList = account.getTransaction();
		double accountBalance = account.getAccountBalance();
		account.setAccountBalance(accountBalance - amount);
		setTransaction(transactionList, amount, "withdraw");
		bankappDao.save(account);
		return 0;
	}

	/**
	 * Transfers the specified amount from the source account to the target account.
	 * 
	 * @param sourceAccountNo the source account number
	 * @param targetAccountNo the target account number
	 * @param amount          the amount to be transferred
	 * @return the updated account balances of source and target accounts
	 * @throws Exception if the source or target account is not found, or
	 *                   insufficient balance in the source account
	 */
	@Override
	public double fundTransfer(int sourceAccountNo, int targetAccountNo, double amount) throws AccountNotFoundException, SameAccountException, IncorrectAmountException, InsufficientBalanceException {
		Account sourceAccount = bankappDao.findById(sourceAccountNo).orElseThrow(
				() -> new AccountNotFoundException("Account with account number " + sourceAccountNo + " not found."));
		Account targetAccount = bankappDao.findById(targetAccountNo).orElseThrow(
				() -> new AccountNotFoundException("Account with account number " + targetAccountNo + " not found."));
		if (sourceAccountNo == targetAccountNo)
			throw new SameAccountException("Source and target account cannot be same.");
		if (amount <= 0)
			throw new IncorrectAmountException(amount + " is invalid input. Please provide valid amount to transfer");
		if (sourceAccount.getAccountBalance() < amount)
			throw new InsufficientBalanceException("Account " + sourceAccountNo + " does not have sufficient balance.");
		List<Transaction> transactionList = sourceAccount.getTransaction();
		List<Transaction> transactionList2 = targetAccount.getTransaction();
		double sourceAccountBalance = sourceAccount.getAccountBalance();
		double targetAccountBalance = targetAccount.getAccountBalance();
		sourceAccount.setAccountBalance(sourceAccountBalance - amount);
		setTransaction(transactionList, amount, "transfer to account " + targetAccountNo);
		targetAccount.setAccountBalance(targetAccountBalance + amount);
		setTransaction(transactionList2, amount, "deposit");
		bankappDao.save(sourceAccount);
		bankappDao.save(targetAccount);
		return 0;
	}

	/**
	 * Adds a new account with the specified details.
	 * 
	 * @param accountNumber     the account number
	 * @param accountBalance    the account balance
	 * @param customerFirstName the customer's first name
	 * @param customerLastName  the customer's last name
	 * @param customerId        the customer ID
	 * @return the newly created account
	 */
	@Override
	public Account addAccount(int accountNumber, double accountBalance, String customerFirstName,
			String customerLastName, int customerId) throws AccountNotFoundException, AccountAlreadyExists {
		if (bankappDao.findById(accountNumber).isPresent())
			throw new AccountAlreadyExists("Account " + accountNumber + " already exists.");
		Customer customer = new Customer(customerFirstName, customerLastName, customerId);
		Account account = new Account();
		account.setAccountBalance(accountBalance);
		account.setAccountNo(accountNumber);
		account.setCustomer(customer);
		account.setTransaction(new ArrayList<Transaction>());
		bankappDao.save(account);
		return account;
	}

	/**
	 * Retrieves the transaction details for a given account number.
	 * 
	 * @param accountNo the account number
	 * @return the list of transaction details
	 * @throws Exception if the account is not found
	 */
	@Override
	public List<Transaction> getAllTransactionDetails(int accountNo) throws Exception {
		Account account = bankappDao.findById(accountNo).orElseThrow(() -> new AccountNotFoundException("Account with account number " + accountNo + " not found."));
		List<Transaction> transactionList = account.getTransaction();
		if (transactionList.isEmpty())
			throw new TransactionListEmpty("Account " + accountNo + " does not have any transactions yet.");
		return transactionList;
	}

}
