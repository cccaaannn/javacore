package com.kurtcan.javacore.repository.multipartfile.abstracts;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public interface IMultipartFileRepository<T> {
    Path save(T entity) throws IOException;

    boolean delete(T entity);

    File get(T entity);

    Path getPath(T entity);

    boolean isExists(T entity);
}
