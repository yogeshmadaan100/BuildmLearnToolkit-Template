package com.example.buildmlearntoolkit;

import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.Theme;
import com.buildmlearn.base.BaseActivity;
import com.buildmlearn.fragments.NoProjectFragment;


public class MainActivity extends BaseActivity {
	
	
	private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private Toolbar toolbar;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private String[] mTemplatesTitles;
    private String[] mTemplateDescription;
    public ImageView thumbnail;
    boolean isFirstTime=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_main);
     /*   if (Build.VERSION.SDK_INT >= 21) {
	           getWindow().setNavigationBarColor(BaseActivity.navigationColor);
	           getWindow().setStatusBarColor(BaseActivity.navigationColor) ;
	           Log.e("status bar color is ", ""+BaseActivity.currentColor);
	         
	        }
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(currentColor));*/
        
        mTitle = mDrawerTitle = getTitle();
        mTemplatesTitles = getResources().getStringArray(R.array.templates_array);
        mTemplateDescription=getResources().getStringArray(R.array.template_description_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        // set a custom shadow that overlays the main content when the drawer opens
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        // set up the drawer's list view with items and click listener
        mDrawerList.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_list_item, mTemplatesTitles));
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        // enable ActionBar app icon to behave as action to toggle nav drawer
       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
       // getSupportActionBar().setHomeButtonEnabled(true);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        if (toolbar != null) {
            toolbar.setTitle("BuildmLearn");
            setSupportActionBar(toolbar);
        }

        // ActionBarDrawerToggle ties together the the proper interactions
        // between the sliding drawer and the action bar app icon
        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                toolbar,  /* nav drawer image to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description for accessibility */
                R.string.drawer_close  /* "close drawer" description for accessibility */
                ) {
            public void onDrawerClosed(View view) {
                getSupportActionBar().setTitle("BuildmLearn");
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                getSupportActionBar().setTitle("Choose Template");
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
                isFirstTime=false;
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        if (savedInstanceState == null) {
            selectItem(0);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content view
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        menu.findItem(R.id.action_add).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
    	 if (mDrawerToggle.onOptionsItemSelected(item)) {
             return true;
         }
         // Handle action buttons
         switch(item.getItemId()) {
         case R.id.action_add:
             // create intent to perform web search for this planet
            if(!mDrawerLayout.isDrawerOpen(Gravity.LEFT))
             mDrawerLayout.openDrawer(Gravity.LEFT);
            	return true;
         default:
             return super.onOptionsItemSelected(item);
         }
    }
    
    
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        	if(!isFirstTime)
            selectItem(position);
        }
    }

    private void selectItem(int position) {
        // update the main content by replacing fragments
    	thumbnail=new ImageView(getApplicationContext());
    	thumbnail.setBackground(null);
    	String dialog_title,dialog_description;
    	dialog_title=mTemplatesTitles[position];
    	dialog_description=mTemplateDescription[position];
    	switch (position) {
		case 0:
			
			thumbnail.setImageResource(R.drawable.mlearning_thumbnail);
			
			break;
		case 1:
			thumbnail.setImageResource(R.drawable.flashcard_thumbnail);
			
			break;
		case 2:
			thumbnail.setImageResource(R.drawable.spellings_thumbnail);
			break;
		case 3:
			thumbnail.setImageResource(R.drawable.quiz_thumbnail);
			break;

		default:
			break;
		}
    	
        Fragment fragment = new NoProjectFragment();
       
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

        // update selected item and title, then close the drawer
        mDrawerList.setItemChecked(position, true);
        //setTitle(mTemplatesTitles[position]);
        showThemed(dialog_title,dialog_description);
        mDrawerLayout.closeDrawer(mDrawerList);
        
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
    private void showThemed(String title,String description) {
        new MaterialDialog.Builder(this)
                .title(title)
                .content(description)
                .positiveText(R.string.agree)
                .negativeText(R.string.disagree)
                .positiveColorRes(R.color.primaryColor)
                .negativeColorRes(R.color.primaryColor)
                .titleGravity(GravityEnum.CENTER)
                .titleColorRes(R.color.primaryColor)
                .contentColorRes(android.R.color.white)
                .backgroundColorRes(R.color.material_blue_grey_800)
                .dividerColorRes(R.color.status_bar)
                .btnSelector(R.drawable.md_btn_selector_custom, DialogAction.POSITIVE)
                .positiveColor(Color.WHITE)
                .negativeColorAttr(android.R.attr.textColorSecondaryInverse)
                .theme(Theme.LIGHT)
                .customView(thumbnail, true)
                .callback(new MaterialDialog.ButtonCallback() {
                    @Override
                    public void onPositive(MaterialDialog dialog) {
                        Toast.makeText(getApplicationContext(), "Positive!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNeutral(MaterialDialog dialog) {
                        Toast.makeText(getApplicationContext(), "Neutral", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNegative(MaterialDialog dialog) {
                        Toast.makeText(getApplicationContext(), "Negative…", Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
    }
    
}
