package com.tudok.mystuder.database;

import androidx.room.*;

@Entity(tableName = "grades")
public class Grade {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "subject_id")
    private String subject_id;

    @ColumnInfo(name = "date")
    private long date;

    @ColumnInfo(name = "value")
    private int value;

    public Grade(String subject_id, long date, int value){
        this.subject_id = subject_id;
        this.date = date;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public long getDate() {
        return date;
    }

    public String getSubject_id() {
        return subject_id;
    }

    public int getValue() {
        return value;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public void setSubject_id(String subject_id) {
        this.subject_id = subject_id;
    }

    public void setValue(int value) {
        this.value = value;
    }
}

