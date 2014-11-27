package com.example.recoder;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;


public class MainActivity extends ActionBarActivity {

	private ToggleButton record,control;
	private Button stop;
	private MediaRecorder recorder;
	private ListView list;
	private ArrayList<String> mylist;
	private ArrayAdapter<String> adapter;
	private String songPath;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        record=(ToggleButton)findViewById(R.id.toggleButton1);
        control=(ToggleButton)findViewById(R.id.toggleButton2);
        stop=(Button)findViewById(R.id.button1);
        list=(ListView)findViewById(R.id.listView1);
        mylist=new ArrayList<String>();
        
        adapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_activated_1,mylist);
        list.setAdapter(adapter);
        
        refreshFiles();
        
        record.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (record.isChecked()) {
					startRecord();
				} else {
					stopRecord();
					refreshFiles();
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
        
        list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				songPath=(String) list.getItemAtPosition(position);
				songPath=Environment.getExternalStorageDirectory().getPath()+"/sound_recorder/"+songPath;
			}
		});
        
        

    }
    
    private void startRecord() {
    	recorder=new MediaRecorder();
    	recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
    	recorder.setOutputFormat(MediaRecorder.OutputFormat.AAC_ADTS);
    	recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC_ELD);
    	recorder.setAudioChannels(2);
    	recorder.setOutputFile(Environment.getExternalStorageDirectory().getPath()+"/sound_recorder/"+getTime()+".acc");
    	
    	
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
    	
    	
    	
        recorder.stop();
        recorder.reset();
        recorder.release();
    }
    
    private void startPlay() {
    	
    }
    
    private void refreshFiles() {
    	String dir=Environment.getExternalStorageDirectory().getPath()+"/sound_recorder";
        File []files=new File(dir).listFiles();
        String result;
        mylist.clear();
        
        for (File file:files) {
        	mylist.add(file.getName());
        }
        
    	adapter.notifyDataSetChanged();
    }
    
    private String getTime() {
    	SimpleDateFormat formatter=new SimpleDateFormat("yyyyƒÍMM‘¬dd»’HH:mm:ss");
    	Date curDate=new Date(System.currentTimeMillis());
    	String str=formatter.format(curDate);
    	return str;
    }

    
}
