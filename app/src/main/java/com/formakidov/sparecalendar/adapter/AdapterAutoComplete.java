package com.formakidov.sparecalendar.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.formakidov.sparecalendar.R;

import java.util.List;

public class AdapterAutoComplete extends ArrayAdapter<String> {

    private int resourceId;

    public AdapterAutoComplete(Context context, int resourceId, List<String> list) {
        super(context, resourceId, list);
        this.resourceId = resourceId;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        LinearLayout layout;

        if (convertView == null) {
            layout = new LinearLayout(getContext());
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            inflater.inflate(resourceId, layout);

            holder = new ViewHolder();
            holder.itemName = (TextView) layout.findViewById(R.id.item_name);
            layout.setTag(holder);
        } else {
            layout = (LinearLayout) convertView;
            holder = (ViewHolder) layout.getTag();
        }

        String name = getItem(position);

        holder.itemName.setText(name);

        return layout;
    }

    private static class ViewHolder {
        public TextView itemName;
    }

}