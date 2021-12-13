package edu.neu.khoury.madsea.anqi.zhao.remind;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;


public class AlertReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManager.sendNotification(context,1 ,"Alarm!","Your AlarmManager is working.");
    }
}