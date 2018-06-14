package com.realty.drake.supernote;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.realty.drake.supernote.TodoContract.TodoEntry;

public class TodoDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION =1;
    public static final String DATABASE_NAME = "todo.db";

    TodoDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_TODO_TABLE = "CREATE TABLE " + TodoEntry.TABLE_NAME + "("
                + TodoEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TodoEntry.COLUMN_TODO_ITEM + " TEXT NOT NULL);";
        db.execSQL(SQL_CREATE_TODO_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
