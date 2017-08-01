package ganhuo.ly.com.ganhuo.data.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public  class TasksManagerDBOpenHelper extends SQLiteOpenHelper {
        public final static String DATABASE_NAME = "freebook.db";
        public final static int DATABASE_VERSION = 2;

        public TasksManagerDBOpenHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }


        @Override
        public void onCreate(SQLiteDatabase db) {

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//            if (oldVersion == 1 && newVersion == 2) {
//                db.delete(TasksManagerDBController.TABLE_NAME, null, null);
//            }
        }
    }