package com.tudok.mystuder.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.tudok.mystuder.R;
import com.tudok.mystuder.database.Database;
import com.tudok.mystuder.database.Subject;

public class AddSubjectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subject);
        getSupportActionBar().setTitle("Add Subject");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //get subjects from db
        //display subjects

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void onAddClicked(View v){
        String subjectname = ((EditText)findViewById(R.id.subject_et_name)).getText().toString();
        double laverage = Double.parseDouble(((EditText)findViewById(R.id.subject_et_laverage)).getText().toString());
        double gaverage = Double.parseDouble(((EditText)findViewById(R.id.subject_et_goal)).getText().toString());
        boolean isReal = ((CheckBox)findViewById(R.id.subject_cb_real)).isChecked();

        if(laverage<0 || laverage>10 || gaverage<1 || gaverage>10){
            Toast.makeText(this, "Please provide accurate values.", Toast.LENGTH_SHORT).show();
            return;
        }

        //save new subject
        Database.getInstance(this).subjectDAO().insertSubject(new Subject(subjectname, laverage, gaverage, isReal));

        //go back
        Toast.makeText(this, "Subject added", Toast.LENGTH_SHORT).show();
        finish();
    }

}
