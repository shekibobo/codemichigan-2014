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
public class StateLandAttraction extends MichiganAttraction {
    @Getter long id;
    @Getter String phone;
    @Getter String ttynum;

    @SerializedName("unittype") @Getter String unitType;
    @SerializedName("unitdescription") String resourceType;
    @Getter String description;
    @Getter String county;

    @Getter boolean cabin;
    @Getter boolean yurt;
    @Getter boolean rustic;
    @Getter boolean modern;
    @Getter boolean semimodern;
    @Getter boolean teepee;
    @Getter boolean boat;
    @Getter boolean equestrian;
    @Getter boolean minicabin;
    @Getter boolean lodge;
    @Getter boolean walkin;
    @SerializedName("orv") @Getter boolean offRoadVehicles;
    @SerializedName("permitrequired") @Getter boolean permitRequired;
    @SerializedName("entrancefee") @Getter boolean entranceFee;
    @SerializedName("ada") @Getter boolean accessible;
    @Getter boolean active;

    @SerializedName("ocesitefee") @Getter long oceSiteFee;
    @SerializedName("grouprate") @Getter long groupRate;
    @SerializedName("totalnumofsites") @Getter long totalNumOfSites;

    @SerializedName("location_1") @Getter Location location;

    public static String toQuery() {
        return null;
    }
}

