package com.plasius.mystuder.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.plasius.mystuder.database.User;

public class PersistenceUtils {
    private SharedPreferences preferences;
    private static PersistenceUtils instance;

    public static PersistenceUtils getInstance(Activity context){
        if(instance != null)
        return instance;

        instance = new PersistenceUtils(context);
        return instance;
    }

    public PersistenceUtils(Activity context){
        preferences = context.getPreferences(Context.MODE_PRIVATE);
    }
    public boolean getBoolean(String key, boolean defaultValue){
        return preferences.getBoolean(key, defaultValue);
    }

    public void setBoolean(String key, boolean value){
        preferences.edit().putBoolean(key, value).commit();
    }

    public void saveUser(User user){
        String json = user == null ? null : new Gson().toJson(user);
        preferences.edit().putString("user", json).apply();
    }

    public User getUser(){
        String json = preferences.getString("user", null);
        return json == null ? null : new Gson().fromJson(json, User.class);
    }
}
