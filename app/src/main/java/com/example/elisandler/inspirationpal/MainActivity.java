package com.example.elisandler.inspirationpal;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends ActionBarActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (android.os.Build.VERSION.SDK_INT > 9) {//something i added for internet access??? i think?
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        boolean previouslyStarted = prefs.getBoolean(getString(R.string.pref_previously_started), false);
        if(!previouslyStarted) {
            SharedPreferences.Editor edit = prefs.edit();
            edit.putBoolean(getString(R.string.pref_previously_started), Boolean.TRUE);
            edit.commit();
            FirstUseOfApp();

        }else {
            DatabaseHandler db = new DatabaseHandler(this);
            setTitle(db.getUserName());
        }



    }
    @Override
    public void onResume(){
        super.onResume();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        boolean previouslyStarted = prefs.getBoolean(getString(R.string.pref_previously_started), false);
        if(!previouslyStarted) {
            SharedPreferences.Editor edit = prefs.edit();
            edit.putBoolean(getString(R.string.pref_previously_started), Boolean.TRUE);
            edit.commit();
            FirstUseOfApp();

        }

    }


    public void FirstUseOfApp(){
        Intent intent = new Intent(this, InitialSetup.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void OnWorkOutClick(View v){
        Intent intent = new Intent(this, recordWorkOut.class);
        startActivity(intent);
    }
    public void OnCreateWorkout(View v){
        Intent intent = new Intent(this, ClickableCreateWorkOutList.class);
        startActivity(intent);
    }

    public void onChooseWorkOut(View v){
        //Log.i("","this does not do anything right now");

        Intent intent = new Intent(MainActivity.this,CreateWorkout.class);
        WorkOut workOut = new WorkOut();
        intent.putExtra("myExtra",workOut);
        startActivity(intent);
    }

    public void onCalendarButtonClick(View v){
        Intent intent = new Intent(MainActivity.this,myCalendar.class);
        startActivity(intent);
    }
    public void onGraphButtonClick(View v){
        Intent intent = new Intent(MainActivity.this,HistoryGraph.class);
        startActivity(intent);
    }
}
