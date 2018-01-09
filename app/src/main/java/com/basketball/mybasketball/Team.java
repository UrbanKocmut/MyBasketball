package com.basketball.mybasketball;

import android.arch.persistence.room.*;

@Entity
public class Team {
    @PrimaryKey
    private int eid;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "score")
    private int score;


    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }


    // Getters and setters are ignored for brevity,
    // but they're required for Room to work.
}
