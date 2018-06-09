package com.realty.drake.supernote;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> todoItems;
    ArrayAdapter<String> aTodoAdapter;
    ListView lvItems;
    EditText etEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        populateArrayItems();
        lvItems = findViewById(R.id.lvItems);
        lvItems.setAdapter(aTodoAdapter);
        etEditText = findViewById(R.id.etEditText);

        //This method remove an item when item is long pressed
        lvItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                todoItems.remove(position);
                aTodoAdapter.notifyDataSetChanged();
                writeItems();
                return true;
            }
        });

    }

    //This method populate the view with input data
    public void populateArrayItems() {
        Log.i("debug", "populateArrayItem");
        readItems();
        aTodoAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, todoItems);



    }

    public void readItems() {
        Log.i("debug", "readItem");
        File filesDir = getFilesDir();
        File file = new File(filesDir, "todo.txt");
        try{
            todoItems = new ArrayList<String>(FileUtils.readLines(file));
        }catch (IOException e){
            todoItems = new ArrayList<String>();

        }


    }

    public void writeItems() {
        Log.i("debug", "writeItem");
        File filesDir = getFilesDir();
        File file = new File(filesDir, "todo.txt");
        try{
            FileUtils.writeLines(file, todoItems);
        }catch (IOException e){
            e.printStackTrace();
        }


    }

    //This method add item to the Adapter
    public void onAddItem(View view) {
        Log.i("debug", "onAddItem");
        aTodoAdapter.add(etEditText.getText().toString());
        etEditText.setText("");
        writeItems();
    }
}
