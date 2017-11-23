package com.mobilecreative.mobileapps;

import android.app.Application;

import com.mobilecreative.mobileapps.config.AuthService;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by eko on 10/26/17.
 */

public class App extends Application {
@Override
public void onCreate () {
	super.onCreate();
	AuthService.initialize();
//	WCBuilder builder = new WCBuilder();
//	builder.setIsHttps(false);
//	builder.setBaseUrl("www.jajoju-yumoto.com");
//	builder.setSigning_method(SigningMethod.HMACSHA1);
//	builder.setWc_key("ck_3f1394ec3f4195daaf79090df87d823d2ac45e94");
//	builder.setWc_secret("cs_37be3e941f68039dd59f9c59dd3df19a5c5baf55");
//	WooCommerce.getInstance().initialize(builder);
//	LeakCanary.install(this);
//
//	System.out.println(WooCommerce.getInstance().toString());
}
}
