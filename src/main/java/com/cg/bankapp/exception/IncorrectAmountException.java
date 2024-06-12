package com.cg.bankapp.exception;

public class IncorrectAmountException extends Exception {
	final String msg;
	public IncorrectAmountException (String msg) {
		this.msg = msg;
	}
	@Override
	public String toString() {
		return msg;
	}

}
