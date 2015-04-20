package com.buildmlearn.activities;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.Theme;
import com.buildmlearn.application.MyApplication;
import com.buildmlearn.base.BaseActivity;
import com.buildmlearn.design.models.ColorGenerator;
import com.buildmlearn.design.models.TextDrawable;
import com.buildmlearn.fragments.MetaDataFragment;
import com.buildmlearn.simulator.SimulationActivity;
import com.example.buildmlearntoolkit.MainActivity;
import com.example.buildmlearntoolkit.R;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;

public class TemplateActivity extends BaseActivity {
	 private Toolbar toolbar;
	 private ColorGenerator mColorGenerator = ColorGenerator.MATERIAL;
	    private TextDrawable.IBuilder mDrawableBuilder;
	    private Context mContext=this;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.activity_template);
		 toolbar=(Toolbar)findViewById(R.id.toolbar);
	        if (toolbar != null) {
	            toolbar.setTitle(""+((MyApplication)getApplication()).getmModel().getmTemplate());
	            setSupportActionBar(toolbar);
	            toolbar.setNavigationIcon(R.drawable.ic_menu_back);
	        }
	        else
	        	Log.e("toolbar ", "null");
	        toolbar.setNavigationOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Log.e("back", "called");
					onBackPressed();
				}
			});
	        
		 Fragment fragment = new MetaDataFragment();
	       
	        FragmentManager fragmentManager = getSupportFragmentManager();
	        fragmentManager.beginTransaction().replace(R.id.frame, fragment).commit();
	        
	        
	        
	        mDrawableBuilder=TextDrawable.builder().round();
			 
			 TextDrawable drawable = mDrawableBuilder.build("", Color.parseColor("#e81e61"));
			
			final ImageView fabIconNew = new ImageView(this);
	       fabIconNew.setImageDrawable(getResources().getDrawable(R.drawable.ic_menu_close_clear_cancel));
	      final FloatingActionButton rightLowerButton = new FloatingActionButton.Builder(this)
	               .setContentView(fabIconNew).setBackgroundDrawable(R.drawable.fab_selector)
	               .build();

	       SubActionButton.Builder rLSubBuilder = new SubActionButton.Builder(this);
	       ImageView rlIcon1 = new ImageView(this);
	       
	       ImageView rlIcon2 = new ImageView(this);
	       ImageView rlIcon3 = new ImageView(this);
	       ImageView rlIcon4 = new ImageView(this);

	       rlIcon1.setImageDrawable(getResources().getDrawable(android.R.drawable.ic_menu_add));
	       rlIcon2.setImageDrawable(getResources().getDrawable(android.R.drawable.ic_menu_agenda));
	       rlIcon3.setImageDrawable(getResources().getDrawable(android.R.drawable.ic_menu_share));
	       rlIcon4.setImageDrawable(getResources().getDrawable(android.R.drawable.ic_menu_help));

	       // Build the menu with default options: light theme, 90 degrees, 72dp radius.
	       // Set 4 default SubActionButtons
	       final FloatingActionMenu rightLowerMenu = new FloatingActionMenu.Builder(this)
	       
	                                               .addSubActionView(rLSubBuilder.setContentView(rlIcon1).setBackgroundDrawable(drawable).build())
	                                               .addSubActionView(rLSubBuilder.setContentView(rlIcon2).build())
	                                               .addSubActionView(rLSubBuilder.setContentView(rlIcon3).build())
	                                               .addSubActionView(rLSubBuilder.setContentView(rlIcon4).build())
	                                               .attachTo(rightLowerButton)
	                                               .build();

	       // Listen menu open and close events to animate the button content view
	       rightLowerMenu.setStateChangeListener(new FloatingActionMenu.MenuStateChangeListener() {
	           @Override
	           public void onMenuOpened(FloatingActionMenu menu) {
	               // Rotate the icon of rightLowerButton 45 degrees clockwise
	               fabIconNew.setRotation(0);
	               PropertyValuesHolder pvhR = PropertyValuesHolder.ofFloat(View.ROTATION, 45);
	               ObjectAnimator animation = ObjectAnimator.ofPropertyValuesHolder(fabIconNew, pvhR);
	               animation.start();
	           }

	           @Override
	           public void onMenuClosed(FloatingActionMenu menu) {
	               // Rotate the icon of rightLowerButton 45 degrees counter-clockwise
	               fabIconNew.setRotation(45);
	               PropertyValuesHolder pvhR = PropertyValuesHolder.ofFloat(View.ROTATION, 0);
	               ObjectAnimator animation = ObjectAnimator.ofPropertyValuesHolder(fabIconNew, pvhR);
	               animation.start();
	           }
	       });
	       rlIcon1.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent i = new Intent(getApplicationContext(),MainActivity.class);
					startActivity(i);
					finish();
					rightLowerMenu.close(true);
				}
			});
	       rlIcon2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(getApplicationContext(),MainActivity.class);
				startActivity(i);
				finish();
				rightLowerMenu.close(true);
			}
		});
	       rlIcon3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent sendIntent = new Intent();
		       	 sendIntent.setAction(Intent.ACTION_SEND);
		       	 sendIntent.putExtra(Intent.EXTRA_TEXT, "Hi I am using BuildmLearn Toolkit  - An initiative by BuildmLearn .You must also try https://play.google.com/store/apps/details?id=org.buildmlearn.learnfrommap");
		       	 sendIntent.setType("text/plain");
		       	 startActivity(sendIntent);
		       	rightLowerMenu.close(true);
			}
		});
	       rlIcon4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new MaterialDialog.Builder(mContext)
	            .title("Welcome to Help")
	            .content(R.string.help_text)
	            .positiveText(R.string.agree)
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
	            .callback(new MaterialDialog.ButtonCallback() {
	                @Override
	                public void onPositive(MaterialDialog dialog) {
	                	
	                	
	                	
	                }

	               
	            })
	            .show();
				rightLowerMenu.close(true);
			}
		});
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
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		Intent i =new Intent(TemplateActivity.this,MainActivity.class);
		startActivity(i);
		finish();
	}
}
