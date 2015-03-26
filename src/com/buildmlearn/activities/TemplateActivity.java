package com.buildmlearn.activities;

import com.buildmlearn.base.BaseActivity;
import com.buildmlearn.fragments.MetaDataFragment;
import com.buildmlearn.fragments.NoProjectFragment;
import com.example.buildmlearntoolkit.R;
import com.example.buildmlearntoolkit.R.id;
import com.example.buildmlearntoolkit.R.layout;
import com.example.buildmlearntoolkit.R.menu;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.database.MergeCursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class TemplateActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.activity_template);
		 Fragment fragment = new MetaDataFragment();
	       
	        FragmentManager fragmentManager = getSupportFragmentManager();
	        fragmentManager.beginTransaction().replace(android.R.id.content, fragment).commit();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.template, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
