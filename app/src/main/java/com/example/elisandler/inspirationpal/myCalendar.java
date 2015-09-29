package com.example.elisandler.inspirationpal;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.vdesmet.lib.calendar.DayAdapter;
import com.vdesmet.lib.calendar.MultiCalendarAdapter;
import com.vdesmet.lib.calendar.MultiCalendarView;
import com.vdesmet.lib.calendar.OnDayClickListener;

import java.util.Calendar;


public class myCalendar extends ActionBarActivity{

    private TextView mSelectedTextView;
    private Typeface mSelectedTypeface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_calendar);

        // Retrieve the CalendarView
        final MultiCalendarView multiMonth = (MultiCalendarView) findViewById(R.id.multi_calendar);

// Set the first valid day
        final Calendar firstValidDay = Calendar.getInstance();
        //firstValidDay.add(Calendar.DATE,-50);
        multiMonth.setFirstValidDay(firstValidDay);


// Set the last valid day
        final Calendar lastValidDay = Calendar.getInstance();
        lastValidDay.add(Calendar.MONTH, 30);
        multiMonth.setLastValidDay(lastValidDay);

        // Change typeface
        multiMonth.setTypeface(Typeface.DEFAULT);

        // Create adapter
        //final MultiCalendarAdapter adapter = new MultiCalendarAdapter(this,multiMonth);

        // Set listener and adapter
        multiMonth.setOnDayClickListener(new OnDayClickListener() {
            @Override
            public void onDayClick(long dayInMillis) {
                // Reset the previously selected TextView to his previous Typeface
                if(mSelectedTextView != null) {
                    mSelectedTextView.setTypeface(mSelectedTypeface);
                }

                final TextView day = multiMonth.getTextViewForDate(dayInMillis);

                if(day != null) {
                    // Remember the selected TextView and it's font
                    mSelectedTypeface = day.getTypeface();
                    mSelectedTextView = day;

                    // Show the selected TextView as bold
                    day.setTypeface(Typeface.DEFAULT_BOLD);
                }

                Intent intent = new Intent(myCalendar.this,CreateWorkout.class);
                intent.putExtra("theDate",dayInMillis);
                startActivity(intent);

            }
        });

    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my_calendar, menu);
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
}
