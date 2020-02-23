package com.tudok.mystuder.database;

import androidx.annotation.NonNull;
import androidx.room.*;

@Entity(tableName = "subjects")
public class Subject {

    @PrimaryKey @NonNull
    private String name;

    @ColumnInfo(name = "last_average")
    private double lastAverage;

    @ColumnInfo(name = "goal_average")
    private double goalAverage;

    @ColumnInfo(name = "is_real")
    private boolean isReal;

    public Subject(String name, double lastAverage, double goalAverage, boolean isReal){
        this.name = name;
        this.lastAverage = lastAverage;
        this.goalAverage = goalAverage;
        this.isReal = isReal;
    }

    public double getGoalAverage() {
        return goalAverage;
    }

    public void setGoalAverage(double goalAverage) {
        this.goalAverage = goalAverage;
    }

    public void setReal(boolean real) {
        isReal = real;
    }


    public boolean getReal(){
        return isReal;
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
