package com.kurtcan.javacore.repository.filesystem.exceptions;

public class IllegalFileSystemRepositoryUsageException extends FileSystemRepositoryException {
    public IllegalFileSystemRepositoryUsageException() {
        super();
    }

    public IllegalFileSystemRepositoryUsageException(String message) {
        super(message);
    }
}