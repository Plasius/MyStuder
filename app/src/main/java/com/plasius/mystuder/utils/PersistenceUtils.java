package com.plasius.mystuder.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.plasius.mystuder.R;

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
}
