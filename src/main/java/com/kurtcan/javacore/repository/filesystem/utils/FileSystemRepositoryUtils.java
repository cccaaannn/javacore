package com.kurtcan.javacore.repository.filesystem.utils;

import org.apache.commons.text.StringSubstitutor;

import java.util.Map;
import java.util.Objects;

public class FileSystemRepositoryUtils {

    public static String formatWithNamedParams(String template, Map<String, String> params) {
        return StringSubstitutor.replace(template, params, "{", "}");
    }

    public static boolean safeIsBlank(String str) {
        return Objects.isNull(str) || str.isBlank();
    }

}
