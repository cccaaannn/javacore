package com.kurtcan.javacore.repository.multipartfile.concretes;

import com.kurtcan.javacore.repository.multipartfile.abstracts.IMultipartFileRepository;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

/**
 * <h2>MultipartFileRepository implementation.</h2>
 * <hr/>
 *
 * @param <T> entity type
 *
 * @author Can Kurt
 * @version 1.0
 * @since 2022-11-26
 */
@Component
public class MultipartFileRepository<T> extends MultipartFileRepositoryBase<T> implements IMultipartFileRepository<T> {

    @Override
    public Path save(T entity) throws IOException {
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
