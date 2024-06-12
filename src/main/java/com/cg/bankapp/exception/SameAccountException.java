package com.cg.bankapp.exception;

public class SameAccountException extends Exception {
	final String msg;
	public SameAccountException(String msg) {
		this.msg = msg;
	}
	
	@Override
	public String toString() {
		return msg;
	}
	
}
