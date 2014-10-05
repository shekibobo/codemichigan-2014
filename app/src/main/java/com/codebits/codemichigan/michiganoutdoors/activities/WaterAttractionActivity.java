package com.codebits.codemichigan.michiganoutdoors.activities;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.codebits.codemichigan.michiganoutdoors.R;
import com.codebits.codemichigan.michiganoutdoors.data.models.StateWaterAttraction;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class WaterAttractionActivity extends Activity {

    private StateWaterAttraction sWaterAttraction;
    @InjectView(R.id.site_water_name_heading) TextView headerT;
    @InjectView(R.id.water_county_text) TextView countyNameT;
    @InjectView(R.id.site_locate_button) Button locateButt;
    @InjectView(R.id.water_detailed_text) TextView detailT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_attraction);
        sWaterAttraction = Parcels.unwrap(this.getIntent().getExtras().getParcelable("STATE_WATER_ATTRACTION"));
        ButterKnife.inject(this);

        headerT.setText(sWaterAttraction.getName());
        countyNameT.setText(sWaterAttraction.getCounty());
        ArrayList<String> infoList = new ArrayList<>();

        infoList.add("Open Season: " + sWaterAttraction.getOpenSeason());

        infoList.add("Fishing/Possession Season: " + sWaterAttraction.getFishingSeasonPossessionSeason());

        infoList.add("Tackle: " + sWaterAttraction.getTackle());

        infoList.add("\uD83D\uDC1F " + " Splake Minimum Size: " + sWaterAttraction.getSpLakeMinSize());

        infoList.add("\uD83D\uDC1F "+ "Brook Trout Minimum Size: " + sWaterAttraction.getBrookTroutMinSize());

        infoList.add("\uD83D\uDC1F " + "Pink Salmon Minimum Size: " + sWaterAttraction.getPinkSalmonMinSize());

        infoList.add("\uD83D\uDC1F " + "Brown Trout Minimum Size: " + sWaterAttraction.getBrownTroutMinSize());

        infoList.add("\uD83D\uDC1F " + "Coho Salmon Minimum Size: " + sWaterAttraction.getCohoSalmonMinSize());

        infoList.add("\uD83D\uDC1F " + "Rainbow Trout Minimum Size: " + sWaterAttraction.getRainbowTroutMinSize());

        infoList.add("Daily Possession Limit: " + sWaterAttraction.getDailyPossessionLimit());


        detailT.setText(TextUtils.join("\n", infoList));
    }

    @OnClick(R.id.site_locate_button)
    void locateButtonClicked(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW);

        String geo = sWaterAttraction.getGeoLocation();

        List<String> locations = Arrays.asList(sWaterAttraction.getName(),sWaterAttraction.getCounty(),"Michigan");
        Log.i("COUNTY",sWaterAttraction.getCounty());
        Uri geoLocation = Uri.parse("geo:" + geo + "?q=" + Uri.encode(TextUtils.join(" ",locations)));
        intent.setData(geoLocation);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Toast.makeText(this, "Unable to find application to launch maps.", Toast.LENGTH_SHORT).show();
        }

    }



}
