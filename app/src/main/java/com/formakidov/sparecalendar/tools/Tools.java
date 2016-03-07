package com.formakidov.sparecalendar.tools;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationManagerCompat;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

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
        try {
            ConnectivityManager mgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = mgr.getActiveNetworkInfo();
            return networkInfo != null && networkInfo.isAvailable() && networkInfo.isConnected();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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


    public static void hideKeyboard(Activity activity) {
        View currentFocus = activity.getCurrentFocus();
        if (currentFocus != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
        }
    }

    public static void sendEmail(Context context) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{Constants.APP_EMAIL});
        intent.putExtra(Intent.EXTRA_SUBJECT, context.getString(R.string.send_feedback));
        intent.setType("message/rfc822");
        context.startActivity(Intent.createChooser(intent, context.getString(R.string.choose_client)));
    }

    public static void reviewApp(Context context) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("market://details?id=" + context.getPackageName()));
        context.startActivity(intent);
    }

    public static boolean isPreLollipop() {
        return Build.VERSION.SDK_INT < 21;
    }
}