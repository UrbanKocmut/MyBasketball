package com.basketball.mybasketball;

import android.arch.persistence.room.*;

import java.util.List;

@Dao
public interface IgralecDao {
    @Query("SELECT * FROM Igralec")
    List<Igralec> getAll();

    @Insert
    void insertAll(Igralec... users);

    @Update
    void updateUsers(Igralec... users);

    @Delete
    void delete(Igralec user);
}
