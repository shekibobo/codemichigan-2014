package com.codebits.codemichigan.michiganoutdoors.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.codebits.codemichigan.michiganoutdoors.R;
import com.codebits.codemichigan.michiganoutdoors.data.models.StateWaterAttraction;

import org.parceler.Parcels;

public class WaterAttractionActivity extends Activity {

    private StateWaterAttraction sWaterAttraction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_attration);
        sWaterAttraction = Parcels.unwrap(this.getIntent().getExtras().getParcelable("STATE_WATER_ATTRACTION"));
    }



}
