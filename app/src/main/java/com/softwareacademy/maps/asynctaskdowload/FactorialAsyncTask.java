package com.softwareacademy.maps.asynctaskdowload;

import android.os.AsyncTask;

/**
 *
 */

public class FactorialAsyncTask extends AsyncTask<Integer, Integer, String> {

    private ProgressInterface progressInterface;

    public FactorialAsyncTask(ProgressInterface progressInterface) {
        this.progressInterface = progressInterface;
    }

    @Override
    protected String doInBackground(Integer... params) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < params.length; i++) {
            if (isCancelled()) {
                return stringBuilder.toString();
            }
            stringBuilder.append("\n");
            int valueToFactor = params[i];
            stringBuilder.append("factor ");
            stringBuilder.append(String.valueOf(valueToFactor));
            stringBuilder.append(" is ");

            if (valueToFactor < 30) {
                stringBuilder.append(factorial(valueToFactor));
            } else {
                stringBuilder.append(" number is above 30 ");
            }
            publishProgress(100 * (i + 1) / params.length);
        }
        return stringBuilder.toString();
    }


    @Override
    protected void onProgressUpdate(Integer... values) {
        if (values.length > 0)
            progressInterface.updateProgress(values[0]);
    }

    @Override
    protected void onPostExecute(String s) {
        progressInterface.setText(s);
    }

    private static int factorial(int i) {
        if (i < 1)
            return 1;
        else
            return i * factorial(i - 1);
    }

}
