package com.example.myapplication;

import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();
    public static final String KEY_CHRONOMETER_BASE = "chronometer base";
    public static final String KEY_CHRONOMETER_ON = "chronometer on";
    public static final String KEY_CHRONOMETER_NICE = "chronometer real time";
    private Button start;
    private Button stop;
    private Button reset;
    private Chronometer timer;
    private long base = 0;
    private int isRunning;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wireWidgets();
        setListeners();
        Log.d(TAG, "onCreate: ");

        if (savedInstanceState != null) {
            timer.setBase(savedInstanceState.getLong(KEY_CHRONOMETER_BASE));
            //if (savedInstanceState.getInt(KEY_CHRONOMETER_ON) == 1)

                timer.start();
        }
//        else{
//            base = savedInstanceState.getLong(KEY_CHRONOMETER_NICE);
//            timer.setBase(savedInstanceState.getLong(KEY_CHRONOMETER_NICE) + SystemClock.elapsedRealtime());
//        }


        // if the savedInstanceState isn't null
        //pull out the value of the base that we saved from the bundle
        //set the chronometer's base to that value
        //start the chronometer
        // next functionality would be to also store in the bundle
        //whether it was running or stopped to decide if you should
        //start it or not in onCreate

    }

    private void setListeners() {
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isRunning = 1;
                timer.setBase(SystemClock.elapsedRealtime() + base);
                timer.start();
                start.setEnabled(false);
                reset.setEnabled(true);
                stop.setEnabled(true);


            }
        });


        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isRunning = 0;
                base = timer.getBase() - SystemClock.elapsedRealtime();
                timer.stop();
                start.setEnabled(true);
                reset.setEnabled(true);
                stop.setEnabled(false);

            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timer.setBase(SystemClock.elapsedRealtime());
                base = 0;
                start.setEnabled(true);
                reset.setEnabled(true);
                stop.setEnabled(true);

            }
        });
    }

    private void wireWidgets() {
        start = findViewById(R.id.button_main_start);
        stop = findViewById(R.id.button_main_stop);
        reset = findViewById(R.id.button_main_reset);
        timer = findViewById(R.id.chronometer_main_timer);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    // activity is now running

    // another activity is covering part of this activity
    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    // this activity is completely hidden
    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    // when activity is finished
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        base = timer.getBase() - SystemClock.elapsedRealtime();
        outState.putLong(KEY_CHRONOMETER_BASE, SystemClock.elapsedRealtime() + base);
        outState.putInt(KEY_CHRONOMETER_ON, isRunning);
        outState.putLong(KEY_CHRONOMETER_NICE, base);




    }
}