package com.plasius.mystuder.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;

import androidx.core.app.NotificationCompat;

import com.plasius.mystuder.R;
import com.plasius.mystuder.activity.MainActivity;
import com.plasius.mystuder.database.Database;
import com.plasius.mystuder.database.Grade;
import com.plasius.mystuder.database.Subject;
import com.plasius.mystuder.util.Constants;
import com.plasius.mystuder.util.PersistenceUtils;

import java.util.Calendar;
import java.util.List;

import static android.app.PendingIntent.FLAG_ONE_SHOT;

public class NotificationReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 1);

        boolean isImportant = false;
        String importantSubject = "";

        List<com.plasius.mystuder.database.Class> classes = Database.getInstance(context).classDAO().getClassesByDay(Constants.DAYS_OF_THE_WEEK[calendar.get(Calendar.DAY_OF_WEEK)-1]);

        for(com.plasius.mystuder.database.Class cls : classes){
            Subject subject = Database.getInstance(context).subjectDAO().getSubjectById(cls.getSubject_id());
            List<Grade> grades = Database.getInstance(context).gradeDAO().getGradesBySubject(subject.getName());
            double average=0;

            for(Grade grade : grades)
                average+=grade.getValue();

            if(grades.size()!=0)
                average /= grades.size();

            if(average < subject.getGoalAverage()){
                isImportant = true;
                importantSubject+= subject.getName() + " ";
                break;
            }
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "wowchannel");

        Intent myIntent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(
                context,
                0,
                myIntent,
                FLAG_ONE_SHOT );

        builder.setAutoCancel(false)
                .setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.logo_small)
                .setContentIntent(pendingIntent)
                .setDefaults(Notification.DEFAULT_LIGHTS | Notification.DEFAULT_SOUND);

        if(isImportant) {
            builder.setContentTitle("Watch out for tomorrow!");
            builder.setContentText("Your average is below your goal in the case of: " + importantSubject);
        }else{

            builder.setContentTitle("You can take it easy. Nothing big tomorrow.");
            builder.setContentText("You are not under performing in any of tomorrow's subjects.");

        }


        NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(3204 ,builder.build());
    }
}
