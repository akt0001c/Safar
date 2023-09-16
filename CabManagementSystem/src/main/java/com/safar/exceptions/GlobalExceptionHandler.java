package com.safar.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Exception handler for DriverException
    @ExceptionHandler(DriverException.class)
    public ResponseEntity<ErrorDetails> driverExceptionHandler(DriverException de, WebRequest web) {
        ErrorDetails err = new ErrorDetails();
        err.setMessage(de.getMessage());
        err.setTimeStamp(LocalDateTime.now());
        err.setDescription(web.getDescription(false));
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }

    // Exception handler for UsersException
    @ExceptionHandler(UsersException.class)
    public ResponseEntity<ErrorDetails> userExceptionHandler(UsersException de, WebRequest web) {
        ErrorDetails err = new ErrorDetails();
        err.setMessage(de.getMessage());
        err.setTimeStamp(LocalDateTime.now());
        err.setDescription(web.getDescription(false));
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }

    // Exception handler for CabBookingException
    @ExceptionHandler(CabBookingException.class)
    public ResponseEntity<ErrorDetails> cabBookingExceptionHandler(CabBookingException de, WebRequest web) {
        ErrorDetails err = new ErrorDetails();
        err.setMessage(de.getMessage());
        err.setTimeStamp(LocalDateTime.now());
        err.setDescription(web.getDescription(false));
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }

    // Exception handler for TransactionFaliureException
    @ExceptionHandler(TransactionFaliureException.class)
    public ResponseEntity<ErrorDetails> transactionsFaliureExceptionHandler(TransactionFaliureException ex, WebRequest req) {
        ErrorDetails err = new ErrorDetails();
        err.setTimeStamp(LocalDateTime.now());
        err.setMessage(ex.getLocalizedMessage());
        err.setDescription(req.getDescription(false));
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }

    // Exception handler for TransactionsException
    @ExceptionHandler(TransactionsException.class)
    public ResponseEntity<ErrorDetails> transactionsExceptionHandler(TransactionsException ex, WebRequest req) {
        ErrorDetails err = new ErrorDetails();
        err.setTimeStamp(LocalDateTime.now());
        err.setMessage(ex.getLocalizedMessage());
        err.setDescription(req.getDescription(false));
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }

    // Exception handler for WalletException
    @ExceptionHandler(WalletException.class)
    public ResponseEntity<ErrorDetails> walletExceptionHandler(WalletException ex, WebRequest req) {
        ErrorDetails err = new ErrorDetails();
        err.setTimeStamp(LocalDateTime.now());
        err.setMessage(ex.getLocalizedMessage());
        err.setDescription(req.getDescription(false));
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }

    // Exception handler for general exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> gereralExceptionHandler(Exception ex, WebRequest req) {
        ErrorDetails err = new ErrorDetails();
        err.setTimeStamp(LocalDateTime.now());
        err.setMessage(ex.getLocalizedMessage());
        err.setDescription(req.getDescription(false));
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }

    // Exception handler for NoHandlerFoundException
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ErrorDetails> walletExceptionHandler(NoHandlerFoundException ex, WebRequest req) {
        ErrorDetails err = new ErrorDetails();
        err.setTimeStamp(LocalDateTime.now());
        err.setMessage(ex.getLocalizedMessage());
        err.setDescription(req.getDescription(false));
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }

    // Exception handler for MethodArgumentNotValidException
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDetails> walletExceptionHandler(MethodArgumentNotValidException ex) {
        ErrorDetails err = new ErrorDetails();
        err.setTimeStamp(LocalDateTime.now());
        err.setMessage(ex.getLocalizedMessage());
        err.setDescription(ex.getBindingResult().getFieldError().getDefaultMessage());
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }
}
