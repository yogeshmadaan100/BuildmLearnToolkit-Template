package com.buildmlearn.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.example.buildmlearntoolkit.R;

public class BaseActivity extends ActionBarActivity {
	LinearLayout child_activity;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.activity_base);
		child_activity=(LinearLayout)findViewById(R.id.child);
	}
	@Override
	public void setContentView(int layoutResID) {
		// TODO Auto-generated method stub
		LayoutInflater inflater = (LayoutInflater)getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(layoutResID, child_activity);
	}
	
}
