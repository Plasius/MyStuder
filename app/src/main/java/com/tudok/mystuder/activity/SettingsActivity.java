package com.tudok.mystuder.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.tudok.mystuder.R;
import com.tudok.mystuder.util.Constants;
import com.tudok.mystuder.util.PersistenceUtils;
import com.tudok.mystuder.database.User;

public class SettingsActivity extends AppCompatActivity {


    EditText name, absences;
    EditText lastAverage;
    EditText averageGoal;
    RadioGroup gender, grade, profile;
    SeekBar importance1, importance2, importance3, importance4, importance5, importance6, extra, studytime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        getSupportActionBar().setTitle(R.string.settings);

        //display adequate welcoming message
        if(PersistenceUtils.getInstance(this).getBoolean(Constants.STATE_FIRST_LAUNCHED, true)){
            ((TextView)findViewById(R.id.settings_tv_welcome)).setText(R.string.welcome);
        }else{
            ((TextView)findViewById(R.id.settings_tv_welcome)).setText(R.string.edit_profile);
        }

        importance1 = findViewById(R.id.settings_sb_importance1);
        importance2 = findViewById(R.id.settings_sb_importance2);
        importance3 = findViewById(R.id.settings_sb_importance3);
        importance4 = findViewById(R.id.settings_sb_importance4);
        importance5 = findViewById(R.id.settings_sb_importance5);
        importance6 = findViewById(R.id.settings_sb_importance6);
        extra = findViewById(R.id.settings_sb_extra);

        gender = findViewById(R.id.settings_rg_gender);
        grade = findViewById(R.id.settings_rg_grade);
        profile = findViewById(R.id.settings_rg_profile);

        name = findViewById(R.id.settings_et_name);
        absences = findViewById(R.id.settings_et_absences);
        studytime = findViewById(R.id.settings_sb_studytime);
        
        lastAverage = findViewById(R.id.settings_et_last_average);
        
        averageGoal = findViewById(R.id.settings_et_goal_average);

        TextView studyTracker = findViewById(R.id.settings_tv_tracker_study);
        studytime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                studyTracker.setText(Integer.toString(progress+1));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        TextView importanceTracker1 = findViewById(R.id.settings_tv_tracker_importance1);
        importance1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                importanceTracker1.setText(Integer.toString(progress+1));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        TextView importanceTracker2 = findViewById(R.id.settings_tv_tracker_importance2);
        importance2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                importanceTracker2.setText(Integer.toString(progress+1));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        TextView importanceTracker3 = findViewById(R.id.settings_tv_tracker_importance3);
        importance3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                importanceTracker3.setText(Integer.toString(progress+1));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        TextView importanceTracker4 = findViewById(R.id.settings_tv_tracker_importance4);
        importance4.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                importanceTracker4.setText(Integer.toString(progress+1));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        TextView importanceTracker5 = findViewById(R.id.settings_tv_tracker_importance5);
        importance5.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                importanceTracker5.setText(Integer.toString(progress+1));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        TextView importanceTracker6 = findViewById(R.id.settings_tv_tracker_importance6);
        importance6.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                importanceTracker6.setText(Integer.toString(progress+1));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        TextView extraTracker = findViewById(R.id.settings_tv_tracker_extra);
        extra.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                extraTracker.setText(Integer.toString(progress+1));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }

    //save user preferences
    public void onSaveClicked(View v){
        if(!name.getText().toString().isEmpty()){
            if(gender.getCheckedRadioButtonId()!=-1){
                if(grade.getCheckedRadioButtonId()!=-1){
                    if(profile.getCheckedRadioButtonId()!=-1) {
                        if(checkEditText(lastAverage, 0, 10)){
                            if(checkEditText(averageGoal, 0, 10)){
                                if (checkEditText(absences, 0, 75)) {
                                    //save all user data
                                    User user = new User(name.getText().toString());

                                    user.setLaverage(Double.parseDouble(lastAverage.getText().toString()));
                                    user.setGoal(Double.parseDouble(averageGoal.getText().toString()));

                                    user.setImportance(
                                            (importance1.getProgress() + 1
                                    + importance2.getProgress() + 1
                                    + importance3.getProgress() + 1
                                    + importance4.getProgress() + 1
                                    + importance5.getProgress() + 1
                                    + importance6.getProgress() + 1)/6
                                    );

                                    user.setStudyTime((studytime.getProgress()+1)*60);

                                    switch (gender.getCheckedRadioButtonId()) {
                                        case R.id.settings_rb_man:
                                            user.setGender(1);
                                            break;
                                        case R.id.settings_rb_woman:
                                            user.setGender(2);
                                            break;
                                    }

                                    switch (grade.getCheckedRadioButtonId()) {
                                        case R.id.settings_rb_nine:
                                            user.setGrade(9);
                                            break;
                                        case R.id.settings_rb_ten:
                                            user.setGrade(10);
                                            break;
                                        case R.id.settings_rb_eleven:
                                            user.setGrade(11);
                                            break;
                                        case R.id.settings_rb_twelve:
                                            user.setGrade(12);
                                            Toast.makeText(this, "run", Toast.LENGTH_SHORT).show();
                                            break;
                                    }

                                    switch (profile.getCheckedRadioButtonId()) {
                                        case R.id.settings_rb_human:
                                            user.setProfile(2);
                                            break;
                                        case R.id.settings_rb_real:
                                            user.setProfile(1);
                                            break;
                                    }

                                    user.setAbsences(Integer.parseInt(absences.getText().toString()));
                                    user.setExtraDays(extra.getProgress()+1);


                                    //go back or alternatively, launch main activity
                                    if (getIntent().getBooleanExtra(Constants.STATE_FIRST_LAUNCHED, true)) {
                                        PersistenceUtils.getInstance(this).setBoolean(Constants.STATE_FIRST_LAUNCHED, false);
                                        PersistenceUtils.getInstance(this).saveUser(user);

                                        Intent intent = new Intent(this, MainActivity.class);
                                        startActivity(intent);

                                    }

                                    finish();
                                }
                            }
                        }
                    }

                }
            }
        }
    }

    @Override
    public void onBackPressed() {
        if(!PersistenceUtils.getInstance(this).getBoolean(Constants.STATE_FIRST_LAUNCHED, true)){
            finish();
        }
    }



    //CHECKER
    public boolean checkEditText(EditText editText, int lowBoundary, int highBoundary){
        if(!editText.getText().toString().isEmpty()){
            double num = Double.parseDouble(editText.getText().toString());
            return (num>=lowBoundary && num<=highBoundary);
        }

        return false;
    }

}
