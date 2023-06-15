package com.safar.exceptions;

public class CabBookingException extends RuntimeException{
	public CabBookingException() {
		
	}
	public CabBookingException(String msg) {
			super(msg);
	}

}
