package com.codebits.codemichigan.michiganoutdoors.data.models;

import android.location.Location;
import android.text.TextUtils;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

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
    @SerializedName("unitdescription") @Getter String resourceType;
    @Getter String description;
    @Getter String county;

    @Getter boolean cabin;
    @Getter boolean yurt;
    @Getter boolean rustic;
    @Getter boolean modern;
    @Getter boolean semimodern;
    @Getter boolean teepee;
    @Getter boolean minicabin;
    @Getter boolean lodge;

    @Getter boolean boat;
    @Getter boolean equestrian;
    @SerializedName("orv") @Getter boolean offRoadVehicles;

    @Getter boolean walkin;
    @SerializedName("permitrequired") @Getter boolean permitRequired;
    @SerializedName("entrancefee") @Getter boolean entranceFee;
    @SerializedName("ada") @Getter boolean accessible;
    @Getter boolean active;

    @SerializedName("ocesitefee") @Getter long oceSiteFee;
    @SerializedName("grouprate") @Getter long groupRate;
    @SerializedName("totalnumofsites") @Getter long totalNumOfSites;

    @SerializedName("location_1") @Getter Location location;

    private String vehicles;
    private String campingStyles;

    public static String toQuery() {
        return null;
    }

    public String getCampingStyles() {
        if (this.campingStyles != null && this.campingStyles.length() == 0) {
            ArrayList<String> styles = new ArrayList<>();

            if (isCabin()) styles.add("Cabin");
            if (isYurt()) styles.add("Yurt");
            if (isRustic()) styles.add("Rustic");
            if (isModern()) styles.add("Modern");
            if (isSemimodern()) styles.add("Semi-Modern");
            if (isTeepee()) styles.add("Teepee");
            if (isMinicabin()) styles.add("Mini-Cabin");
            if (isLodge()) styles.add("Lodge");

            this.campingStyles = TextUtils.join(" | ", styles);
        }

        return this.campingStyles;
    }

    public String getVehicles() {
        if (this.vehicles != null && this.vehicles.length() == 0) {
            ArrayList<String> vehicles = new ArrayList<>();

            if (isBoat()) vehicles.add("Boat");
            if (isEquestrian()) vehicles.add("Equestrian");
            if (isOffRoadVehicles()) vehicles.add("Off-Road Vehicles");

            this.vehicles = TextUtils.join(" | ", vehicles);
        }

        return this.vehicles;
    }
}

