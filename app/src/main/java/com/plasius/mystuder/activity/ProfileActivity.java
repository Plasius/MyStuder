package com.plasius.mystuder.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.plasius.mystuder.R;
import com.plasius.mystuder.database.Database;
import com.plasius.mystuder.database.Grade;
import com.plasius.mystuder.database.User;
import com.plasius.mystuder.util.PersistenceUtils;

import java.util.List;

public class ProfileActivity extends AppCompatActivity {
    private TextView averageActual, averageDesc;
    private TextView goalActual, goalDesc;
    private TextView absencesActual, absencesDesc;
    private TextView studyActual, studyDesc;
    private TextView extraActual, extraDesc;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().setTitle("Profile analyser");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        averageActual = findViewById(R.id.profile_tv_average);
        averageDesc = findViewById(R.id.profile_tv_average_detail);

        goalActual = findViewById(R.id.profile_tv_goal);
        goalDesc = findViewById(R.id.profile_tv_goal_detail);

        absencesActual = findViewById(R.id.profile_tv_absences);
        absencesDesc = findViewById(R.id.profile_tv_absences_detail);

        studyActual = findViewById(R.id.profile_tv_studytime);
        studyDesc = findViewById(R.id.profile_tv_studytime_detail);

        extraActual = findViewById(R.id.profile_tv_extra);
        extraDesc = findViewById(R.id.profile_tv_extra_detail);

