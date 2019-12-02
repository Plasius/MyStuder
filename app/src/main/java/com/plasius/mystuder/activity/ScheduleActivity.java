package com.plasius.mystuder.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.plasius.mystuder.R;
import com.plasius.mystuder.adapter.ClassAdapter;
import com.plasius.mystuder.database.Database;
import com.plasius.mystuder.adapter.GradeAdapter;
import com.plasius.mystuder.database.Grade;
import com.plasius.mystuder.database.Subject;
import com.plasius.mystuder.util.Constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ScheduleActivity extends AppCompatActivity {

    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Schedule");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_schedule);
        initSpinner();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    //after a subject is selected, the list of grades needs to be created
    public void loadDay(String Id){
        List<com.plasius.mystuder.database.Class> classes = Id.contentEquals("All days") ? Database.getInstance(this).classDAO().getClasses() : Database.getInstance(this).classDAO().getClassesByDay(Id);



        //create adapter and bind to listview
        ClassAdapter adapter=new ClassAdapter(this, classes);
        ((ListView)findViewById(R.id.schedule_lv_classes)).setAdapter(adapter);

    }


    private void initSpinner(){
        spinner = findViewById(R.id.schedule_sp_days);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                loadDay(spinner.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        List<String> days = new ArrayList<String>(Arrays.asList(Constants.DAYS_OF_THE_WEEK));
        days.add(0, "All days");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, days);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_schedule, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add_class) {
            startActivity(new Intent(this, AddClassActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    //get currently selected subject and delete all entries linked to it
    public void onDeleteClassesClicked(View v){
        if(spinner.getSelectedItem().toString().contentEquals("All days")){
            Toast.makeText(this, "Select a day first", Toast.LENGTH_SHORT).show();
            return;
        }

        Database.getInstance(this).classDAO().deleteClassesByDay(spinner.getSelectedItem().toString());
        onResume();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initSpinner();
        if(spinner.isSelected())
            loadDay(spinner.getSelectedItem().toString());
    }
}
