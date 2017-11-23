package com.mobilecreative.mobileapps.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.mobilecreative.mobileapps.R;

public class DiveScheduleActivity extends AppCompatActivity {
private WebView ScheduleDive;
@Override
protected void onCreate ( Bundle savedInstanceState ) {
	super.onCreate(savedInstanceState);
	getWindow().requestFeature(Window.FEATURE_NO_TITLE);
	ScheduleDive = new WebView(this);
	ScheduleDive.loadUrl("http://indonesiadivesafaris.com/trip2017/");
	ScheduleDive.setWebViewClient(new WebViewClient(){
		@SuppressWarnings( "deprecation" )
		@Override
		public boolean shouldOverrideUrlLoading ( WebView view, String url ) {
			ScheduleDive.loadUrl(url);
			return true;
		}
	});
	this.setContentView(ScheduleDive);
}

@Override
public boolean onKeyDown ( int keyCode, KeyEvent event ) {
	if ( (keyCode == event.KEYCODE_BACK) && ScheduleDive.canGoBack() ){
		ScheduleDive.goBack();
		return true;
	}
	return super.onKeyDown(keyCode, event);
}
}