        initTips();
    }


    double[][] kilencHuman = {
            {9.4, 9,7},
            {9, 9.5},
            {8, 9}
    };

    double[][] kilencReal = {
            {9.5, 9.75},
            {9, 9.5},
            {9, 9.5}
    };

    double[][] tizHuman = {
            {8.5, 9.1},
            {8, 9},
            {7, 8.5}
    };
    double[][] tizReal = {
            {8.5, 8.9},
            {8, 9},
            {8.5, 9}
    };
    double[][] tizenegyHuman = {
            {8.5, 9.1},
            {8, 9},
            {7, 8.5}
    };
    double[][] tizenegyReal = {
            {8.5, 8.9},
            {8, 9},
            {8.5, 9}
    };
    double[][] tizenkettoHuman = {
            {8.5, 9.1},
            {8.5, 9},
            {7.5, 8.5}
    };
    double[][] tizenkettoReal = {
            {8.5, 9},
            {8, 9},
            {8.5, 9}
    };
    double[][] tizenharomHuman = {
            {8.5, 9.1},
            {7, 8.9},
            {7, 8.9}
    };

    double[][] tizenharomReal = {
            {8.5, 9.1},
            {6, 7.8},
            {7, 8.9}
    };


    public void initTips(){

        //last average
        User user = PersistenceUtils.getInstance(this).getUser();
        if(user == null)
            return;

        //last average
        ((TextView)findViewById(R.id.profile_tv_average)).setText(Double.toString(user.getLaverage()));
        switch (user.getGrade()){
            case 9:
                if(user.getProfile()==1){
                    //real
                    if(user.getLaverage()<kilencReal[0][0]){
                        //bad, you should have been around:
                        averageDesc.setText(R.string.last_under);
                        averageDesc.append(Double.toString(kilencReal[0][0]));
                    }else if(user.getLaverage()<kilencReal[0][1]){
                        //average, the top performers start from:
                        averageDesc.setText(R.string.last_average);
                        averageDesc.append(Double.toString(kilencReal[0][1]));

                    }else{
                        //above average, congrats!
                        averageDesc.setText(R.string.last_top);

                    }

                }else{
                    //human
                    if(user.getLaverage()<kilencHuman[0][0]){
                        //bad, you should have been around:
                        averageDesc.setText(R.string.last_under);
                        averageDesc.append(Double.toString(kilencHuman[0][0]));

                    }else if(user.getLaverage()<kilencHuman[0][1]){
                        //average, the top performers start from:
                        averageDesc.setText(R.string.last_average);
                        averageDesc.append(Double.toString(kilencHuman[0][1]));

                    }else{
                        //above average, congrats!
                        averageDesc.setText(R.string.last_top);

                    }
                }
                break;
            case 10:
                if(user.getProfile()==1){
                    //real
                    if(user.getLaverage()<tizReal[0][0]){
                        //bad, you should have been around:
                        averageDesc.setText(R.string.last_under);
                        averageDesc.append(Double.toString(tizReal[0][0]));

                    }else if(user.getLaverage()<tizReal[0][1]){
                        //average, the top performers start from:
                        averageDesc.setText(R.string.last_average);
                        averageDesc.append(Double.toString(tizReal[0][1]));

                    }else{
                        //above average, congrats!
                        averageDesc.setText(R.string.last_top);

                    }

                }else{
                    //human
                    if(user.getLaverage()<tizHuman[0][0]){
                        //bad, you should have been around:
                        averageDesc.setText(R.string.last_under);
                        averageDesc.append(Double.toString(tizHuman[0][0]));

                    }else if(user.getLaverage()<tizHuman[0][1]){
                        //average, the top performers start from:
                        averageDesc.setText(R.string.last_average);
                        averageDesc.append(Double.toString(tizHuman[0][1]));

                    }else{
                        //above average, congrats!
                        averageDesc.setText(R.string.last_top);

                    }
                }
                break;
            case 11:
                if(user.getProfile()==1){
                    //real
                    if(user.getLaverage()<tizenegyReal[0][0]){
                        //bad, you should have been around:
                        averageDesc.setText(R.string.last_under);
                        averageDesc.append(Double.toString(tizenegyReal[0][0]));

                    }else if(user.getLaverage()<tizenegyReal[0][1]){
                        //average, the top performers start from:
                        averageDesc.setText(R.string.last_average);
                        averageDesc.append(Double.toString(tizenegyReal[0][1]));

                    }else{
                        //above average, congrats!
                        averageDesc.setText(R.string.last_top);

                    }

                }else{
                    //human
                    if(user.getLaverage()<tizenegyHuman[0][0]){
                        //bad, you should have been around:
                        averageDesc.setText(R.string.last_under);
                        averageDesc.append(Double.toString(tizenegyHuman[0][0]));

                    }else if(user.getLaverage()<tizenegyHuman[0][1]){
                        //average, the top performers start from:
                        averageDesc.setText(R.string.last_average);
                        averageDesc.append(Double.toString(tizenegyHuman[0][1]));

                    }else{
                        //above average, congrats!
                        averageDesc.setText(R.string.last_top);

                    }
                }
                break;
            case 12:
                if(user.getProfile()==1){
                    //real
                    if(user.getLaverage()<tizenkettoReal[0][0]){
                        //bad, you should have been around:
                        averageDesc.setText(R.string.last_under);
                        averageDesc.append(Double.toString(tizenkettoReal[0][0]));

                    }else if(user.getLaverage()<tizenkettoReal[0][1]){
                        //average, the top performers start from:
                        averageDesc.setText(R.string.last_average);
                        averageDesc.append(Double.toString(tizenkettoReal[0][1]));

                    }else{
                        //above average, congrats!
                        averageDesc.setText(R.string.last_top);

                    }

                }else{
                    //human
                    if(user.getLaverage()<tizenkettoHuman[0][0]){
                        //bad, you should have been around:
                        averageDesc.setText(R.string.last_under);
                        averageDesc.append(Double.toString(tizenkettoHuman[0][0]));

                    }else if(user.getLaverage()<tizenkettoHuman[0][1]){
                        //average, the top performers start from:
                        averageDesc.setText(R.string.last_average);
                        averageDesc.append( Double.toString(tizenkettoHuman[0][1]));

                    }else{
                        //above average, congrats!
                        averageDesc.setText(R.string.last_top);

                    }
                }
                break;
        }

        //goal
        ((TextView)findViewById(R.id.profile_tv_goal)).setText(Double.toString(user.getGoal()));
        switch (user.getGrade()){
            case 9:
                if(user.getProfile()==1){
                    //real
                    if(user.getGoal()<tizReal[0][0]){
                        //bad, you should have been around:
                        goalDesc.setText(R.string.goal_under);
                        goalDesc.append(Double.toString(tizReal[0][0]));
                    }else if(user.getGoal()<tizReal[0][1]){
                        //goal, the top performers start from:
                        goalDesc.setText(R.string.goal_average);
                        goalDesc.append(Double.toString(tizReal[0][1]));

                    }else{
                        //above goal, congrats!
                        goalDesc.setText(R.string.goal_top);

                    }

                }else{
                    //human
                    if(user.getGoal()<tizHuman[0][0]){
                        //bad, you should have been around:
                        goalDesc.setText(R.string.goal_under);
                        goalDesc.append(Double.toString(tizHuman[0][0]));

                    }else if(user.getGoal()<tizHuman[0][1]){
                        //goal, the top performers start from:
                        goalDesc.setText(R.string.goal_average);
                        goalDesc.append(Double.toString(tizHuman[0][1]));

                    }else{
                        //above goal, congrats!
                        goalDesc.setText(R.string.goal_top);

                    }
                }
                break;
            case 10:
                if(user.getProfile()==1){
                    //real
                    if(user.getGoal()<tizenegyReal[0][0]){
                        //bad, you should have been around:
                        goalDesc.setText(R.string.goal_under);
                        goalDesc.append(Double.toString(tizenegyReal[0][0]));

                    }else if(user.getGoal()<tizenegyReal[0][1]){
                        //goal, the top performers start from:
                        goalDesc.setText(R.string.goal_average);
                        goalDesc.append(Double.toString(tizenegyReal[0][1]));

                    }else{
                        //above goal, congrats!
                        goalDesc.setText(R.string.goal_top);

                    }

                }else{
                    //human
                    if(user.getGoal()<tizenegyHuman[0][0]){
                        //bad, you should have been around:
                        goalDesc.setText(R.string.goal_under);
                        goalDesc.append(Double.toString(tizenegyHuman[0][0]));

                    }else if(user.getGoal()<tizenegyHuman[0][1]){
                        //goal, the top performers start from:
                        goalDesc.setText(R.string.goal_average);
                        goalDesc.append(Double.toString(tizenegyHuman[0][1]));

                    }else{
                        //above goal, congrats!
                        goalDesc.setText(R.string.goal_top);

                    }
                }
                break;
            case 11:
                if(user.getProfile()==1){
                    //real
                    if(user.getGoal()<tizenkettoReal[0][0]){
                        //bad, you should have been around:
                        goalDesc.setText(R.string.goal_under);
                        goalDesc.append(Double.toString(tizenkettoReal[0][0]));

                    }else if(user.getGoal()<tizenkettoReal[0][1]){
                        //goal, the top performers start from:
                        goalDesc.setText(R.string.goal_average);
                        goalDesc.append(Double.toString(tizenkettoReal[0][1]));

                    }else{
                        //above goal, congrats!
                        goalDesc.setText(R.string.goal_top);

                    }

                }else{
                    //human
                    if(user.getGoal()<tizenkettoHuman[0][0]){
                        //bad, you should have been around:
                        goalDesc.setText(R.string.goal_under);
                        goalDesc.append(Double.toString(tizenkettoHuman[0][0]));

                    }else if(user.getGoal()<tizenkettoHuman[0][1]){
                        //goal, the top performers start from:
                        goalDesc.setText(R.string.goal_average);
                        goalDesc.append(Double.toString(tizenkettoHuman[0][1]));

                    }else{
                        //above goal, congrats!
                        goalDesc.setText(R.string.goal_top);

                    }
                }
                break;
            case 12:
                if(user.getProfile()==1){
                    //real
                    if(user.getGoal()<tizenharomReal[0][0]){
                        //bad, you should have been around:
                        goalDesc.setText(R.string.goal_under );
                        goalDesc.append(Double.toString(tizenharomReal[0][0]));

                    }else if(user.getGoal()<tizenharomReal[0][1]){
                        //goal, the top performers start from:
                        goalDesc.setText(R.string.goal_average);
                        goalDesc.append(" " + tizenharomReal[0][1]);

                    }else{
                        //above goal, congrats!
                        goalDesc.setText(R.string.goal_top);

                    }

                }else{
                    //human
                    if(user.getGoal()<tizenharomHuman[0][0]){
                        //bad, you should have been around:
                        goalDesc.setText(R.string.goal_under);
                        goalDesc.setText(Double.toString(tizenharomHuman[0][0]));

                    }else if(user.getGoal()<tizenharomHuman[0][1]){
                        //goal, the top performers start from:
                        goalDesc.setText(R.string.goal_average );
                        goalDesc.append(Double.toString(tizenharomHuman[0][1]));

                    }else{
                        //above goal, congrats!
                        goalDesc.setText(R.string.goal_top);

                    }
                }
                break;
        }


        //current average
        List<Grade> grades = Database.getInstance(this).gradeDAO().getGrades();
        double average = 0;
        for(Grade grade : grades)
            average += grade.getValue();

        average/=grades.size();

        displayEvaluation(R.id.profile_tv_caverage, R.id.profile_tv_caverage_detail, average, user.getGoal(), 0.5);

        //absences
        ((TextView)findViewById(R.id.profile_tv_absences)).setText(Integer.toString(user.getAbsences()));
        if(user.getAbsences()>20){
            ((TextView)findViewById(R.id.profile_tv_absences_detail)).setText("You have too many absences!");
        }else{
            ((TextView)findViewById(R.id.profile_tv_absences_detail)).setText("You are doing good!");
        }

        //extra
        ((TextView)findViewById(R.id.profile_tv_extra)).setText(Integer.toString(user.getExtraDays()));
        if(user.getExtraDays()>4){
            ((TextView)findViewById(R.id.profile_tv_extra_detail)).setText("You have an overwhelming amount of activities!");
        }else{
            ((TextView)findViewById(R.id.profile_tv_extra_detail)).setText("Currently, you are under the threshold.");
        }

        //studytime
        ((TextView)findViewById(R.id.profile_tv_studytime)).setText(Integer.toString(user.getStudyTime()));
        if(user.getStudyTime()<=60){
            ((TextView)findViewById(R.id.profile_tv_studytime_detail)).setText("Currently, you are underperforming.");

        }else if(user.getStudyTime()<=120){
            ((TextView)findViewById(R.id.profile_tv_studytime_detail)).setText("You are putting in a moderate amount of work.");

        }else{
            ((TextView)findViewById(R.id.profile_tv_studytime_detail)).setText("You are investing a lot of time into this! Good job.");

        }

    }


    public void displayEvaluation(int actualTextViewResourceId, int detailTextViewResourceId, double value, double average, double errorMargin){
        ((TextView)findViewById(actualTextViewResourceId)).setText(String.format("%.2f", value));

        String text;
        if(value<=average-errorMargin){
            //under the average
            text = getResources().getString(R.string.current_under);
        }else if(value>=average-errorMargin && value<=average+errorMargin){
            //average
            text = getResources().getString(R.string.current_average);
        }else{
            //above average
            text = getResources().getString(R.string.current_top);
        }


        ((TextView)findViewById(detailTextViewResourceId)).setText(text);
    }

}
