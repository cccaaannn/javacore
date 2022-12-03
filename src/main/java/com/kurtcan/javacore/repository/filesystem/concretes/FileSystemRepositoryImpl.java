package com.kurtcan.javacore.repository.filesystem.concretes;

import com.kurtcan.javacore.repository.filesystem.abstracts.IFileSystemRepository;
import com.kurtcan.javacore.repository.filesystem.exceptions.FileSystemRepositoryIOException;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Path;

/**
 * <h2>FileSystemRepository implementation.</h2>
 * <hr/>
 *
 * @param <T> entity type
 * @author Can Kurt
 * @version 2.0
 * @since 2022-11-26
 */
@Component
public class FileSystemRepositoryImpl<T> extends FileSystemRepositoryBase<T> implements IFileSystemRepository<T> {

    @Override
    public Path save(T entity) throws FileSystemRepositoryIOException {
        Path absSavePath = this.buildSavePathFromEntityFields(entity);
        this.saveFileFromEntityField(entity, absSavePath);
        return absSavePath;
    }

    @Override
    public boolean delete(T entity) {
        Path absSavePath = this.buildSavePathFromEntityFields(entity);
        return this.deleteFile(absSavePath);
    }

    @Override
    public File get(T entity) {
        return this.buildSavePathFromEntityFields(entity).toFile();
    }

    @Override
    public Path getPath(T entity) {
        return this.buildSavePathFromEntityFields(entity);
    }

    @Override
    public boolean isExists(T entity) {
        return this.buildSavePathFromEntityFields(entity).toFile().exists();
    }

}
