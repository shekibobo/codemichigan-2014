package com.codebits.codemichigan.michiganoutdoors.data.models;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by joshuakovach on 10/4/14.
 */
@Parcel
@ToString @NoArgsConstructor
public class StateWaterAttraction extends MichiganAttraction {
    @Getter @Setter String id; // Not unique!!!!
    @SerializedName("body") @Getter @Setter String county;
    @Getter @Setter String tackle;
    @SerializedName("county") @Getter @Setter String resourceType;
    @Getter @Setter String type;
    @Getter @Setter String note;

    @SerializedName("laketype") @Getter @Setter String openSeason;
    @SerializedName("designatedtroutlakeorstream") @Getter @Setter boolean designatedTroutLakeOrStream;
    @SerializedName("fishingseasonpossessionseason") @Getter @Setter String fishingSeasonPossessionSeason;

    @SerializedName("splakeminsize") @Getter @Setter String spLakeMinSize;
    @SerializedName("atlanticsalmonminsize") @Getter @Setter String atlanticSalmonMinSize;
    @SerializedName("chinookminsize") @Getter @Setter String chinookMinSize;
    @SerializedName("brooktroutminsize") @Getter @Setter String brookTroutMinSize;
    @SerializedName("pinksalmonminsize") @Getter @Setter String pinkSalmonMinSize;
    @SerializedName("browntroutminsize") @Getter @Setter String brownTroutMinSize;
    @SerializedName("cohosalmonminsize") @Getter @Setter String cohoSalmonMinSize;
    @SerializedName("laketroutminsize") @Getter @Setter String lakeTroutMinSize;
    @SerializedName("rainbowtroutminsize") @Getter @Setter String rainbowTroutMinSize;
    @SerializedName("dailypossessionlimit") @Getter @Setter String dailyPossessionLimit;

    public static String toQuery() {

        return null;
    }

    public String getGeoLocation() {
        return "0,0";
    }
}
