package com.codebits.codemichigan.michiganoutdoors.data.models;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by joshuakovach on 10/4/14.
 */
public class MichiganAttraction {
    @Getter @Setter String name;
    @Getter @Setter String resourceType;

    public static String equalsCondition(String key, String value) {
        return String.format("%s='%s'", key, value);
    }

    public static String notEqualsCondition(String key, String value) {
        return String.format("%s!='%s'", key, value);
    }
}
