package com.example.stacyzolnikov.jobschedulerlab;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements DataSingleton.TextChangeListener {
    private static final String TAG = "MainActivity";
    Button mButton, mSecondButton;
    TextView mOldText, mNewText;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DataSingleton.getInstance().setListener(this);

        mOldText = (TextView) findViewById(R.id.oldText);
        mNewText = (TextView) findViewById(R.id.newText);

        PersistableBundle bundleOne = new PersistableBundle();
        bundleOne.putString("message", "Message One");
        JobInfo jobInfo = new JobInfo.Builder(1, new ComponentName(getPackageName(), MyJobService.class.getName()))
                .setExtras(bundleOne)
                .setPeriodic(10_000)
                .build();


        PersistableBundle bundleTwo = new PersistableBundle();
        bundleTwo.putString("message", "Message Two");
        JobInfo jobInfoTwo = new JobInfo.Builder(2, new ComponentName(getPackageName(), MyJobService.class.getName()))
                .setExtras(bundleTwo)
                .setPeriodic(15_000)
                .build();


        mButton = (Button) findViewById(R.id.btn_schedule_job);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        mSecondButton = (Button) findViewById(R.id.btn_schedule_job_two);
        mSecondButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {


            }
        });


        JobScheduler jobScheduler = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
       // int result = scheduler.schedule(jobInfo);
       // if (result == JobScheduler.RESULT_SUCCESS)
       //     Toast.makeText(this, "Job scheduled now", Toast.LENGTH_LONG).show();
        jobScheduler.schedule(jobInfo);
        jobScheduler.schedule(jobInfoTwo);

       // Log.d(TAG, "onCreate: Job scheduled now");
       // int resultTwo = scheduler.schedule(jobInfoTwo);
       // if (resultTwo == JobScheduler.RESULT_SUCCESS)
       //     Toast.makeText(this, "Job scheduled now", Toast.LENGTH_LONG).show();
        Log.d(TAG, "onCreate: Job scheduled now");

    }


    @Override
    public void onTextChanged(String oldText, String newText) {
        Toast.makeText(MainActivity.this, "Message is changing",Toast.LENGTH_LONG).show();
        if(mNewText.getText().length() != 0){
            mOldText.setText(oldText);
            mNewText.setText("The new message is: " + DataSingleton.getInstance().getMyText2());
        }

    }
}
