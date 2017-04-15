package com.serverproject.facedetection.threading;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btncounterthreadone;
    Button btncounterthreadtwo;
    TextView txtcounterone;
    TextView txtcountertwo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btncounterthreadone=(Button)findViewById(R.id.btncounterone);
        btncounterthreadone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Threadone threadone = new Threadone();
                threadone.start();
            }
        });
        btncounterthreadtwo=(Button)findViewById(R.id.btncountertwo);
        btncounterthreadtwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Threadtwo threadtwo = new Threadtwo();
                threadtwo.start();
            }
        });
    }


    public class Threadtwo extends Thread{
        int counter = 15;

        @Override
        public void run() {
            super.run();
            while (true){

                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        txtcountertwo = (TextView)findViewById(R.id.txtcountertwo);
                        txtcountertwo.setText(""+counter);
                        Toast.makeText(getApplicationContext(),"counter="+counter,Toast.LENGTH_SHORT).show();
                    }
                });

                
                try {
                    Thread.sleep(3000);


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                counter++;
            }
        }

    }

  public   class Threadone extends Thread{
        int counter = 0;
      @Override
        public void run() {
            super.run();
            while (true){

                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        txtcounterone = (TextView)findViewById(R.id.txtcounterone);
                        txtcounterone.setText(""+counter);
                    }
                });

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                counter++;
            }
        }
    }
}
