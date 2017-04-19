package com.softwareacademy.maps.asynctaskdowload;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements ProgressInterface {


    private TextView textView;
    private ExampleAsyncTask exampleAsyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView);
        exampleAsyncTask = new ExampleAsyncTask(this);
        exampleAsyncTask.execute();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        exampleAsyncTask.setStopFlag(true);
    }

    public void setTextOnTextView(String text) {

    }

    @Override
    public void setText(String text) {
        textView.setText(text);
    }
}
