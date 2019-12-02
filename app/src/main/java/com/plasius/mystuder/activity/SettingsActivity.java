package com.plasius.mystuder.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.plasius.mystuder.R;
import com.plasius.mystuder.util.Constants;
import com.plasius.mystuder.util.PersistenceUtils;
import com.plasius.mystuder.database.User;

public class SettingsActivity extends AppCompatActivity {


    EditText name, laverage, absences, goal;
    RadioGroup gender, grade, profile;
    SeekBar importance, extra, studytime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        getSupportActionBar().setTitle("Profile");

        //display adequate welcoming message
        if(PersistenceUtils.getInstance(this).getBoolean(Constants.STATE_FIRST_LAUNCHED, true)){
            ((TextView)findViewById(R.id.settings_tv_welcome)).setText(R.string.welcome);
        }else{
            ((TextView)findViewById(R.id.settings_tv_welcome)).setText(R.string.settings);
        }

        importance = findViewById(R.id.settings_sb_importance);
        extra = findViewById(R.id.settings_sb_extra);

        gender = findViewById(R.id.settings_rg_gender);
        grade = findViewById(R.id.settings_rg_grade);
        profile = findViewById(R.id.settings_rg_profile);

        name = findViewById(R.id.settings_et_name);
        laverage = findViewById(R.id.settings_et_laverage);
        absences = findViewById(R.id.settings_et_absences);
        studytime = findViewById(R.id.settings_sb_studytime);
        goal = findViewById(R.id.settings_et_goal);
    }

    //save user preferences
    public void onSaveClicked(View v){
        if(!name.getText().toString().isEmpty()){
            if(gender.getCheckedRadioButtonId()!=-1){
                if(grade.getCheckedRadioButtonId()!=-1){
                    if(profile.getCheckedRadioButtonId()!=-1) {
                            if (checkEditText(laverage, 0, 10)) {
                                if (checkEditText(goal, 0, 10)) {
                                    if (checkEditText(absences, 0, 75)) {
                                            //save all user data
                                            User user = new User(name.getText().toString());
                                            user.setLastAverage(Double.parseDouble(laverage.getText().toString()));
                                            user.setGoalAverage(Double.parseDouble(goal.getText().toString()));
                                            user.setImportance(importance.getProgress() + 1);
                                            user.setStudyTime(studytime.getProgress()+1);

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
