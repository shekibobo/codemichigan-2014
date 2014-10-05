package com.codebits.codemichigan.michiganoutdoors.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.codebits.codemichigan.michiganoutdoors.R;
import com.codebits.codemichigan.michiganoutdoors.adapters.MichiganAttractionAdapter;
import com.codebits.codemichigan.michiganoutdoors.data.models.MichiganAttraction;

import java.util.ArrayList;

/**
 * Created by kenny on 10/4/14.
 */
public class AttractionFragmentListView extends Fragment {

    private MichiganAttractionAdapter attractionAdapter;
    private ListView mListView;
    private TextView mHeaderView;

    public MichiganAttractionAdapter getAdapter() {
        return this.attractionAdapter;
    }

    public TextView getHeaderView() {
        return this.mHeaderView;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mListView = (ListView) inflater.inflate(R.layout.michigan_attraction_list, container, false);
        mHeaderView = (TextView) inflater.inflate(R.layout.michigan_attraction_list_header, mListView, false);
        mListView.setHeaderDividersEnabled(true);
        mHeaderView.setText("Swipe Right for Attractions!");
        mListView.addHeaderView(mHeaderView);

        ArrayList<MichiganAttraction> li = new ArrayList<>();

        attractionAdapter = new MichiganAttractionAdapter(getActivity().getApplicationContext(),
                R.layout.michigan_attraction_list_item, li);
        mListView.setAdapter(attractionAdapter);

        return mListView;
    }
}