package com.codebits.codemichigan.michiganoutdoors.adapters;

import android.content.Context;
import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.codebits.codemichigan.michiganoutdoors.R;
import com.codebits.codemichigan.michiganoutdoors.data.models.MichiganAttraction;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by kenny on 10/4/14.
 */
public class MichiganAttractionAdapter extends ArrayAdapter<MichiganAttraction> {

    LayoutInflater inflater;
    TypedArray attractionIcons;
    final String[] attractionNames = {
            "State Forest Campground",
            "State Park",
            "State Park Trail",
            "Visitor Center",
            "Stream",
            "Lake"
    };

    public MichiganAttractionAdapter(Context context, int resourceId, ArrayList<MichiganAttraction> dataList) {
        super(context, resourceId, dataList);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        attractionIcons = context.getResources().obtainTypedArray(R.array.attraction_icons);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            convertView = inflater.inflate(R.layout.michigan_attraction_list_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }

        MichiganAttraction attraction = getItem(position);
        holder.attractionName.setText(attraction.getName());

        // categorize on the resource type and set icon
        String type = attraction.getResourceType();
        for (int i=0; i < attractionNames.length; i++) {
            if (type.equalsIgnoreCase(attractionNames[i])) {
                holder.attractionIcon.setImageResource(attractionIcons.getResourceId(i, -1));
            }
        }
        if (holder.attractionIcon.getDrawable() == null) {
            holder.attractionIcon.setImageResource(attractionIcons.getResourceId(0, -1));
        }

        return convertView;
    }

    static class ViewHolder {

        @InjectView(R.id.attraction_name) TextView attractionName;
        @InjectView(R.id.attraction_icon) ImageView attractionIcon;

        public ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
