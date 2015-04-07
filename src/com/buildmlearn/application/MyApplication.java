package com.buildmlearn.application;

import java.util.ArrayList;

import android.app.Application;
import android.util.Log;

public class MyApplication extends Application {
	
	public static MyApplication mApplication;
	private ApplicationModel mModel;
	public static ArrayList mDataList=new ArrayList();
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
	public ArrayList getData()
	{
		Log.e("list size", ""+mDataList.size());
		return mDataList;
	}
	public void resetModel()
	{
		mModel=new ApplicationModel();
	}
}
