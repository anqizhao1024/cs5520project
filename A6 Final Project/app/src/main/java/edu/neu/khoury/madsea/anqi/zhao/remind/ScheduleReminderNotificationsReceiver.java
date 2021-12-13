package edu.neu.khoury.madsea.anqi.zhao.remind;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.util.List;

import edu.neu.khoury.madsea.anqi.zhao.source.TaskDao;
import edu.neu.khoury.madsea.anqi.zhao.vo.Task;


public class ScheduleReminderNotificationsReceiver extends BroadcastReceiver {

    private static final String TAG = ScheduleReminderNotificationsReceiver.class.toString();

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "Running ScheduleReminderNotificationReceiver");
        List<Task> todos = TaskDao.queryTask();
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}