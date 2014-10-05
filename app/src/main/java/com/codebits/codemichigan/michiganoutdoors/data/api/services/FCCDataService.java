package com.codebits.codemichigan.michiganoutdoors.data.api.services;

import com.codebits.codemichigan.michiganoutdoors.data.models.CountyFinderResponse;

import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by joshuakovach on 10/3/14.
 */
public interface FCCDataService {
    @GET("/api/block/find?format=json&showall=true")
    Observable<CountyFinderResponse> stateWaterAttractionList(
            @Query("latitude") double latitude,
            @Query("longitude") double longitude);
}
