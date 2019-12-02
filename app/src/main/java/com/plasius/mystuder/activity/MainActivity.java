package com.plasius.mystuder.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.plasius.mystuder.R;
import com.plasius.mystuder.util.Constants;
import com.plasius.mystuder.util.PersistenceUtils;

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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void onSubjectsClicked(View view){
        startActivity(new Intent(this, SubjectsActivity.class));
    }

    public void onScheduleClicked(View v){
        startActivity(new Intent(this, ScheduleActivity.class));
    }
}
