package com.formakidov.sparecalendar;

import android.app.IntentService;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.TaskStackBuilder;

import com.formakidov.sparecalendar.activity.ConsumablesActivity;
import com.formakidov.sparecalendar.activity.MainActivity;
import com.formakidov.sparecalendar.db.repository.CarsRepository;
import com.formakidov.sparecalendar.db.repository.ConsumablesRepository;
import com.formakidov.sparecalendar.model.Car;

import java.sql.SQLException;
import java.util.List;

public class NotificationService extends IntentService {

    public NotificationService() {
        super("NotificationService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        // TODO: 02.03.2016 styling notification
        Context context = getApplicationContext();
        long carId;
        try {
            carId = getCarId(context);
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }

        Notification notification;
        if (carId == -1) {
            notification = getNewCarNotification(context);
        } else {
            notification = getUpdateNotification(context, carId);
        }

        NotificationManagerCompat.from(context).notify(Constants.NOTIFICATION_ID, notification);
    }

    private static Notification getNewCarNotification(Context context) {
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.drawable.ic_menu_camera)
                        .setContentTitle("Add car");

        Intent resultIntent = new Intent(context, MainActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addNextIntent(resultIntent);

        PendingIntent contentIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);

        return builder.build();
    }

    private static Notification getUpdateNotification(Context context, long carId) {
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.drawable.ic_menu_camera)
                        .setContentTitle("Update mileage");

        try {
            ConsumablesRepository repo = new ConsumablesRepository(context);
            boolean hasStale = repo.hasStaleConsumables();
            if (hasStale) {
                builder.setContentText("Also you should replace some consumables");
            }
        } catch (SQLException ignored) { }

        Intent resultIntent = new Intent(context, ConsumablesActivity.class);
        resultIntent.putExtra(Constants.EXTRA_CAR_ID, carId);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(resultIntent);

        PendingIntent contentIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);

//        PendingIntent remindIntent = ...;
//        builder.addAction(R.drawable.remind_later, "Remind in 15 minutes", remindIntent);
        // TODO: 02.03.2016 watch, auto, glass (NotificationCompat.WearableExtender & NotificationCompat.AutoExtender)

        return builder.build();
    }

    private static long getCarId(Context context) throws SQLException {
        CarsRepository repo = new CarsRepository(context);
        List<Car> cars = repo.queryAll();
        if (cars.size() > 0) {
            return cars.get(0).getId();
        } else {
            return -1;
        }
    }
}
