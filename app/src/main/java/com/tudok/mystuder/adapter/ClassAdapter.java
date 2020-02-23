package com.tudok.mystuder.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.tudok.mystuder.R;
import com.tudok.mystuder.database.Class;

import java.util.List;

public class ClassAdapter extends ArrayAdapter<Class> {
    public List<Class> classes;
    Activity context;

    public ClassAdapter(Activity context, List<Class> classes){
        super(context, R.layout.item_grade, classes);

        this.context = context;
        this.classes = classes;
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


            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // Bind that data efficiently!
        holder.tv_grade.setText(classes.get(position).getOrder());
        holder.tv_subject.setText(classes.get(position).getSubject_id());
        holder.tv_date.setText(classes.get(position).getDay_id());

        return convertView;
    }

    static class ViewHolder {
        TextView tv_grade;
        TextView tv_subject;
        TextView tv_date;
    }
}
