package com.codebits.codemichigan.michiganoutdoors.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.codebits.codemichigan.michiganoutdoors.fragments.MapFragment;
import com.codebits.codemichigan.michiganoutdoors.fragments.SuperAwesomeCardFragment;

public class MainPagerAdapter extends FragmentPagerAdapter {

    private final String[] TITLES = { "ListView", "Map" };

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

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 1:
                return MapFragment.getInstance();
            default:
                return SuperAwesomeCardFragment.newInstance(position);
        }
    }

}
