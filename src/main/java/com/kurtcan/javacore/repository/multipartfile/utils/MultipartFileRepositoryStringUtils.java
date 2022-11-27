package com.kurtcan.javacore.repository.multipartfile.utils;

import org.apache.commons.text.StringSubstitutor;

import java.util.Map;

public class MultipartFileRepositoryStringUtils {

    public static String formatWithNamedParams(String template, Map<String, String> params) {
        return StringSubstitutor.replace(template, params, "${", "}");
    }

}
