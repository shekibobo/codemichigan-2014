package com.codebits.codemichigan.michiganoutdoors.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.codebits.codemichigan.michiganoutdoors.R;
import com.codebits.codemichigan.michiganoutdoors.data.models.StateWaterAttraction;

public class WaterAttrationActivity extends Activity {

    private ListView lView;
    private StateWaterAttraction sWaterAttraction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_attration);
        lView = (ListView) findViewById(R.id.water_list_view);
        //final Adapter adapter = new ArrayAdapter<String>(this,R.layout.michigan_attraction_list_item,sWaterAttraction);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.water_attration, menu);
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
