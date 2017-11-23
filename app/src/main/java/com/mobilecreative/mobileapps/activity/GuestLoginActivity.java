package com.mobilecreative.mobileapps.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.mobilecreative.mobileapps.R;

public class GuestLoginActivity extends AppCompatActivity {
EditText email_gures, password_guest;
SessionManager session;
SharedPreferences shared;
String status = "Guest";
@Override
protected void onCreate ( Bundle savedInstanceState ) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_guest_login);
	email_gures = (EditText) findViewById(R.id.email_guest);
	password_guest = (EditText) findViewById(R.id.password_guest);
	session = new SessionManager(getApplicationContext());
}

public void onSignUp ( View view ) {

	Intent intent = new Intent(GuestLoginActivity.this, SignUpActivity.class);
	startActivity(intent);
}

@SuppressLint( "ApplySharedPref" )
public void onLoginGuest ( View view ) {
	session.createLoginSession("",email_gures.getText().toString(),status);
	Intent intent = new Intent(GuestLoginActivity.this, Main2Activity.class);
	intent.putExtra("email", email_gures.getText().toString());
	intent.putExtra("status", status);
	startActivity(intent);
}
}
