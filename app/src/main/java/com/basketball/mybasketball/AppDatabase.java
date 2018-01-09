package com.basketball.mybasketball;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {Team.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TeamDao teamDao();

}
