package com.plasius.mystuder.controller;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.plasius.mystuder.model.Subject;

import java.util.List;

@Dao
public interface SubjectDAO {
    @Query("SELECT * from subjects")
    List<Subject> getSubjects();
    @Insert
    void insertSubject(Subject subject);
    @Delete
    void deleteSubject(Subject subject);
    @Update
    void updateSubject(Subject subject);
    @Query("DELETE FROM subjects")
    void deleteSubjects();
}
