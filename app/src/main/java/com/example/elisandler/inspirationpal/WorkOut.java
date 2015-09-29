package com.example.elisandler.inspirationpal;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by elisandler on 7/9/15.
 */
public class WorkOut implements Parcelable{

    //I want to be able to save the day that its on,
    //The list of exersises being done
    //how much sets,reps,weights for each exersise ____________>Done!!!
    //
    //String date;
    ArrayList<String> listOfExercises = new ArrayList<>();
    ArrayList<Integer> exerciseAttributes = new ArrayList<>();//could be a problem because it is an INTEGER not int
    int index = 0;
    ArrayList<Integer> spotsClicked = new ArrayList<>();//this does not belong here!!! it should be moved to ClickAbleCreatWOrkOUtLIst
    String name;
    ArrayList<Integer> isRestTimer = new ArrayList<>();
    ArrayList <Integer> restTime = new ArrayList<>();
    int postion = 0;

    public void addSpotClicked(int i){
        spotsClicked.add(i);
    }


    public WorkOut(){

    }

    public WorkOut(ArrayList listOfExercises, ArrayList exerciseAttributes, int index, ArrayList spotsClicked, String name, ArrayList<Integer> isRestTimer, ArrayList<Integer> restTime, int postion){
        //this.date = date;
        this.listOfExercises = listOfExercises;
        this.exerciseAttributes = exerciseAttributes;
        this.index = index;
        this.spotsClicked = spotsClicked;
        this.name = name;
        this.isRestTimer = isRestTimer;
        this.restTime = restTime;
        this.postion = postion;

    }

    public WorkOut(Parcel parcel){
        //this.date = parcel.readString();
        this.listOfExercises = parcel.readArrayList(null);
        this.exerciseAttributes = parcel.readArrayList(null);
        this.index = parcel.readInt();
        this.spotsClicked = parcel.readArrayList(null);
        this.name = parcel.readString();
        this.isRestTimer = parcel.readArrayList(null);
        this.restTime = parcel.readArrayList(null);
        this.postion = parcel.readInt();

    }

   // public void setDate(String date){
     //   this.date = date;
    //}
    //public String getDate(){
      //  return date;
    //}
    public void setPostion(int postion){
        this.postion = postion;
    }
    public int getPostion(){
        return postion;
    }

    public void setIsRestTimer(boolean isRestTimer){
        if(isRestTimer){
            this.isRestTimer.add(0,1);
        }else{
            this.isRestTimer.add(0,0);
        }
    }
    public void setIsRestTimer(int isRestTimer){
        this.isRestTimer.add(0,isRestTimer);
    }
    public ArrayList<Integer> getIsRestTimer(){
        return isRestTimer;
    }
    public ArrayList<Integer> getRestTime(){
        return restTime;
    }
    public void setRestTime(int restTime){
        this.restTime.add(0,restTime);
    }

    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public ArrayList<Integer> getExerciseAttributes(){
        return exerciseAttributes;
    }
    public void addExercise(String exercise){
        listOfExercises.add(0,exercise);//just changed to zero was index
    }
    public String getExercise(){
        //TODO:NEEED TO IMPLEMENT
        return null;
    }
    public ArrayList<String> getListOfExercises(){

        return listOfExercises;
    }
    public void addExerciseAttributes(int zero,int one,int two){
        exerciseAttributes.add((index*3)+0,zero);//reps
        exerciseAttributes.add((index*3)+1,one);//weight
        exerciseAttributes.add((index*3)+2,two);//sets

        index += 1;
    }

    public void printWorkOut(){
        if(listOfExercises != null) {
           // Log.i("", "The Date is: " + date);
            Log.i("","THe Index is: " + index);
            Log.i("", "The exercises are: ");
            for (int i = 0; i < listOfExercises.size(); i++) {
                Log.i("", listOfExercises.get(i));
            }
            Log.i("", "The Attrubuites are: ");
            Log.i("","The size of exerciseAttributes is: "+ exerciseAttributes.size());

            for (int i = 0; i < exerciseAttributes.size(); i=i+3) {
                Log.i("","This For Loop Wass Entered");
                Log.i("", "Reps: " + exerciseAttributes.get(i));
                Log.i("", "Weight: " + exerciseAttributes.get(i+1));
                Log.i("", "Sets: " + exerciseAttributes.get(i+2));
            }
        }else{
            Log.i("","You Havent Picked any Exercises");
        }

    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
       // dest.writeString(date);
        dest.writeList(listOfExercises);
        dest.writeList(exerciseAttributes);
        dest.writeInt(index);
        dest.writeList(spotsClicked);
        dest.writeString(name);
        dest.writeList(isRestTimer);
        dest.writeList(restTime);
        dest.writeInt(postion);
    }

    public static Creator<WorkOut> CREATOR = new Creator<WorkOut>() {

        @Override
        public WorkOut createFromParcel(Parcel source) {
            return new WorkOut(source);
        }

        @Override
        public WorkOut[] newArray(int size) {
            return new WorkOut[size];
        }

    };

}
