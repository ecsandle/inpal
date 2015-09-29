package com.example.elisandler.inspirationpal;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;


public class InitialSetup extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial_setup);

        if (android.os.Build.VERSION.SDK_INT > 9) {//something i added for internet access??? i think?
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_initial_setup, menu);
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

    public void onInitialSetupDoneButtonClick(View v){
        DatabaseHandler db = new DatabaseHandler(this);
        EditText height = (EditText) findViewById(R.id.editTextHeight);
        EditText age = (EditText) findViewById(R.id.editTextAge);
        EditText gender = (EditText) findViewById(R.id.editTextGender);
        EditText weight = (EditText) findViewById(R.id.editTextWeight);
        EditText UserName = (EditText) findViewById(R.id.editTextUsername);

        int genderInt = 0;

        if(gender.getText().toString().equals("male") ||gender.getText().toString().equals("Male")){
            genderInt = 1;
        }else{
            genderInt = 0;
        }

        double percentBodyFat = 0;
        percentBodyFat = calculateBodyFatPercent(Integer.parseInt(weight.getText().toString()),Integer.parseInt(height.getText().toString()),Integer.parseInt(age.getText().toString()), genderInt);
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();

        db.addUserName(UserName.getText().toString());
        db.addUserData(Integer.parseInt(height.getText().toString()), Integer.parseInt(weight.getText().toString()), percentBodyFat, Integer.parseInt(age.getText().toString()),genderInt, date);

        //TODO:FIX
        //If the user presses the back button durring the dialog and stays on the InitailSetup activity than presses the done button again,
        //the same info could be added to the database for a second time or more

        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        //TODO:Make tutorial
                        Intent intent1 = new Intent(InitialSetup.this, MainActivity.class);
                        startActivity(intent1);
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        Intent intent = new Intent(InitialSetup.this, MainActivity.class);
                        startActivity(intent);
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(InitialSetup.this);
        builder.setMessage("Do You Want A Quick Tutorial?").setPositiveButton("Yes", dialogClickListener).setNegativeButton("No", dialogClickListener).show();


    }

    public double calculateBodyFatPercent(int weight, int height, int age, int gender){
        double bmi = (weight/(height*height))*703;
        double result = (1.20*bmi)+(0.23*age)-(10.8*gender)-5.4;
        return result;
    }

}
