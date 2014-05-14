package com.hromadske.tv.ck.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ProgressBar;

/**
 * Created by Cheb on 15.05.2014.
 */
public abstract class BaseTask extends AsyncTask<String, Integer, Boolean> {

    protected Context context;
    protected ProgressBar progressBar;

    public BaseTask(Context context, ProgressBar progressBar) {
        this.context = context;
        this.progressBar = progressBar;
    }

    @Override
    protected void onPreExecute() {
        progressBar.post(new Runnable() {

            @Override
            public void run() {
                progressBar.setVisibility(ProgressBar.VISIBLE);
            }
        });
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Boolean result) {
        progressBar.post(new Runnable() {

            @Override
            public void run() {
                progressBar.setVisibility(ProgressBar.INVISIBLE);
            }
        });
        super.onPostExecute(result);
    }

}
