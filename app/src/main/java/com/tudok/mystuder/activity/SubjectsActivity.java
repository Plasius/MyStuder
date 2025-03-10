package com.tudok.mystuder.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.tudok.mystuder.R;
import com.tudok.mystuder.database.Database;
import com.tudok.mystuder.adapter.GradeAdapter;
import com.tudok.mystuder.database.Grade;
import com.tudok.mystuder.database.Subject;
import com.tudok.mystuder.database.User;
import com.tudok.mystuder.util.PersistenceUtils;

import java.util.ArrayList;
import java.util.List;

public class SubjectsActivity extends AppCompatActivity {

    private Spinner spinner;
    private GraphView graph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle(getString(R.string.subjects));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_subjects);
        initSpinner();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    //after a subject is selected, the list of grades needs to be created
    public void loadSubject(String Id){
        List<Grade> grades = Id.contentEquals(getString(R.string.all_subjects)) ? Database.getInstance(this).gradeDAO().getGrades() : Database.getInstance(this).gradeDAO().getGradesBySubject(Id);

        //create adapter and bind to listview
        GradeAdapter adapter=new GradeAdapter(this, grades);
        ((ListView)findViewById(R.id.subject_lv_grades)).setAdapter(adapter);


        //graph
        double average = 0;

        DataPoint[] dataPoints = new DataPoint[grades.size()];
        int index = 0;

        for(Grade grade : grades){
            dataPoints[index] = new DataPoint(index+1, grade.getValue());
            average += grade.getValue();
            index++;
        }

        if(grades.size()!=0)
            average/=grades.size();

        if(Id.contentEquals(getString(R.string.all_subjects))){
            User user = PersistenceUtils.getInstance(this).getUser();
            if(user != null) {
                ((TextView) findViewById(R.id.subject_tv_laverage)).setText(String.format("%.2f", average));
                ((TextView) findViewById(R.id.subject_tv_gaverage)).setText(String.format("%.2f", user.getGoal()));
            }
        }else {
            ((TextView) findViewById(R.id.subject_tv_laverage)).setText(String.format("%.2f", average));
            ((TextView) findViewById(R.id.subject_tv_gaverage)).setText(String.format("%.2f", Database.getInstance(this).subjectDAO().getSubjectById(Id).getGoalAverage()));
        }

        //load graph
        LineGraphSeries <DataPoint> series = new LineGraphSeries<>(dataPoints);


        graph = findViewById(R.id.graph);
        graph.removeAllSeries();
        graph.addSeries(series);

    }


    private void initSpinner(){
        spinner = findViewById(R.id.subjects_sp_subjects);

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

        list.add(0, getString(R.string.all_subjects));

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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //get currently selected subject and delete it, along with its grades
    public void onDeleteSubjectClicked(View v){
        if(spinner.getSelectedItem().toString().contentEquals("all subjects")){
            Toast.makeText(this, "Select a subject to delete", Toast.LENGTH_SHORT).show();
            return;
        }

        String subjectId = spinner.getSelectedItem().toString();
        onDeleteGradesClicked(v);
        Database.getInstance(this).subjectDAO().deleteSubjectByName(subjectId);
        onResume();
    }

    //get currently selected subject and delete all entries linked to it
    public void onDeleteGradesClicked(View v){
        if(spinner.getSelectedItem().toString().contentEquals("all subjects")){
            Toast.makeText(this, "Select a subject first", Toast.LENGTH_SHORT).show();
            return;
        }

        Database.getInstance(this).gradeDAO().deleteGradesBySubject(spinner.getSelectedItem().toString());
        onResume();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initSpinner();

        if(spinner.isSelected()){
            loadSubject(spinner.getSelectedItem().toString());
            if(graph != null)
             graph.removeAllSeries();
        }
    }
}
