package com.tudok.mystuder.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.tudok.mystuder.R;
import com.tudok.mystuder.database.Database;
import com.tudok.mystuder.database.Grade;
import com.tudok.mystuder.database.Subject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AddGradeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Add grade");
        setContentView(R.layout.activity_add_grade);
        initSpinner();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void onAddClicked(View v){
        String subjectName = ((Spinner)findViewById(R.id.grades_sp_subjects)).getSelectedItem().toString();
        if(subjectName.isEmpty())
            return;
        int value = Integer.parseInt(((EditText)findViewById(R.id.grades_et_grade)).getText().toString());

        if(value<1 || value >10){
            Toast.makeText(this, "Sorry, that's not a valid entry.", Toast.LENGTH_SHORT).show();
            return;
        }

        DatePicker datePicker = findViewById(R.id.grades_dp_date);
        Calendar calendar = Calendar.getInstance();
        calendar.set(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth());
        Date date = calendar.getTime();

        //save new grade
        Database.getInstance(this).gradeDAO().insertGrade(new Grade(subjectName, date.getTime(), value));

        //go back
        Toast.makeText(this, "Grade added", Toast.LENGTH_SHORT).show();
        finish();
    }

    private void initSpinner(){
        Spinner spinner = findViewById(R.id.grades_sp_subjects);

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
    }

}
