package com.realty.drake.supernote;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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

    //This method is called when adding an item to the database
    public long onAddItem(String item){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TodoEntry.COLUMN_TODO_ITEM, item);
       return db.insert(TodoEntry.TABLE_NAME, null, values);


    }

    //This method is called when updating an item
    public int updateItem(long id, String item) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TodoEntry._ID, id);
        values.put(TodoEntry.COLUMN_TODO_ITEM, item);
        return db.update(TodoEntry.TABLE_NAME, values, "_id = ?",
                new String[]{String.valueOf(id)});
    }

    public int deleteItem(long id) {
        SQLiteDatabase db = getWritableDatabase();
        return db.delete(TodoEntry.TABLE_NAME, "_id = ?", new
                String[]{String.valueOf(id)});
    }

    //Read all the data from the table and put it in a cursor
    public Cursor getWordList() {
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT " + TodoEntry._ID + ", "  + TodoEntry.COLUMN_TODO_ITEM +
                " FROM " + TodoEntry.TABLE_NAME + " ORDER BY " + TodoEntry._ID +
                " ASC";
        return db.rawQuery(query, null);
    }
}
