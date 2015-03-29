package com.buildmlearn.application;

import android.app.Application;

public class MyApplication extends Application {
	
	private static MyApplication mApplication;
	
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		mApplication=this;
	}
	
	public MyApplication getApplication()
	{
		return mApplication;
	}

}
