package com.plasius.mystuder.database;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {
    private String name;
    private int gender;
    private int grade;
    private int profile;
    private double lastAverage;
    private double goalAverage;
    private int absences;
    private int importance;
    private int extraDays;
    private int studyTime;


    public User(String name){
        this.name = name;
    }

    public void setStudyTime(int studyTime) {
        this.studyTime = studyTime;
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
    public void setName(String name) {
        this.name = name;
    }
    public void setLastAverage(double lastAverage) {
        this.lastAverage = lastAverage;
    }
    public void setGoalAverage(double goalAverage) {
        this.goalAverage = goalAverage;
    }
    public void setAbsences(int absences) {
        this.absences = absences;
    }
    public void setExtraDays(int extraDays) {
        this.extraDays = extraDays;
    }
    public void setImportance(int importance) {
        this.importance = importance;
    }


    //Parcelable
    private User(Parcel in) {
        name = in.readString();
        gender = in.readInt();
        grade = in.readInt();
        profile = in.readInt();
        lastAverage = in.readDouble();
        goalAverage = in.readDouble();
        absences = in.readInt();
        importance = in.readInt();
        extraDays = in.readInt();
        studyTime = in.readInt();
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
        dest.writeDouble(lastAverage);
        dest.writeDouble(goalAverage);
        dest.writeInt(absences);
        dest.writeInt(importance);
        dest.writeInt(extraDays);
        dest.writeInt(studyTime);
    }

    public String getName() {
        return name;
    }

    public int getGender() {
        return gender;
    }

    public double getGoalAverage() {
        return goalAverage;
    }

    public double getLastAverage() {
        return lastAverage;
    }

    public int getGrade() {
        return grade;
    }

    public int getAbsences() {
        return absences;
    }

    public int getExtraDays() {
        return extraDays;
    }

    public int getImportance() {
        return importance;
    }

    public int getProfile() {
        return profile;
    }

    public int getStudyTime() {
        return studyTime;
    }
}
