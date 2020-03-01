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
    EditText et_subjectName;
    EditText et_lastAverage;
    EditText et_goalAverage;
    CheckBox cb_isReal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subject);
        getSupportActionBar().setTitle("Add Subject");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        et_subjectName = findViewById(R.id.subject_et_name);
        et_lastAverage = findViewById(R.id.subject_et_laverage);
        et_goalAverage = findViewById(R.id.subject_et_goal);
        cb_isReal = findViewById(R.id.subject_cb_real);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void onAddClicked(View v){
        if(et_subjectName.getText().toString().isEmpty() || et_lastAverage.getText().toString().isEmpty() ||et_goalAverage.getText().toString().isEmpty()){
            Toast.makeText(this, getString(R.string.error_values), Toast.LENGTH_SHORT).show();
            return;
        }

        String subjectname = ((EditText)findViewById(R.id.subject_et_name)).getText().toString();
        double laverage = Double.parseDouble(((EditText)findViewById(R.id.subject_et_laverage)).getText().toString());
        double gaverage = Double.parseDouble(((EditText)findViewById(R.id.subject_et_goal)).getText().toString());
        boolean isReal = ((CheckBox)findViewById(R.id.subject_cb_real)).isChecked();

        if(laverage<0 || laverage>10 || gaverage<1 || gaverage>10){
            Toast.makeText(this, getString(R.string.error_values), Toast.LENGTH_SHORT).show();
            return;
        }

        //save new subject
        Database.getInstance(this).subjectDAO().insertSubject(new Subject(subjectname, laverage, gaverage, isReal));

        //go back
        Toast.makeText(this, getString(R.string.subject_added), Toast.LENGTH_SHORT).show();
        finish();
    }

}
