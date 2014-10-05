package com.codebits.codemichigan.michiganoutdoors.data.models;

import android.location.Location;
import android.text.TextUtils;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.util.ArrayList;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by joshuakovach on 10/3/14.
 */
@Parcel
@ToString @NoArgsConstructor
public class StateLandAttraction extends MichiganAttraction {
    @Getter @Setter long id;
    @Getter @Setter String phone;
    @Getter @Setter String ttynum;

    @SerializedName("unittype") @Getter @Setter String unitType;
    @SerializedName("unitdescription") @Getter @Setter String resourceType;
    @Getter @Setter String description;
    @Getter @Setter String county;

    @Getter @Setter boolean cabin;
    @Getter @Setter boolean yurt;
    @Getter @Setter boolean rustic;
    @Getter @Setter boolean modern;
    @Getter @Setter boolean semimodern;
    @Getter @Setter boolean teepee;
    @Getter @Setter boolean minicabin;
    @Getter @Setter boolean lodge;

    @Getter @Setter boolean boat;
    @Getter @Setter boolean equestrian;
    @SerializedName("orv") @Getter @Setter boolean offRoadVehicles;

    @Getter @Setter boolean walkin;
    @SerializedName("permitrequired") @Getter @Setter boolean permitRequired;
    @SerializedName("entrancefee") @Getter @Setter boolean entranceFee;
    @SerializedName("ada") @Getter @Setter boolean accessible;
    @Getter @Setter boolean active;

    @SerializedName("ocesitefee") @Getter @Setter long oceSiteFee;
    @SerializedName("grouprate") @Getter @Setter long groupRate;
    @SerializedName("totalnumofsites") @Getter @Setter long totalNumOfSites;

    @SerializedName("location_1") @Getter @Setter Location location;

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

