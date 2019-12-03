package com.plasius.mystuder.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.plasius.mystuder.R;
import com.plasius.mystuder.util.PersistenceUtils;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().setTitle("Profile");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initTips();
    }

    public void initTips(){
        displayEvaluation(R.id.profile_tv_average, R.id.profile_tv_average_detail, PersistenceUtils.getInstance(this).getUser().getLastAverage(), 9, 0.3);

    }


    public void displayEvaluation(int actualTextViewResourceId, int detailTextViewResourceId, double value, double average, double errorMargin){
        ((TextView)findViewById(actualTextViewResourceId)).setText(Double.toString(value));

        String text;
        if(value<=average-errorMargin){
            //under the average
            text = getResources().getStringArray(R.array.evaluations)[0];
        }else if(value>=average-errorMargin && value<=average+errorMargin){
            //average
            text = getResources().getStringArray(R.array.evaluations)[1];
        }else{
            //above average
            text = getResources().getStringArray(R.array.evaluations)[2];
        }

        text += average;

        ((TextView)findViewById(detailTextViewResourceId)).setText(text);
    }

}
