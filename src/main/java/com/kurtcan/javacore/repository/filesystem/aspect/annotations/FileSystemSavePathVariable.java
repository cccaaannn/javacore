package com.kurtcan.javacore.repository.filesystem.aspect.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>Use this for specifying the string to be used to replace the fields inside the value of 'FileSystemEntity'</p>
 * <p>Also you can get values form environment automatically by setting a value to 'fromEnvironment' <i>Ex: fromEnvironment="${root.save.path}"</i></p>
 * <hr>
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FileSystemSavePathVariable {
    String value();
    String fromEnvironment();
}
