package com.basketball.mybasketball;

import android.arch.persistence.room.*;

import java.util.List;

@Dao
public interface TeamDao {
    @Query("SELECT * FROM Team")
    List<Team> getAll();

    @Insert
    void insertAll(Team... teams);

    @Insert
    void insert(Team team);

    @Update
    void updateTeams(Team... teams);

    @Update
    void updateTeam(Team team);

    @Delete
    void delete(Team team);
}
