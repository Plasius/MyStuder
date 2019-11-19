package com.plasius.mystuder.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.plasius.mystuder.R;
import com.plasius.mystuder.SettingsActivity;
import com.plasius.mystuder.utils.Constants;
import com.plasius.mystuder.utils.PersistenceUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //decide whether a register is necessary
        if(PersistenceUtils.getInstance(this).getBoolean(Constants.STATE_FIRST_LAUNCHED, true)){
            startActivity(new Intent(this, SettingsActivity.class));
            finish();
        }
    }

    public void onSubjectsClicked(View view){
        startActivity(new Intent(this, SubjectsActivity.class));
    }
}
