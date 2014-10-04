package com.codebits.codemichigan.michiganoutdoors.data.models;

/**
 * Created by joshuakovach on 10/3/14.
 */
public class LakeAttraction extends StateWaterAttraction {
    public static String toQuery() {
        return equalsCondition("county", "lake");
    }
}
