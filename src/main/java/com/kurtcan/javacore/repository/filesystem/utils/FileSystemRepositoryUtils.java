package com.kurtcan.javacore.repository.filesystem.utils;

import org.apache.commons.text.StringSubstitutor;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public class FileSystemRepositoryUtils {

    public static String formatWithNamedParams(String template, Map<String, String> params) {
        return StringSubstitutor.replace(template, params, "{", "}");
    }

    public static List<String> findEnvironmentValues(String template) {
        return Pattern.compile("\\$\\{(.*?)}")
                .matcher(template)
                .results()
                .map(MatchResult::group)
                .toList();
    }

    public static String replaceEnvironmentValue(String template, String pattern, String value) {
        return template.replace(pattern, value);
    }

    public static boolean safeIsBlank(String str) {
        return Objects.isNull(str) || str.isBlank();
    }

}
