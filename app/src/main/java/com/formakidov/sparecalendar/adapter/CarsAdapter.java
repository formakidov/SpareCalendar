package com.formakidov.sparecalendar.adapter;

import android.view.View;

import com.formakidov.sparecalendar.R;
import com.formakidov.sparecalendar.adapter.viewholder.CarViewHolder;
import com.formakidov.sparecalendar.model.Car;

public class CarsAdapter extends AbstractRecyclerBindableAdapter<Car, CarViewHolder> {

    @Override
    protected void onBindItemViewHolder(CarViewHolder viewHolder, int position, int type) {
        viewHolder.bind(getItem(position));
    }

    @Override
    protected CarViewHolder viewHolder(View view, int type) {
        return new CarViewHolder(view);
    }

    @Override
    protected int layoutId(int type) {
        return R.layout.layout_car;
    }
}
