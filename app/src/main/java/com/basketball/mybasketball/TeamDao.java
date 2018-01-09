package com.basketball.mybasketball;

import android.arch.persistence.room.*;

import java.util.List;

@Dao
public interface TeamDao {
    @Query("SELECT * FROM Team")
    List<Team> getAll();

    @Insert
    void insertAll(Team... teams);

    @Update
    void updateUsers(Team... teams);

    @Delete
    void delete(Team team);
}
