package com.codebits.codemichigan.michiganoutdoors.data.api.services;

import com.codebits.codemichigan.michiganoutdoors.data.models.StateForestCampground;
import com.codebits.codemichigan.michiganoutdoors.data.models.StateLandAttraction;
import com.codebits.codemichigan.michiganoutdoors.data.models.StatePark;
import com.codebits.codemichigan.michiganoutdoors.data.models.StateWaterAttraction;
import com.codebits.codemichigan.michiganoutdoors.data.models.VisitorCenter;

import java.util.List;

import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by joshuakovach on 10/3/14.
 */
public interface MichiganDataService {
    @GET("/resource/w9tw-628x")
    Observable<List<StateLandAttraction>> stateLandAttractionList(
            @Query("$where") String attractionType,
            @Query("$q") String searchString);

    @GET("/resource/w9tw-628x?$where=unitdescription='State%20Park'")
    Observable<List<StatePark>> stateParkList();

    @GET("/resource/w9tw-628x?$where=unitdescription='State%20Forest%20Campground'")
    Observable<List<StateForestCampground>> campgroundList();

    @GET("/resource/w9tw-628x?$where=unitdescription='Visitor%20Center'")
    Observable<List<VisitorCenter>> visitorCenterList();

    @GET("/resource/3qit-aik4")
    Observable<List<StateWaterAttraction>> stateWaterAttractionList(
            @Query("$where") String attractionType,
            @Query("$q") String searchString);
}
