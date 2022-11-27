package com.kurtcan.javacore.repository.multipartfile.aspect.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface SavePathTemplate {
    String value() default "";
    boolean overrideExisting() default true;
}
