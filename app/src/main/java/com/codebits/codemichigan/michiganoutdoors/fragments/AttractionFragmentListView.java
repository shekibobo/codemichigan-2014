package com.codebits.codemichigan.michiganoutdoors.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

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

    public MichiganAttractionAdapter getAdapter() {
        return this.attractionAdapter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mListView = (ListView) inflater.inflate(R.layout.michigan_attraction_list, container, false);

        // Temporary
        ArrayList<MichiganAttraction> li = new ArrayList<>();
        MichiganAttraction t = new MichiganAttraction();
        t.setName("PARKKK");
        t.setResourceType("Lake");
        li.add(t);

        attractionAdapter = new MichiganAttractionAdapter(getActivity().getApplicationContext(),
                R.layout.michigan_attraction_list_item, li);
        Log.wtf("Adapter", "Made adapter");

        mListView.setAdapter(attractionAdapter);


        return mListView;
    }
}
