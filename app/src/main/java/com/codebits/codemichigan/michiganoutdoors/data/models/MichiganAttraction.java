package com.codebits.codemichigan.michiganoutdoors.data.models;

import org.parceler.Parcel;

import java.util.Arrays;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by joshuakovach on 10/4/14.
 */
@Parcel
public class MichiganAttraction {
    @Getter @Setter String name;
    @Getter @Setter String resourceType;

    public static String equalsCondition(String key, String value) {
        return String.format("%s='%s'", key, value);
    }

    public static String notEqualsCondition(String key, String value) {
        return String.format("%s!='%s'", key, value);
    }

    public boolean isLandAttraction() {
        return Arrays.asList("State Park", "State Forest Campground", "State Park Trail", "Visitor Center")
                .contains(getResourceType());
    }

    public boolean isWaterAttraction() {
        return Arrays.asList("Lake", "Stream").contains(getResourceType());
    }
}
