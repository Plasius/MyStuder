package com.tudok.mystuder.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface SubjectDAO {
    @Query("SELECT * from subjects")
    List<Subject> getSubjects();

    @Query("SELECT * from subjects WHERE name = :subject_name")
    Subject getSubjectById(String subject_name);

    @Insert
    void insertSubject(Subject subject);
    @Delete
    void deleteSubject(Subject subject);
    @Update
    void updateSubject(Subject subject);

    @Query("DELETE FROM subjects")
    void deleteSubjects();

    @Query("DELETE FROM subjects WHERE subjects.name = :subject_id")
    void deleteSubjectByName(String subject_id);
}
