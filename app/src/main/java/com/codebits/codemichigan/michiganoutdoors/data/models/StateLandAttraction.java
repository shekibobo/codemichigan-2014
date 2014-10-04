package com.codebits.codemichigan.michiganoutdoors.data.models;

import android.location.Location;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Created by joshuakovach on 10/3/14.
 */
@ToString @NoArgsConstructor
public class StateLandAttraction {
    @Getter long id;
    @Getter String phone;
    @Getter String ttynum;

    @Getter boolean cabin;
    @Getter boolean yurt;
    @Getter boolean rustic;
    @Getter boolean modern;
    @Getter boolean semimodern;
    @Getter boolean teepee;
    @Getter boolean orv;
    @Getter boolean permitrequired;
    @Getter boolean walkin;
    @Getter boolean entrancefee;
    @Getter boolean boat;
    @Getter boolean equestrian;
    @Getter boolean ada;
    @Getter boolean minicabin;
    @Getter boolean active;
    @Getter boolean lodge;

    @Getter long ocesitefee;
    @Getter long grouprate;
    @Getter long totalnumofsites;

    @Getter String description;
    @Getter String name;
    @Getter String unittype;
    @Getter String unitdescription;
    @Getter String county;

    @SerializedName("location_1") @Getter Location location;

    public static String equalsCondition(String key, String value) {
        return String.format("%s='%s'", key, value);
    }

    public static String notEqualsCondition(String key, String value) {
        return String.format("%s!='%s'", key, value);
    }

    public static String toQuery() {
        return equalsCondition("unitdescription", "State Park")
                + " OR " + equalsCondition("unitdescription", "State Forest Campground")
                + " OR " + equalsCondition("unitdescription", "Visitor Center");
    }
}

