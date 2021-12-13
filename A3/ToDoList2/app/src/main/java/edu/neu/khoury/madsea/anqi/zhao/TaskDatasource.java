package edu.neu.khoury.madsea.anqi.zhao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TaskDatasource extends SQLiteOpenHelper {

    private static String DB_NAME = "myTask.db";

    private static int DB_VERSION = 5;

    public static String TASK_TABLE = "task";

    public static String FR_TASKTITLE_COL = "taskTitle";

    public static String FR_DETAIL_COL = "details";

    public static String FR_TAG_COL = "tag";

    public static String FR_DEALLINE_COL = "deadLine";

    public static String FR_ISREMIND_COL = "isRemind";

    public static String FR_REMINDTIMES_COL = "rmindTimes";

    public TaskDatasource(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TASK_TABLE + "(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT,  " +
                FR_TASKTITLE_COL + " TEXT, " +
                FR_DETAIL_COL + " TEXT, " +
                FR_TAG_COL + " TEXT, " +
                FR_DEALLINE_COL + " TEXT, " +
                FR_ISREMIND_COL + " TEXT, " +
                FR_REMINDTIMES_COL + " TEXT );");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TASK_TABLE);
        onCreate(db);
    }
}
