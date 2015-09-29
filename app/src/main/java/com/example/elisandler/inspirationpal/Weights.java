package com.example.elisandler.inspirationpal;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

import kankan.wheel.widget.OnWheelChangedListener;
import kankan.wheel.widget.WheelView;
import kankan.wheel.widget.adapters.NumericWheelAdapter;


public class Weights extends ActionBarActivity {

    private Menu menu;
    int repsDone;
    int weightDone;
    int setsDone = 0;
    WheelView weight;
    WheelView reps;
    //WheelView sets;
    WorkOut workout;
    View frameForWeightRowItem;
    ListView lv;
    WeightAdapter adapter;
    DatabaseHandler db;
    String exerciseName;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weights);
        weight = (WheelView) findViewById(R.id.weight);
        reps = (WheelView) findViewById(R.id.reps);
        db = new DatabaseHandler(this);
       // sets = (WheelView) findViewById(R.id.sets);

        Intent intent = getIntent();
        workout =  intent.getParcelableExtra("workoutDataFromDatabase");
        //How do i know which exsersise it is????
        //I should get the parcable info from recordWorkout and then Set THe Title of the Page

        updateWhight(weight);
        weight.setCurrentItem(10);

        updateReps(reps);
        reps.setCurrentItem(5);

       // updateSets(sets);
        //sets.setCurrentItem(1);

        //View button = findViewById(R.id.addSetButton);
        //button.setBackgroundColor(Color.argb(25,43,55,100));

        setTitle(workout.listOfExercises.get((workout.getListOfExercises().size() - 1) - workout.getPostion()));
        exerciseName = workout.listOfExercises.get((workout.getListOfExercises().size() - 1) - workout.getPostion());

        int spot = (workout.getListOfExercises().size()-1) - workout.getPostion();//trying this it seems kinda hacky though

        SetsInfo setsInfos = new SetsInfo();

       // for(int i = 0;i < workout.getListOfExercises().size();i++){
            //exerciseNames.add(0,workout.getListOfExercises().get(i));
           // setsInfos.add(0,setsInfos);//this could be replacing the old SETSINFO object

            for(int j = workout.getExerciseAttributes().get((spot*3)+2);j>=0;j--){//this could be the wrong number of times
                setsInfos.addRep(workout.getExerciseAttributes().get(spot * 3));
                setsInfos.addWeight(workout.getExerciseAttributes().get((spot*3)+1));
            }

      //  }

        frameForWeightRowItem = findViewById(R.id.frameForWeightRowItem);
        lv = (ListView)frameForWeightRowItem.findViewById(R.id.listForWeightRow);
        adapter = new WeightAdapter(setsInfos,frameForWeightRowItem.getContext());
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new ListClickHandler());//TODO I think that i dont actually need this list to be clicked on


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_weights, menu);
        this.menu = menu;


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
        if(id == R.id.exerciseCompleted){
            //TODO:log into database and go back to createworkout page
            Log.i("", "the exercise is done");
        }

        return super.onOptionsItemSelected(item);
    }

    void updateWhight(WheelView wheight) {
        int maxDays = 500;
        wheight.setViewAdapter(new NumericWheelAdapter(this,0,20));
        int curDay = Math.min(maxDays, wheight.getCurrentItem()+1);//this was +1
        wheight.setCurrentItem(curDay);//this was -2
    }

    void updateReps(WheelView reps) {
        int maxReps = 50;
        reps.setViewAdapter(new NumericWheelAdapter(this,0,20));
        int curDay = Math.min(maxReps, reps.getCurrentItem()+1);
        reps.setCurrentItem(curDay-1, true);//this was -1

    }

    //void updateSets(WheelView sets) {
        //Calendar calendar = Calendar.getInstance();
        //calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) + year.getCurrentItem());
        //calendar.set(Calendar.MONTH, month.getCurrentItem());

      //  int maxReps = 15;
      //  sets.setViewAdapter(new NumericWheelAdapter(this,0,15));
      //  int curDay = Math.min(maxReps, reps.getCurrentItem()+1);
      //  sets.setCurrentItem(curDay-1, true);//this was -1

    //}

   // public void OnDoneButtonClick(View v){
     //   weightDone = weight.getCurrentItem();
       // repsDone = reps.getCurrentItem();
        //setsDone = sets.getCurrentItem();
       // Log.i("","The Weight is: "+ weightDone);
       // Log.i("", "The reps is: " + repsDone);
       // Log.i("", "The Sets Selected is: " + setsDone);
    //}

    public void onAddSetClick(View view){
        Log.i("", "the add set button was clicked");

        int[] weightRepsMax;
        weightRepsMax = db.getWeightRepsMax(exerciseName);

        //view.setBackgroundColor(Color.GREEN);

        ViewGroup parent =(ViewGroup) view.getParent();
       // adapter.getView(setsDone,findViewById(R.id.listForWeightRow),parent).findViewById(R.id.backgroundLayout).setBackgroundColor(Color.BLUE);
        View changeingView = adapter.getView(setsDone, findViewById(R.id.frameForWeightRowItem), parent);
        changeingView.setBackgroundResource(R.color.background_material_dark);
        TextView x = (TextView)changeingView.findViewById(R.id.WeightWeightNumber1);
        TextView y = (TextView)changeingView.findViewById(R.id.WeightRepsNumber1);

        Log.i("","this is the weight of the view: "+x.getText().toString());
        Log.i("","this is the reps of the view: "+ y.getText().toString());

        if(Integer.parseInt(y.getText().toString()) > weightRepsMax[0] || Integer.parseInt(x.getText().toString())> weightRepsMax[1]){
            db.addMaxData(exerciseName, Integer.parseInt(y.getText().toString()), Integer.parseInt(x.getText().toString()));
            Toast.makeText(getApplicationContext(), "NEW RECORD", Toast.LENGTH_LONG).show();
        }

//TODO: I should add a checkable box or something into wheightRowItem to signify more or less work, it might be easier


        /*

        if(Integer.parseInt(x.getText().toString()) < weight.getCurrentItem()){
            Log.i("","THis set was less weight than planed");
            x.setTextColor(Color.RED);
            x.setText(Integer.toString(weight.getCurrentItem()));

        }
        if(Integer.parseInt(x.getText().toString()) > weight.getCurrentItem()){
            Log.i("","You did extra weight YEEAH");
            x.setTextColor(Color.GREEN);
            x.setText(Integer.toString(weight.getCurrentItem()));
        }
        if(Integer.parseInt(y.getText().toString()) < reps.getCurrentItem()){
            Log.i("","THis set was less reps than planed");
            y.setTextColor(Color.RED);
            y.setText(Integer.toString(reps.getCurrentItem()));
        }
        if(Integer.parseInt(y.getText().toString()) > reps.getCurrentItem()){
            Log.i("","You didddd extra reps YEEAH");
            y.setTextColor(Color.GREEN);
            y.setText(Integer.toString(reps.getCurrentItem()));
        }
        */

        setsDone++;//if sets done is greater than planed, then it crashes when it should add extra rows

    }

    public void onStartTimerClick(View view){
        Log.i("","the tiemr started");
        if(workout.isRestTimer.get(workout.getPostion()) == 1) {
            CountDownTimer countDownTimer = new CountDownTimer((workout.getRestTime().get(workout.getPostion()))*1000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    Log.i("","This is how much time has elapsed: "+ millisUntilFinished);
                }

                @Override
                public void onFinish() {
                    Log.i("", "The timer is done!");
                    Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    // Vibrate for 500 milliseconds
                    v.vibrate(500);
                    Log.i("","This just vibrated");
                }
            };
            countDownTimer.start();
        }else{
            Log.i("","there was no timer");
        }
    }

    public class ListClickHandler implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> adapter, View view, int position, long arg3) {//this was changed from arrayAdapter
            // TODO Auto-generated method stub
            //String clickedListItem = (String) lv.getItemAtPosition(position);
            //Log.i("", "this is the clickedListItem: " + clickedListItem);
            Log.i("","The item was clicked on"+ position);

            //TODO I think that i dont actually need this list to be clicked on


        }

    }


}


