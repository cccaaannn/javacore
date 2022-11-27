package com.kurtcan.javacore.repository.multipartfile.exceptions;

public class MultipartFileRepositoryException extends RuntimeException{
    public MultipartFileRepositoryException() {
        super();
    }
    public MultipartFileRepositoryException(Exception e) {
        super(e);
    }
    public MultipartFileRepositoryException(String message) {
        super(message);
    }
}