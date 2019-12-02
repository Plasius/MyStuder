package com.plasius.mystuder.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.plasius.mystuder.R;
import com.plasius.mystuder.database.Grade;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class GradeAdapter extends ArrayAdapter<Grade> {
    public List<Grade> grades;
    Activity context;

    public GradeAdapter(Activity context, List<Grade> grades){
        super(context, R.layout.item_grade, grades);

        this.context = context;
        this.grades = grades;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = context.getLayoutInflater().inflate(R.layout.item_grade, null);
            holder = new ViewHolder();
            // Create a ViewHolder and store references to the two children views
            holder.tv_grade =convertView.findViewById(R.id.item_tv_grade);
            holder.tv_date = convertView.findViewById(R.id.item_tv_date);
            holder.tv_subject = convertView.findViewById(R.id.item_tv_subject);

            // The tag can be any Object, this just happens to be the ViewHolder TODO
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // Bind that data efficiently!
        holder.tv_grade.setText(Integer.toString(grades.get(position).getValue()));
        holder.tv_subject.setText(grades.get(position).getSubject_id());
        holder.tv_date.setText( new SimpleDateFormat("dd/MM/yyyy").format(new Date(grades.get(position).getDate())));

        return convertView;
    }

    static class ViewHolder {
        TextView tv_grade;
        TextView tv_subject;
        TextView tv_date;
    }
}
