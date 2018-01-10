package com.basketball.mybasketball;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ChangeName extends AppCompatActivity {

    Button set;
    EditText input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_name);

        set = (Button) findViewById(R.id.setName);
        input = (EditText) findViewById(R.id.nameInput);
        String name = getIntent().getStringExtra("NAME");
        input.setText(name);

        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setName();
            }
        });

    }

    private void setName() {
        String newName = input.getText().toString();
        Intent intent = new Intent(this,MainActivity.class);
        intent.putExtra("NAME",newName);
        setResult(RESULT_OK,intent);
        finish();
    }
}
