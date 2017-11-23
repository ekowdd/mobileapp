package com.mobilecreative.mobileapps.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.mobilecreative.mobileapps.R;

public class BenefitActivity extends AppCompatActivity {
private WebView webView;
@Override
protected void onCreate ( Bundle savedInstanceState ) {
	super.onCreate(savedInstanceState);
	getWindow().requestFeature(Window.FEATURE_NO_TITLE);
	webView = new WebView(this);
	webView.loadUrl("http://indonesiadivesafaris.com/benefits/");
	webView.setWebViewClient(new WebViewClient(){
		@SuppressWarnings( "deprecation" )
		@Override
		public boolean shouldOverrideUrlLoading ( WebView view, String url ) {
			view.loadUrl(url);
			return true;
		}
	});
	this.setContentView(webView);
}

@Override
public boolean onKeyDown ( int keyCode, KeyEvent event ) {
	if ( (keyCode == event.KEYCODE_BACK) && webView.canGoBack() ){
		webView.goBack();
		return true;
	}
	return super.onKeyDown(keyCode, event);
}
}
