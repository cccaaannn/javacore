package com.kurtcan.javacore.repository.filesystem.exceptions;

public class FileSystemRepositoryIOException extends FileSystemRepositoryException {
    public FileSystemRepositoryIOException() {
        super();
    }
    public FileSystemRepositoryIOException(Exception e) {
        super(e);
    }
    public FileSystemRepositoryIOException(String message) {
        super(message);
    }
}