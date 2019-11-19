package com.plasius.mystuder.model;

import androidx.annotation.NonNull;
import androidx.room.*;

@Entity(tableName = "subjects")
public class Subject {

    @PrimaryKey @NonNull
    private String name;

    @ColumnInfo(name = "last_average")
    private double lastAverage;

    public Subject(String name, double lastAverage){
        this.name = name;
        this.lastAverage = lastAverage;
    }

    public double getLastAverage() {
            return lastAverage;
    }

    public String getName() {
        return name;
    }

    public void setLastAverage(double lastAverage) {
        this.lastAverage = lastAverage;
    }

    public void setName(String name) {
        this.name = name;
    }
}
