package com.formakidov.sparecalendar.adapter.viewholder;

import android.view.View;
import android.widget.TextView;

import com.formakidov.sparecalendar.R;
import com.formakidov.sparecalendar.model.BaseModel;
import com.formakidov.sparecalendar.model.Car;

import butterknife.Bind;

public class CarViewHolder extends BaseViewHolder {
    @Bind(R.id.car_name) TextView name;
    @Bind(R.id.car_distance) TextView distance;
    @Bind(R.id.car_comment) TextView comment;

    public CarViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bind(BaseModel item) {
        Car car = (Car) item;
        name.setText(car.getName());
        distance.setText(String.valueOf(car.getDistance()));
        comment.setText(car.getComment());
    }
}
