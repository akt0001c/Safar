package com.safar.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(CabBookingException.class)
	public ResponseEntity<ErrorDetails> cabBookingExceptionHandler(CabBookingException ex,WebRequest req){
		ErrorDetails err=new ErrorDetails();
		err.setTimeStamp(LocalDateTime.now());
		err.setMessage(ex.getMessage());
		err.setDescription(req.getDescription(false));
		return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
	}

	
}
