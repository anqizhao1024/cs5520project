package edu.neu.khoury.madsea.anqi.zhao.remind;


import android.content.Context;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import java.util.List;

import edu.neu.khoury.madsea.anqi.zhao.source.TaskDao;
import edu.neu.khoury.madsea.anqi.zhao.vo.Task;

public class ScheduleReminderNotificationsWorker extends Worker {

    final static String TAG = "WORKER--";
    private Context mContext;


    public ScheduleReminderNotificationsWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        mContext = context;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @NonNull
    @Override
    public Result doWork() {
        Log.i(TAG, "Running ScheduleReminderNotificationWorker");
        List<Task> tasks =    TaskDao.queryTask( );
        for (Task task : tasks) {
            TaskReminderManager.scheduleNotificationForTodo(getApplicationContext(), task);
        }
        return Result.success();
    }
}
