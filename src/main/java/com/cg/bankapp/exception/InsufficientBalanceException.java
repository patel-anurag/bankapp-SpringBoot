package com.cg.bankapp.exception;

public class InsufficientBalanceException extends Exception{
	final String msg;
	public InsufficientBalanceException (String msg) {
		this.msg = msg;
		
	}
	@Override
	public String toString() {
		return msg;
	}
	
	
}
