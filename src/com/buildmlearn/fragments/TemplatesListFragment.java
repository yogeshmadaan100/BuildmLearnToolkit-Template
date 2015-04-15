package com.buildmlearn.fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
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
import com.buildmlearn.models.Template;
import com.example.buildmlearntoolkit.R;
import com.example.buildmlearntoolkit.MainActivity.LovelyView;

public class TemplatesListFragment extends Fragment{
	 private ListView mDrawerList;
	 private String[] mTemplatesTitles;
	    private String[] mTemplateDescription;
	    public int template_thumbnail;
	   
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		ViewGroup rootView=(ViewGroup)inflater.inflate(R.layout.layout_templates_list, container,false);
		mDrawerList = (ListView) rootView.findViewById(R.id.left_drawer);
		mTemplatesTitles = getResources().getStringArray(R.array.templates_array);
        mTemplateDescription=getResources().getStringArray(R.array.template_description_array);
       
		 mDrawerList.setAdapter(new ArrayAdapter<String>(getActivity(),
	                R.layout.drawer_list_item, mTemplatesTitles));
		 mDrawerList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				String dialog_title,dialog_description;
		    	dialog_title=mTemplatesTitles[position];
		    	dialog_description=mTemplateDescription[position];
		    	
		    	
		    	 switch (position) {
					case 0:
						
						template_thumbnail=R.drawable.mlearning_thumbnail;
						 showThemed(dialog_title,dialog_description);
						 ((MyApplication)getActivity().getApplication()).getmModel().setmTemplate(Template.LEARNING);
						break;
					case 1:
						template_thumbnail=R.drawable.flashcard_thumbnail;
						 showThemed(dialog_title,dialog_description);
						 ((MyApplication)getActivity().getApplication()).getmModel().setmTemplate(Template.FLASHCARD);
						break;
					case 2:
						template_thumbnail=R.drawable.spellings_thumbnail;
						 showThemed(dialog_title,dialog_description);
						 ((MyApplication)getActivity().getApplication()).getmModel().setmTemplate(Template.SPELLLING);
						break;
					case 3:
						template_thumbnail=R.drawable.quiz_thumbnail;
						 showThemed(dialog_title,dialog_description);
						 ((MyApplication)getActivity().getApplication()).getmModel().setmTemplate(Template.QUIZ);
						break;
			
					default:
						break;
					}
			}
		});
		return rootView;
	}
	private void showThemed(String title,String description) {
    	LovelyView view =new LovelyView(getActivity());
    	view.setDescription(description);
    	view.setThumbnail(template_thumbnail);
    	
    	 new MaterialDialog.Builder(getActivity())
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
                    	
                    	startActivity(new Intent(getActivity(),TemplateActivity.class));
                    	getActivity().finish();
                    }

                    @Override
                    public void onNeutral(MaterialDialog dialog) {
                      //  Toast.makeText(getApplicationContext(), "Neutral", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNegative(MaterialDialog dialog) {
                       // Toast.makeText(getApplicationContext(), "Negative…", Toast.LENGTH_SHORT).show();
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
	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
	}

}
