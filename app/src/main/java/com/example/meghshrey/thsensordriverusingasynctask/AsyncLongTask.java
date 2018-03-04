package com.example.meghshrey.thsensordriverusingasynctask;

/**
 * Created by MeghShrey on 3/17/2017.
 */

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.util.Random;




public class AsyncLongTask extends AsyncTask<Integer, Integer, Integer> {
    UpdateUIMethod mUICallBack = null;
    Context context;
    ProgressDialog Progressbar = null;

    public AsyncLongTask(UpdateUIMethod callBack) {
        mUICallBack = callBack;
    }


    @Override
    protected Integer doInBackground(Integer... params) {
        int count = params[0];
        Random r = new Random();
        for (int i = 1; i <= count; i++) {
            int tempVal = r.nextInt(101);
            int humiVal = r.nextInt(101);
            int actId = r.nextInt(1000);
            publishProgress(tempVal, humiVal, actId, i);
            Utils.sleepForInSecs(5);
        }
        return null;
    }


    @Override
    protected void onProgressUpdate(Integer... params) {
        this.mUICallBack.UpdateUI(params[0], params[1], params[2], params[3]);
        Toast.makeText((Context) mUICallBack, "Received Output " + params[3], Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onPostExecute(Integer integer) {
        Toast.makeText(context, " Random Values generated", Toast.LENGTH_SHORT).show();
        Progressbar.cancel();
    }




}