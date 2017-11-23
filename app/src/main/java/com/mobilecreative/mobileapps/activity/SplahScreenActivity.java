package com.mobilecreative.mobileapps.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mobilecreative.mobileapps.R;

public class SplahScreenActivity extends AppCompatActivity {
	Thread thread;
@Override
protected void onCreate ( Bundle savedInstanceState ) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_splah_screen);
	thread = new Thread(){
		@Override
		public void run () {
			super.run();
			try{
				sleep(3000);
			}catch ( Exception e ){
				e.fillInStackTrace();
			}finally {
				Intent intent = new Intent(SplahScreenActivity.this,Main2Activity.class);
				startActivity(intent);
			}
		}
	};
	thread.start();
}
}
