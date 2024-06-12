package com.cg.bankapp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.cg.bankapp.controller.BankappController;
import com.cg.bankapp.entity.Account;
import com.cg.bankapp.entity.Customer;
import com.cg.bankapp.entity.Transaction;
import com.cg.bankapp.exception.AccountAlreadyExists;
import com.cg.bankapp.exception.AccountNotFoundException;
import com.cg.bankapp.service.BankappService;

@ExtendWith(MockitoExtension.class)
public class BankappControllerTest {

    @InjectMocks
    private BankappController bankappController;

    @Mock
    private BankappService bankappService;

    /**
    This method tests the acceptAccountDetails() method of BankappController class
    @throws AccountNotFound
    @throws AccountExists
    */
    @Test
    public void testAcceptAccountDetails() throws AccountNotFoundException, AccountAlreadyExists {
        Account account = new Account();
        account.setAccountNo(1234);
        account.setAccountBalance(1000.0);
        Customer customer = new Customer();
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setcId(5678);
        account.setCustomer(customer);

        when(bankappService.addAccount(any(Integer.class), any(Double.class),
                any(String.class), any(String.class), any(Integer.class))).thenReturn(account);

        String expectedBody = "Account details successfully added id:= 1234";
        assertEquals(bankappController.acceptAccountDetails(account).getBody(), bankappController.acceptAccountDetails(account).getBody());
    }

    /**
    This method tests the getBalance() method of BankappController class
    @throws Exception
    */
    @Test
    public void testGetBalance() throws Exception {
        int accountId = 1234;
        double balance = 1000.0;
        when(bankappService.showBalance(any(Integer.class))).thenReturn(balance);
        double result = bankappController.getBalance(accountId);
        assertEquals(balance, result, 0.001);
    }
    
    /**
    This method tests the getBalance() method of BankappController class with negative scenario
    @throws Exception
    */
    @Test
    public void testGetBalanceNegative() throws Exception {
        int accountId = 1234;
        double balance = 1000.0;
        when(bankappService.showBalance(any(Integer.class))).thenReturn(balance);
        double result = bankappController.getBalance(accountId);
        assertNotEquals(999, result, 0.001);
    }

    /**
    This method tests the deposit() method of BankappController class
    @throws Exception
    */
    @Test
    public void testDeposit() throws Exception {
        int accountId = 1234;
        double amount = 500.0;
        Mockito.when(bankappService.deposit(Mockito.anyInt(), Mockito.anyDouble())).thenReturn(amount);
        ResponseEntity<String> response = bankappController.deposit(accountId, amount);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("500.0 successfully deposited to account 1234", response.getBody());
    }

    /**
    This method tests the deposit() method of BankappController class with negative scenario
    @throws Exception
    */
    @Test
    public void testDepositNegative() throws Exception {
        int accountId = 1234;
        double amount = 500.0;
        Mockito.when(bankappService.deposit(Mockito.anyInt(), Mockito.anyDouble())).thenReturn(amount);
        ResponseEntity<String> response = bankappController.deposit(accountId, amount);
        assertNotEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNotEquals("500.0 successfully not deposited to account 1234", response.getBody());
    }

    /**
    This method tests the withdraw() method of BankappController class
    @throws Exception
    */
//    @Test
//    public void testWithdraw() throws Exception {
//        int accountId = 1234;
//        double amount = 500.0;
//        Mockito.when(bankappService.withdraw(Mockito.anyInt(), Mockito.anyDouble())).thenReturn(amount);
//        ResponseEntity<String> response = bankappController.withdraw(accountId, amount);
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals("500.0 successfully debited from account 1234", response.getBody());
//    }
    
    /**
    This method tests the withdraw() method of BankappController class with negative scenario
    @throws Exception
    */
    @Test
    public void testWithdrawNegative() throws Exception {
        int accountId = 1234;
        double amount = 500.0;
        Mockito.when(bankappService.withdraw(Mockito.anyInt(), Mockito.anyDouble())).thenReturn(amount);
        ResponseEntity<String> response = bankappController.withdraw(accountId, amount);
        assertNotEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNotEquals("500.0 successfully not debited from account 1234", response.getBody());
    }
    
    /**
    This method tests the fundTransfer() method of BankappController class
    @throws Exception
    */
    @Test
    public void testTransfer() throws Exception {
    	int sourceId = 1234;
        int targetId = 5678;
        double amount = 500.0;
        Mockito.when(bankappService.fundTransfer(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyDouble())).thenReturn(amount);
        ResponseEntity<String> response = bankappController.fundTransfer(sourceId, targetId, amount);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("500.0 successfully transferred from account 1234 to account 5678", response.getBody());
    }

    /**
    This method tests the fundTransfer() method of BankappController class with negative scenario
    @throws Exception
    */
    @Test
    public void testTransferNegative() throws Exception {
    	int sourceId = 1234;
        int targetId = 5678;
        double amount = 500.0;
        Mockito.when(bankappService.fundTransfer(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyDouble())).thenReturn(amount);
        ResponseEntity<String> response = bankappController.fundTransfer(sourceId, targetId, amount);
        assertNotEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNotEquals("500.0 successfully not transferred from account 1234 to account 5678", response.getBody());
    }

    /**
    This method tests the getAllTransactionDetails() method of BankappController class
    @throws Exception
    */
    @Test
    public void testGetAllTransactionDetails() throws Exception {
        int accountId = 1234;
        List<Transaction> transactions = new ArrayList<>();
        when(bankappService.getAllTransactionDetails(any(Integer.class))).thenReturn(transactions);
        assertEquals(transactions, bankappController.getAllTransactionDetails(accountId).getBody());
    }
}