package com.example.elisandler.inspirationpal;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.NumberPicker;
import android.widget.TextView;

import kankan.wheel.widget.OnWheelChangedListener;
import kankan.wheel.widget.WheelView;
import kankan.wheel.widget.adapters.NumericWheelAdapter;


public class SetUpExercise extends ActionBarActivity {

    public static WorkOut workout;

    int repsDone;
    int weightDone;
    int setsDone;
    WheelView weight;
    WheelView reps;
    WheelView sets;
    WheelView timer;
    int restTimer;
    CheckBox check;
    boolean checkbox = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_up_exercise);

        Intent i = getIntent();
        workout = i.getParcelableExtra("theExtra");






        weight = (WheelView) findViewById(R.id.weight);
        reps = (WheelView) findViewById(R.id.reps);
        sets = (WheelView) findViewById(R.id.sets);
        timer = (WheelView) findViewById(R.id.timer);
        check = (CheckBox) findViewById(R.id.checkBox);

        updateWhight(weight);
        weight.setCurrentItem(75);

        updateReps(reps);
        reps.setCurrentItem(10);

        updateSets(sets);
        sets.setCurrentItem(1);

        updateTimer(timer);
        timer.setCurrentItem(30);
        timer.setVisibility(View.GONE);

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_set_up_exercise, menu);
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

        return super.onOptionsItemSelected(item);
    }

    void updateWhight(WheelView wheight) {
        //Calendar calendar = Calendar.getInstance();
        //calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) + year.getCurrentItem());
        //calendar.set(Calendar.MONTH, month.getCurrentItem());

        int maxDays = 500;
        wheight.setViewAdapter(new NumericWheelAdapter(this,0,250));
        int curDay = Math.min(maxDays, wheight.getCurrentItem()+1);//this was +1
        wheight.setCurrentItem(curDay);//this was -2
    }

    void updateReps(WheelView reps) {
        //Calendar calendar = Calendar.getInstance();
        //calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) + year.getCurrentItem());
        //calendar.set(Calendar.MONTH, month.getCurrentItem());

        int maxReps = 50;
        reps.setViewAdapter(new NumericWheelAdapter(this,0,50));
        int curDay = Math.min(maxReps, reps.getCurrentItem()+1);
        reps.setCurrentItem(curDay-1, true);//this was -1

    }

    void updateSets(WheelView sets) {
        //Calendar calendar = Calendar.getInstance();
        //calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) + year.getCurrentItem());
        //calendar.set(Calendar.MONTH, month.getCurrentItem());

        int maxReps = 15;
        sets.setViewAdapter(new NumericWheelAdapter(this,0,15));
        int curDay = Math.min(maxReps, reps.getCurrentItem()+1);
        sets.setCurrentItem(curDay-1, true);//this was -1

    }

    void updateTimer(WheelView timer) {
        //Calendar calendar = Calendar.getInstance();
        //calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) + year.getCurrentItem());
        //calendar.set(Calendar.MONTH, month.getCurrentItem());

        int maxReps = 90;
        timer.setViewAdapter(new NumericWheelAdapter(this,0,90));
        int curDay = Math.min(maxReps, timer.getCurrentItem()+1);
        timer.setCurrentItem(curDay-1, true);//this was -1

    }

    public void onClickFinishButton(View v){//I need to update workout so that it will pick up the restTimer number
        weightDone = weight.getCurrentItem();
        repsDone = reps.getCurrentItem();
        setsDone = sets.getCurrentItem();
        restTimer = timer.getCurrentItem();
        workout.setIsRestTimer(checkbox);
        workout.setRestTime(restTimer);
        workout.addExerciseAttributes(repsDone,weightDone,setsDone);
        Intent intent = new Intent(SetUpExercise.this,ClickableCreateWorkOutList.class);
        intent.putExtra("myExtra", workout);
        //Log.i("", "this is finished");
        startActivity(intent);
      //  finish();

        //Log.i("", "this is after the finish()Method");
    }

    public void onRestCheckbox(View v){

        if(checkbox == false){
            checkbox = true;
            check.setChecked(true);
            timer.setVisibility(View.VISIBLE);
        } else{
            checkbox = false;
            check.setChecked(false);
            timer.setVisibility(View.GONE);
        }
    }







}
