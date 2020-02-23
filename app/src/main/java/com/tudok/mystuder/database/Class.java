package com.tudok.mystuder.database;

import androidx.room.*;

@Entity(tableName = "classes")
public class Class {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "day_id")
    private String day_id;

    @ColumnInfo(name = "order")
    private String order;

    @ColumnInfo(name = "subject_id")
    private String subject_id;

    public Class(String subject_id, String day, String order){
        this.subject_id = subject_id;
        this.order = order;
        this.day_id = day;
    }

    public Class(){}


    public int getId() {
        return id;
    }

    public String getOrder() {
        return order;
    }

    public String getDay_id() {
        return day_id;
    }

    public String getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(String subject_id) {
        this.subject_id = subject_id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDay_id(String day_id) {
        this.day_id = day_id;
    }

    public void setOrder(String order) {
        this.order = order;
    }
}

