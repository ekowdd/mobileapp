package com.mobilecreative.mobileapps.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.mobilecreative.mobileapps.R;

public class NewActivity extends AppCompatActivity {

@Override
protected void onCreate ( Bundle savedInstanceState ) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_new);
	setTitle("News");
}

public void isCekToLog ( View view ) {
	Intent intent = new Intent(NewActivity.this, DiveLogActivity.class);
	startActivity(intent);
}
}
