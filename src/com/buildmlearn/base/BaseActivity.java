package com.buildmlearn.base;

import android.R.color;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Matrix.ScaleToFit;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;

import com.buildmlearn.design.models.ColorGenerator;
import com.buildmlearn.design.models.TextDrawable;
import com.example.buildmlearntoolkit.R;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;


public class BaseActivity extends ActionBarActivity {
	LinearLayout child_activity;
	public static  int currentColor=Color.parseColor("#1567bf") ;
	public static int navigationColor=Color.parseColor("#CC1978d1");
	private ColorGenerator mColorGenerator = ColorGenerator.MATERIAL;
    private TextDrawable.IBuilder mDrawableBuilder;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.activity_base);
		child_activity=(LinearLayout)findViewById(R.id.child);
		 mDrawableBuilder=TextDrawable.builder().round();
		 
		 TextDrawable drawable = mDrawableBuilder.build("", Color.parseColor("#e81e61"));
		
		final ImageView fabIconNew = new ImageView(this);
        fabIconNew.setImageDrawable(getResources().getDrawable(R.drawable.ic_menu_close_clear_cancel));
        //fabIconNew.setRotation(45);
       // fabIconNew.setScaleType(ScaleType.CENTER_CROP);
        
      //  fabIconNew.setBackgroundColor(BaseActivity.currentColor);
        final FloatingActionButton rightLowerButton = new FloatingActionButton.Builder(this)
                .setContentView(fabIconNew).setBackgroundDrawable(drawable)
                .build();

        SubActionButton.Builder rLSubBuilder = new SubActionButton.Builder(this);
        ImageView rlIcon1 = new ImageView(this);
        
        ImageView rlIcon2 = new ImageView(this);
        ImageView rlIcon3 = new ImageView(this);
        ImageView rlIcon4 = new ImageView(this);

        rlIcon1.setImageDrawable(getResources().getDrawable(R.drawable.ic_menu_close_clear_cancel));
        rlIcon2.setImageDrawable(getResources().getDrawable(R.drawable.ic_menu_close_clear_cancel));
        rlIcon3.setImageDrawable(getResources().getDrawable(R.drawable.ic_menu_close_clear_cancel));
        rlIcon4.setImageDrawable(getResources().getDrawable(R.drawable.ic_menu_close_clear_cancel));

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
	}
	@Override
	public void setContentView(int layoutResID) {
		// TODO Auto-generated method stub
		LayoutInflater inflater = (LayoutInflater)getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(layoutResID, child_activity);
	}
	
}
