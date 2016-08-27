package com.example.stacyzolnikov.jobschedulerlab;

import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.Context;
import android.content.Intent;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

import java.util.LinkedList;

/**
 * Created by stacyzolnikov on 8/27/16.
 */
public class MessageJobService extends JobService {
    private static final String TAG = "MessageJobService";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate: Service Created");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: Service Destroyed");
    }



    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {

        return true;
    }
    MainActivity mainActivity;
    public void setUiCallback(MainActivity activity){
        mainActivity = activity;
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Messenger callback = intent.getParcelableExtra("messenger");
        Message message = Message.obtain();
        message.what = 2;
        message.obj = this;
        try {
            callback.send(message);
        } catch (RemoteException e){
            Log.i(TAG, "onStartCommand: Error passing message");

        }

        return START_NOT_STICKY;
    }
    public void scheduleJob(JobInfo jobInfo){
        Log.d(TAG, "scheduleJob: Scheduling Job Now");
        JobScheduler jobSched= (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
        jobSched.schedule(jobInfo);
    }


}
