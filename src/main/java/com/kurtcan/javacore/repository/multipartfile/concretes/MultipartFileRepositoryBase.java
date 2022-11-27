package com.kurtcan.javacore.repository.multipartfile.concretes;

import com.kurtcan.javacore.repository.multipartfile.aspect.annotations.SavePathTemplate;
import com.kurtcan.javacore.repository.multipartfile.aspect.annotations.SavePathVariable;
import com.kurtcan.javacore.repository.multipartfile.exceptions.IllegalMultipartFileRepositoryUsageException;
import com.kurtcan.javacore.repository.multipartfile.exceptions.MultipartFileRepositoryException;
import com.kurtcan.javacore.repository.multipartfile.utils.MultipartFileRepositoryStringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * <h2>MultipartFileRepository helper class.</h2>
 * <hr/>
 *
 * @param <T> entity type
 * @author Can Kurt
 * @version 1.0
 * @since 2022-11-26
 */
class MultipartFileRepositoryBase<T> {

    /**
     * @param absSavePath file path to delete
     * @return file delete result
     */
    protected boolean deleteFile(Path absSavePath) {
        return absSavePath.toFile().delete();
    }

    /**
     * @param entity      used to get file from MultipartFile
     * @param absSavePath save path for the file
     * @throws IOException
     */
    protected void saveFileFromEntityField(T entity, Path absSavePath) throws IOException {
        // Check if the file exists and throw if 'overrideExisting' flag is disabled
        SavePathTemplate savePathTemplateAnnotation = entity.getClass().getDeclaredAnnotation(SavePathTemplate.class);
        boolean overrideExisting = savePathTemplateAnnotation.overrideExisting();
        if (absSavePath.toFile().exists() && !overrideExisting) {
            throw new IOException();
        }

        // Check if MultipartFile is occurred only once in entity
        List<Field> multipartFileFields = Arrays.stream(entity.getClass().getDeclaredFields()).filter(field -> field.getType() == MultipartFile.class).toList();
        if (multipartFileFields.size() != 1) {
            throw new IllegalMultipartFileRepositoryUsageException("Entity class must contain exactly one '%s' type.".formatted(MultipartFile.class.getSimpleName()));
        }

        // Create parent directories if needed
        absSavePath.toFile().getParentFile().mkdirs();

        // Get MultipartFile field from entity and save
        Field multipartFileField = multipartFileFields.get(0);
        multipartFileField.setAccessible(true);
        try {
            MultipartFile multipartFile = (MultipartFile) multipartFileField.get(entity);
            multipartFile.transferTo(absSavePath.toFile());
        } catch (Exception e) {
            throw new MultipartFileRepositoryException(e);
        }
    }

    /**
     * @param entity to be used for building save path
     * @return absSavePath created by fields from the entity
     */
    protected Path buildSavePathFromEntityFields(T entity) {
        SavePathTemplate savePathTemplateAnnotation = entity.getClass().getDeclaredAnnotation(SavePathTemplate.class);
        if (Objects.isNull(savePathTemplateAnnotation)) {
            throw new IllegalMultipartFileRepositoryUsageException("Entity class must have '%s' annotation.".formatted(SavePathTemplate.class.getSimpleName()));
        }

        String pathTemplate = savePathTemplateAnnotation.value();
        Map<String, String> templateFillerMap = getTemplateFillerMap(entity);
        String filledTemplate = MultipartFileRepositoryStringUtils.formatWithNamedParams(pathTemplate, templateFillerMap);
        return Paths.get(filledTemplate);
    }

    /**
     * @param entity to be used for filling the Map
     * @return the Map which filled by the values of the entity.
     */
    protected Map<String, String> getTemplateFillerMap(T entity) {
        Map<String, String> templateFillerMap = new HashMap<>();
        for (Field field : entity.getClass().getDeclaredFields()) {
            if (field.getType() == MultipartFile.class) {
                continue;
            }
            try {
                field.setAccessible(true);
                String fieldName = getPathVariableName(field);
                String fieldValue = field.get(entity).toString();
                templateFillerMap.put(fieldName, fieldValue);
            } catch (IllegalAccessException ignored) {
            } catch (Exception e) {
                throw new MultipartFileRepositoryException(e);
            }
        }
        return templateFillerMap;
    }

    /**
     * @param field entity field
     * @return fields name or if 'SavePathVariable' annotation is used on the variable returns value passed to it.
     */
    protected String getPathVariableName(Field field) {
        SavePathVariable savePathVariableAnnotation = field.getAnnotation(SavePathVariable.class);
        if (Objects.nonNull(savePathVariableAnnotation) && !savePathVariableAnnotation.value().isBlank()) {
            return savePathVariableAnnotation.value();
        }
        return field.getName();
    }

}
