package com.ms.myapp.exceptions;

public class RequestInvalidException extends  RuntimeException{
    public RequestInvalidException() {
    }

    public RequestInvalidException(String message) {
        super(message);
    }

    public RequestInvalidException(String message, Throwable cause) {
        super(message, cause);
    }

    public RequestInvalidException(Throwable cause) {
        super(cause);
    }

    public RequestInvalidException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
