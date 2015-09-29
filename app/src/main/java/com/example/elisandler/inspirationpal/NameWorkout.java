package com.example.elisandler.inspirationpal;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class NameWorkout extends ActionBarActivity {

    public static WorkOut workout;

    public TextView title;
    public View save;
    DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_workout);

        Intent i = getIntent();
        workout = i.getParcelableExtra("myExtra");

        save = findViewById(R.id.saveWorkout);
        title = (TextView) findViewById(R.id.title);

       db = new DatabaseHandler(this);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_name_workout, menu);
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

    public void onSaveClick(View v){
       String name =  title.getText().toString();
        Log.i("", "This is the Title: "+name);
        workout.setName(name);
        db.addWorkout(workout);
        Intent intent = new Intent(NameWorkout.this, MainActivity.class);
        startActivity(intent);
        //this should bring you to the home screen to see your workout saved..


    }

}
