package edu.neu.khoury.madsea.anqi.zhao.remind;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class TaskReminderNotificationReceiver extends BroadcastReceiver {

    public static String TASK_ID = "TASK_ID";
    public static String TASK_TITLE = "TASK_TITLE";
    public static String TASK_DETAIL = "TASK_DETAIL";

    @Override
    public void onReceive(Context context, Intent intent) {
        int todoId = intent.getIntExtra(TASK_ID, -1);
        String todoTitle = intent.getStringExtra(TASK_TITLE);
        String detail = intent.getStringExtra(TASK_DETAIL);
        if (todoId != -1) {
            NotificationManager.sendNotification(context, todoId, todoTitle, detail);
        }
    }
}