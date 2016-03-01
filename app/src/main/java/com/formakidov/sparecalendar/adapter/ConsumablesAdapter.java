package com.formakidov.sparecalendar.adapter;

import android.view.View;

import com.formakidov.sparecalendar.R;
import com.formakidov.sparecalendar.adapter.viewholder.BaseViewHolder;
import com.formakidov.sparecalendar.adapter.viewholder.ConsumableViewHolder;
import com.formakidov.sparecalendar.model.Consumable;

public class ConsumablesAdapter extends AbstractFilterBindableAdapter<Consumable, BaseViewHolder> {
    @Override
    protected String itemToString(Consumable item) {
        // TODO: 01.03.2016
        return null;
    }

    @Override
    protected void onBindItemViewHolder(BaseViewHolder viewHolder, int position, int type) {
        viewHolder.bind(getItem(position));
    }

    @Override
    protected ConsumableViewHolder viewHolder(View view, int type) {
        return null;
    }

    @Override
    protected int layoutId(int type) {
        return R.layout.layout_consumables;
    }

}
