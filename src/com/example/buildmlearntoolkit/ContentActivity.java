package com.example.buildmlearntoolkit;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

import com.buildmlearn.fragments.QuestionsListFragment;
public class ContentActivity extends ActionBarActivity {
	
	private Toolbar toolbar;
	public static ArrayList mDataList=new ArrayList();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.activity_content);
		toolbar=(Toolbar)findViewById(R.id.toolbar);
        if (toolbar != null) {
            toolbar.setTitle("BuildmLearn");
            setSupportActionBar(toolbar);
            toolbar.setNavigationIcon(R.drawable.ic_menu_back);
        }
        toolbar.setNavigationOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.e("back", "called");
				onBackPressed();
			}
		});
        
	 Fragment fragment = new QuestionsListFragment();
       
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frame, fragment).commit();
		 
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.content, menu);
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
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		Intent i =new Intent(ContentActivity.this,MainActivity.class);
		startActivity(i);
		finish();
	}
	
	public ArrayList getData()
	{
		Log.e("list size", ""+mDataList.size());
		return mDataList;
	}
	public void switchFragment(Fragment f)
	{
		Log.e("switch", ""+f.toString());
		 Fragment fragment = f;
	       
	        FragmentManager fragmentManager = getSupportFragmentManager();
	        fragmentManager.beginTransaction().replace(R.id.frame, fragment).commit();
	}
}
