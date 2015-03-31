package com.buildmlearn.application;

import android.app.Application;

public class MyApplication extends Application {
	
	public static MyApplication mApplication;
	private ApplicationModel mModel;
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		mApplication=this;
		mModel=new ApplicationModel();
	}
	
	public MyApplication getApplication()
	{
		return mApplication;
	}

	public ApplicationModel getmModel() {
		return mModel;
	}

	public void setmModel(ApplicationModel mModel) {
		this.mModel = mModel;
	}

}
