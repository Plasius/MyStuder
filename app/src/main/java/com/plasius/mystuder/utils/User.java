package com.plasius.mystuder.utils;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {
    private String name;
    private int gender;
    private int grade;
    private int profile;
    private int energy;
    private double lastAverage;
    private double humanAverage;
    private double realAverage;
    private double hoursSlept;
    private int absences;
    private int minutesFromSchool;
    private int motivationLevel;
    private int parentImportance;
    private int extraDays;

    public User(String name){
        this.name = name;
    }

    public void setGender(int a){
        gender = a;
    }
    public void setGrade(int a){
        grade = a;
    }
    public void setProfile(int a){
        profile = a;
    }
    public void setEnergy(int a){
        energy = a;
    }

    public void setSliders(int motivation, int importance, int extra){
        motivationLevel = motivation;
        parentImportance = importance;
        extraDays = extra;

    }

    public void setAverages(double laverage, double haverage, double raverage){
        lastAverage = laverage;
        humanAverage = haverage;
        realAverage = raverage;
    }


    //Parcelable
    private User(Parcel in) {
        name = in.readString();
        gender = in.readInt();
        grade = in.readInt();
        profile = in.readInt();
        energy = in.readInt();
        lastAverage = in.readDouble();
        humanAverage = in.readDouble();
        realAverage = in.readDouble();
        hoursSlept = in.readDouble();
        absences = in.readInt();
        minutesFromSchool = in.readInt();
        motivationLevel = in.readInt();
        parentImportance = in.readInt();
        extraDays = in.readInt();
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(gender);
        dest.writeInt(grade);
        dest.writeInt(profile);
        dest.writeInt(energy);
        dest.writeDouble(lastAverage);
        dest.writeDouble(humanAverage);
        dest.writeDouble(realAverage);
        dest.writeDouble(hoursSlept);
        dest.writeInt(absences);
        dest.writeInt(minutesFromSchool);
        dest.writeInt(motivationLevel);
        dest.writeInt(parentImportance);
        dest.writeInt(extraDays);
    }
}
