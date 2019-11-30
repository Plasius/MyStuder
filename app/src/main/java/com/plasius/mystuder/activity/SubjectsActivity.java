package com.plasius.mystuder.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.plasius.mystuder.R;
import com.plasius.mystuder.controller.Database;
import com.plasius.mystuder.controller.GradeAdapter;
import com.plasius.mystuder.controller.SubjectDAO;
import com.plasius.mystuder.model.Grade;
import com.plasius.mystuder.model.Subject;

import java.util.ArrayList;
import java.util.List;

public class SubjectsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Subjects");
        setContentView(R.layout.activity_subjects);
        initSpinner();
    }

    //after a subject is selected, the list of grades needs to be created
    public void loadSubject(String Id){
        Toast.makeText(this, Integer.toString(Database.getInstance(this).gradeDAO().getGradesBySubject(Id).size()), Toast.LENGTH_SHORT).show();
        List<Grade> grades = Database.getInstance(this).gradeDAO().getGradesBySubject(Id);

        //create adapter and bind to listview
        GradeAdapter adapter=new GradeAdapter(this, grades);
        ((ListView)findViewById(R.id.subject_lv_grades)).setAdapter(adapter);

    }


    private void initSpinner(){
        Spinner spinner = findViewById(R.id.subjects_sp_subjects);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                loadSubject(spinner.getSelectedItem().toString());
              }

             @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


                List < Subject > subjects = Database.getInstance(this).subjectDAO().getSubjects();
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_subjects, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add_subject) {
            startActivity(new Intent(this, AddSubjectActivity.class));
            return true;
        }else if(id == R.id.action_add_grade){
            startActivity(new Intent(this, AddGradeActivity.class));
        }else if(id == R.id.action_reset){
            Database.getInstance(this).gradeDAO().deleteGrades();
            Database.getInstance(this).subjectDAO().deleteSubjects();
        }

        return super.onOptionsItemSelected(item);
    }

}
