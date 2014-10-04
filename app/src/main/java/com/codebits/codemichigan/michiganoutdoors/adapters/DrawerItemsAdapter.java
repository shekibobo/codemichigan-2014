package com.codebits.codemichigan.michiganoutdoors.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.codebits.codemichigan.michiganoutdoors.R;
import com.codebits.codemichigan.michiganoutdoors.models.DrawerItem;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by kenny on 10/3/14.
 */
public class DrawerItemsAdapter extends ArrayAdapter<DrawerItem> {

    @InjectView(R.id.drawer_item_name) TextView name;
    @InjectView(R.id.drawer_item_checkbox) CheckBox checkBoxFilter;


    public DrawerItemsAdapter(Context context, ArrayList<DrawerItem> drawerItems) {
        super(context, 0, drawerItems);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        DrawerItem item = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.drawer_list_item, parent, false);
        }

        ButterKnife.inject(this, convertView);

        name.setText(item.getName());
        checkBoxFilter.setSelected(item.getCheck());

        return convertView;
    }
}
