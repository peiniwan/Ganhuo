package ganhuo.ly.com.ganhuo.data.db;

import android.database.sqlite.SQLiteDatabase;

import ganhuo.ly.com.ganhuo.MyApplication;


public class TasksManagerDBController {
    public final static String TABLE_NAME = "freebook";
    public final SQLiteDatabase db;

    public TasksManagerDBController() {
        TasksManagerDBOpenHelper openHelper = new TasksManagerDBOpenHelper(MyApplication.getInstance());

        db = openHelper.getWritableDatabase();
    }
}