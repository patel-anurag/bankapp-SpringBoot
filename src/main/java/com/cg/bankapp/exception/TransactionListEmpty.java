package com.cg.bankapp.exception;

public class TransactionListEmpty extends Exception {
	final String msg;
	public TransactionListEmpty(String msg) {
		this.msg = msg;
	}
	
	@Override
	public String toString() {
		return msg;
	}
	
}
