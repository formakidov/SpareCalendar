package com.formakidov.sparecalendar.tools;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.v4.app.NotificationManagerCompat;

import com.formakidov.sparecalendar.Application;
import com.formakidov.sparecalendar.Constants;
import com.formakidov.sparecalendar.NotificationService;
import com.formakidov.sparecalendar.R;

public class Tools {

    public static void previousActivityAnimation(Activity activity) {
        activity.finish();
        activity.overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
    }

    public static void nextActivityAnimation(Activity activity) {
        activity.overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
    }

    public static boolean isNetworkAvailable(Context context) {
        return ((ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE))
                .getActiveNetworkInfo() != null;
    }

    public static void resetNotification(Context context){
        Intent intent = new Intent(context, NotificationService.class);
        PendingIntent pendingIntent = PendingIntent.getService(context, 0, intent, 0);
        long triggerAtMillis = System.currentTimeMillis() + 10000;
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(context.ALARM_SERVICE);
        // TODO: 02.03.2016 check on lollipop
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, triggerAtMillis, /*AlarmManager.INTERVAL_DAY*/55000, pendingIntent);
    }

    public static void cancelNotification() {
        NotificationManagerCompat.from(Application.getContext()).cancel(Constants.NOTIFICATION_ID);
    }
}