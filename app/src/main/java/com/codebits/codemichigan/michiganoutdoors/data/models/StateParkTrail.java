package com.codebits.codemichigan.michiganoutdoors.data.models;

/**
 * Created by joshuakovach on 10/3/14.
 */
public class StateParkTrail extends StateLandAttraction {
    public static String toQuery() {
        return equalsCondition("unitdescription", "State Park Trail");
    }
}
