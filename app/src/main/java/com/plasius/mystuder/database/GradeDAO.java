package com.plasius.mystuder.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface GradeDAO {

    @Query("SELECT * from grades")
    List<Grade> getGrades();
    @Query("SELECT * FROM grades WHERE grades.subject_id = :subjectId")
    List<Grade> getGradesBySubject(String subjectId);
    @Insert
    void insertGrade(Grade grade);
    @Delete
    void deleteGrade(Grade grade);
    @Query("DELETE FROM grades")
    void deleteGrades();
    @Update
    void updateGrade(Grade grade);

    @Query("DELETE from grades WHERE grades.subject_id = :subject_id")
    void deleteGradesBySubject(String subject_id);

}
