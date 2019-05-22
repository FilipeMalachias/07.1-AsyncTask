package com.filipe.malachias.simpleasynctask;

import android.os.AsyncTask;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.Random;

public class SimpleAsyncTask extends AsyncTask<Void, Integer, String> {

    // The TextView where we will show results
    private WeakReference<TextView> mTextView;

    public SimpleAsyncTask(TextView tv) {
        mTextView = new WeakReference<>(tv);
    }

    @Override
    protected String doInBackground(Void... voids) {

        // Generate a random number between 0 and 10.
        Random r = new Random();
        int n = r.nextInt(11);

        // Make the task take long enough that we have
        // time to rotate the phone while it is running.
        int s = n * 200;

        // Sleep for the random amount of time.
        int seconds = 5;
        for (int i = 0; i < seconds; i++)
            try {
                Thread.sleep(s);
                publishProgress(( i + 1) * 100 / seconds);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        // Return a String result.
        return "Awake at last after sleeping for " + s + " milliseconds!";
    }

    @Override
    protected void onProgressUpdate(Integer ... values) {
        //super.onProgressUpdate(values);
        // gets called during the background mark process (by publicProgress();)
        mTextView.get().setText("\nCompleted: " + values[0] + "%");

    }

    protected void onPostExecute(String result) {
        mTextView.get().setText(result);
    }
}