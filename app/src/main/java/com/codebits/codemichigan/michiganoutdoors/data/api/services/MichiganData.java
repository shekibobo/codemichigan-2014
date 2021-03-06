package com.codebits.codemichigan.michiganoutdoors.data.api.services;

import com.squareup.okhttp.OkHttpClient;

import lombok.Getter;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

/**
 * Created by joshuakovach on 10/4/14.
 */
public class MichiganData {
    RestAdapter restAdapter;
    RestAdapter fccRestAdapter;

    @Getter MichiganDataService dataService;
    @Getter FCCDataService fccDataService;

    public MichiganData() {
        OkHttpClient okHttpClient = new OkHttpClient();

        restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://data.michigan.gov")
                .setRequestInterceptor(new MichiganDataRequestIntercepter())
                .setConverter(new GsonConverter(MichiganDataGson.getGson()))
                .setClient(new OkClient(okHttpClient))
                .build();

        dataService = restAdapter.create(MichiganDataService.class);

        fccRestAdapter = new RestAdapter.Builder()
                .setEndpoint("http://data.fcc.gov")
                .setClient((new OkClient(okHttpClient)))
                .build();
        fccDataService = fccRestAdapter.create(FCCDataService.class);
    }
}
