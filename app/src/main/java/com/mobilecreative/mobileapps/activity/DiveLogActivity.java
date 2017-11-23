package com.mobilecreative.mobileapps.activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.mobilecreative.mobileapps.R;
import com.mobilecreative.mobileapps.model.DiveLog;
import com.mobilecreative.mobileapps.model.Member;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DiveLogActivity extends AppCompatActivity {
Button btn_show_sheets;
Button saveButton;
EditText viewDate, ViewCity, viewCountry,viewDepth, viewDiveTime, viewSiteLocation;
DatabaseReference mDatabase;
private View view;
private String diveLogId;
DatePicker mDatePicker;
TimePicker mTimePicker;
TimePickerDialog mTImePickerDialog;
DatePickerDialog mDatePickerDialog;
int year,month, day,hours,minute;
AlertDialog.Builder dialog;
int am_pm;
List<DiveLog> list;
ListView lv;
ListAdapter adapter;
DiveLog diveLog;
@Override
protected void onCreate ( Bundle savedInstanceState ) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_dive_log);
	setTitle("Dive Log");
	mDatabase = FirebaseDatabase.getInstance().getReference("divelog");
	list = new ArrayList<>();
	btn_show_sheets = (Button) findViewById(R.id.btn_show_sheets);
	view = LayoutInflater.from(this).inflate(R.layout.sheet_fragment,null);
	dialog = new AlertDialog.Builder(DiveLogActivity.this);
	mDatePicker = new DatePicker(this);
	mTimePicker = new TimePicker(this);
	lv = (ListView ) findViewById(R.id.getLS);
	viewDate = (EditText ) view.findViewById(R.id.viewDate);
	ViewCity = (EditText ) view.findViewById(R.id.viewCity);
	viewCountry = (EditText ) view.findViewById(R.id.viewCountry);
	viewDepth = (EditText ) view.findViewById(R.id.viewDepth);
	viewDiveTime = (EditText ) view.findViewById(R.id.viewDiveTime);

	viewDiveTime.setOnFocusChangeListener(new View.OnFocusChangeListener() {
		@Override
		public void onFocusChange ( View view, boolean b ) {
			if ( b ){
					Calendar calendar = Calendar.getInstance();
					hours = calendar.get(Calendar.HOUR_OF_DAY);
					minute = calendar.get(Calendar.MINUTE);

					mTImePickerDialog = new TimePickerDialog(view.getContext(), new TimePickerDialog.OnTimeSetListener() {
						@Override
						public void onTimeSet ( TimePicker timePicker, int i, int i1 ) {
							viewDiveTime.setText(new StringBuilder().append(hours).append(":").append(minute));
						}
					},hours,minute,false);
					mTImePickerDialog.show();
				}else {

			}
		}
	});

	viewDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
		@Override
		public void onFocusChange ( View view, boolean b ) {
			if ( b ){
				Calendar calendar = Calendar.getInstance();
				year = calendar.get(Calendar.YEAR);
				month = calendar.get(Calendar.MONTH);
				day = calendar.get(Calendar.DAY_OF_MONTH);
				mDatePickerDialog = new DatePickerDialog(view.getContext(), new DatePickerDialog.OnDateSetListener() {
					@Override
					public void onDateSet ( DatePicker datePicker, int i, int i1, int i2 ) {
						viewDate.setText(new StringBuilder().append(year).append("/").append(month+1).append("/").append(day));
					}
				},year,month,day);
				mDatePickerDialog.show();
				mDatePicker.init(year,month,day,null);
			}else{

			}
		}
	});

	viewSiteLocation = (EditText) view.findViewById(R.id.viewSiteLoc);
	btn_show_sheets.setOnClickListener(new View.OnClickListener() {
		@RequiresApi( api = Build.VERSION_CODES.LOLLIPOP )
		@Override
		public void onClick ( View v ) {
			dialog.setView(view);
			dialog.setCancelable(true);
			dialog.setTitle("Add Dive");
			dialog.setPositiveButton("Save", new DialogInterface.OnClickListener() {
				@Override
				public void onClick ( DialogInterface dialogInterface, int i ) {
					SubmmitDiveLog();
				}
			});
			dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
				@Override
				public void onClick ( DialogInterface dialogInterface, int i ) {
					dialogInterface.dismiss();
				}
			});
			dialog.show();
		}
	});
}
public void SubmmitDiveLog(){
	String date = viewDate.getText().toString();
	String city = ViewCity.getText().toString();
	String country = viewCountry.getText().toString();
	String depth = viewDepth.getText().toString();
	String divetime = viewDiveTime.getText().toString();
	String site_loc = viewSiteLocation.getText().toString();
	if ( TextUtils.isEmpty(date) ){
		viewDate.setError("");
	} else if( TextUtils.isEmpty(city)){
		ViewCity.setError("");
	} else if ( TextUtils.isEmpty(country) ){
		viewCountry.setError("");
	} else if ( TextUtils.isEmpty(depth) ){
		viewDepth.setError("");
	} else if ( TextUtils.isEmpty(divetime) ) {
		viewDiveTime.setError("");
	}else if ( TextUtils.isEmpty(site_loc) ){
		viewSiteLocation.setError("");
	}else{
		if ( TextUtils.isEmpty(diveLogId) ) {
			String id = mDatabase.push().getKey();
			DiveLog diveLog = new DiveLog(city,country,date,depth,divetime,id,site_loc);
			mDatabase.child(id).setValue(diveLog);
			DataGet();
			Toast.makeText(getApplicationContext(),"Success Creating", Toast.LENGTH_LONG).show();
		}else{
			mDatabase.child(diveLogId).child("site_location").setValue(site_loc);
		}
	}


}
public class ListAdapter extends BaseAdapter{
	List<DiveLog> list =new ArrayList<DiveLog>();
	Context context;
	public ListAdapter( Context mContext, List<DiveLog> mList){
		this.context = mContext;
		this.list = mList;
	}
	@Override
	public int getCount () {
		return list.size();
	}

