package com.example.elisandler.inspirationpal;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class recordWorkOut extends ActionBarActivity {

    //String[] workouts;    // = {"Squat", "Leg Press", "Lunge","Mile Run", "Deadlift","Leg Extension","Leg Curl", "Standing Calf Raise", "Hip Adductor", "Bench Press", "Chest Fly","Push-Up", "Pulldown","Pull-Up", "Shoulder Press", "Shoulder Fly", "Lateral Raise", "Shoulder Shrug", "Pushdown", "Triceps Extension", "Bieceps Curl", "Crunch", "Russian Twist", "Leg Raise", "Back Extension"};
    ArrayList<String> workouts = new ArrayList<>();
    View frameForList;
    ListView lv;
    ArrayAdapter adapter;
    String clickedListItem;
    WorkOut workout;
    //true = weight exsersise , false = cardio
    //boolean[] weightArray = {true,true,true,false,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_work_out);

        DatabaseHandler db = new DatabaseHandler(this);
         workout = db.getAllFromWorkOut();

        workouts = workout.getListOfExercises();

        frameForList = findViewById(R.id.frameForList);
        lv = (ListView)frameForList.findViewById(R.id.listView);
        adapter = new ArrayAdapter(frameForList.getContext(),android.R.layout.simple_list_item_1,workouts);
        lv.setAdapter(adapter);
      //  lv.setTextFilterEnabled(true);
        lv.setOnItemClickListener(new ListClickHandler());


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_record_work_out, menu);
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


    public class ListClickHandler implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> arrayAdapter, View view, int position, long arg3) {
            // TODO Auto-generated method stub
            clickedListItem = (String) lv.getItemAtPosition(position);
            Log.i("", "this is the clickedListItem: " + clickedListItem);

            /*
            switch(position){
                case 0:
                    Intent newActivity0 = new Intent(recordWorkOut.this ,Squat.class);
                    startActivity(newActivity0);
                    break;
                case 11:
                    Intent newActivity1 = new Intent(recordWorkOut.this,PushUp.class);
                    startActivity(newActivity1);
                    break;
            }
*/
           // if(weightArray[position]){
                Intent newWeightActivity = new Intent(recordWorkOut.this, Weights.class);
                newWeightActivity.putExtra("WorkoutDataFromRecordWorkOut",workout);
                startActivity(newWeightActivity);
            //}
            //else{
                //Intent newCardioActivity = new Intent(recordWorkOut.this, Cardio.class);
              //  startActivity(newCardioActivity);
            //}



        }

    }
}
