package com.basketball.mybasketball;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.SharedPreferences;


public class App extends Application {


    public static App INSTANCE;
    private static final String DATABASE_NAME = "TeamsDB";

    private AppDatabase db;

    public static App get() {
        return INSTANCE;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        // create database
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, DATABASE_NAME).build();


        INSTANCE = this;
    }

    public AppDatabase getDB() {
        return db;
    }

}
