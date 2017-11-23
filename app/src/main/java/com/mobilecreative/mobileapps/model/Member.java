package com.mobilecreative.mobileapps.model;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by eko on 11/1/17.
 */
public class Member {
public String id;
public String email;
public String password;
public String rpassword;
public Member(){}

public Member ( String id, String email, String password, String rpassword ) {
	this.id = id;
	this.email = email;
	this.password = password;
	this.rpassword = rpassword;
}

public String getId () {
	return id;
}

public void setId ( String id ) {
	this.id = id;
}

public String getEmail () {
	return email;
}

public void setEmail ( String email ) {
	this.email = email;
}

public String getPassword () {
	return password;
}

public void setPassword ( String password ) {
	this.password = password;
}

public String getRpassword () {
	return rpassword;
}

public void setRpassword ( String rpassword ) {
	this.rpassword = rpassword;
}
}
