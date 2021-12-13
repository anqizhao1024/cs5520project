package edu.neu.khoury.madsea.anqi.zhao.source;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import edu.neu.khoury.madsea.anqi.zhao.vo.Task;

public class TaskDao {
    /**
     * add a task to sqlite
     *
     * @param taskDatasource
     * @param task
     * @return
     */
    public static boolean addTask(TaskDatasource taskDatasource, Task task) {
        SQLiteDatabase sqLiteDatabase = taskDatasource.getWritableDatabase();
        ContentValues cValue = new ContentValues();
        cValue.put(TaskDatasource.FR_TASKTITLE_COL, task.getTaskTitle());
        cValue.put(TaskDatasource.FR_DETAIL_COL, task.getDetails());
        cValue.put(TaskDatasource.FR_TAG_COL, task.getTag());
        cValue.put(TaskDatasource.FR_DEALLINE_COL, task.getDealLine());
        cValue.put(TaskDatasource.FR_ISREMIND_COL, task.getIsRemind());
        cValue.put(TaskDatasource.FR_REMINDTIMES_COL, task.getRemindTimes());
        return sqLiteDatabase.insert(TaskDatasource.TASK_TABLE, null, cValue) > 0;
    }

    /**
     * edit task
     *
     * @param taskDatasource
     * @param task
     * @return
     */
    public static boolean editTask(TaskDatasource taskDatasource, Task task) {
        SQLiteDatabase sqLiteDatabase = taskDatasource.getWritableDatabase();
        ContentValues cValue = new ContentValues();
        cValue.put(TaskDatasource.FR_TASKTITLE_COL, task.getTaskTitle());
        cValue.put(TaskDatasource.FR_DETAIL_COL, task.getDetails());
        cValue.put(TaskDatasource.FR_TAG_COL, task.getTag());
        cValue.put(TaskDatasource.FR_DEALLINE_COL, task.getDealLine());
        cValue.put(TaskDatasource.FR_ISREMIND_COL, task.getIsRemind());
        cValue.put(TaskDatasource.FR_REMINDTIMES_COL, task.getRemindTimes());
        String arr[] = {task.get_id() + ""};
        return sqLiteDatabase.update(TaskDatasource.TASK_TABLE, cValue, "_id=?", arr) > 0;
    }

    /**
     * delete a task by task id
     *
     * @param taskDatasource
     * @param taskId
     * @return
     */
    public static boolean deleteTask(TaskDatasource taskDatasource, int taskId) {
        SQLiteDatabase sqLiteDatabase = taskDatasource.getWritableDatabase();
        String arr[] = {taskId + ""};
        return sqLiteDatabase.delete(TaskDatasource.TASK_TABLE, "_id=?", arr) > 0;
    }


    /**
     * query all task
     *
     * @param taskDatasource
     * @return
     */
    public static List<Task> queryTask(TaskDatasource taskDatasource) {
        List<Task> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = taskDatasource.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.query(TaskDatasource.TASK_TABLE, null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            Task task = new Task(cursor.getInt(0), cursor.getString(1)
                    , cursor.getString(2), cursor.getString(3)
                    , cursor.getString(4), cursor.getString(5)
                    , cursor.getString(6));
            list.add(task);
        }
        return list;
    }

}
