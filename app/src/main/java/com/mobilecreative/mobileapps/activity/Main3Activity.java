package com.mobilecreative.mobileapps.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.mobilecreative.mobileapps.R;
import com.mobilecreative.mobileapps.model.DiveLog;

public class Main3Activity extends AppCompatActivity {

@Override
protected void onCreate ( Bundle savedInstanceState ) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_main3);
}

@Override
protected void onActivityResult ( int requestCode, int resultCode, Intent data ) {
	LayoutInflater.from(this).inflate(R.layout.form_data,null);
	super.onActivityResult(requestCode, resultCode, data);
}

public void onCoba ( View view ) {
	Intent intent = new Intent(this,DiveLogActivity.class);
	startActivityForResult(intent,1111);
}
}
