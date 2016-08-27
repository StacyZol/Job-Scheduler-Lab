package com.example.stacyzolnikov.jobschedulerlab;

import android.app.job.JobParameters;
import android.app.job.JobService;

import android.os.AsyncTask;
import android.os.PersistableBundle;
import android.os.SystemClock;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by stacyzolnikov on 8/27/16.
 */
public class MyJobService extends JobService {
    private static final String TAG = "MyJobService";
    private AsyncTask<String,Void,String> mTask;
   // private UpdateAppsAsyncTask updateTask = new UpdateAppsAsyncTask();

    @Override
    public boolean onStartJob(final JobParameters jobParameters) {
       // updateTask.execute(jobParameters);

        PersistableBundle bundle = jobParameters.getExtras();
            final String message = bundle.getString("message");

         mTask = new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... string) {
                String newText = message;
                return newText;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                DataSingleton.getInstance().updateMyText(s,s);
                jobFinished(jobParameters, false);
            }
        };
          mTask.execute();

        return true;
    }


    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        if (mTask != null && mTask.getStatus().equals(AsyncTask.Status.RUNNING)) {
            mTask.cancel(false);
            // boolean shouldReschedule = updateTask.stopJob(jobParameters);
            // return shouldReschedule;

        }
        return false;
    }

 // private class UpdateAppsAsyncTask extends AsyncTask<JobParameters, Void, JobParameters[]> {


 //     @Override
 //     protected JobParameters[] doInBackground(JobParameters... params) {
 //         SystemClock.sleep(5000);
 //         Log.d(TAG, "doInBackground: Updating apps in the background");
 //         return params;
 //     }

 //     @Override
 //     protected void onPostExecute(JobParameters[] result) {
 //         for (JobParameters params : result) {
 //             if (!hasJobBeenStopped(params)) {
 //                 jobFinished(params, false);
 //             }
   //         }
   //     }
//
   //     private boolean hasJobBeenStopped(JobParameters params) {
   //         return false;
   //     }
//
   //     public boolean stopJob(JobParameters params) {
   //         return false;
   //     }
   // }
}
