package com.example.buildmlearntoolkit;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.Theme;
import com.buildmlearn.activities.TemplateActivity;
import com.buildmlearn.application.MyApplication;
import com.buildmlearn.base.BaseActivity;
import com.buildmlearn.design.models.ColorGenerator;
import com.buildmlearn.design.models.TextDrawable;
import com.buildmlearn.fragments.NoProjectFragment;
import com.buildmlearn.models.Template;
import com.buildmlearn.simulator.SimulationActivity;
import com.buildmlearn.utils.HelpGenerator;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;


public class MainActivity extends BaseActivity {
	
	
	private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private Toolbar toolbar;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private String[] mTemplatesTitles;
    private String[] mTemplateDescription;
    public int template_thumbnail;
    boolean isFirstTime=true;
    private ColorGenerator mColorGenerator = ColorGenerator.MATERIAL;
    private TextDrawable.IBuilder mDrawableBuilder;
    private Context mContext=this;
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
        
        mDrawableBuilder=TextDrawable.builder().round();
		 
		 TextDrawable drawable = mDrawableBuilder.build("", Color.parseColor("#e81e61"));
		
		final ImageView fabIconNew = new ImageView(this);
       fabIconNew.setImageDrawable(getResources().getDrawable(R.drawable.ic_menu_close_clear_cancel));
      final FloatingActionButton rightLowerButton = new FloatingActionButton.Builder(this)
               .setContentView(fabIconNew).setBackgroundDrawable(drawable)
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
				 if(!mDrawerLayout.isDrawerOpen(Gravity.LEFT))
		             mDrawerLayout.openDrawer(Gravity.LEFT);
			}
		});
       rlIcon2.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Toast.makeText(mContext, "Select From the list of projects from the above list", 2000).show();
			
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
		}
	});
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
    	
    	//thumbnail.getLayoutParams().height=350;
    	//thumbnail.requestLayout();
    	String dialog_title,dialog_description;
    	dialog_title=mTemplatesTitles[position];
    	dialog_description=mTemplateDescription[position];
    	
    	
        Fragment fragment = new NoProjectFragment();
       
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

        // update selected item and title, then close the drawer
        mDrawerList.setItemChecked(position, true);
        //setTitle(mTemplatesTitles[position]);
       
        mDrawerLayout.closeDrawer(mDrawerList);
        if(!isFirstTime)
        {
	        switch (position) {
			case 0:
				
				template_thumbnail=R.drawable.mlearning_thumbnail;
				 showThemed(dialog_title,dialog_description);
				 ((MyApplication)getApplication()).getmModel().setmTemplate(Template.LEARNING);
				break;
			case 1:
				template_thumbnail=R.drawable.flashcard_thumbnail;
				 showThemed(dialog_title,dialog_description);
				 ((MyApplication)getApplication()).getmModel().setmTemplate(Template.FLASHCARD);
				break;
			case 2:
				template_thumbnail=R.drawable.spellings_thumbnail;
				 showThemed(dialog_title,dialog_description);
				 ((MyApplication)getApplication()).getmModel().setmTemplate(Template.SPELLLING);
				break;
			case 3:
				template_thumbnail=R.drawable.quiz_thumbnail;
				 showThemed(dialog_title,dialog_description);
				 ((MyApplication)getApplication()).getmModel().setmTemplate(Template.QUIZ);
				break;
	
			default:
				break;
			}
        }
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
    	LovelyView view =new LovelyView(getApplicationContext());
    	view.setDescription(description);
    	view.setThumbnail(template_thumbnail);
    	
    	 new MaterialDialog.Builder(this)
                .title(title)
                .content(R.string.no_project)
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
                .customView(view, true)
                .callback(new MaterialDialog.ButtonCallback() {
                    @Override
                    public void onPositive(MaterialDialog dialog) {
                    	
                    	startActivity(new Intent(getApplicationContext(),TemplateActivity.class));
                    	finish();
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
    
    
    
    public class LovelyView extends LinearLayout {
    	private String description = "";
    	private int res;
    	private TextView template_description;
    	private ImageView thumbnail;
    	

    	public LovelyView(Context context) {
    		super(context);
    		LayoutInflater.from(context).inflate(R.layout.layout_template_dialog, this);
    		initViews(context, null);
    	}

    	public LovelyView(Context context, AttributeSet attrs) {
    		super(context, attrs);
    		initViews(context, attrs);
    	}

    	public LovelyView(Context context, AttributeSet attrs, int defStyle) {
    		this(context, attrs);
    		initViews(context, attrs);
    	}

    	private void initViews(Context context, AttributeSet attrs) {
    		

    		LayoutInflater.from(context).inflate(R.layout.layout_template_dialog, this);

    		//left text view
    		template_description = (TextView) this.findViewById(R.id.template_description);
    		template_description.setText(description);
    		
    		//right text view
    		thumbnail = (ImageView) this.findViewById(R.id.template_thumbnail);
    		thumbnail.setImageResource(res);
    		
    	}

    	public String getDescription() {
    		return description;
    	}

    	public void setDescription(String description) {
    		this.description = description;
    		if(template_description!=null){
    			template_description.setText(description);
    		}
    	}

    	

    	public int getThumbnail()
    	{
    		return res;
    	}
    	public void setThumbnail(int res)
    	{
    		this.res=res;
    		
    			thumbnail.setImageResource(res);
    		
    	}
    }
    
}
