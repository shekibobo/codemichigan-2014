package com.codebits.codemichigan.michiganoutdoors.data.api.services;

import android.location.Location;

import com.codebits.codemichigan.michiganoutdoors.data.type_adapters.BooleanAsIntTypeAdapter;
import com.codebits.codemichigan.michiganoutdoors.data.type_adapters.LocationTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by joshuakovach on 10/4/14.
 */
public class MichiganDataGson {
    public static Gson getGson() {
        BooleanAsIntTypeAdapter booleanAsIntAdapter = new BooleanAsIntTypeAdapter();
        LocationTypeAdapter locationTypeAdapter = new LocationTypeAdapter();

        return new GsonBuilder()
                .registerTypeAdapter(Boolean.class, booleanAsIntAdapter)
                .registerTypeAdapter(boolean.class, booleanAsIntAdapter)
                .registerTypeAdapter(Location.class, locationTypeAdapter)
                .create();
    }
}
