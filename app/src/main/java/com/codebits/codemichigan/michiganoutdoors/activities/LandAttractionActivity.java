package com.codebits.codemichigan.michiganoutdoors.activities;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.codebits.codemichigan.michiganoutdoors.R;
import com.codebits.codemichigan.michiganoutdoors.data.models.StateLandAttraction;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class LandAttractionActivity extends Activity {

    StateLandAttraction mLandAttraction;
    @InjectView(R.id.site_name_heading) TextView siteNameHeading;
    @InjectView(R.id.site_county_text) TextView siteCountyAndCampingTypes;
    @InjectView(R.id.site_call_button) Button siteCallButton;
    @InjectView(R.id.site_permit_text) TextView sitePermitText;
    @InjectView(R.id.site_locate_button) Button siteLocationButton;
    @InjectView(R.id.campground_detailed_text) TextView detailText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_land_attraction);

        mLandAttraction = Parcels.unwrap(this.getIntent().getExtras().getParcelable("STATE_LAND_ATTRACTION"));

        ButterKnife.inject(this);

        siteNameHeading.setText(mLandAttraction.getName());

        List<String> countyAndStyle = Arrays.asList(mLandAttraction.getCounty());
        if (mLandAttraction.getCampingStyles() != null) countyAndStyle.add(mLandAttraction.getCampingStyles());
        siteCountyAndCampingTypes.setText(TextUtils.join(" | ", countyAndStyle));

        siteCallButton.setText(mLandAttraction.getPhone());
        siteLocationButton.setText(R.string.locate);
        sitePermitText.setText("Permit Required: " + (mLandAttraction.isPermitRequired() ? "Yes" : "No"));

        List<String> otherStuff = new ArrayList<>();
        if (mLandAttraction.getDescription() != null) otherStuff.add(mLandAttraction.getDescription());
        if (mLandAttraction.getGroupRate() > 0) otherStuff.add("Group Rate: " + mLandAttraction.getGroupRate());
        if (mLandAttraction.getTotalNumOfSites() > 0) otherStuff.add("Total Number of Sites: " + mLandAttraction.getTotalNumOfSites());
        if (mLandAttraction.getVehicles() != null) otherStuff.add("Allowed Vehicles: " + mLandAttraction.getVehicles());

        detailText.setText(TextUtils.join("\n\n", otherStuff));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.land_attraction, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
