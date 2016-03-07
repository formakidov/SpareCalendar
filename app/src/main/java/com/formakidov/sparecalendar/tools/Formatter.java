package com.formakidov.sparecalendar.tools;

import android.content.Context;
import android.content.res.Resources;

import com.formakidov.sparecalendar.Application;
import com.formakidov.sparecalendar.Constants;
import com.formakidov.sparecalendar.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Formatter {
    private static SimpleDateFormat lastUpdateFormat = new SimpleDateFormat("dd MMM yyyy HH:mm:ss");

    public static String formatParameterValue(Context context, String s, int resId) {
        return !s.isEmpty() ? s : context.getString(resId);
    }

    public static String formatGearboxType(int gearboxType) {
        Resources res = Application.getContext().getResources();
        String typeName;
        switch (gearboxType) {
            case Constants.GEARBOX_TYPE_MECHANIC:
                typeName = res.getString(R.string.gearbox_type_mechanic);
                break;
            case Constants.GEARBOX_TYPE_AUTOMATIC:
                typeName = res.getString(R.string.gearbox_type_automatic);
                break;
            case Constants.GEARBOX_TYPE_ROBOTIC:
                typeName = res.getString(R.string.gearbox_type_robotic);
                break;
            default:
                typeName = res.getString(R.string.gearbox_type_mechanic);
                break;
        }
        return typeName;
    }

    public static String formatLastUpdate(long lastUpdate) {
        return lastUpdateFormat.format(new Date(lastUpdate));
    }

    public static String formatMileage(long mileage) {
        return String.valueOf(mileage);
    }
}