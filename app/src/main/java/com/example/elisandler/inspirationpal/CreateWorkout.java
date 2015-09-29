package com.example.elisandler.inspirationpal;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.DrawableRes;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class CreateWorkout extends ActionBarActivity {

    TextView date;
    Calendar calendar;
    String[] dayOfWeek = {"jkhkjh","Sunday", "Monday" , "Tuesday" , "Wednesday" , "Thursday" , "Friday", "Saturday"};
    String[] month = {"January", "Febuary", "March", "April", "May", "June", "July", "August","September","October", "November","December"};
    //boolean dateForMyUseIsThereAWorkOutForThisDay = true;

    //View createWorkoutLayout;
    View gestureView;
    View frameForList;
    ListView lv;
    ExerciseAdapter adapter;
    ArrayList<SetsInfo> setsInfos = new ArrayList<>();
    ArrayList<String> exerciseNames = new ArrayList<>();
    View chooseWorkoutButton;
    TextView noWorkout;
    String pickedWorkoutName;
    String formattedDate;
    final DatabaseHandler db = new DatabaseHandler(this);
    WorkOut workout;
    boolean workoutStarted = false;
    //SimpleDateFormat simpleDateFormat;
    Date actualDate;
    long calendarClickedDate;
    Menu myMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_workout);

        calendar = Calendar.getInstance();
        //simpleDateFormat = new SimpleDateFormat("MM-dd-yyyy");
        actualDate = new Date();
        Log.i("","this is the actualDate: "+ actualDate);
        Intent intent = getIntent();


        if(intent.hasExtra("theDate")){
            calendarClickedDate = intent.getLongExtra("theDate",0);
            Log.i("","this is what got passed through: "+ calendarClickedDate);
            calendar.setTimeInMillis(calendarClickedDate);

        }

         /*
        I am going to have to change all of the bellow STRING DATES to actuall DATE dates at some point,
        I will need to change it here and in the database
         */
        date = (TextView)findViewById(R.id.date);
        date.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_bright));
        date.setTextColor(getResources().getColor(android.R.color.black));
        //Log.i("", "This is the date: " + calendar.get(calendar.DATE));
        //Log.i("", "This is the Month: " + calendar.get(calendar.MONTH));
        //Log.i("","This is the Day Of week: "+calendar.get(calendar.DAY_OF_WEEK));
        //Log.i("", "This is teh year: " + calendar.get(calendar.YEAR));
        formattedDate = dayOfWeek[calendar.get(calendar.DAY_OF_WEEK)] + ", " + calendar.get(calendar.DATE) + " " + month[calendar.get(calendar.MONTH)] + ", " + calendar.get(calendar.YEAR);
        date.setText(formattedDate);
        ArrayList<String> isWorkout = db.getDate(formattedDate);

        frameForList = findViewById(R.id.frameForList);
        chooseWorkoutButton = findViewById(R.id.chooseWorkout);
        noWorkout = (TextView) findViewById(R.id.noWorkoutNote);
        //createWorkoutLayout = findViewById(R.id.createWorkoutLayout);
        gestureView = findViewById(R.id.gestureOverlayView);

        gestureView.setOnTouchListener(new OnSwipeTouchListener(this) {
            @Override
            public void onSwipeLeft() {
                // Whatever
                Log.i("", "I swiped left");
                mySwipeLeft();
            }

            @Override
            public void onSwipeRight() {
                super.onSwipeRight();
                Log.i("", "I swiped right");
                mySwipeRight();
            }
        });
        frameForList.setOnTouchListener(new OnSwipeTouchListener(this) {
            @Override
            public void onSwipeLeft() {
                // Whatever
                Log.i("", "I swiped left");
                mySwipeLeft();
            }

            @Override
            public void onSwipeRight() {
                super.onSwipeRight();
                Log.i("", "I swiped right");
                mySwipeRight();
            }
        });





        if(!isWorkout.isEmpty()){
            chooseWorkoutButton.setVisibility(View.GONE);
            noWorkout.setVisibility(View.GONE);
            workout = db.getWorkoutByName(isWorkout.get(1));
            //SetsInfo temp = new SetsInfo();

            for(int i = 0;i < workout.getListOfExercises().size();i++){
                exerciseNames.add(0,workout.getListOfExercises().get(i));
                setsInfos.add(0,new SetsInfo());//this could be replacing the old SETSINFO object
                for(int j = workout.getExerciseAttributes().get((i*3)+2);j>=0;j--){//this could be the wrong number of times
                    setsInfos.get(0).addRep(workout.getExerciseAttributes().get(i*3));
                    setsInfos.get(0).addWeight(workout.getExerciseAttributes().get((i*3)+1));
                }

            }


            lv = (ListView)frameForList.findViewById(R.id.listOfExercises);
            adapter = new ExerciseAdapter(setsInfos,exerciseNames,frameForList.getContext());
            lv.setAdapter(adapter);
            lv.setOnItemClickListener(new ListClickHandler());

            //exerciseNames.clear();
            //setsInfos.clear();

            lv.setOnTouchListener(new OnSwipeTouchListener(this) {
                @Override
                public void onSwipeLeft() {
                    // Whatever
                    Log.i("", "I swiped left");
                    mySwipeLeft();
                }

                @Override
                public void onSwipeRight() {
                    super.onSwipeRight();
                    Log.i("", "I swiped right");
                    mySwipeRight();
                }
            });

           // adapter = new ArrayAdapter(frameForList.getContext(),R.layout.listview_item_row,workouts);//this is an array
            //I need a entire class here, that holds the information of
            //ExerciseName
            //Array of Reps_weight_rows Objects
            //I should extend the BaseAdapter class most probably


        }else{//this should be a continued while loop or something with the above if statement, so while there is no date saved

            frameForList.setVisibility(View.GONE);

        }







    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_workout, menu);
        this.myMenu = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if(id == R.id.start){
            startTheWorkout();
        }
        if(id == R.id.menuCalendarButton){
            menuCalendarButton();
        }
        if(id == R.id.menuHomeButton){
            menuHomeButton();
        }

        return super.onOptionsItemSelected(item);
    }

    public void startTheWorkout(){
        //TODO: this shuold make the play icon turn to a stop icon
        workoutStarted = true;
        //myMenu.getItem(R.id.start).setIcon(android.R.drawable.ic_media_pause);//this causese it to crash right now


        Calendar c = Calendar.getInstance();
        int seconds = c.get(Calendar.SECOND);

    }
    public void menuCalendarButton(){
        Intent intent = new Intent(this, myCalendar.class);
        startActivity(intent);
    }
    public void menuHomeButton(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


    public class ListClickHandler implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> adapter, View view, int position, long arg3) {//this was changed from arrayAdapter
            // TODO Auto-generated method stub
            //String clickedListItem = (String) lv.getItemAtPosition(position);
            //Log.i("", "this is the clickedListItem: " + clickedListItem);
            if(workoutStarted) {
                Log.i("", "The item was clicked on");
                workout.setPostion(position);


                Intent nextExercise = new Intent(CreateWorkout.this, Weights.class);
                nextExercise.putExtra("workoutDataFromDatabase", workout);
                startActivity(nextExercise);
            }else{
                Toast.makeText(getApplicationContext(), "Click the start button at top to start the workout, then click on the exercise you want", Toast.LENGTH_LONG).show();
            }
        }

    }


    public void onChooseWorkoutClick(View view){
        //DatabaseHandler db = new DatabaseHandler(this);
        final ArrayList names = db.getAllWorkOutNames();
        //String pickedWorkoutName;

        AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(CreateWorkout.this);
        myAlertDialog.setTitle("Pick a Workout");
       // myAlertDialog.setMessage("Choose a color.");

       // List<String> items = new ArrayList<String>();
        //items.add("RED");
        //items.add("BLUE");
        //items.add("GREEN");

        final ArrayAdapter<String> arrayAdapterItems = new ArrayAdapter<String>(CreateWorkout.this,android.R.layout.simple_expandable_list_item_1, names);
        /*

        myAlertDialog.setNegativeButton("NO",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // do stuff
            }
        });
        myAlertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // do stuff
            }
        });
        */
        myAlertDialog.setAdapter(arrayAdapterItems,
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //do what you want
                        pickedWorkoutName = names.get(which).toString();
                        db.addWorkoutToDate(formattedDate,pickedWorkoutName);

                        exerciseNames.clear();
                        setsInfos.clear();

                        ArrayList<String> isWorkout = db.getDate(formattedDate);

                        if(!isWorkout.isEmpty()){
                            chooseWorkoutButton.setVisibility(View.GONE);
                            noWorkout.setVisibility(View.GONE);
                            frameForList.setVisibility(View.VISIBLE);
                            workout = db.getWorkoutByName(isWorkout.get(1));
                            //SetsInfo temp = new SetsInfo();

                            for(int i = 0;i < workout.getListOfExercises().size();i++){
                                exerciseNames.add(0,workout.getListOfExercises().get(i));
                                setsInfos.add(0,new SetsInfo());//this could be replacing the old SETSINFO object
                                for(int j = workout.getExerciseAttributes().get((i*3)+2);j>=0;j--){//this could be the wrong number of times
                                    setsInfos.get(0).addRep(workout.getExerciseAttributes().get(i*3));
                                    setsInfos.get(0).addWeight(workout.getExerciseAttributes().get((i*3)+1));
                                }

                            }

                            lv = (ListView)frameForList.findViewById(R.id.listOfExercises);
                            adapter = new ExerciseAdapter(setsInfos,exerciseNames,frameForList.getContext());
                            lv.setAdapter(adapter);
                            lv.setOnItemClickListener(new ListClickHandler());



                        }else{//this should be a continued while loop or something with the above if statement, so while there is no date saved

                            frameForList.setVisibility(View.GONE);
                            chooseWorkoutButton.setVisibility(View.VISIBLE);
                            noWorkout.setVisibility(View.VISIBLE);
                        }
                    }
                });
        myAlertDialog.show();

        Log.i("", "This is the name that was picked: " + pickedWorkoutName);


    }


    public void mySwipeRight(){
        Log.i("","the onSwipeRight() was called");
        calendar.add(Calendar.DATE, -1);

        formattedDate = dayOfWeek[calendar.get(Calendar.DAY_OF_WEEK)] + ", " + calendar.get(calendar.DATE) + " " + month[calendar.get(calendar.MONTH)] + ", " + calendar.get(calendar.YEAR);
        date.setText(formattedDate);

        exerciseNames.clear();
        setsInfos.clear();

        ArrayList<String> isWorkout = db.getDate(formattedDate);

        if(!isWorkout.isEmpty()){
            chooseWorkoutButton.setVisibility(View.GONE);
            noWorkout.setVisibility(View.GONE);
            frameForList.setVisibility(View.VISIBLE);
            workout = db.getWorkoutByName(isWorkout.get(1));
            //SetsInfo temp = new SetsInfo();

            for(int i = 0;i < workout.getListOfExercises().size();i++){
                exerciseNames.add(0,workout.getListOfExercises().get(i));
                setsInfos.add(0,new SetsInfo());//this could be replacing the old SETSINFO object
                for(int j = workout.getExerciseAttributes().get((i*3)+2);j>=0;j--){//this could be the wrong number of times
                    setsInfos.get(0).addRep(workout.getExerciseAttributes().get(i*3));
                    setsInfos.get(0).addWeight(workout.getExerciseAttributes().get((i*3)+1));
                }

            }

            lv = (ListView)frameForList.findViewById(R.id.listOfExercises);
            adapter = new ExerciseAdapter(setsInfos,exerciseNames,frameForList.getContext());
            lv.setAdapter(adapter);
            lv.setOnItemClickListener(new ListClickHandler());

           // exerciseNames.clear();
            //setsInfos.clear();

            lv.setOnTouchListener(new OnSwipeTouchListener(this) {
                @Override
                public void onSwipeLeft() {
                    // Whatever
                    Log.i("", "I swiped left");
                    mySwipeLeft();
                }

                @Override
                public void onSwipeRight() {
                    super.onSwipeRight();
                    Log.i("", "I swiped right");
                    mySwipeRight();
                }
            });

            // adapter = new ArrayAdapter(frameForList.getContext(),R.layout.listview_item_row,workouts);//this is an array
            //I need a entire class here, that holds the information of
            //ExerciseName
            //Array of Reps_weight_rows Objects
            //I should extend the BaseAdapter class most probably


        }else{//this should be a continued while loop or something with the above if statement, so while there is no date saved

            frameForList.setVisibility(View.GONE);
            chooseWorkoutButton.setVisibility(View.VISIBLE);
            noWorkout.setVisibility(View.VISIBLE);


        }


    }

    public void mySwipeLeft(){
        Log.i("","the onSwipeLeft() was called");
        calendar.add(Calendar.DATE, +1);

        formattedDate = dayOfWeek[calendar.get(Calendar.DAY_OF_WEEK)] + ", " + calendar.get(calendar.DATE) + " " + month[calendar.get(calendar.MONTH)] + ", " + calendar.get(calendar.YEAR);
        date.setText(formattedDate);

        exerciseNames.clear();
        setsInfos.clear();

        ArrayList<String> isWorkout = db.getDate(formattedDate);

        if(!isWorkout.isEmpty()){
            chooseWorkoutButton.setVisibility(View.GONE);
            noWorkout.setVisibility(View.GONE);
            frameForList.setVisibility(View.VISIBLE);
            workout = db.getWorkoutByName(isWorkout.get(1));
            //SetsInfo temp = new SetsInfo();

            for(int i = 0;i < workout.getListOfExercises().size();i++){
                exerciseNames.add(0,workout.getListOfExercises().get(i));
                setsInfos.add(0,new SetsInfo());//this could be replacing the old SETSINFO object
                for(int j = workout.getExerciseAttributes().get((i*3)+2);j>=0;j--){//this could be the wrong number of times
                    setsInfos.get(0).addRep(workout.getExerciseAttributes().get(i*3));
                    setsInfos.get(0).addWeight(workout.getExerciseAttributes().get((i*3)+1));
                }

            }

            lv = (ListView)frameForList.findViewById(R.id.listOfExercises);
            adapter = new ExerciseAdapter(setsInfos,exerciseNames,frameForList.getContext());
            lv.setAdapter(adapter);
            lv.setOnItemClickListener(new ListClickHandler());

        }else{//this should be a continued while loop or something with the above if statement, so while there is no date saved

            frameForList.setVisibility(View.GONE);
            chooseWorkoutButton.setVisibility(View.VISIBLE);
            noWorkout.setVisibility(View.VISIBLE);

        }

    }










}
