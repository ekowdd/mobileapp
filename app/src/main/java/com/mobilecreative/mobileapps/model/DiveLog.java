package com.mobilecreative.mobileapps.model;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by eko on 11/13/17.
 */
@IgnoreExtraProperties
public class DiveLog {
public String city;
public String country;
public String date;
public String depth;
public String dive_time;
public String id;
public String site_location;
public DiveLog ( String id, String city, String country, String date, String depth, String dive_time, String site_location ) {
	this.id = id;
	this.city = city;
	this.country = country;
	this.date = date;
	this.depth = depth;
	this.dive_time = dive_time;
	this.site_location = site_location;
}
public DiveLog(){}
@Exclude
public String getId () {
	return id;
}
@Exclude
public void setId ( String id ) {
	this.id = id;
}
@Exclude
public String getCity () {
	return city;
}
@Exclude
public void setCity ( String city ) {
	this.city = city;
}
@Exclude
public String getCountry () {
	return country;
}
@Exclude
public void setCountry ( String country ) {
	this.country = country;
}
@Exclude
public String getDate () {
	return date;
}
@Exclude
public void setDate ( String date ) {
	this.date = date;
}
@Exclude
public String getDepth () {
	return depth;
}
@Exclude
public void setDepth ( String depth ) {
	this.depth = depth;
}
@Exclude
public String getDive_time () {
	return dive_time;
}
@Exclude
public void setDive_time ( String dive_time ) {
	this.dive_time = dive_time;
}
@Exclude
public String getSite_location () {
	return site_location;
}
@Exclude
public void setSite_location ( String site_location ) {
	this.site_location = site_location;
}
}
