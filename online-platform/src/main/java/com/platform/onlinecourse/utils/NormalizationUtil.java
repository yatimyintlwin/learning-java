package com.platform.onlinecourse.utils;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class NormalizationUtil {
    public static String normalize(String input) {
        if (input == null) return null;
        return input.toLowerCase().replaceAll("\\s+", "");
    }
}
