package com.plasius.mystuder.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.plasius.mystuder.R;
import com.plasius.mystuder.database.Database;
import com.plasius.mystuder.database.Subject;

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

        //save new subject
        Database.getInstance(this).subjectDAO().insertSubject(new Subject(subjectname, laverage, gaverage, isReal));

        //go back
        Toast.makeText(this, "Subject added", Toast.LENGTH_SHORT).show();
        finish();
    }

}
