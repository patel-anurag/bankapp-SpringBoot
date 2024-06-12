package com.cg.bankapp.exception;

public class AccountNotFoundException extends Exception{
	final String msg;
	public AccountNotFoundException (String msg) {
		this.msg = msg;
	}
	@Override
	public String toString() {
		return msg;
	}


}
