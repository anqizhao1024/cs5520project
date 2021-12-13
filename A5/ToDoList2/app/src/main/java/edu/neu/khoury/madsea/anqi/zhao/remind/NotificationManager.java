package edu.neu.khoury.madsea.anqi.zhao.remind;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import edu.neu.khoury.madsea.anqi.zhao.R;
import edu.neu.khoury.madsea.anqi.zhao.activity.MainActivity;


/***
 * Responsible for creating notifications.
 */
public class NotificationManager {

    private static final String CHANNEL_ID = "TASKChannel";

    private static final String GROUP_KEY_REMINDERS = "tasksGroup";


    public static void sendNotification(Context context, int toDoId, String todoTitle, String detail) {
        // Prepare intent which is triggered if the
        // notification is selected
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(MainActivity.EXTRA_KEY_TODO_ID, toDoId);

        PendingIntent pIntent = PendingIntent.getActivity(
                context,
                (int) System.currentTimeMillis(),
                intent,
                0);

        Notification.Builder notificationBuilder;
        if (Build.VERSION.SDK_INT <= 25) {
            notificationBuilder = new Notification.Builder(context)
                    .setSmallIcon(R.mipmap.icon)
                    .setContentTitle(todoTitle)
                    .setContentText(detail)
                    .setAutoCancel(true)
                    .setContentIntent(pIntent);
        } else {
            notificationBuilder = new Notification.Builder(context, CHANNEL_ID)
                    .setSmallIcon(R.mipmap.icon)
                    .setContentTitle(todoTitle)
                    .setContentText(detail)
                    .setAutoCancel(false)
                    .setGroup(GROUP_KEY_REMINDERS)
                    .setContentIntent(pIntent);
        }

        android.app.NotificationManager notificationManager =
                (android.app.NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0,
                notificationBuilder.build());
    }

    public static void createNotificationChannel(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = context.getString(R.string.app_name);
            String description = context.getString(R.string.app_description);
            int importance = android.app.NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            android.app.NotificationManager notificationManager = context.getSystemService(android.app.NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }


}
