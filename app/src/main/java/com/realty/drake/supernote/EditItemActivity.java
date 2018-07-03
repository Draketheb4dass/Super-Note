package com.realty.drake.supernote;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EditItemActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        String textBody = getIntent().getStringExtra("textBody");
        //int code = getIntent().getIntExtra("code", 0);
        EditText etBody = (EditText) findViewById(R.id.editText);
        etBody.setText(textBody);
        etBody.setSelection(etBody.getText().length());
        }

    //Called when user click the button and return edited data to MainActivity
    public void onSubmit(View v) {
        EditText etBody = (EditText) findViewById(R.id.editText);
        long id = getIntent().getLongExtra("id", 0);

        if (!etBody.getText().toString().equals("")) { //If field is empty reject adding
            Intent data = new Intent();
            // Pass relevant data back as a result
            data.putExtra("textBody", etBody.getText().toString());
            data.putExtra("code", 123);
            data.putExtra("id", id);
            // Activity finished ok, return the data
            setResult(RESULT_OK, data);
            finish();
        } else Toast.makeText(this, "Text field is empty ", Toast.LENGTH_SHORT).show();
    }
}
