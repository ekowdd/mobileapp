package com.mobilecreative.mobileapps.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mobilecreative.mobileapps.R;
import com.mobilecreative.mobileapps.model.Member;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@SuppressWarnings( "deprecation" )
public class SignUpActivity extends AppCompatActivity {
private ProgressDialog dialog;
private DatabaseReference mDatabase;
 EditText mEmail, mPassword, mRPassword;
List<Member> listMember;
private static String memberId;
@Override
protected void onCreate ( Bundle savedInstanceState ) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_sign_up);
	setTitle("SignUp");
	mEmail = (EditText)findViewById(R.id.mEmail);
	mPassword = (EditText)findViewById(R.id.mPassword);
	mRPassword = (EditText)findViewById(R.id.mRPassword);
	mDatabase = FirebaseDatabase.getInstance().getReference("members");
}

private void SubmitMember(){
	String vEmail = mEmail.getText().toString();
	String vPassword = mPassword.getText().toString();
	String vRPassword = mRPassword.getText().toString();
	listMember = new ArrayList<Member>();
	if ( TextUtils.isEmpty(vEmail) ){
		mEmail.setError("Not Null");
	}else if ( TextUtils.isEmpty(vPassword) ){
		mPassword.setError("Not Null");
	}else if ( TextUtils.isEmpty(vRPassword) ){
		mRPassword.setError("Not Null");
	}else {
		if ( TextUtils.isEmpty(memberId) ){
			String id = mDatabase.push().getKey();
			Member member = new Member(id, vEmail,vPassword,vRPassword);
			mDatabase.child(id).setValue(member);
			Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_LONG).show();
		}else {
			mDatabase.child(memberId).child("email").setValue(vEmail);
			Toast.makeText(getApplicationContext(),"Update Successfully", Toast.LENGTH_LONG).show();
		}
		Clear();
	}
}

@Override
protected void onStart () {
	super.onStart();

	mDatabase.addValueEventListener(new ValueEventListener() {
		@Override
		public void onDataChange ( DataSnapshot dataSnapshot ) {
			listMember = new ArrayList<Member>();
			for ( DataSnapshot post: dataSnapshot.getChildren() ){
				Member member = post.getValue(Member.class);
				Toast.makeText(getApplicationContext(),member.getEmail() + "  " + member.getId(), Toast.LENGTH_LONG).show();
				listMember.add(member);
			}
		}

		@Override
		public void onCancelled ( DatabaseError databaseError ) {

		}
	});
}

private void Clear(){
	mEmail.setText("");
	mPassword.setText("");
	mRPassword.setText("");
}
private void writeNewUser(String mId,String MemberId, String mEmail, String mPasword,String mRPassword) {
//	String key = mDatabase.child("posts").push().getKey();
//	Member members = new Member(mEmail, mPasword,mRPassword);
//	Map<String , Objects> postValues = new HashMap<>();
//	mDatabase.child("members").child(MemberId).setValue(members);
}
private void showProgressDialog(){
	if ( dialog == null ){
		dialog = new ProgressDialog(this);
		dialog.setTitle(null);
		dialog.setCancelable(false);
		dialog.setMessage("Loading....");
	}
	dialog.show();
}

public void OnSignUp ( View view ) {
	SubmitMember();
}

public void onSiginAccount ( View view ) {
	Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
	startActivity(intent);
}
}
