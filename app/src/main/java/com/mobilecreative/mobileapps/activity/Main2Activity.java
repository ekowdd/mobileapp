package com.mobilecreative.mobileapps.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


import com.mobilecreative.mobileapps.fragment.HomeFragment;
import com.mobilecreative.mobileapps.R;

import java.util.List;


public class Main2Activity extends AppCompatActivity
		implements NavigationView.OnNavigationItemSelectedListener {
SessionManager session;

@Override
protected void onCreate ( Bundle savedInstanceState ) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_main2);
	setTitle("Home");
	Fragment fragment = new HomeFragment();
	FragmentManager fragmentManager = getSupportFragmentManager();
	fragmentManager.beginTransaction().addToBackStack(null).replace(R.id.frame,fragment).commit();
	Toolbar toolbar = ( Toolbar ) findViewById(R.id.toolbar);
	setSupportActionBar(toolbar);
	session = new SessionManager(getApplicationContext());

	DrawerLayout drawer = ( DrawerLayout ) findViewById(R.id.drawer_layout);
	ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
			this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
	//noinspection deprecation
	drawer.setDrawerListener(toggle);
	toggle.syncState();

	NavigationView navigationView = ( NavigationView ) findViewById(R.id.nav_view);
	navigationView.setNavigationItemSelectedListener(this);
}

@Override
public void onBackPressed () {
	DrawerLayout drawer = ( DrawerLayout ) findViewById(R.id.drawer_layout);
	if ( drawer.isDrawerOpen(GravityCompat.START) ) {
		drawer.closeDrawer(GravityCompat.START);
	} else {
		super.onBackPressed();
	}
}

@Override
public boolean onCreateOptionsMenu ( Menu menu ) {
	// Inflate the menu; this adds items to the action bar if it is present.
	getMenuInflater().inflate(R.menu.main2, menu);
	return true;
}

@Override
public boolean onOptionsItemSelected ( MenuItem item ) {
	// Handle action bar item clicks here. The action bar will
	// automatically handle clicks on the Home/Up button, so long
	// as you specify a parent activity in AndroidManifest.xml.
	int id = item.getItemId();

	//noinspection SimplifiableIfStatement
	if ( id == R.id.action_settings ) {
		return true;
	}

	return super.onOptionsItemSelected(item);
}

@SuppressWarnings( "StatementWithEmptyBody" )
@Override
public boolean onNavigationItemSelected ( MenuItem item ) {
	// Handle navigation view item clicks here.
	int id = item.getItemId();

	if ( id == R.id.nav_camera ) {
		// Handle the camera action
		Fragment fragment = new HomeFragment();
		setTitle("Aplikasi Member");
		FragmentManager fragmentManager = getSupportFragmentManager();
		fragmentManager.beginTransaction().addToBackStack(null).replace(R.id.frame,fragment).commit();
	} else if ( id == R.id.nav_gallery ) {
		Intent mInten = new Intent(Main2Activity.this,EditProfileActivity.class);
		startActivity(mInten);
	}else if ( id == R.id.nav_share ) {

	} else if ( id == R.id.nav_send ) {
		session.logoutUser();
	}

	DrawerLayout drawer = ( DrawerLayout ) findViewById(R.id.drawer_layout);
	drawer.closeDrawer(GravityCompat.START);
	return true;
}
public void init(){

}
}
