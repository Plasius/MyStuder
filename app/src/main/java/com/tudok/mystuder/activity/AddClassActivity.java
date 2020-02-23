package com.tudok.mystuder.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.tudok.mystuder.R;
import com.tudok.mystuder.database.Database;
import com.tudok.mystuder.database.Subject;
import com.tudok.mystuder.util.Constants;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class AddClassActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getString(R.string.add_class));
        setContentView(R.layout.activity_add_class);




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
        String order = String.format("%02d:%02d", ((TimePicker)findViewById(R.id.class_tp_time)).getHour(), ((TimePicker)findViewById(R.id.class_tp_time)).getMinute());

        //save new class
        Database.getInstance(this).classDAO().insertClass(
                new com.tudok.mystuder.database.Class(subjectName, dayName, order));

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


        ArrayList<String> days = new ArrayList<>();
        days.add(getString(R.string.monday));
        days.add(getString(R.string.tuesday));
        days.add(getString(R.string.wednesday));
        days.add(getString(R.string.thursday));
        days.add(getString(R.string.friday));
        days.add(getString(R.string.saturday));
        days.add(getString(R.string.sunday));
        //days
        spinner = findViewById(R.id.class_sp_days);
        dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, days);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

    }

}
