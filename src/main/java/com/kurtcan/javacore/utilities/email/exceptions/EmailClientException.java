package com.kurtcan.javacore.utilities.email.exceptions;

public class EmailClientException extends RuntimeException{
    public EmailClientException() {
        super("Email client exception");
    }
    public EmailClientException(String message) {
        super(message);
    }
}