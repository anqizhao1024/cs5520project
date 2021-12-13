package edu.neu.khoury.madsea.anqi.zhao.remind;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.SystemClock;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import java.time.ZoneOffset;
import java.util.concurrent.TimeUnit;

import edu.neu.khoury.madsea.anqi.zhao.vo.Task;
/**
 * This class encapsulates all the tasks needed to keep track of the AlarmManager
 * and reminders and all that jazz.
 */
public class TaskReminderManager {

    private static final int SCHEDULE_REMINDER_ALARM_ID = 0;
    private static final int DO_REMINDER_NOTIFICATION_ID = 1;

    private static final String TAG = TaskReminderManager.class.toString();

    public static boolean isReminderSchedulerSet(Context context) {
        return (PendingIntent.getBroadcast(context,
                SCHEDULE_REMINDER_ALARM_ID,
                new Intent(context, ScheduleReminderNotificationsReceiver.class),
                PendingIntent.FLAG_NO_CREATE) != null);
    }

    public static void scheduleReminderScheduler(Context appContext) {
        Log.i(TAG, "Scheduling reminder scheduler");
        Intent schedulerIntent = new Intent(appContext, ScheduleReminderNotificationsReceiver.class);
        PendingIntent schedulerPendingIntent = PendingIntent.getBroadcast
                (appContext,
                        SCHEDULE_REMINDER_ALARM_ID,
                        schedulerIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT);

//        long repeatInterval = AlarmManager.INTERVAL_HOUR * 3;
        long repeatInterval = AlarmManager.INTERVAL_FIFTEEN_MINUTES * 3;
        long triggerTime = SystemClock.elapsedRealtime()
                + repeatInterval;

        AlarmManager alarmManager = (AlarmManager) appContext.getSystemService(Context.ALARM_SERVICE);
        alarmManager.setInexactRepeating
                (AlarmManager.ELAPSED_REALTIME_WAKEUP,
                        SystemClock.elapsedRealtime(),//triggerTime,
                        repeatInterval,
                        schedulerPendingIntent);

    }

    public static void scheduleReminderWorker(Context appContext) {
        Log.i(TAG, "Scheduling the reminder for the WorkManager");

        WorkManager manager = WorkManager.getInstance(appContext);
        PeriodicWorkRequest request =
                new PeriodicWorkRequest.Builder(
                        ScheduleReminderNotificationsWorker.class,
                        15, // Must be at least 15 minutes
                        TimeUnit.MINUTES)
                        .addTag("reminderScheduler_1")
                        .build();


        manager.enqueueUniquePeriodicWork("reminderScheduler",
                ExistingPeriodicWorkPolicy.REPLACE,
                request); //(request); //OneTimeWorkRequest.from(BlurWorker.class));

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void scheduleNotificationForTodo(Context appContext, Task task) {
        Log.i(TAG, "Scheduling notification for todo ");
        Intent doReminderNotificationIntent = new Intent(appContext, TaskReminderNotificationReceiver.class);
        doReminderNotificationIntent.putExtra(TaskReminderNotificationReceiver.TASK_ID, task.get_id());
        doReminderNotificationIntent.putExtra(TaskReminderNotificationReceiver.TASK_TITLE, task.getTaskTitle());

        PendingIntent schedulerPendingIntent = PendingIntent.getBroadcast
                (appContext,
                        DO_REMINDER_NOTIFICATION_ID,
                        doReminderNotificationIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT);


        //将文字转时间
//        long triggerTime = task.getDealLine().toEpochSecond(ZoneOffset.UTC);
//        Log.i(TAG, "triggerTime: " + triggerTime);


        AlarmManager alarmManager = (AlarmManager) appContext.getSystemService(Context.ALARM_SERVICE);

        alarmManager.setExact(AlarmManager.RTC_WAKEUP,
                System.currentTimeMillis() + 5000, //triggerTime,
                schedulerPendingIntent);
    }

}
