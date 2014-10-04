package com.codebits.codemichigan.michiganoutdoors.data.models;

/**
 * Created by joshuakovach on 10/4/14.
 */
public class MichiganDataResource {
    public static String equalsCondition(String key, String value) {
        return String.format("%s='%s'", key, value);
    }

    public static String notEqualsCondition(String key, String value) {
        return String.format("%s!='%s'", key, value);
    }
}
