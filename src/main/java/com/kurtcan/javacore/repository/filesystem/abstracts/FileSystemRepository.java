package com.kurtcan.javacore.repository.filesystem.abstracts;

import com.kurtcan.javacore.repository.filesystem.exceptions.FileSystemRepositoryIOException;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Path;

@Component
public interface FileSystemRepository<T> {
    Path save(T entity) throws FileSystemRepositoryIOException;

    boolean delete(T entity);

    File get(T entity);

    Path getPath(T entity);

    boolean isExists(T entity);
}
