package com.codebits.codemichigan.michiganoutdoors.activities;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.codebits.codemichigan.michiganoutdoors.R;
import com.codebits.codemichigan.michiganoutdoors.data.api.services.MichiganData;
import com.codebits.codemichigan.michiganoutdoors.data.api.services.MichiganDataService;
import com.codebits.codemichigan.michiganoutdoors.data.models.LakeAttraction;
import com.codebits.codemichigan.michiganoutdoors.data.models.MichiganAttraction;
import com.codebits.codemichigan.michiganoutdoors.data.models.StateForestCampground;
import com.codebits.codemichigan.michiganoutdoors.data.models.StateLandAttraction;
import com.codebits.codemichigan.michiganoutdoors.data.models.StatePark;
import com.codebits.codemichigan.michiganoutdoors.data.models.StateParkTrail;
import com.codebits.codemichigan.michiganoutdoors.data.models.StateWaterAttraction;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.android.observables.AndroidObservable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import com.codebits.codemichigan.michiganoutdoors.data.models.StreamAttraction;
import com.codebits.codemichigan.michiganoutdoors.data.models.VisitorCenter;
import com.codebits.codemichigan.michiganoutdoors.fragments.AttractionsFragment;
import com.codebits.codemichigan.michiganoutdoors.fragments.FilterDrawerFragment;

import org.parceler.Parcels;

import butterknife.ButterKnife;


public class MainActivity extends FragmentActivity
    implements FilterDrawerFragment.FilterDrawerCallbacks,
                AttractionsFragment.OnFragmentInteractionListener {

    ArrayList<MichiganAttraction> resourceArray;
    private FilterDrawerFragment mFilterDrawerFragment;
    private ActionBar actionBar;
    MichiganDataService dataService;

    private AttractionsFragment mAttractionsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        actionBar = getActionBar();

        mFilterDrawerFragment = (FilterDrawerFragment)
                getFragmentManager().findFragmentById(R.id.filter_drawer);

        actionBar.setTitle(R.string.app_name);

        mAttractionsFragment = AttractionsFragment.newInstance();
        getFragmentManager().beginTransaction().replace(R.id.container, mAttractionsFragment).commit();

        dataService = new MichiganData().getDataService();
        resourceArray = new ArrayList<>();

        // Set up the drawer.
        mFilterDrawerFragment.setUp(
                R.id.filter_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

        reloadResourcesFromFilters();
    }

    private void reloadResourcesFromFilters() {
        resourceArray.clear();
        AndroidObservable.bindActivity(this, Observable.merge(landAttractionRequest(), waterAttractionRequest()))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> updateDataSet(s));
    }

    private Observable<List<StateWaterAttraction>> waterAttractionRequest() {
        ArrayList<String> queriesForWater = new ArrayList<>(2);
        if (mFilterDrawerFragment.isChecked(FilterDrawerFragment.LAKE_FILTER_INDEX))
            queriesForWater.add(LakeAttraction.toQuery());

        if (mFilterDrawerFragment.isChecked(FilterDrawerFragment.STREAM_FILTER_INDEX))
            queriesForWater.add(StreamAttraction.toQuery());

        if (queriesForWater.isEmpty()) {
            return Observable.empty();
        } else {
            String waterQuery = TextUtils.join(" OR ", queriesForWater);
            return dataService.stateWaterAttractionList(waterQuery, null);
        }
    }

    private Observable<List<StateLandAttraction>> landAttractionRequest() {
        ArrayList<String> queriesForLand = new ArrayList<>(4);
        if (mFilterDrawerFragment.isChecked(FilterDrawerFragment.CAMPGROUND_FILTER_INDEX))
            queriesForLand.add(StateForestCampground.toQuery());

        if (mFilterDrawerFragment.isChecked(FilterDrawerFragment.VISITOR_CENTER_FILTER_INDEX))
            queriesForLand.add(VisitorCenter.toQuery());

        if (mFilterDrawerFragment.isChecked(FilterDrawerFragment.TRAIL_FILTER_INDEX))
            queriesForLand.add(StateParkTrail.toQuery());

        if (mFilterDrawerFragment.isChecked(FilterDrawerFragment.STATE_PARK_FILTER_INDEX))
            queriesForLand.add(StatePark.toQuery());

        if (queriesForLand.isEmpty()) {
            return Observable.empty();
        } else {
            String landQuery = TextUtils.join(" OR ", queriesForLand);
            return dataService.stateLandAttractionList(landQuery, null);
        }
    }

    private void updateDataSet(List<? extends MichiganAttraction> s) {
        updateHeaderView();
        resourceArray.addAll(s);
        mAttractionsFragment.getAdapter().clear();
        mAttractionsFragment.getAdapter().addAll(resourceArray);
        mAttractionsFragment.getAdapter().notifyDataSetChanged();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    /* Called whenever we call invalidateOptionsMenu() */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    public void restoreActionBar() {
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
            actionBar.setDisplayShowTitleEnabled(true);
            actionBar.setTitle(R.string.app_name);
        }
    }

    private void updateHeaderView() {
        String[] filters = getResources().getStringArray(R.array.filter_array);
        ArrayList<String> appliedFilters = new ArrayList<>();

        for (int i = 0; i < filters.length; i++) {
            if (mFilterDrawerFragment.isChecked(i + 1)) {
                appliedFilters.add(filters[i]);
            }
        }

        String text = "";
        if (appliedFilters.size() == 0) {
            text = "Swipe Right for Attractions!";
        } else {
            text = TextUtils.join(", ", appliedFilters);
        }

        mAttractionsFragment.updateHeader(text);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mFilterDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onFilterDrawerItemSelected(int position) {
        reloadResourcesFromFilters();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void onAttractionSelected(MichiganAttraction attraction) {
        if (attraction.isLandAttraction()) {
            StateLandAttraction landAttraction = (StateLandAttraction) attraction;
            Intent intent = new Intent(this, LandAttractionActivity.class);
            intent.putExtra("STATE_LAND_ATTRACTION", Parcels.wrap(landAttraction));
            startActivity(intent);
        }
    }
}
