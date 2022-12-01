package com.kurtcan.javacore.exception;

public class UnAuthorizedException extends RuntimeException{
    public UnAuthorizedException() {
        super("Unauthorized");
    }
    public UnAuthorizedException(String message) {
        super(message);
    }
}