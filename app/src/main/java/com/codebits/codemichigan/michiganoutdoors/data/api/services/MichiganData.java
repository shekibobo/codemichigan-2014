package com.codebits.codemichigan.michiganoutdoors.data.api.services;

import lombok.Getter;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * Created by joshuakovach on 10/4/14.
 */
public class MichiganData {
    RestAdapter restAdapter;

    @Getter MichiganDataService dataService;

    public MichiganData() {
        restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://data.michigan.gov")
                .setRequestInterceptor(new MichiganDataRequestIntercepter())
                .setConverter(new GsonConverter(MichiganDataGson.getGson()))
                .build();

        dataService = restAdapter.create(MichiganDataService.class);
    }
}
