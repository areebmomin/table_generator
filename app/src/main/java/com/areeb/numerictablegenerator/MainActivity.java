package com.areeb.numerictablegenerator;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    final int tableCountToDisplay = 10;
    ListView tableListView;
    ArrayAdapter<String> arrayAdapter;
    ArrayList<String> tableContents;
    long maxValue;
    int mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initializing xml views
        SeekBar tableSeekBar = findViewById(R.id.tableSeekBar);
        tableListView = findViewById(R.id.tableListView);
        EditText editTextNumber = findViewById(R.id.editTextNumber);

        // EditText text change listener
        editTextNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().isEmpty())
                    maxValue = Long.parseLong(s.toString());

                if (mProgress > 0)
                    generateTimeTables(mProgress);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //initialize listView and adapter
        tableContents = new ArrayList<>();
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tableContents);

        //setting initial maximum number for table
        tableSeekBar.setMax(tableCountToDisplay);

        //SeekBar onChangeListener
        tableSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mProgress = progress;
                generateTimeTables(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void generateTimeTables(long timeTableNumber)
    {
        //clear previous data
        tableContents.clear();

        //remove all the values from list of number is 0
        if (timeTableNumber <= 0)
        {
            tableListView.setAdapter(arrayAdapter);
            return;
        }

        if (maxValue > 10) {
            timeTableNumber = maxValue - (10 - timeTableNumber);
        }

        //calculate all the values
        for (int i = 1; i <= 10; i++)
        {
            tableContents.add(Long.toString(timeTableNumber * i));
        }

        //set calculated values in ListView
        tableListView.setAdapter(arrayAdapter);
    }
}