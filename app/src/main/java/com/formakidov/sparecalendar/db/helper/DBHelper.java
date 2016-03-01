package com.formakidov.sparecalendar.db.helper;

import android.content.Context;

import com.formakidov.sparecalendar.model.Car;
import com.formakidov.sparecalendar.model.Consumable;

public class DBHelper extends AbstractDBHelper {
    private static final String TABLE_NAME = "spare_calendar.db";

    public DBHelper(Context context) {
        super(context, TABLE_NAME);
    }

    @Override
    protected Class<?>[] getTableClassList() {
        return new Class<?>[] {Car.class, Consumable.class};
    }
}
