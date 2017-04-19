package com.softwareacademy.maps.asynctaskdowload;


import android.os.AsyncTask;
import android.util.Log;

import java.lang.ref.WeakReference;

/**
 *
 */

public class ExampleAsyncTask extends AsyncTask<Void, Integer, Void> {

    public static final String ASYNC_TASK = "ASYNC_TASK";
    private WeakReference<ProgressInterface> mainActivityWeakReference;

    private boolean stopFlag;

    public ExampleAsyncTask(ProgressInterface mainActivity) {
        this.mainActivityWeakReference = new WeakReference<ProgressInterface>(mainActivity);
    }


    public void setStopFlag(boolean stopFlag) {
        this.stopFlag = stopFlag;
    }

    @Override
    protected Void doInBackground(Void... params) {
        for (int i = 0; i < 100; i++) {

            String textToShow = String.format("to jest krok numer %d na wątku %s",
                    i, Thread.currentThread().getName());

            publishProgress(i);
            Log.d(ASYNC_TASK, textToShow);
            if(isCancelled()){
                return null;
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return null;
    }



    @Override
    protected void onProgressUpdate(Integer... values) {
        if (values.length > 0) {
            String textToShow = String.format("to jest krok numer %d na wątku %s",
                    values[0], Thread.currentThread().getName());
            ProgressInterface mainActivity = mainActivityWeakReference.get();
            if(mainActivity !=null){
                mainActivity.setText(textToShow);
            }
        }
    }
}
