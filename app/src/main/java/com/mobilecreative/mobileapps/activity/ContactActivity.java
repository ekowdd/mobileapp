package com.mobilecreative.mobileapps.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mobilecreative.mobileapps.R;

public class ContactActivity extends AppCompatActivity {

@Override
protected void onCreate ( Bundle savedInstanceState ) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_contact);
	setTitle("Contacts Us");
}
}
