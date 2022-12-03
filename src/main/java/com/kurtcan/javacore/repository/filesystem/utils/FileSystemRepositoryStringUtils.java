package com.kurtcan.javacore.repository.filesystem.utils;

import org.apache.commons.text.StringSubstitutor;

import java.util.Map;

public class FileSystemRepositoryStringUtils {

    public static String formatWithNamedParams(String template, Map<String, String> params) {
        return StringSubstitutor.replace(template, params, "{", "}");
    }

}
