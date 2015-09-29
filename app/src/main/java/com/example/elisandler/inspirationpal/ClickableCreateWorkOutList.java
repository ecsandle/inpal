package com.example.elisandler.inspirationpal;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ListView;


public class ClickableCreateWorkOutList extends ActionBarActivity {
    //this is bad because now if i want to update the list I have to change it in two places, here and recordWorkOut
    public static WorkOut workout;

    String[] workouts = {"Squat", "Leg Press", "Lunge","Mile Run", "Deadlift","Leg Extension","Leg Curl", "Standing Calf Raise", "Hip Adductor", "Bench Press", "Chest Fly","Push-Up", "Pulldown","Pull-Up", "Shoulder Press", "Shoulder Fly", "Lateral Raise", "Shoulder Shrug", "Pushdown", "Triceps Extension", "Bieceps Curl", "Crunch", "Russian Twist", "Leg Raise", "Back Extension"};
    View frameForList;
    ListView lv;
    ArrayAdapter adapter;
    String clickedListItem;
    boolean[] weightArray = {true,true,true,false,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true};
    //boolean[] isSelected = new boolean[workouts.length];
    boolean hasExtra = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clickable_create_work_out_list);

        Intent i = getIntent();
        if(i.hasExtra("myExtra")) {
            workout = i.getParcelableExtra("myExtra");
            hasExtra = true;
        } else{
            workout = new WorkOut();
        }

        frameForList = findViewById(R.id.frameForList);
        lv = (ListView)frameForList.findViewById(R.id.listView);
        adapter = new ArrayAdapter(frameForList.getContext(),android.R.layout.simple_list_item_checked,workouts);
        lv.setAdapter(adapter);
        //  lv.setTextFilterEnabled(true);
        lv.setOnItemClickListener(new ListClickHandler());



        lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);//just added these two lines and they work
        //lv.setItemChecked(0, true);
        if(hasExtra) {
            for (int j = 0; j < workout.spotsClicked.size(); j++) {
                lv.setItemChecked(workout.spotsClicked.get(j), true);
            }
        }


    //    if(savedInstanceState != null){
  //          Log.i("","the instance on create was NOT Null");
//            for(int i=0;i<isSelected.length;i++){

             //   lv.setItemChecked(i,isSelected[i]);
           //     Log.i("","the for loop is running");
         //   }
       // }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_clickable_create_work_out_list, menu);
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
        if(id==R.id.save){
            Log.i("", "the saveAndFinsh button was clicked on");
            onClickSaveAndFinish();

        }

        return super.onOptionsItemSelected(item);
    }

    public void onClickSaveAndFinish(){
        String temp;
        int counter = 0;
        String[] choosenItems = new String[100];
        SparseBooleanArray checked = lv.getCheckedItemPositions();

        for (int i = 0; i < lv.getAdapter().getCount(); i++) {
            if (checked.get(i)) {
                Log.i("","This was checked: "+workouts[i]);
                choosenItems[counter] = workouts[i];
                counter++;
            }
        }
        workout.printWorkOut();

        Intent intent = new Intent(ClickableCreateWorkOutList.this,NameWorkout.class);
        intent.putExtra("myExtra", workout);
        startActivity(intent);

    }



/*
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        Log.i("","the onSavedInstanceState() was called");
        savedInstanceState.putBooleanArray("isSelected",isSelected);

        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
        // Always call the superclass so it can restore the view hierarchy
        super.onRestoreInstanceState(savedInstanceState);
        Log.i("", "the restoreInstance() was called");
        if(savedInstanceState != null){
            Log.i("","the instance on create was NOT Null");
            for(int i=0;i<isSelected.length;i++){

                lv.setItemChecked(i,isSelected[i]);
                Log.i("","the for loop is running");
            }
        }

        // Restore state members from saved instance
        //mCurrentScore = savedInstanceState.getInt(STATE_SCORE);
       // mCurrentLevel = savedInstanceState.getInt(STATE_LEVEL);
    }

*/

//Im not sure tht i even want this...Because i havent decide what i want to do when they are clicked
    public class ListClickHandler implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> arrayAdapter, View view, int position, long arg3) {
            // TODO Auto-generated method stub
            clickedListItem = (String) lv.getItemAtPosition(position);
            Log.i("", "this is the clickedListItem: " + clickedListItem);
            CheckedTextView check = (CheckedTextView)view;
            check.setChecked(check.isChecked());
            workout.addExercise(clickedListItem);
            workout.addSpotClicked(position);
           // isSelected[position] = true;


            if(weightArray[position]){//this will always make a cardio becasue they are both SETUPEXERCISE.class
                Intent newWeightActivity = new Intent(ClickableCreateWorkOutList.this, SetUpExercise.class);
                newWeightActivity.putExtra("theExtra", workout);
                startActivity(newWeightActivity);
            }
            else{
                Intent newCardioActivity = new Intent(ClickableCreateWorkOutList.this, SetUpExercise.class);
                newCardioActivity.putExtra("theExtra", workout);
                startActivity(newCardioActivity);
            }
        }

//there is probably a problem with the attributes exsersise because the OnCreate method does not get called again when
    //the SetUpExercise calls the Finish() method.


    }

}
