package com.example.recoder;

import java.io.IOException;

import android.media.MediaRecorder;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ToggleButton;


public class MainActivity extends ActionBarActivity {

	private ToggleButton record,control;
	private Button stop;
	private MediaRecorder recorder;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        record=(ToggleButton)findViewById(R.id.toggleButton1);
        control=(ToggleButton)findViewById(R.id.toggleButton2);
        stop=(Button)findViewById(R.id.button1);
        
        record.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (record.isChecked()) {
//					Toast.makeText(MainActivity.this, "被按了", Toast.LENGTH_SHORT).show();
					startRecord();
				} else {
//					Toast.makeText(MainActivity.this, "弹起了", Toast.LENGTH_SHORT).show();
					stopRecord();
				}
			}
		});
        
        control.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (record.isChecked()) {
					
				} else {
					
				}
			}
		});
        
        stop.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
    }
    
    private void startRecord() {
    	recorder=new MediaRecorder();
    	recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
    	recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
    	recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
//    	recorder.setAudioChannels(2);
    	recorder.setOutputFile("/sdcard/sound_recorder/test.amr");
    	try {
    		recorder.prepare();
    		recorder.start();
    	} catch (IllegalStateException e) {
    		e.printStackTrace();
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }
    
    private void stopRecord() {
    	try {
        	recorder.stop();
        	recorder.reset();
        	recorder.release();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
    
    private void startPlay() {
    	
    }

    
}
