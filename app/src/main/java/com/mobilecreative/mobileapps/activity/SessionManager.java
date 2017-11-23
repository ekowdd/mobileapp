package com.mobilecreative.mobileapps.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.HashMap;

public class SessionManager {
SharedPreferences pref;
SharedPreferences.Editor editor;

Context _context;

// Shared pref mode
int PRIVATE_MODE = 0;

private static final String PREF_NAME = "members_login";

private static final String IS_LOGIN = "IsLoggedIn";

public static final String KEY_NAME = "id";

public static final String KEY_EMAIL = "email";
public static final String STATUS = "status";
public SessionManager(Context context){
	this._context = context;
	pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
	editor = pref.edit();
}

/**
 * Create login session
 * */
public void createLoginSession(String MemberId, String email,String status){
	editor.putBoolean(IS_LOGIN, true);

	// Storing name in pref
	editor.putString(KEY_NAME, MemberId);

	editor.putString(KEY_EMAIL, email);
	editor.putString(STATUS,status);
	editor.commit();
}

public void checkLogin(){
	if(!this.isLoggedIn()){
		Intent i = new Intent(_context, MainActivity.class);
		// Closing all the Activities
		i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		_context.startActivity(i);
	}

}

public HashMap<String, String> getUserDetails(){
	HashMap<String, String> member = new HashMap<String, String>();

	member.put(KEY_NAME, pref.getString(KEY_NAME, null));
	member.put(STATUS,pref.getString(STATUS,null));
	member.put(KEY_EMAIL, pref.getString(KEY_EMAIL, null));
	return member;
}
public void logoutUser(){
	editor.clear();
	editor.commit();
	Intent i = new Intent(_context, MainActivity.class);
	// Closing all the Activities
	i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

	i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	_context.startActivity(i);
}
public boolean isLoggedIn(){
	return pref.getBoolean(IS_LOGIN, false);
}
}
