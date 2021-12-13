package edu.neu.khoury.madsea.anqi.zhao.remind;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;


public class AlertReceiver extends BroadcastReceiver {

    private static final String CHANNEL_ID = "TASKChannel";

    private static final String GROUP_KEY_REMINDERS = "tasksGroup";


    @Override
    public void onReceive(Context context, Intent intent) {
//       Task task = (Task) intent.getSerializableExtra("data");
//        NotificationManager.sendNotification(context,task.get_id() ,task.getTaskTitle(),task.getDetails());
        NotificationManager.sendNotification(context,1 ,"Alarm!","Your AlarmManager is working.");
    }
}