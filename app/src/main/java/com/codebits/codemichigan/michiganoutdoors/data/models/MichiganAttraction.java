package com.codebits.codemichigan.michiganoutdoors.data.models;

import lombok.Getter;

/**
 * Created by joshuakovach on 10/4/14.
 */
public class MichiganAttraction {
    @Getter String name;

    public static String equalsCondition(String key, String value) {
        return String.format("%s='%s'", key, value);
    }

    public static String notEqualsCondition(String key, String value) {
        return String.format("%s!='%s'", key, value);
    }
}
