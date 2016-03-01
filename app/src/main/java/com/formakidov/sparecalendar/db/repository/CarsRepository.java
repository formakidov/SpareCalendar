package com.formakidov.sparecalendar.db.repository;

import android.content.Context;

import com.formakidov.sparecalendar.model.Car;

public class CarsRepository extends BaseRepository<Car> {
    // TODO: 27.02.2016 make singleton
    public CarsRepository(Context context) {
        super(context);
    }
}
