package com.cg.bankapp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.bankapp.dao.BankappDao;
import com.cg.bankapp.entity.Account;
import com.cg.bankapp.entity.Customer;
import com.cg.bankapp.entity.Transaction;
import com.cg.bankapp.service.BankappService;
import com.cg.bankapp.service.BankappServiceImpl;

@SpringBootTest
class BankappApplicationTests {
/*
	@Test
	void contextLoads() {
	}
	
//	BankappDao mockDao = mock(BankappDao.class);
//	 Create a BankappService object and inject the mock DAO
//    BankappService services = new BankappServiceImpl(mockDao);
	@Autowired
	BankappService service;

	@Test
	public void testShowBalance() throws Exception {
	    // Create a mock BankappDao object
	    BankappDao mockDao = mock(BankappDao.class);

	    BankappService services = mock(BankappServiceImpl.class);
	    // Specify the behavior of the mock object
	    when(mockDao.findById(1)).thenReturn(Optional.of(new Account(1, 1000, new Customer("Anu", "p", 1001), new ArrayList<Transaction>())));


	    // Call the showBalance() method and verify the result
	    double balance = services.showBalance(1);
	    assertEquals(1000, balance);
	}
	
	@Test
	public void testDeposit() throws Exception {
	    // Create a mock BankappDao object
	    BankappDao mockDao = mock(BankappDao.class);

	    // Specify the behavior of the mock object
	    when(mockDao.findById(1)).thenReturn(Optional.of(new Account(1, 1000, new Customer("Anu", "p", 1001), new ArrayList<Transaction>())));


	    // Call the deposit() method and verify the result
	    double newBalance = service.deposit(1, 500);
	    assertEquals(1500, newBalance, 0.01);
	}

	@Test
	public void testWithdraw() throws Exception {
	    // Create a mock BankappDao object
	    BankappDao mockDao = mock(BankappDao.class);

	    // Specify the behavior of the mock object
	    when(mockDao.findById(1)).thenReturn(Optional.of(new Account(1, 1000, new Customer("Anu", "p", 1001), new ArrayList<Transaction>())));


	    // Call the withdraw() method and verify the result
	    double newBalance = service.withdraw(1, 500);
	    assertEquals(500, newBalance, 0.01);
	}

	@Test
	public void testFundTransfer() throws Exception {
	    // Create a mock BankappDao object
	    BankappDao mockDao = mock(BankappDao.class);

	    // Specify the behavior of the mock object
	    when(mockDao.findById(1)).thenReturn(Optional.of(new Account(1, 1000, new Customer("Anu", "p", 1001), new ArrayList<Transaction>())));
	    when(mockDao.findById(2)).thenReturn(Optional.of(new Account(2, 2000, new Customer("adi", "b", 1002), new ArrayList<Transaction>())));

	    // Call the fundTransfer() method and verify the result
	    double newBalance = service.fundTransfer(1, 2, 500);
	    assertEquals(500, newBalance, 0.01);
	}
*/
}
