package com.mobilecreative.mobileapps.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.mobilecreative.mobileapps.R;
import com.mobilecreative.mobileapps.model.Member;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
private DatabaseReference mDatabase;
EditText vemail, vPassword;
Button onLoginMembers;
List<Member> listMember;
SessionManager sessionManager;
@Override
protected void onCreate ( Bundle savedInstanceState ) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_main);
	vemail = (EditText )findViewById(R.id.email);
	vPassword = (EditText )findViewById(R.id.password);
	onLoginMembers = (Button) findViewById(R.id.onLoginMembers);
	mDatabase = FirebaseDatabase.getInstance().getReference("members");
	sessionManager = new SessionManager(getApplicationContext());
	onLoginMembers.setOnClickListener(new View.OnClickListener() {
		@Override
		public void onClick ( View view ) {
			final String mEmail = vemail.getText().toString();
			final String mPAss = vPassword.getText().toString();
			if ( TextUtils.isEmpty(mEmail) ){
				vemail.setError("Not Null");
			}else if ( TextUtils.isEmpty(mPAss)  ){
				vPassword.setError("Not Null");
			}else{
				final String status = "Member";
				final String emails = vemail.getText().toString();
				final String pass = vPassword.getText().toString();
				mDatabase.addValueEventListener(new ValueEventListener() {
					@Override
					public void onDataChange ( DataSnapshot dataSnapshot ) {
						listMember = new ArrayList<Member>();
						for ( DataSnapshot post: dataSnapshot.getChildren() ){
							Member member = post.getValue(Member.class);
							if ( member != null ){
								sessionManager.createLoginSession(member.getId(),member.getEmail(),status);
								listMember.add(member);
								Intent in = new Intent(MainActivity.this, Main2Activity.class);
								in.putExtra("status", status);
								startActivity(in);
								finish();
							}else{
								Toast.makeText(getApplicationContext(),"Data Members is Null", Toast.LENGTH_LONG).show();
							}

						}
					}

					@Override
					public void onCancelled ( DatabaseError databaseError ) {

					}
				});
			}
		}
	});
}
public void onSignUp ( View view ) {
	Intent vintent = new Intent(MainActivity.this, SignUpActivity.class);
	startActivity(vintent);
}

public void onLoginGuest ( View view ) {
	Intent gintent = new Intent(MainActivity.this, GuestLoginActivity.class);
	startActivity(gintent);
}
private void initLogin(){

}
}
