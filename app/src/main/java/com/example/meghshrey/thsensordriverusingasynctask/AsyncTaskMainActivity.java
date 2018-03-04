package com.example.meghshrey.thsensordriverusingasynctask;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



public class AsyncTaskMainActivity extends AppCompatActivity implements UpdateUIMethod {
    private EditText Tempval = null;
    private EditText SensorCount = null;
    private AsyncLongTask exec = null;
    private EditText Humidval = null;
    private EditText Actval = null;
    private EditText getOutput = null;
    private int readCount = 0;

    public AsyncLongTask longTask = new AsyncLongTask(AsyncTaskMainActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task_main);

        Tempval = (EditText) findViewById(R.id.Temp);
        Humidval = (EditText) findViewById(R.id.Humid);
        Actval = (EditText) findViewById(R.id.Activity);
        SensorCount = (EditText) findViewById(R.id.SensorCount);
        getOutput = (EditText) findViewById(R.id.OutputField);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void UpdateUI(int temp, int humid, int activity, int counter) {
        Tempval.setText(temp + "");
        Humidval.setText(humid + "");
        Actval.setText(activity + "");
        SensorCount.setText(readCount - counter + "");

        StringBuilder s = new StringBuilder();
        s.append("Output ").append(counter + ":\n");
        s.append("Temperature: ").append(temp + " F\n");
        s.append("Humidity: ").append(humid + " %\n");
        s.append("Activity: ").append(activity + " \n\n");
        getOutput.append(s.toString());
    }


    public void Generate(View view) {
        this.readCount = Integer.parseInt(SensorCount.getText().toString());

        longTask.execute(readCount);
    }

    public void Cancel(View view) {

        longTask.cancel(true);

        Toast.makeText(this, "AsyncTask stopped", Toast.LENGTH_SHORT).show();
    }



}



