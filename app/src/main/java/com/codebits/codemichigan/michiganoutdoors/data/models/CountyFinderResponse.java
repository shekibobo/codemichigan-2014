package com.codebits.codemichigan.michiganoutdoors.data.models;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;

/**
 * Created by joshuakovach on 10/5/14.
 */
public class CountyFinderResponse {
   @SerializedName("County") @Getter CountyItem county;
}