	@Override
	public Object getItem ( int i ) {
		return list.get(i);
	}

	@Override
	public long getItemId ( int i ) {
		return i;
	}

	@Override
	public View getView ( int i, View view, ViewGroup viewGroup ) {
		if ( view == null ){
			view = LayoutInflater.from(view.getContext()).inflate(R.layout.list_dive_log,viewGroup,false);
		}
		TextView SiteLocation = view.findViewById(R.id.site_log);
		TextView City = view.findViewById(R.id.city);
		TextView Country = view.findViewById(R.id.country);
		TextView Depth = view.findViewById(R.id.dept);
		TextView DiveTime = view.findViewById(R.id.dive_time);
		TextView DiveDate = view.findViewById(R.id.date);
		final DiveLog d = (DiveLog) this.getItem(i);
		SiteLocation.setText(d.getSite_location());
		City.setText(d.getCity());
		Country.setText(d.getCountry());
		Depth.setText(d.getDepth());
		DiveTime.setText(d.getDive_time());
		DiveDate.setText(d.getDate());

		view.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick (View v ) {
				v = LayoutInflater.from(v.getContext()).inflate(R.layout.get_details,null);

				if ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP ) {
					new AlertDialog.Builder(v.getContext())
							.setCancelable(true)
							.setView(v)
							.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
								@Override
								public void onClick ( DialogInterface dialogInterface, int i ) {

								}
							}).show();
				}
			}
		});
		return view;
	}
}

@Override
protected void onStart () {
	super.onStart();
	DataGet();
}
void DataGet(){
	String id = mDatabase.getKey();
	mDatabase.child(id).addValueEventListener(new ValueEventListener() {
		@Override
		public void onDataChange ( DataSnapshot dataSnapshot ) {
			list = new ArrayList<DiveLog>();
			for ( DataSnapshot post: dataSnapshot.getChildren() ){
				DiveLog divelog = post.getValue(DiveLog.class);
				Toast.makeText(getApplicationContext(),
						divelog.getCity()
								+ "  "
								+ divelog.getId()
								+ " "
								+ divelog.getCountry()
								+ " "
								+ divelog.getDepth()
								+ " "
								+ divelog.getDate()
								+ " "
								+ divelog.getSite_location(),
						Toast.LENGTH_LONG).show();
				list.add(divelog);
			}
			adapter = new ListAdapter(DiveLogActivity.this, list);
			lv.setAdapter(adapter);
			adapter.notifyDataSetChanged();
		}

		@Override
		public void onCancelled ( DatabaseError databaseError ) {

		}
	});
}
void getData(){
	mDatabase.child("id").child("divelog").addValueEventListener(new ValueEventListener() {
		@Override
		public void onDataChange ( DataSnapshot dataSnapshot ) {
			list = new ArrayList<DiveLog>();
			diveLog = new DiveLog();
			for ( DataSnapshot data: dataSnapshot.getChildren() ) {
				diveLog = data.getValue(DiveLog.class);
				list.add(diveLog);
			}
			adapter = new ListAdapter(DiveLogActivity.this,list);
			lv.setAdapter(adapter);
			adapter.notifyDataSetChanged();
		}

		@Override
		public void onCancelled ( DatabaseError databaseError ) {

		}
	});
}
}
