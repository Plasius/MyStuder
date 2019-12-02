package com.plasius.mystuder.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ClassDAO {

    @Query("SELECT * from classes")
    List<Class> getClasses();

    @Query("SELECT * FROM classes WHERE classes.day_id = :day_id")
    List<Class> getClassesByDay(String day_id);

    @Insert
    void insertClass(Class classy);

    @Delete
    void deleteClass(Class classy);

    @Query("DELETE FROM classes")
    void deleteClasses();

    @Query("DELETE FROM classes WHERE classes.day_id = :day_id")
    void deleteClassesByDay(String day_id);

    @Update
    void updateClass(Class classy);


}
