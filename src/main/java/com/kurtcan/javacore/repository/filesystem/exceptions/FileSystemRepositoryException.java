package com.kurtcan.javacore.repository.filesystem.exceptions;

public class FileSystemRepositoryException extends RuntimeException{
    public FileSystemRepositoryException() {
        super();
    }
    public FileSystemRepositoryException(Exception e) {
        super(e);
    }
    public FileSystemRepositoryException(String message) {
        super(message);
    }
}