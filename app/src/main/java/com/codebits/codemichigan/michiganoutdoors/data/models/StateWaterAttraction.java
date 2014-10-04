package com.codebits.codemichigan.michiganoutdoors.data.models;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Created by joshuakovach on 10/4/14.
 */
@ToString @NoArgsConstructor
public class StateWaterAttraction {
    @Getter String id; // Not unique!!!!
    @Getter String name;
    @Getter String body;
    @Getter String tackle;
    @Getter String type;
    @Getter String county;
    @Getter String note;

    @SerializedName("laketype") @Getter String openSeason;
    @SerializedName("designatedtroutlakeorstream") @Getter boolean designatedTroutLakeOrStream;
    @SerializedName("fishingseasonpossessionseason") @Getter String fishingSeasonPossessionSeason;

    @SerializedName("splakeminsize") @Getter String spLakeMinSize;
    @SerializedName("atlanticsalmonminsize") @Getter String atlanticSalmonMinSize;
    @SerializedName("chinookminsize") @Getter String chinookMinSize;
    @SerializedName("brooktroutminsize") @Getter String brookTroutMinSize;
    @SerializedName("pinksalmonminsize") @Getter String pinkSalmonMinSize;
    @SerializedName("browntroutminsize") @Getter String brownTroutMinSize;
    @SerializedName("cohosalmonminsize") @Getter String cohoSalmonMinSize;
    @SerializedName("laketroutminsize") @Getter String lakeTroutMinSize;
    @SerializedName("rainbowtroutminsize") @Getter String rainbowTroutMinSize;
    @SerializedName("dailypossessionlimit") @Getter String dailyPossessionLimit;

    public static String equalsCondition(String key, String value) {
        return String.format("%s='%s'", key, value);
    }

    public static String notEqualsCondition(String key, String value) {
        return String.format("%s!='%s'", key, value);
    }

    public static String toQuery() {
        return null;
    }
}
