package com.codebits.codemichigan.michiganoutdoors.adapters;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.codebits.codemichigan.michiganoutdoors.R;

import butterknife.ButterKnife;
import butterknife.InjectView;


/**
 * Created by kenny on 10/4/14.
 */
public class FilterDrawerAdapter extends ArrayAdapter<String> {

    String[] nameArray;
    TypedArray icons;
    LayoutInflater inflater;

    public FilterDrawerAdapter(Context context, int nameResource) {
        super(context, nameResource);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        nameArray = context.getResources().getStringArray(R.array.filter_array);
        icons = context.getResources().obtainTypedArray(R.array.filter_icons);


    }

    @Override
    public int getCount() {
        return nameArray.length;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            convertView = inflater.inflate(R.layout.filter_drawer_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }

        holder.name.setText(nameArray[position]);
        holder.icon.setImageResource(icons.getResourceId(position, -1));

        return convertView;
    }

    static class ViewHolder {
        @InjectView(R.id.filter_name) TextView name;
        @InjectView(R.id.filter_icon) ImageView icon;

        public ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
