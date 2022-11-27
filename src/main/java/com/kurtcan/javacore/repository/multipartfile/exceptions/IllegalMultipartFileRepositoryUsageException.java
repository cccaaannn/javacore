package com.kurtcan.javacore.repository.multipartfile.exceptions;

public class IllegalMultipartFileRepositoryUsageException extends MultipartFileRepositoryException {
    public IllegalMultipartFileRepositoryUsageException() {
        super();
    }

    public IllegalMultipartFileRepositoryUsageException(String message) {
        super(message);
    }
}