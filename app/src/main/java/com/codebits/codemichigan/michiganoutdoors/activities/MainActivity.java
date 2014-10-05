package com.codebits.codemichigan.michiganoutdoors.activities;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

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
import com.codebits.codemichigan.michiganoutdoors.data.models.StreamAttraction;
import com.codebits.codemichigan.michiganoutdoors.data.models.VisitorCenter;
import com.codebits.codemichigan.michiganoutdoors.fragments.FilterDrawerFragment;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.android.observables.AndroidObservable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import com.codebits.codemichigan.michiganoutdoors.fragments.AttractionsFragment;

import org.parceler.Parcels;

public class MainActivity extends FragmentActivity
    implements FilterDrawerFragment.FilterDrawerCallbacks,
                AttractionsFragment.OnFragmentInteractionListener {

    ArrayList<MichiganAttraction> resourceArray;
    private FilterDrawerFragment mFilterDrawerFragment;
    private ActionBar actionBar;

    private AttractionsFragment mAttractionsFragment;
    private DrawerLayout mDrawerLayout;
    MichiganDataService dataService;

    private Boolean landNotFound = false;
    private Boolean waterNotFound = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        actionBar = getActionBar();

        mFilterDrawerFragment = (FilterDrawerFragment)
                getFragmentManager().findFragmentById(R.id.filter_drawer);

        actionBar.setTitle(R.string.app_name);

        mAttractionsFragment = AttractionsFragment.newInstance();
        getFragmentManager().beginTransaction().replace(R.id.container, mAttractionsFragment).commit();

        dataService = new MichiganData().getDataService();
        resourceArray = new ArrayList<>();

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        // Set up the drawer.
        mFilterDrawerFragment.setUp(
                R.id.filter_drawer,
                mDrawerLayout);


        //reloadResourcesFromFilters(null);
    }

    private void reloadResourcesFromFilters(String query) {
        resourceArray.clear();
        AndroidObservable.bindActivity(this, Observable.merge(landAttractionRequest(query), waterAttractionRequest(query)))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> updateDataSet(s));
        if (landNotFound && waterNotFound) {
            // Clear all items
            updateHeaderView();
            mAttractionsFragment.getAdapter().clear();
            mAttractionsFragment.getAdapter().notifyDataSetChanged();

        } else {
            landNotFound = waterNotFound = false;
        }
    }

    private Observable<List<StateWaterAttraction>> waterAttractionRequest(String query) {
        ArrayList<String> queriesForWater = new ArrayList<>(2);
        if (mFilterDrawerFragment.isChecked(FilterDrawerFragment.LAKE_FILTER_INDEX))
            queriesForWater.add(LakeAttraction.toQuery());

        if (mFilterDrawerFragment.isChecked(FilterDrawerFragment.STREAM_FILTER_INDEX))
            queriesForWater.add(StreamAttraction.toQuery());

        if (queriesForWater.isEmpty()) {
            waterNotFound = true;
            return Observable.empty();
        } else {
            String waterQuery = TextUtils.join(" OR ", queriesForWater);
            return dataService.stateWaterAttractionList(waterQuery, query);
        }
    }

    private Observable<List<StateLandAttraction>> landAttractionRequest(String query) {
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
            landNotFound = true;
            return Observable.empty();
        } else {
            String landQuery = TextUtils.join(" OR ", queriesForLand);
            return dataService.stateLandAttractionList(landQuery, query);
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
            text = "Swipe Right for Michigan Attractions!";
        } else {
            text = TextUtils.join(", ", appliedFilters);
        }

        mAttractionsFragment.updateHeader(text);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mFilterDrawerFragment.isDrawerOpen()) {
            getMenuInflater().inflate(R.menu.main, menu);

            SearchView searchView = (SearchView) menu.findItem(R.id.menu_item_search).getActionView();

            searchView.setIconifiedByDefault(true);
            SearchView.OnQueryTextListener textChangeListener = new SearchView.OnQueryTextListener()
            {

                @Override
                public boolean onQueryTextChange(String newText) { return true; }
                @Override
                public boolean onQueryTextSubmit(String query)
                {
                    reloadResourcesFromFilters(query+"*");
                    return true;
                }
            };
            searchView.setOnQueryTextListener(textChangeListener);
            searchView.setOnCloseListener(new SearchView.OnCloseListener() {
                @Override
                public boolean onClose() {
                    reloadResourcesFromFilters(null);
                    return false;
                }
            });
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onFilterDrawerItemSelected() { reloadResourcesFromFilters(null); }

    @Override
    public void onFilterDrawerClosed() {
        mAttractionsFragment.updateHeader("Loading...");
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
        }else if(attraction.isWaterAttraction()){
            StateWaterAttraction waterAttraction = (StateWaterAttraction) attraction;
            Intent intent = new Intent(this, WaterAttrationActivity.class);
            intent.putExtra("STATE_WATER_ATTRACTION",Parcels.wrap(waterAttraction));
            startActivity(intent);
        }
    }
}
