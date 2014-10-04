package com.codebits.codemichigan.michiganoutdoors.data.api.services;

import retrofit.RequestInterceptor;

/**
 * Created by joshuakovach on 10/3/14.
 */
public class MichiganDataRequestIntercepter implements RequestInterceptor {
    @Override
    public void intercept(RequestInterceptor.RequestFacade request) {
        request.addHeader("X-App-Token", "VnX35ZdecINJ66pVEUCsHFLo1");
    }
}
