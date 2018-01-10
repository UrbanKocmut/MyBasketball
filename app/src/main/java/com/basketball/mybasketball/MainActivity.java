package com.basketball.mybasketball;

import android.Manifest;
import android.app.Activity;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

    TextView name;
    String owner;
    public static final String PREFS_NAME = "MY_NAME";
    public static final String PREFS_KEY = "owner";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        owner = settings.getString(PREFS_KEY, "by Urban Kocmut");


        setContentView(R.layout.activity_main);

        final Button Ekipe = findViewById(R.id.EkipeBtn);
        Ekipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeActivity(TeamsActivity.class);
            }
        });

        name = (TextView) findViewById(R.id.appOwner);
        name.setText(owner);

        final Button Tekma = findViewById(R.id.TekmaBtn);
        Tekma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeActivity(Match.class);
            }
        });

        final Button setOwner = findViewById(R.id.setOwner);
        setOwner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeOwner();
            }
        });


    }

    @Override
    protected void onStop(){
        super.onStop();

        // We need an Editor object to make preference changes.
        // All objects are from android.context.Context
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(PREFS_KEY, owner);

        // Commit the edits!
        editor.apply();
    }

    void changeOwner(){
        Intent i = new Intent(this, ChangeName.class);
        i.putExtra("NAME",owner);
        startActivityForResult(i, 1);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
                String strName = data.getStringExtra("NAME");
                owner = strName;
                name.setText(strName);
            }
        }
    }



    void changeActivity(Class activity ){
        Intent intent = new Intent(this,activity);
        startActivity(intent);
    }


}
