package com.codebits.codemichigan.michiganoutdoors;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.util.Log;

import com.codebits.codemichigan.michiganoutdoors.data.api.services.MichiganData;
import com.codebits.codemichigan.michiganoutdoors.data.api.services.MichiganDataService;
import com.codebits.codemichigan.michiganoutdoors.data.models.MichiganAttraction;
import com.codebits.codemichigan.michiganoutdoors.data.models.StateLandAttraction;
import com.codebits.codemichigan.michiganoutdoors.data.models.StateWaterAttraction;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.android.observables.AndroidObservable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import com.codebits.codemichigan.michiganoutdoors.adapters.MainPagerAdapter;
import com.codebits.codemichigan.michiganoutdoors.fragments.FilterDrawerFragment;
import com.codebits.codemichigan.michiganoutdoors.fragments.MapFragment;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class MainActivity extends FragmentActivity
    implements MapFragment.OnFragmentInteractionListener, FilterDrawerFragment.FilterDrawerCallbacks {



    ArrayList<MichiganAttraction> resourceArray;
    private FilterDrawerFragment mFilterDrawerFragment;
    @InjectView(R.id.pager) ViewPager pager;
    private MainPagerAdapter pagerAdapter;
    private ActionBar actionBar;

    // This needs to stay static for the MapFragment.
    // It's not pretty, but I don't feel like fighting it under our current time constraints.
    public static FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        actionBar = getActionBar();
        fragmentManager = getSupportFragmentManager();

        pagerAdapter = new MainPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(pagerAdapter);

        // This is here to allow drawer swiping from the map view (potentially)
        final int pageMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources()
                .getDisplayMetrics());
        pager.setPageMargin(pageMargin);
        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i2) { }

            @Override
            public void onPageSelected(int i) {
                restoreActionBar();
            }

            @Override
            public void onPageScrollStateChanged(int i) { }
        });

        mFilterDrawerFragment = (FilterDrawerFragment)
                getFragmentManager().findFragmentById(R.id.filter_drawer);

        actionBar.setTitle(getCurrentTitle());

        // Set up the drawer.
        mFilterDrawerFragment.setUp(
                R.id.filter_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

        MichiganDataService service = new MichiganData().getDataService();

        Observable<List<StateLandAttraction>> landAttractions =
                service.stateLandAttractionList(StateLandAttraction.toQuery(), null);

        Observable<List<StateWaterAttraction>> waterAttractions =
                service.stateWaterAttractionList(StateWaterAttraction.toQuery(), null);

        resourceArray = new ArrayList<>();
        AndroidObservable.bindActivity(this, Observable.merge(landAttractions, waterAttractions))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> updateDataSet(s));
    }

    private void updateDataSet(List<? extends MichiganAttraction> s) {
        resourceArray.addAll(s);
        //Log.i("All the stuff", resourceArray.toString());
//        pagerAdapter.getAttractionFragmentListView().getAdapter().addAll(resourceArray);
//        pagerAdapter.getAttractionFragmentListView().getAdapter().notifyDataSetChanged();
        // TODO: Update the active adapters
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
            actionBar.setTitle(getCurrentTitle());
        }
    }

    private String getCurrentTitle() {
        return MainPagerAdapter.TITLES[pager.getCurrentItem()];
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
        // Loop through all drawer items and filter
        // the ones that are selected.
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
    public void listNavigationButtonClicked() {
        pager.setCurrentItem(MainPagerAdapter.LIST_FRAGMENT_INDEX);
        actionBar.setTitle(getCurrentTitle());
    }
}
