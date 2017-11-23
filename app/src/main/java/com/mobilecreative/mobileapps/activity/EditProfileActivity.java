package com.mobilecreative.mobileapps.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mobilecreative.mobileapps.R;
import com.mobilecreative.mobileapps.model.Member;

public class EditProfileActivity extends AppCompatActivity {
	DatabaseReference mDarabase;
	private EditText id, name, email,passwword, confirm_password;
	private LinearLayout lin_buton;
@Override
protected void onCreate ( Bundle savedInstanceState ) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_edit_profile);
	setTitle("Profile");
	email = (EditText) findViewById(R.id.eEmail);
	passwword = (EditText) findViewById(R.id.ePass);
	confirm_password = (EditText) findViewById(R.id.eCPas);
	lin_buton = (LinearLayout)findViewById(R.id.ln_button);
//	email = (EditText) findViewById(R.id.eEmail);

}

private boolean updateProfile(String id, String emalil, String password, String confrim_password){
	mDarabase = FirebaseDatabase.getInstance().getReference("members").child("id");
	Member member = new Member(id,emalil,password,confrim_password);
	mDarabase.setValue(member);
	Toast.makeText(getApplicationContext(),"Update Success", Toast.LENGTH_LONG).show();
	return true;
}

public void onEditProfile ( View view ) {
	email.setEnabled(true);
	confirm_password.setEnabled(true);
	passwword.setEnabled(true);
	lin_buton.setVisibility(View.VISIBLE);
}

public void onEditCancel ( View view ) {
	email.setEnabled(false);
	confirm_password.setEnabled(false);
	passwword.setEnabled(false);
	lin_buton.setVisibility(View.GONE);
}

public void onEditSave ( View view ) {
	lin_buton.setVisibility(View.GONE);
}
}
