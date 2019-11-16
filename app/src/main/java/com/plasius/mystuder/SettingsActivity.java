package com.plasius.mystuder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.plasius.mystuder.utils.Constants;
import com.plasius.mystuder.utils.User;

public class SettingsActivity extends AppCompatActivity {


    EditText name, laverage, haverage, raverage, sleeptime, absences, distance;
    RadioGroup gender, grade, profile, energy;
    SeekBar motivation, parentImportance, extra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        //display adequate welcoming message
        if(getIntent().getBooleanExtra(Constants.STATE_FIRST_LAUNCHED, true)){
            ((TextView)findViewById(R.id.settings_tv_welcome)).setText(R.string.welcome);
        }else{
            ((TextView)findViewById(R.id.settings_tv_welcome)).setText(R.string.settings);
        }

        motivation = findViewById(R.id.settings_sb_motivation);
        parentImportance = findViewById(R.id.settings_sb_importance);
        extra = findViewById(R.id.settings_sb_extra);

        gender = findViewById(R.id.settings_rg_gender);
        grade = findViewById(R.id.settings_rg_grade);
        profile = findViewById(R.id.settings_rg_profile);
        energy = findViewById(R.id.settings_rg_energy);

        name = findViewById(R.id.settings_et_name);
        laverage = findViewById(R.id.settings_et_laverage);
        haverage = findViewById(R.id.settings_et_haverage);
        raverage = findViewById(R.id.settings_et_raverage);
        sleeptime = findViewById(R.id.settings_et_sleeptime);
        absences = findViewById(R.id.settings_et_absences);
        distance = findViewById(R.id.settings_et_distance);
    }

    //save user preferences
    public void onSaveClicked(View v){
        if(!name.getText().toString().isEmpty()){
            if(gender.getCheckedRadioButtonId()!=-1){
                if(grade.getCheckedRadioButtonId()!=-1){
                    if(profile.getCheckedRadioButtonId()!=-1){
                        if(energy.getCheckedRadioButtonId()!=-1){
                            if(checkEditText(laverage, 0, 10)){
                                if(checkEditText(haverage, 0, 10)){
                                    if(checkEditText(raverage, 0, 10)){
                                        if(checkEditText(sleeptime, 0, 10)){
                                            if(checkEditText(absences, 0, 100)){
                                                if(checkEditText(distance, 0, 180)){
                                                    //save all user data
                                                    User user = new User(name.getText().toString());

                                                    user.setAverages(Double.parseDouble(laverage.getText().toString()),Double.parseDouble(haverage.getText().toString()),Double.parseDouble(raverage.getText().toString()));
                                                    user.setSliders(motivation.getProgress()+1, parentImportance.getProgress()+1, extra.getProgress()+1);

                                                    switch (gender.getCheckedRadioButtonId()){
                                                        case R.id.settings_rb_man:
                                                            user.setGender(1);
                                                            break;
                                                        case R.id.settings_rb_woman:
                                                            user.setGender(2);
                                                            break;
                                                    }

                                                    switch (grade.getCheckedRadioButtonId()){
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

                                                    switch (profile.getCheckedRadioButtonId()){
                                                        case R.id.settings_rb_human:
                                                            user.setProfile(2);
                                                            break;
                                                        case R.id.settings_rb_real:
                                                            user.setProfile(1);
                                                            break;
                                                    }

                                                    switch (energy.getCheckedRadioButtonId()){
                                                        case R.id.settings_rb_calm:
                                                            user.setEnergy(1);
                                                            break;
                                                        case R.id.settings_rb_turbulent:
                                                            user.setEnergy(3);
                                                            break;
                                                        case R.id.settings_rb_balanced:
                                                            user.setEnergy(2);
                                                            break;
                                                    }

                                                    //save user


                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    //CHECKERS
    public boolean checkEditText(EditText editText, int lowBoundary, int highBoundary){
        if(!editText.getText().toString().isEmpty()){
            int num = Integer.parseInt(editText.getText().toString());
            return (num>lowBoundary && num<highBoundary);
        }

        return false;
    }

    public boolean checkRadioGroup(int id){
        return ((RadioGroup)findViewById(id)).getCheckedRadioButtonId()!=-1;
    }
}
