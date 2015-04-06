package com.buildmlearn.simulator;

import com.buildmlearn.fragments.QuestionsListFragment;
import com.example.buildmlearntoolkit.R;
import com.example.buildmlearntoolkit.R.layout;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

public class SimulationActivity extends ActionBarActivity {
	LinearLayout child_activity;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.activity_simulation);
		child_activity=(LinearLayout)findViewById(R.id.child);
		 Fragment fragment = new SimulatorMetadataFragment();
	       
	        FragmentManager fragmentManager = getSupportFragmentManager();
	        fragmentManager.beginTransaction().replace(R.id.child, fragment).commit();
			 
	}
	@Override
	public void setContentView(int layoutResID) {
		// TODO Auto-generated method stub
		LayoutInflater inflater = (LayoutInflater)getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(layoutResID, child_activity);
	}
	public void switchFragment(Fragment f)
	{
		Log.e("switch", ""+f.toString());
		 Fragment fragment = f;
	       
	        FragmentManager fragmentManager = getSupportFragmentManager();
	        fragmentManager.beginTransaction().replace(R.id.child, fragment).commit();
	}
}
