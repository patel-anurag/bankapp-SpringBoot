package com.cg.bankapp.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cg.bankapp.exception.AccountAlreadyExists;
import com.cg.bankapp.exception.AccountNotFoundException;
import com.cg.bankapp.exception.IncorrectAmountException;
import com.cg.bankapp.exception.InsufficientBalanceException;
import com.cg.bankapp.exception.SameAccountException;
import com.cg.bankapp.exception.TransactionListEmpty;

@ControllerAdvice
public class ExceptionHandlerBankapp {
	
	@ExceptionHandler(value=AccountNotFoundException.class)
	public ResponseEntity<String> handleAccountNotFoundException(AccountNotFoundException e){
		return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value=IncorrectAmountException.class)
	public ResponseEntity<String> handleNegativeAmountException(IncorrectAmountException e){
		return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value=SameAccountException.class)
	public ResponseEntity<String> handleTransferringInSameAccountException(SameAccountException e){
		return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value=InsufficientBalanceException.class)
	public ResponseEntity<String> handleInsufficientBalanceException(InsufficientBalanceException e){
		return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value=TransactionListEmpty.class)
	public ResponseEntity<String> handleTransactionListEmptyException(TransactionListEmpty e){
		return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value=AccountAlreadyExists.class)
	public ResponseEntity<String> handleAccountExistsException(AccountAlreadyExists e){
		return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
	}
}
