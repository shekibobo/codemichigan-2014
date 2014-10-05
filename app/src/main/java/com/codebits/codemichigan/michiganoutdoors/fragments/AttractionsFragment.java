package com.codebits.codemichigan.michiganoutdoors.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.codebits.codemichigan.michiganoutdoors.R;
import com.codebits.codemichigan.michiganoutdoors.adapters.MichiganAttractionAdapter;
import com.codebits.codemichigan.michiganoutdoors.data.models.MichiganAttraction;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnItemClick;
import lombok.NoArgsConstructor;

/**
 * A fragment which contains an expandable list of time slots
 * sorted by date.
 *
 */
@NoArgsConstructor
public class AttractionsFragment extends Fragment {
    private OnFragmentInteractionListener mListener;

    @InjectView(R.id.list_view) ListView mListView;
    private MichiganAttractionAdapter mAdapter;
    private TextView mHeaderView;

    /**
     * @return A new instance of fragment TimeSlotsFragment.
     */
    public static AttractionsFragment newInstance() {
        AttractionsFragment fragment = new AttractionsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_list, container, false);
        ButterKnife.inject(this, root);

        mHeaderView = (TextView) inflater.inflate(R.layout.michigan_attraction_list_header, mListView, false);
        mListView.setHeaderDividersEnabled(true);
        mHeaderView.setText("Swipe Right for Attractions!");
        mListView.addHeaderView(mHeaderView);

        ArrayList<MichiganAttraction> li = new ArrayList<>();

        mAdapter = new MichiganAttractionAdapter(getActivity().getApplicationContext(),
                R.layout.michigan_attraction_list_item, li);

        mListView.setAdapter(mAdapter);

        return root;
    }

    public void updateHeader(String string) {
        mHeaderView.setText(string);
    }

    public MichiganAttractionAdapter getAdapter() {
        return mAdapter;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        ButterKnife.reset(this);
        mListener = null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnItemClick(R.id.list_view)
    public void attractionClicked(int position) {
        MichiganAttraction selectedAttraction = mAdapter.getItem(position);

        if (mListener != null) {
            mListener.onAttractionSelected(selectedAttraction);
        }
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     */
    public interface OnFragmentInteractionListener {
        public void onAttractionSelected(MichiganAttraction attraction);
    }
}
