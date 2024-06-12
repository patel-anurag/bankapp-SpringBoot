package com.cg.bankapp.exception;

public class AccountAlreadyExists extends Exception{
	final String msg;
	public AccountAlreadyExists(String msg) {
		this.msg = msg;
	}
	
	@Override
	public String toString() {
		return msg;
	}
}
