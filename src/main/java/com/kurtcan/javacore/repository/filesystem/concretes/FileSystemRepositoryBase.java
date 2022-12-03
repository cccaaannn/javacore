package com.kurtcan.javacore.repository.filesystem.concretes;

import com.kurtcan.javacore.repository.filesystem.aspect.annotations.FileSystemEntity;
import com.kurtcan.javacore.repository.filesystem.aspect.annotations.FileSystemSavePathVariable;
import com.kurtcan.javacore.repository.filesystem.exceptions.FileSystemRepositoryException;
import com.kurtcan.javacore.repository.filesystem.exceptions.FileSystemRepositoryIOException;
import com.kurtcan.javacore.repository.filesystem.exceptions.IllegalFileSystemRepositoryUsageException;
import com.kurtcan.javacore.repository.filesystem.utils.FileSystemRepositoryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * <h2>FileSystemRepository helper class.</h2>
 * <hr/>
 *
 * @param <T> entity type
 * @author Can Kurt
 * @version 2.5
 * @since 2022-11-26
 */
@Component
class FileSystemRepositoryBase<T> {

    private Environment environment;

    @Autowired(required = false)
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    /**
     * @param entity      used to get file from MultipartFile
     * @param absSavePath save path for the file
     * @return Saved file path
     * @throws FileSystemRepositoryException   thrown on any other Exception during saving.
     * @throws FileSystemRepositoryIOException thrown if file saving throws IOException
     */
    protected Path saveFileFromEntityField(T entity, Path absSavePath) throws FileSystemRepositoryIOException, FileSystemRepositoryException {
        // Check if the file exists and throw if 'overrideExisting' flag is disabled
        FileSystemEntity fileSystemEntityAnnotation = entity.getClass().getDeclaredAnnotation(FileSystemEntity.class);
        boolean overrideExisting = fileSystemEntityAnnotation.overrideExisting();
        if (absSavePath.toFile().exists() && !overrideExisting) {
            throw new FileSystemRepositoryIOException("File exists");
        }

        // Check if MultipartFile is occurred only once in entity
        List<Field> multipartFileFields = Arrays.stream(entity.getClass().getDeclaredFields()).filter(field -> field.getType() == MultipartFile.class).toList();
        if (multipartFileFields.size() != 1) {
            throw new IllegalFileSystemRepositoryUsageException("Entity class must contain exactly one '%s' type.".formatted(MultipartFile.class.getSimpleName()));
        }

        // Get MultipartFile field from entity and save
        Field multipartFileField = multipartFileFields.get(0);
        multipartFileField.setAccessible(true);

        try {
            // Create parent directories if needed
            absSavePath.toFile().getParentFile().mkdirs();

            // Save file
            MultipartFile multipartFile = (MultipartFile) multipartFileField.get(entity);
            multipartFile.transferTo(absSavePath.toFile());
        } catch (IOException e) {
            throw new FileSystemRepositoryIOException(e);
        } catch (Exception e) {
            throw new FileSystemRepositoryException(e);
        }

        return absSavePath;
    }

    /**
     * @param absSavePath file path to delete
     * @return file delete result
     */
    protected boolean deleteFile(Path absSavePath) {
        return absSavePath.toFile().delete();
    }

    /**
     * @param entity to be used for building save path
     * @return absSavePath created by fields from the entity
     */
    protected Path buildSavePathFromEntityFields(T entity) {
        FileSystemEntity fileSystemEntityAnnotation = entity.getClass().getDeclaredAnnotation(FileSystemEntity.class);
        if (Objects.isNull(fileSystemEntityAnnotation)) {
            throw new IllegalFileSystemRepositoryUsageException("Entity class must have '%s' annotation.".formatted(FileSystemEntity.class.getSimpleName()));
        }

        String pathTemplate = fileSystemEntityAnnotation.value();
        Map<String, String> templateFillerMap = this.getTemplateFillerMap(entity);
        String filledTemplate = FileSystemRepositoryUtils.formatWithNamedParams(pathTemplate, templateFillerMap);
        return Paths.get(filledTemplate);
    }



    /**
     * @param entity to be used for filling the Map
     * @return the Map which filled by the values of the entity.
     */
    private Map<String, String> getTemplateFillerMap(T entity) {
        Map<String, String> templateFillerMap = new HashMap<>();
        for (Field field : entity.getClass().getDeclaredFields()) {
            if (field.getType() == MultipartFile.class) {
                continue;
            }
            try {
                field.setAccessible(true);
                String fieldName = this.getPathVariableName(field);
                String fieldValue = this.getPathVariableValue(entity, field);
                templateFillerMap.put(fieldName, fieldValue);
            } catch (Exception e) {
                throw new FileSystemRepositoryException(e);
            }
        }
        return templateFillerMap;
    }

    /**
     * @param field entity field
     * @return fields name or if 'SavePathVariable' annotation is used on the variable returns value passed to it.
     */
    private String getPathVariableName(Field field) {
        FileSystemSavePathVariable fileSystemSavePathVariableAnnotation = field.getAnnotation(FileSystemSavePathVariable.class);
        if (Objects.nonNull(fileSystemSavePathVariableAnnotation) &&
                !FileSystemRepositoryUtils.safeIsBlank(fileSystemSavePathVariableAnnotation.value())
        ) {
            return fileSystemSavePathVariableAnnotation.value();
        }
        return field.getName();
    }

    /**
     * @param entity instance for getting values
     * @param field entity field
     * @return fields value or if 'SavePathVariable' annotation is used with 'fromEnvironmentVariable' switch, return environment value for provided value.
     * (Users spring environment to retrieve variables)
     */
    private String getPathVariableValue(T entity, Field field) throws IllegalAccessException {
        FileSystemSavePathVariable fileSystemSavePathVariableAnnotation = field.getAnnotation(FileSystemSavePathVariable.class);
        if (Objects.nonNull(fileSystemSavePathVariableAnnotation) &&
                !FileSystemRepositoryUtils.safeIsBlank(fileSystemSavePathVariableAnnotation.fromEnvironment())
        ) {
            return this.environment.getProperty(fileSystemSavePathVariableAnnotation.fromEnvironment());
        }
        return field.get(entity).toString();
    }

}
