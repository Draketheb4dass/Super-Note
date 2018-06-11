package com.realty.drake.supernote;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //TODO fix focus

    ArrayList<String> todoItems;
    ArrayAdapter<String> aTodoAdapter;
    ListView lvItems;
    EditText etEditText;
    private final int REQUEST_CODE =123;


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

        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getApplicationContext(),EditItemActivity.class);
                // put "extras" into the bundle for access in the second activity
                i.putExtra("textBody", todoItems.get(position));
                i.putExtra("code", 123);
                i.putExtra("position", position);
                startActivityForResult(i, REQUEST_CODE);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // REQUEST_CODE is defined above
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            // Extract name value from result extras
            String textBody = data.getExtras().getString("textBody");
            //int code = data.getExtras().getInt("code", 0);
            int position = data.getExtras().getInt("position");
            todoItems.set(position, textBody);
            aTodoAdapter.notifyDataSetChanged();
            writeItems();
        }
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
        if (!etEditText.getText().toString().equals("")) { //If field is empty reject adding
            aTodoAdapter.add(etEditText.getText().toString());
            etEditText.setText("");
            writeItems();
        }else Toast.makeText(this, "Text field is empty ", Toast.LENGTH_SHORT).show();
    }
}
