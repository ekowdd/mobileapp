package com.mobilecreative.mobileapps.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.mobilecreative.mobileapps.R;

public class WeightClacActivity extends AppCompatActivity {
private WebView WeightClacA;
@Override
protected void onCreate ( Bundle savedInstanceState ) {
	super.onCreate(savedInstanceState);
	getWindow().requestFeature(Window.FEATURE_NO_TITLE);
	WeightClacA = new WebView(this);
	WeightClacA.loadUrl("https://www.divestock.com/calc/weight/weight.php");
	WeightClacA.setWebViewClient(new WebViewClient(){
		@SuppressWarnings( "deprecation" )
		@Override
		public boolean shouldOverrideUrlLoading ( WebView view, String url ) {
			view.loadUrl(url);
			return true;
		}
	});
	this.setContentView(WeightClacA);
}

@Override
public boolean onKeyDown ( int keyCode, KeyEvent event ) {
	if ( (keyCode == event.KEYCODE_BACK) && WeightClacA.canGoBack() ){
		WeightClacA.goBack();
		return true;
	}
	return super.onKeyDown(keyCode, event);
}
}
