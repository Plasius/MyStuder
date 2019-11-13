package com.plasius.mystuder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.plasius.mystuder.utils.Constants;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        //display adequate welcoming message
        if(getIntent().getBooleanExtra(Constants.STATE_FIRST_LAUNCHED, true)){
            ((TextView)findViewById(R.id.settings_tv_welcome)).setText(R.string.welcome);
        }else{
            ((TextView)findViewById(R.id.settings_tv_welcome)).setText(R.string.settings);
        }


    }
}
