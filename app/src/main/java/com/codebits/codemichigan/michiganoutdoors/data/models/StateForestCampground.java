package com.codebits.codemichigan.michiganoutdoors.data.models;

/**
 * Created by joshuakovach on 10/3/14.
 */
public class StateForestCampground extends StateLandAttraction {
    public static String toQuery() {
        return equalsCondition("unitdescription", "State Forest Campground");
    }
}
