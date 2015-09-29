package com.example.elisandler.inspirationpal;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by elisandler on 8/7/15.
 */
public class WorkoutHistory implements Parcelable {


    public WorkoutHistory(){

    }

    public WorkoutHistory(Parcel parcel) {

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }

    public static Creator<WorkoutHistory> CREATOR = new Creator<WorkoutHistory>() {

        @Override
        public WorkoutHistory createFromParcel(Parcel source) {
            return new WorkoutHistory(source);
        }

        @Override
        public WorkoutHistory[] newArray(int size) {
            return new WorkoutHistory[size];
        }

    };
}
