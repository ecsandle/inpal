package com.example.elisandler.inspirationpal;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Calendar;

import kankan.wheel.widget.OnWheelChangedListener;
import kankan.wheel.widget.WheelView;
import kankan.wheel.widget.adapters.NumericWheelAdapter;


public class Squat extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_squat);

        final WheelView whight = (WheelView) findViewById(R.id.whight);
        final WheelView reps = (WheelView) findViewById(R.id.reps);

        OnWheelChangedListener listener = new OnWheelChangedListener() {
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                updateWhight( whight);
            }
        };

        updateWhight( whight);
        whight.setCurrentItem(100);

        updateReps(reps);
        reps.setCurrentItem(5);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_squat, menu);
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
        wheight.setViewAdapter(new DateNumericAdapter(this, 1, maxDays, 100));
        int curDay = Math.min(maxDays, wheight.getCurrentItem() + 1);
        wheight.setCurrentItem(curDay - 1, true);
    }

    void updateReps(WheelView reps) {
        //Calendar calendar = Calendar.getInstance();
        //calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) + year.getCurrentItem());
        //calendar.set(Calendar.MONTH, month.getCurrentItem());

        int maxReps = 50;
        reps.setViewAdapter(new DateNumericAdapter(this, 1, maxReps, 10));
        int curDay = Math.min(maxReps, reps.getCurrentItem() + 1);
        reps.setCurrentItem(curDay - 1, true);
    }



    private class DateNumericAdapter extends NumericWheelAdapter {
        // Index of current item
        int currentItem;
        // Index of item to be highlighted
        int currentValue;

        /**
         * Constructor
         */
        public DateNumericAdapter(Context context, int minValue, int maxValue, int current) {
            super(context, minValue, maxValue);
            this.currentValue = current;
            setTextSize(16);
        }

        @Override
        protected void configureTextView(TextView view) {
            super.configureTextView(view);
            if (currentItem == currentValue) {
                view.setTextColor(0xFF0000F0);
            }
            view.setTypeface(Typeface.SANS_SERIF);
        }

        @Override
        public View getItem(int index, View cachedView, ViewGroup parent) {
            currentItem = index;
            return super.getItem(index, cachedView, parent);
        }
    }
}
