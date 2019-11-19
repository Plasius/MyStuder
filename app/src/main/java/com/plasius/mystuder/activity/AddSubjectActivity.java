package com.plasius.mystuder.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.plasius.mystuder.R;
import com.plasius.mystuder.controller.Database;
import com.plasius.mystuder.model.Grade;
import com.plasius.mystuder.model.Subject;

import java.util.Calendar;
import java.util.Date;

public class AddSubjectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subject);

        //get subjects from db
        //display subjects

    }

    public void onAddClicked(View v){
        String subjectname = ((EditText)findViewById(R.id.subject_et_name)).getText().toString();
        double laverage = Double.parseDouble(((EditText)findViewById(R.id.subject_et_laverage)).getText().toString());

        //save new subject
        Database.getInstance(this).subjectDAO().insertSubject(new Subject(subjectname, laverage));
        //DEBUG
        Database.getInstance(this).gradeDAO().insertGrade(new Grade(subjectname, Calendar.getInstance().getTimeInMillis(), 9));

        //go back
        Toast.makeText(this, "Subject added", Toast.LENGTH_SHORT).show();
        finish();
    }

}
