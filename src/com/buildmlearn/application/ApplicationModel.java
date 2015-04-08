package com.buildmlearn.application;

import android.util.Log;

import com.buildmlearn.models.Template;

public class ApplicationModel {
	
	private Template mTemplate;
	private String mAppName;
	private String mAuthorName;
	private String mFileName;
	
	public Template getmTemplate() {
		return mTemplate;
	}
	public void setmTemplate(Template mTemplate) {
		this.mTemplate = mTemplate;
	}
	public String getmAppName() {
		return mAppName;
	}
	public void setmAppName(String mAppName) {
		Log.e("setting app name as", ""+mAppName);
		this.mAppName = mAppName;
	}
	public String getmAuthorName() {
		return mAuthorName;
	}
	public void setmAuthorName(String mAuthorName) {
		this.mAuthorName = mAuthorName;
	}
	public void reset()
	{
		this.mTemplate=null;
		this.mAppName="";
		this.mAuthorName="";
	}
	public String getmFileName() {
		return mFileName;
	}
	public void setmFileName(String mFileName) {
		this.mFileName = mFileName;
	}
	

}
