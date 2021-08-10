package com.example.asynctaskexapmle;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
ProgressBar progressBar;
TextView numTv,resultTv,msgTv;
Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar=findViewById(R.id.progressBar);
        numTv = findViewById(R.id.numTv);
        resultTv = findViewById(R.id.resultTv);
        msgTv=findViewById(R.id.msgTv);
        btn=findViewById(R.id.factBtn);
        msgTv.setText("");
        progressBar.setVisibility(View.INVISIBLE);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             FactAsyncTask asyncTask = new FactAsyncTask();
             asyncTask.execute(Long.valueOf(numTv.getText().toString()));
            }
        });
    }
    class FactAsyncTask extends AsyncTask<Long,Integer,Long>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            msgTv.setText("Calculating...");
            progressBar.setVisibility(View.VISIBLE);
            btn.setEnabled(false);
        }
        @Override
        protected Long doInBackground(Long... longs) {
            Log.e("POP",longs[0]+"");
            Long fact=1L;
            for(int i=1;i<=longs[0];i++) {
                try{
                    Thread.sleep(500);
                    fact = fact * (long)i;
                }
                catch (Exception e) {
                  Log.e("Error",e.getMessage());
                }
                publishProgress(i);
            }
            return fact;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            progressBar.setProgress(values[0]);
        }
        @Override
        protected void onPostExecute(Long aLong) {
            super.onPostExecute(aLong);
            resultTv.setText(String.valueOf(aLong));
            msgTv.setText("Successflly calculted");
            progressBar.setVisibility(View.INVISIBLE);
            btn.setEnabled(true);
        }
    }
}