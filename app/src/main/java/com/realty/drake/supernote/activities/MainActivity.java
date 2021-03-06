package com.realty.drake.supernote.activities;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.realty.drake.supernote.R;
import com.realty.drake.supernote.TodoContract.TodoEntry;
import com.realty.drake.supernote.TodoDbHelper;
import com.realty.drake.supernote.adapters.NoteCursorAdapter;

public class MainActivity extends AppCompatActivity {

    //TODO (Suggested) Add support for completion due dates for todo items (and display within listview item)
    //TODO (Suggested) Use a DialogFragment instead of new Activity for editing items
    //TODO Add support for selecting the priority of each todo item (and display in listview item)
    //TODO Anything else that you can get done to improve the app functionality or user experience!

    ListView lvItems;
    private final int REQUEST_CODE =123;
    private TodoDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDbHelper = new TodoDbHelper(this);
        //updateWordList();

        // Get access to the underlying writable database
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        // Query for items from the database and get a cursor back
        Cursor noteCursor = db.rawQuery("SELECT  * FROM todo", null);
        lvItems = findViewById(R.id.lvItems);
        // Create the adapter to convert the array to views
        NoteCursorAdapter adapter = new NoteCursorAdapter(this, noteCursor);
        // Attach the adapter to a ListView
        lvItems.setAdapter(adapter);

        //This method remove an item when long clicked
        lvItems.setOnItemLongClickListener((parent, view, position, id) -> {
                    mDbHelper.deleteItem(id);
                    Cursor noteCursor2 = db.rawQuery("SELECT  * FROM todo", null);
                    adapter.changeCursor(noteCursor2);
            Toast.makeText(this, "Deleted!", Toast.LENGTH_SHORT).show();
                    return true;
                });

        //call Edit Activity
        lvItems.setOnItemClickListener((parent, view, position, id) -> {
            Intent i = new Intent(getApplicationContext(),EditItemActivity.class);
            // put "extras" into the bundle for access in the second activity
            i.putExtra("textBody", getItemTodo(id));
            i.putExtra("code", 123);
            i.putExtra("id", id);
            startActivityForResult(i, REQUEST_CODE);
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(v -> {
            saveRecord();
            Cursor noteCursor2 = db.rawQuery("SELECT  * FROM todo", null);
            adapter.changeCursor(noteCursor2);
        });

    }

   // @Override
   // protected void onActivityResult(int requestCode, int resultCode, Intent data) {
   //     // REQUEST_CODE is defined above
   //     if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
   //         // Extract name value from result extras
   //         String textBody = data.getExtras().getString("textBody");
   //         long id = data.getExtras().getLong("id");
   //         mDbHelper.updateItem(id, textBody);
   //         etEditText.setText("");
   //         updateWordList();
   //     }
   // }


    private void saveRecord() { mDbHelper.onAddItem("Test", "Passed");}

    public String getItemTodo(long id) {
        String returnVal = "";
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT "
                        + TodoEntry.COLUMN_TODO_NOTE_TITLE
                        + TodoEntry.COLUMN_TODO_NOTE_BODY
                        + " FROM " + TodoEntry.TABLE_NAME
                        + " WHERE _id = ?", new String[]{String.valueOf(id)});
        if (cursor.getCount() == 1) {
            cursor.moveToFirst();
            returnVal = cursor.getString(0);
        }
        cursor.close();
        return returnVal;
    }


}
