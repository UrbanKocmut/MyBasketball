package com.basketball.mybasketball;

import android.arch.persistence.room.*;

import java.net.URI;

@Entity
public class Team {
    @PrimaryKey(autoGenerate = true)
    private int eid;

    @ColumnInfo(name = "img")
    private String img;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "score")
    private int score;

    public Team() {
        this.name = "Janezi";
        this.score = 0;
        this.img = "?";
    }

    public Team(String name) {
        this.name = name;
        this.score = 0;
        this.img = "?";
    }

    public Team(String name, String img) {
        this.name = name;
        this.score = 0;
        this.img = img;
    }

    int getEid() {
        return eid;
    }

    void setEid(int eid) {
        this.eid = eid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    int getScore() {
        return score;
    }

    String getStringScore() {
        return "" + score;
    }

    void setScore(int score) {
        this.score = score;
    }

    String getImg() {
        return img;
    }

    void setImg(String img) {
        this.img = img;
    }

    void setImg(URI img) {
        this.img = img.toString();
    }


    // Getters and setters are ignored for brevity,
    // but they're required for Room to work.
}
