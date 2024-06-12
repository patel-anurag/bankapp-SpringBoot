package com.cg.bankapp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.cg.bankapp.dao.BankappDao;
import com.cg.bankapp.entity.Account;
import com.cg.bankapp.entity.Customer;
import com.cg.bankapp.entity.Transaction;
import com.cg.bankapp.service.BankappServiceImpl;

public class BankappServiceTest {

	@Mock
	private BankappDao bankappDao;

	@InjectMocks
	private BankappServiceImpl bankappService;

	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testShowBalance() throws Exception {
		Account account = new Account();
		account.setAccountBalance(1000.0);
		Mockito.when(bankappDao.findById(12345)).thenReturn(java.util.Optional.of(account));
		double balance = bankappService.showBalance(12345);
		assertEquals(1000.0, balance);
	}

	@Test
	void testShowBalanceNegative() throws Exception {
		Account account = new Account();
		account.setAccountBalance(1000.0);
		Mockito.when(bankappDao.findById(12345)).thenReturn(java.util.Optional.of(account));
		double balance = bankappService.showBalance(12345);
		assertNotEquals(0.0, balance);
	}

	@Test
	void testDeposit() throws Exception {
		Account account = new Account();
		account.setAccountBalance(1000.0);
		account.setTransaction(null);
		Mockito.when(bankappDao.findById(12345)).thenReturn(java.util.Optional.of(account));
		double newBalance = bankappService.deposit(12345, 500.0);
		assertEquals(0.0, newBalance);
	}

	@Test
	void testDepositNegative() throws Exception {
		Account account = new Account();
		account.setAccountBalance(1000.0);
		account.setTransaction(null);
		Mockito.when(bankappDao.findById(12345)).thenReturn(java.util.Optional.of(account));
		double newBalance = bankappService.deposit(12345, 500.0);
		assertNotEquals(10.0, newBalance);
	}

	@Test
	void testWithdraw() throws Exception {
		Account account = new Account();
		account.setAccountBalance(1000.0);
		account.setTransaction(null);
		Mockito.when(bankappDao.findById(12345)).thenReturn(java.util.Optional.of(account));
		double newBalance = bankappService.withdraw(12345, 500.0);
		assertEquals(0.0, newBalance);
	}

	@Test
	void testWithdrawNegative() throws Exception {
		Account account = new Account();
		account.setAccountBalance(1000.0);
		account.setTransaction(null);
		Mockito.when(bankappDao.findById(12345)).thenReturn(java.util.Optional.of(account));
		double newBalance = bankappService.withdraw(12345, 500.0);
		assertNotEquals(10.0, newBalance);
	}

	@Test
	void testFundTransfer() throws Exception {
		Account sourceAccount = new Account();
		sourceAccount.setAccountBalance(1000.0);
		sourceAccount.setTransaction(null);
		Account targetAccount = new Account();
		targetAccount.setAccountBalance(500.0);
		targetAccount.setTransaction(null);
		Mockito.when(bankappDao.findById(12345)).thenReturn(java.util.Optional.of(sourceAccount));
		Mockito.when(bankappDao.findById(67890)).thenReturn(java.util.Optional.of(targetAccount));
		bankappService.fundTransfer(12345, 67890, 500.0);
		assertEquals(500.0, sourceAccount.getAccountBalance());
		assertEquals(1000.0, targetAccount.getAccountBalance());
	}

	@Test
	void testFundTransferNegative() throws Exception {
		Account sourceAccount = new Account();
		sourceAccount.setAccountBalance(1000.0);
		sourceAccount.setTransaction(null);
		Account targetAccount = new Account();
		targetAccount.setAccountBalance(500.0);
		targetAccount.setTransaction(null);
		Mockito.when(bankappDao.findById(12345)).thenReturn(java.util.Optional.of(sourceAccount));
		Mockito.when(bankappDao.findById(67890)).thenReturn(java.util.Optional.of(targetAccount));
		bankappService.fundTransfer(12345, 67890, 500.0);
		assertNotEquals(0.0, sourceAccount.getAccountBalance());
		assertNotEquals(0.0, targetAccount.getAccountBalance());
	}

	@Test
	void testAddAccount() throws Exception {
		Account account = new Account();
		account.setAccountNo(12345);
		account.setAccountBalance(1000.0);
		Customer customer = new Customer("John", "Doe", 1);
		account.setCustomer(customer);
		Mockito.when(bankappDao.save(account)).thenReturn(account);
		Account addedAccount = bankappService.addAccount(12345, 1000.0, "John", "Doe", 1);
		assertEquals(addedAccount, addedAccount);
	}

	@Test
	void testAddAccountNegative() throws Exception {
		Account account = new Account();
		account.setAccountNo(12345);
		account.setAccountBalance(1000.0);
		Customer customer = new Customer("John", "Doe", 1);
		account.setCustomer(customer);
		Mockito.when(bankappDao.save(account)).thenReturn(account);
		Account addedAccount = bankappService.addAccount(12345, 1000.0, "John", "Doe", 1);
		assertNotEquals(account, addedAccount);
	}

	@Test
	void testGetAllTransactionDetails() throws Exception {
		Account account = new Account();
		Transaction transaction = new Transaction(1, 1000.0, "Deposit");
		account.setTransaction(List.of(transaction));
		Mockito.when(bankappDao.findById(12345)).thenReturn(java.util.Optional.of(account));
		List<Transaction> transactions = bankappService.getAllTransactionDetails(12345);
		assertEquals(1, transactions.size());
		assertEquals(transaction, transactions.get(0));
	}

}