package com.kurtcan.javacore.repository.filesystem.aspect.annotations;

import com.kurtcan.javacore.repository.filesystem.aspect.concrete.FileSystemRepositoryScanRegistrar;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * Scans for 'FileSystemRepository' annotations to get beans, also see FileSystemRepositoryScanRegistrar
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import({FileSystemRepositoryScanRegistrar.class})
public @interface FileSystemRepositoryScan {
    String[] value() default {};
}
