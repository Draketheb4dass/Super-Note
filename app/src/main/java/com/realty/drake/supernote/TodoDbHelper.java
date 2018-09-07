package com.realty.drake.supernote;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.realty.drake.supernote.TodoContract.TodoEntry;

public class TodoDbHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION =1;
    private static final String DATABASE_NAME = "todo.db";

    public TodoDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_TODO_TABLE = "CREATE TABLE " + TodoEntry.TABLE_NAME + "("
                + TodoEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TodoEntry.COLUMN_TODO_NOTE_TITLE + " TEXT NOT NULL, "
                + TodoEntry.COLUMN_TODO_NOTE_BODY + " TEXT NOT NULL);";
        db.execSQL(SQL_CREATE_TODO_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

    //This method is called when adding an item to the database
    public void onAddItem(String noteTitle, String noteBody){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TodoEntry.COLUMN_TODO_NOTE_TITLE, noteTitle);
        values.put(TodoEntry.COLUMN_TODO_NOTE_BODY, noteBody);
        db.insert(TodoEntry.TABLE_NAME, null, values);
    }

    //This method is called when updating an item
    public void updateItem(long id, String noteTitle, String noteBody) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TodoEntry._ID, id);
        values.put(TodoEntry.COLUMN_TODO_NOTE_TITLE, noteTitle);
        values.put(TodoEntry.COLUMN_TODO_NOTE_BODY, noteBody);
        db.update(TodoEntry.TABLE_NAME, values, "_id = ?",
                new String[]{String.valueOf(id)});
    }

    public void deleteItem(long id) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TodoEntry.TABLE_NAME, "_id = ?", new
                String[]{String.valueOf(id)});
    }

    //Read all the data from the table and put it in a cursor
    public Cursor getWordList() {
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT " + TodoEntry._ID + ", "  + TodoEntry.COLUMN_TODO_NOTE_TITLE + ", "
                + TodoEntry.COLUMN_TODO_NOTE_BODY
                + " FROM " + TodoEntry.TABLE_NAME + " ORDER BY " + TodoEntry._ID +
                " ASC";
        return db.rawQuery(query, null);
    }
}
