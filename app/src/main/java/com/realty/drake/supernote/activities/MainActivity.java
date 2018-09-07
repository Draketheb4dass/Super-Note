package com.realty.drake.supernote.activities;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.realty.drake.supernote.Note;
import com.realty.drake.supernote.R;
import com.realty.drake.supernote.TodoContract.TodoEntry;
import com.realty.drake.supernote.TodoDbHelper;
import com.realty.drake.supernote.activities.EditItemActivity;
import com.realty.drake.supernote.adapters.NoteAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //TODO (Suggested) Improve style of the todo items in the list using a custom adapter
    //TODO (Suggested) Add support for completion due dates for todo items (and display within listview item)
    //TODO (Suggested) Use a DialogFragment instead of new Activity for editing items
    //TODO Add support for selecting the priority of each todo item (and display in listview item)
    //TODO Anything else that you can get done to improve the app functionality or user experience!

    ListView lvItems;
    EditText etEditText;
    private final int REQUEST_CODE =123;
    private TodoDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDbHelper = new TodoDbHelper(this);
        lvItems = findViewById(R.id.lvItems);
        etEditText = findViewById(R.id.etEditText);
        ArrayList<Note> arrayOfNotes = new ArrayList<>();
        // Create the adapter to convert the array to views
        NoteAdapter adapter = new NoteAdapter(this, arrayOfNotes);
        // Attach the adapter to a ListView
        lvItems.setAdapter(adapter);
        //displayDatabaseInfo();
        //updateWordList();
        Note newNote = new Note("Nathan", "San Diego");
        Note newNote2 = new Note("Nathan", "San Diego");
        adapter.add(newNote);
        adapter.add(newNote2);

        //This method remove an item when long clicked
        lvItems.setOnItemLongClickListener((parent, view, position, id) -> {
                    mDbHelper.deleteItem(id);
                    updateWordList();
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

        Button btnOnAdd = findViewById(R.id.btnAddItem);
        btnOnAdd.setOnClickListener(v -> saveRecord());

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // REQUEST_CODE is defined above
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            // Extract name value from result extras
            String textBody = data.getExtras().getString("textBody");
            long id = data.getExtras().getLong("id");
            mDbHelper.updateItem(id, textBody);
            etEditText.setText("");
            updateWordList();
        }
    }


    private void saveRecord() {
        if (!etEditText.getText()
                .toString().equals("")) { //If field is empty reject adding
            mDbHelper.onAddItem(etEditText.getText().toString());
            etEditText.setText("");
            updateWordList();
        }else Toast.makeText(this,
                "Text field is empty ", Toast.LENGTH_SHORT).show();
    }

    private void updateWordList() {
        SimpleCursorAdapter simpleCursorAdapter = new
                SimpleCursorAdapter(
                this,
                android.R.layout.simple_list_item_1,
                mDbHelper.getWordList(),
                new String[]{"item"},
                new int[]{android.R.id.text1},
                0);
        lvItems.setAdapter(simpleCursorAdapter);
    }

    public String getItemTodo(long id) {
        String returnVal = "";
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT "
                        + TodoEntry.COLUMN_TODO_ITEM
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
