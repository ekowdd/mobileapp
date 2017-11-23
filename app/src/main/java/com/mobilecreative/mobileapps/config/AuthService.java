package com.mobilecreative.mobileapps.config;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by eko on 10/27/17.
 */

public class AuthService {
private static OkHttpClient mClient;
public static void initialize(){
	if ( mClient == null ){
		mClient = new OkHttpClient();
		mClient.newBuilder().connectTimeout(300, TimeUnit.SECONDS).build();
	}
}

public static String doGET( String url, String token)throws IOException{
	String credential = Credentials.basic("admin","admin123");
	Request request = new Request.Builder()
			.addHeader("Authorization",credential)
			.url(url)
			.build();
	Response response = mClient.newCall(request).execute();
	return response.body().string();
}
}
