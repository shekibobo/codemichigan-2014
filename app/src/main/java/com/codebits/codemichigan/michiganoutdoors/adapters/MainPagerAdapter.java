package com.codebits.codemichigan.michiganoutdoors.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.codebits.codemichigan.michiganoutdoors.fragments.AttractionFragmentListView;
import com.google.android.gms.maps.SupportMapFragment;

public class MainPagerAdapter extends FragmentPagerAdapter {

    public final static int LIST_FRAGMENT_INDEX = 0;
    public final static int MAP_FRAGMENT_INDEX = 1;
    public final static String[] TITLES = { "Michigan Places", "Map" };

    public AttractionFragmentListView attractionFragmentListView;

    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return TITLES[position];
    }

    @Override
    public int getCount() {
        return TITLES.length;
    }

    public SupportMapFragment getMapFragment() {
        return (SupportMapFragment) getItem(MAP_FRAGMENT_INDEX);
    }

    public AttractionFragmentListView getAttractionFragmentListView() {
        return (AttractionFragmentListView) getItem(LIST_FRAGMENT_INDEX);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case MAP_FRAGMENT_INDEX:
                return SupportMapFragment.newInstance();
            case LIST_FRAGMENT_INDEX:
            default:
                if (attractionFragmentListView == null) {
                    attractionFragmentListView = new AttractionFragmentListView();
                }
                return attractionFragmentListView;
        }
    }
}
