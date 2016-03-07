package com.formakidov.sparecalendar.adapter.viewholder;

import android.view.View;
import android.widget.TextView;

import com.formakidov.sparecalendar.R;
import com.formakidov.sparecalendar.model.BaseModel;
import com.formakidov.sparecalendar.model.Car;
import com.formakidov.sparecalendar.tools.Formatter;

import butterknife.Bind;

public class CarViewHolder extends BaseViewHolder {
    @Bind(R.id.car_name) TextView name;
    @Bind(R.id.car_mileage)
    TextView distance;
    @Bind(R.id.car_comment) TextView comment;
    @Bind(R.id.car_gearbox)
    TextView gearbox;
    @Bind(R.id.car_consumables_count)
    TextView consumablesCount;
    @Bind(R.id.car_latest_update)
    TextView latestUpdate;

    public CarViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bind(BaseModel item) {
        Car car = (Car) item;
        name.setText("name: " + car.getName());
        distance.setText("mileage: " + Formatter.formatMileage(car.getMileage()));
        comment.setText("comment: " + car.getComment());
        gearbox.setText("gearbox: " + Formatter.formatGearboxType(car.getGearboxType()));
        latestUpdate.setText("latest update: " + Formatter.formatLastUpdate(car.getLatestUpdate()));
        consumablesCount.setText("consumables: " + car.getConsumablesCount());
    }
}
