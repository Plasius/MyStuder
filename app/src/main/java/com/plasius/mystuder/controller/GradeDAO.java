package com.plasius.mystuder.controller;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.plasius.mystuder.model.Grade;

import java.util.List;

@Dao
public interface GradeDAO {

    @Query("SELECT * from grades")
    List<Grade> getGrades();
    @Insert
    void insertGrade(Grade grade);
    @Delete
    void deleteGrade(Grade grade);
    @Update
    void updateGrade(Grade grade);
}
