package com.plasius.mystuder.controller;

import android.content.Context;
import android.provider.ContactsContract;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.plasius.mystuder.model.Grade;
import com.plasius.mystuder.model.Subject;

@androidx.room.Database(entities = {Subject.class, Grade.class}, exportSchema = false, version = 2)
public abstract class Database extends RoomDatabase {
    private static final String DB_NAME = "db";
    private static Database instance;

    public static Database getInstance(Context context) {
        if(instance==null){
            instance = Room.databaseBuilder(context.getApplicationContext(), Database.class, DB_NAME).allowMainThreadQueries().fallbackToDestructiveMigration().build();
        }
        return instance;
    }

    public abstract SubjectDAO subjectDAO();
    public abstract GradeDAO gradeDAO();

    @NonNull
    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }

    @NonNull
    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }

    @Override
    public void clearAllTables() {

    }
}
