package com.mobilecreative.mobileapps.fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mobilecreative.mobileapps.R;
import com.mobilecreative.mobileapps.activity.BenefitActivity;
import com.mobilecreative.mobileapps.activity.ContactActivity;
import com.mobilecreative.mobileapps.activity.DiveLogActivity;
import com.mobilecreative.mobileapps.activity.DiveScheduleActivity;
import com.mobilecreative.mobileapps.activity.MainActivity;
import com.mobilecreative.mobileapps.activity.NewActivity;
import com.mobilecreative.mobileapps.activity.SessionManager;
import com.mobilecreative.mobileapps.activity.WeightClacActivity;
import com.mobilecreative.mobileapps.config.AuthService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by eko on 10/26/17.
 */

public class HomeFragment extends Fragment implements View.OnClickListener {
private View view;
 TextView name,slug,content,date,url,email_member,member_id;
 ImageView dive_schedule, weight_clac, benefit, dive_log, contact;
LinearLayout news;
Button btn_settings;
SharedPreferences shared;
String status = "";
SessionManager session;
@SuppressLint( "SetTextI18n" )
@Nullable
@Override
public View onCreateView ( LayoutInflater inflater,
                           @Nullable ViewGroup container, @Nullable Bundle savedInstanceState ) {
	view = inflater.inflate(R.layout.home,container,false);
	btn_settings = (Button ) view.findViewById(R.id.btn_settings) ;
	email_member = (TextView ) view.findViewById(R.id.email_member);
	member_id = (TextView ) view.findViewById(R.id.member_id);
	dive_schedule = (ImageView) view.findViewById(R.id.dive_schedule);
	weight_clac = (ImageView) view.findViewById(R.id.weight_clac);
	benefit = (ImageView) view.findViewById(R.id.benefit);
	news = (LinearLayout) view.findViewById(R.id.news);
	dive_log = (ImageView) view.findViewById(R.id.dive_log);
	contact = (ImageView) view.findViewById(R.id.contact);
	dive_schedule.setOnClickListener(this);
	weight_clac.setOnClickListener(this);
	benefit.setOnClickListener(this);
	news.setOnClickListener(this);
	dive_log.setOnClickListener(this);
	contact.setOnClickListener(this);
	session = new SessionManager(view.getContext().getApplicationContext());
	session.checkLogin();
	HashMap<String , String> members = session.getUserDetails();
	status = members.get(SessionManager.STATUS);
	if(status == null){
		Intent intent = new Intent(view.getContext(), MainActivity.class);
		view.getContext().startActivity(intent);
	}else if ( status.equals("Member")){
		btn_settings.setVisibility(View.VISIBLE);
		email_member.setText(" Email :" +members.get(SessionManager.KEY_EMAIL));
		member_id.setText("ID :" + members.get(SessionManager.KEY_NAME));
	}else if ( status.equals("Guest") ) {
		btn_settings.setVisibility(View.GONE);
		email_member.setText(" Email :" + members.get(SessionManager.KEY_EMAIL));
		member_id.setVisibility(View.GONE);
	}
	return view;
}

@Override
public void onClick ( View view ) {
	switch ( view.getId() ){
		case R.id.dive_schedule:
			if (  status.equals("Guest")){
				new AlertDialog.Builder(view.getContext())
						.setTitle(null)
						.setMessage("Your Not Registed")
						.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
							@Override
							public void onClick ( DialogInterface dialogInterface, int i ) {
								dialogInterface.dismiss();
							}
						}).show();
			}else if ( status.equals("Member") ){
				Intent DiveSchedule = new Intent(view.getContext(), DiveScheduleActivity.class);
				view.getContext().startActivity(DiveSchedule);
			}

			break;
		case R.id.weight_clac:
			if (  status.equals("Guest") ){
				new AlertDialog.Builder(view.getContext())
						.setTitle(null)
						.setMessage("Your Not Registed")
						.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
							@Override
							public void onClick ( DialogInterface dialogInterface, int i ) {
								dialogInterface.dismiss();
							}
						}).show();
			}else if ( status.equals("Member") ) {
				Intent WeightClac = new Intent(view.getContext(), WeightClacActivity.class);
				view.getContext().startActivity(WeightClac);
			}
			break;
		case R.id.benefit:
			if (  status.equals("Guest") ){
				new AlertDialog.Builder(view.getContext())
						.setTitle(null)
						.setMessage("Your Not Registed")
						.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
							@Override
							public void onClick ( DialogInterface dialogInterface, int i ) {
								dialogInterface.dismiss();
							}
						}).show();
			}else if ( status.equals("Member") ) {
				Intent Benefit = new Intent(view.getContext(), BenefitActivity.class);
				view.getContext().startActivity(Benefit);
			}
			break;
		case R.id.news:
			Intent New = new Intent(view.getContext(), NewActivity.class);
			view.getContext().startActivity(New);
			break;
		case R.id.dive_log:
			Intent DiveLog = new Intent(view.getContext(), DiveLogActivity.class);
			view.getContext().startActivity(DiveLog);
			break;
		case R.id.contact:
			Intent Contact = new Intent(view.getContext(), ContactActivity.class);
			view.getContext().startActivity(Contact);
			break;
	}
}

}
