package com.formakidov.sparecalendar;

import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;

import com.formakidov.sparecalendar.activity.ConsumablesActivity;
import com.formakidov.sparecalendar.activity.MainActivity;
import com.formakidov.sparecalendar.db.repository.CarsRepository;
import com.formakidov.sparecalendar.model.Car;

import java.sql.SQLException;
import java.util.List;

public class NotificationService extends IntentService {

    public NotificationService() {
        super("NotificationService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d("logf", "onHandleIntent: ");
        // TODO: 02.03.2016 styling notification
        if (intent == null) return;
        Context context = getApplicationContext();
        long carId;
        try {
            carId = getCarId(context);
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }

        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.drawable.ic_menu_camera)
                        .setContentTitle("Обновите километраж");

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

        int notificationId = 18041996; // TODO: 02.03.2016

        NotificationManagerCompat.from(context).notify(notificationId, builder.build());
    }

    private long getCarId(Context context) throws SQLException {
        // TODO: 02.03.2016 logic
        CarsRepository repo = new CarsRepository(context);
        List<Car> cars = repo.queryAll();
        if (cars.size() > 0) {
            return cars.get(0).getId();
        } else {
            return 0;
        }
    }
}
