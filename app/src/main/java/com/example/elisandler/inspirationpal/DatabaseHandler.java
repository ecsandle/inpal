package com.example.elisandler.inspirationpal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by elisandler on 7/9/15.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 10;//this needs to move up to 10 before i run it again

    // Database Name
    private static final String DATABASE_NAME = "WorkOutManager";

    // Workout table name
    private static final String TABLE_WORKOUT = "workout";

    private static final String KEY_EXERCISE = "exercise";
    private static final String KEY_REPS = "reps";
    private static final String KEY_WEIGHT = "weight";
    private static final String KEY_SETS = "sets";
    private static final String KEY_NAME = "name";
    private static final String KEY_ISRESTTIMER = "isRestTimer";
    private static final String KEY_RESTTIME = "restTime";

    private static final String TABLE_DATE ="date";
    private static final String KEY_DATE = "date";
    //private static final String KEY_NAME = "name";

    private static final String TABLE_USERDATA = "userData";
    private static final String KEY_HEIGHT = "height";
    private static final String KEY_BODYWEIGHT = "bodyWeight";
    private static final String KEY_PERCENTBODYFAT = "percentBodyFat";
    private static final String KEY_AGE = "age";
    private static final String KEY_GENDER = "gender";
    //private static final String KEY_DATE = "date";

    private static final String TABLE_USERNAME = "userName";
    private static final String KEY_USERNAME = "userName";

    private static final String TABLE_MAXDATA = "maxData";//this will be the table that keeps track of all the exercises if new ones are added
    //private static final String KEY_EXERCISE = "exercise";
    private static final String KEY_WEIGHTMAX = "weightMax";
    private static final String KEY_REPSMAX = "repsMax";

    private static final String TABLE_HISTORYOFWORKOUTS = "historyOfWorkouts";
    //private static final String KEY_DATE = "date";
    //private static final String KEY_EXERCISE = "exercise";
    private static final String KEY_OVERALLVOLUMELIFTED = "overallVolumeLifted";
    private static final String KEY_OVERALLTIME = "overallTime";

    private static final String TABLE_HISTORYEXERCISEDATA = "historyExerciseData";
    //private static final String KEY_DATE = "date";
    //private static final String KEY_EXERCISE = "exercise";
    //private static final String KEY_REPS = "reps";
    //private static final String KEY_WEIGHT = "weight";
    //private static final String KEY_SETS = "sets";
    private static final String KEY_TOTALVOLUMELIFTEDFOREXERCISE = "totalVolumeLiftedForExercise";
    private static final String KEY_TIMEOFEXERCISE = "timeOfExercise";
    private static final String KEY_PERCENTBODYWEIGHTLIFTED = "percentBodyWeightLifted";



    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_WORKOUT_TABLE = "CREATE TABLE " + TABLE_WORKOUT + "(" + KEY_EXERCISE + " TEXT," + KEY_REPS + " INTEGER," +KEY_WEIGHT + " INTEGER," + KEY_SETS+ " INTEGER," + KEY_NAME+ " Text,"  +KEY_ISRESTTIMER + " INTEGER,"+KEY_RESTTIME+ " INTEGER" +")";
        db.execSQL(CREATE_WORKOUT_TABLE);

        String CREATE_DATE_TABLE = "CREATE TABLE "+ TABLE_DATE + "(" + KEY_DATE + " TEXT,"+KEY_NAME+ " TEXT" + ")";
        db.execSQL(CREATE_DATE_TABLE);

        String CREATE_USERDATA_TABLE = "CREATE TABLE "+ TABLE_USERDATA + "("+ KEY_HEIGHT+ " INTEGER,"+ KEY_BODYWEIGHT+ " INTEGER,"+ KEY_PERCENTBODYFAT+" DOUBLE,"+ KEY_AGE+ " INTEGER,"+ KEY_GENDER+ " INTEGER,"+KEY_DATE+ " TEXT"+")";
        db.execSQL(CREATE_USERDATA_TABLE);

        String CREATE_MAXDATA_TABLE = "CREATE TABLE " + TABLE_MAXDATA + "("+KEY_EXERCISE+" TEXT,"+ KEY_REPSMAX+" INTEGER,"+ KEY_WEIGHTMAX+ " INTEGER"+")";
        db.execSQL(CREATE_MAXDATA_TABLE);

        String CREATE_HISTORYOFWORKOUTS_TABLE = "CREATE TABLE "+ TABLE_HISTORYOFWORKOUTS + "("+KEY_DATE+ " DATE,"+KEY_EXERCISE+ " TEXT,"+KEY_OVERALLVOLUMELIFTED+" INTEGER,"+ KEY_OVERALLTIME+" INTEGER"+")";
        db.execSQL(CREATE_HISTORYOFWORKOUTS_TABLE);

        String CREATE_HISTORYEXERCISEDATA_TABLE = "CREATE TABLE "+ TABLE_HISTORYEXERCISEDATA + "("+KEY_DATE+" DATE,"+KEY_EXERCISE+" TEXT,"+KEY_REPS+" INTEGER,"+KEY_WEIGHT+" INTEGER,"+KEY_SETS+ " INTEGER,"+KEY_TOTALVOLUMELIFTEDFOREXERCISE+ " INTEGER"+KEY_TIMEOFEXERCISE+ " INTEGER,"+ KEY_PERCENTBODYWEIGHTLIFTED+ " INTEGER"+")";
        db.execSQL(CREATE_HISTORYEXERCISEDATA_TABLE);

        String CREATE_USERNAME_TABLE = "CREATE TABLE "+ TABLE_USERNAME + "("+ KEY_USERNAME+ " STRING"+")";
        db.execSQL(CREATE_USERNAME_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WORKOUT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DATE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERDATA);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MAXDATA);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HISTORYOFWORKOUTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HISTORYEXERCISEDATA);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERNAME);


        // Create tables again
        onCreate(db);
    }

    public void addWorkout(WorkOut workout){
        SQLiteDatabase db = this.getWritableDatabase();

        for(int i = 0; i < workout.listOfExercises.size();i++){
            ContentValues values = new ContentValues();
          //  values.put(KEY_DATE, workout.getDate());
            values.put(KEY_EXERCISE, workout.listOfExercises.get(i));
            values.put(KEY_REPS, workout.exerciseAttributes.get(i*3));
            values.put(KEY_WEIGHT, workout.exerciseAttributes.get((i*3)+1));
            values.put(KEY_SETS,workout.exerciseAttributes.get((i*3)+2));
            values.put(KEY_NAME,workout.getName());
            values.put(KEY_ISRESTTIMER, workout.getIsRestTimer().get(i));
            values.put(KEY_RESTTIME, workout.getRestTime().get(i));
            db.insert(TABLE_WORKOUT,null, values);
        }

        db.close();
    }

    public WorkOut getAllFromWorkOut(){
        SQLiteDatabase db = getReadableDatabase();
        String selectQuery = "SELECT  * FROM " + TABLE_WORKOUT;
        Cursor cursor = db.rawQuery(selectQuery, null);
        WorkOut workout = new WorkOut();

        if (cursor.moveToFirst()) {
            do {
                workout.addExercise(cursor.getString(0));
                workout.addExerciseAttributes(Integer.parseInt(cursor.getString(1)), Integer.parseInt(cursor.getString(2)), Integer.parseInt(cursor.getString(3)));
                workout.setName(cursor.getString(4));
                workout.setIsRestTimer(Integer.parseInt(cursor.getString(5)));
                workout.setRestTime(Integer.parseInt(cursor.getString(6)));


            } while (cursor.moveToNext());
        }


        return workout;
    }

    public ArrayList<String> getAllWorkOutNames(){
        SQLiteDatabase db = getReadableDatabase();
        String selectQuery = "SELECT DISTINCT "+KEY_NAME+" FROM "+ TABLE_WORKOUT;
        Cursor cursor = db.rawQuery(selectQuery,null);
        ArrayList<String> returnlist = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {

                returnlist.add(cursor.getString(0));

            } while (cursor.moveToNext());
        }

        return returnlist;
    }

    public void addWorkoutToDate(String date, String name){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_DATE,date);
        values.put(KEY_NAME,name);
        db.insert(TABLE_DATE,null,values);
        db.close();

    }

    public ArrayList<String> getDate(String date){
        SQLiteDatabase db = getReadableDatabase();
        String selectQuery = "SELECT * FROM "+TABLE_DATE +" WHERE "+KEY_DATE +"="+ "'"+date+"'";//this can be injected into right now, should fix
        Cursor cursor = db.rawQuery(selectQuery,null);
        ArrayList<String> returnlist = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {

                returnlist.add(cursor.getString(0));
                returnlist.add(cursor.getString(1));

            } while (cursor.moveToNext());
        }

        return returnlist;//it is very likly that this returns NULL could be a problem

    }





    public WorkOut getWorkoutByName(String name){//Im trying to fix apostrohe bug, old code bellow
        SQLiteDatabase db = getReadableDatabase();
        String selectQuery = "SELECT * FROM "+TABLE_WORKOUT+" WHERE "+KEY_NAME+ "=?";
        Cursor cursor = db.rawQuery(selectQuery,new String [] {String.valueOf(name)});
        WorkOut workout = new WorkOut();


        if (cursor.moveToFirst()) {
            do {

                workout.addExercise(cursor.getString(0));
                workout.addExerciseAttributes(Integer.parseInt(cursor.getString(1)),Integer.parseInt(cursor.getString(2)),Integer.parseInt(cursor.getString(3)));
                workout.setName(cursor.getString(4));
                workout.setIsRestTimer(Integer.parseInt(cursor.getString(5)));
                workout.setRestTime(Integer.parseInt(cursor.getString(6)));

            } while (cursor.moveToNext());
        }



        return workout;
    }

    /*
    public WorkOut getWorkoutByName(String name){//Im trying to fix apostrohe bug, this is the old code
        SQLiteDatabase db = getReadableDatabase();
        String selectQuery = "SELECT * FROM "+TABLE_WORKOUT+" WHERE "+KEY_NAME+ "="+ "'"+name+"'";
        Cursor cursor = db.rawQuery(selectQuery,null);
        WorkOut workout = new WorkOut();


        if (cursor.moveToFirst()) {
            do {

                workout.addExercise(cursor.getString(0));
                workout.addExerciseAttributes(Integer.parseInt(cursor.getString(1)),Integer.parseInt(cursor.getString(2)),Integer.parseInt(cursor.getString(3)));
                workout.setName(cursor.getString(4));
                workout.setIsRestTimer(Integer.parseInt(cursor.getString(5)));
                workout.setRestTime(Integer.parseInt(cursor.getString(6)));

            } while (cursor.moveToNext());
        }



        return workout;
    }
     */





    //TODO: implement the methods for the new tables

    public void addMaxData(String exercise, int repsMax, int weightMax){
        SQLiteDatabase db = this.getWritableDatabase();
        String getExercise = "SELECT * FROM "+TABLE_MAXDATA+ " WHERE "+ KEY_EXERCISE+ "='"+exercise+"'";
        int repsnum = 0;
        int weightnum = 0;

        Cursor cursor = db.rawQuery(getExercise, null);

        if (cursor.moveToFirst()) {
            do {
                repsnum = cursor.getInt(1);
                weightnum = cursor.getInt(2);

            } while (cursor.moveToNext());
        }

        String deletExercise = "DELETE FROM "+TABLE_MAXDATA+" WHERE "+ KEY_EXERCISE+"='"+exercise+"'";
        db.execSQL(deletExercise);

        if(repsnum > repsMax){
            repsMax = repsnum;
        }
        if(weightnum > weightMax){
            weightMax = weightnum;
        }

        ContentValues values = new ContentValues();
        values.put(KEY_EXERCISE,exercise);
        values.put(KEY_REPSMAX,repsMax);
        values.put(KEY_WEIGHTMAX,weightMax);
        db.insert(TABLE_MAXDATA, null, values);
    }

    public int[] getWeightRepsMax(String exercise){
        SQLiteDatabase db = getReadableDatabase();
        int[] returnArray = new int[2];
        String getExercise = "SELECT * FROM "+TABLE_MAXDATA+ " WHERE "+ KEY_EXERCISE+ "='"+exercise+"'";
        Cursor cursor = db.rawQuery(getExercise, null);


        if (cursor.moveToFirst()) {
            do {
                returnArray[0] = cursor.getInt(1);//reps
                returnArray[1] = cursor.getInt(2);//weight

            } while (cursor.moveToNext());
        }

        return returnArray;
    }

    public void addToHistoryOfWorkouts(Date date,String exercise, int overallVolumeLifted, int overallTime ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_DATE, String.valueOf(date));//date is a problem
        values.put(KEY_EXERCISE,exercise);
        values.put(KEY_OVERALLVOLUMELIFTED,overallVolumeLifted);
        values.put(KEY_OVERALLTIME,overallTime);
        db.insert(TABLE_HISTORYOFWORKOUTS,null,values);

    }

    public void addUserData(Integer hight, Integer weight, Double percentBodyFat, Integer age, Integer gender, Date date){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_HEIGHT, hight);
        values.put(KEY_BODYWEIGHT,weight);
        values.put(KEY_PERCENTBODYFAT, percentBodyFat);
        values.put(KEY_AGE, age);
        values.put(KEY_GENDER, gender);
        values.put(KEY_DATE, String.valueOf(date));
        db.insert(TABLE_USERDATA,null,values);
    }

    public void addUserName(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_USERNAME, username);
        db.insert(TABLE_USERNAME,null,values);
    }

    public String getUserName(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT "+KEY_USERNAME+" FROM "+ TABLE_USERNAME;
        Cursor cursor = db.rawQuery(query,null);
        String returnString = "";

        if (cursor.moveToFirst()) {
            do {
                returnString = cursor.getString(0);

            } while (cursor.moveToNext());
        }

        return returnString;
    }




}
