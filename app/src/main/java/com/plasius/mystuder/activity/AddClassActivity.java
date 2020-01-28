package com.plasius.mystuder.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.plasius.mystuder.R;
import com.plasius.mystuder.database.Database;
import com.plasius.mystuder.database.Grade;
import com.plasius.mystuder.database.Subject;
import com.plasius.mystuder.util.Constants;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AddClassActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Add class");
        setContentView(R.layout.activity_add_class);

        TextView textView = findViewById(R.id.class_tv_tracker);
        ((SeekBar)findViewById(R.id.class_sb_order)).setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textView.setText(Integer.toString(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        initSpinners();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    //pick day, pick subject, pick order
    public void onAddClicked(View v){
        String subjectName = ((Spinner)findViewById(R.id.class_sp_subjects))
                .getSelectedItem().toString();
        if(subjectName.isEmpty())
            return;

        String dayName = ((Spinner)findViewById(R.id.class_sp_days)).getSelectedItem().toString();
        int order = ((SeekBar)findViewById(R.id.class_sb_order)).getProgress();

        //save new grade
        Database.getInstance(this).classDAO().insertClass(
                new com.plasius.mystuder.database.Class(subjectName, dayName, order));

        //go back
        Toast.makeText(this, "Class added", Toast.LENGTH_SHORT).show();
        finish();
    }

    private void initSpinners(){
        //subjects
        Spinner spinner = findViewById(R.id.class_sp_subjects);

        List< Subject > subjects = Database.getInstance(this).subjectDAO().getSubjects();
        List<String> list = new ArrayList<String>();
        subjects.forEach(
                obj ->{
                    list.add(obj.getName());
                }
        );

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);


        //days
        spinner = findViewById(R.id.class_sp_days);
        dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, Constants.DAYS_OF_THE_WEEK);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

    }

}
