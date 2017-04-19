package com.softwareacademy.maps.asynctaskdowload;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements ProgressInterface {


    private TextView textView;
    private ProgressBar progressBar;
    private FactorialAsyncTask factorialAsyncTask;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        factorialAsyncTask = new FactorialAsyncTask(this);


    }

    @Override
    protected void onResume() {
        super.onResume();
        Integer[] arrayOfInts = new Integer[100000];
        Integer[] array = {12, 1, 53, 2, 8, 26, 17, 11, 489, 2, 11, 23, 353, 223, 3455, 11};
        for (int i = 0; i < 100000; i++) {
            arrayOfInts[i] = array[i % 16];

        }
        factorialAsyncTask.execute(arrayOfInts);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }


    @Override
    public void setText(String text) {
        textView.setText(text);
    }

    @Override
    public void updateProgress(int progress) {
        progressBar.setProgress(progress);
    }
}
