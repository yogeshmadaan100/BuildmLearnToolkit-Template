package com.example.buildmlearntoolkit;

import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;

import android.app.AlertDialog;
import android.content.DialogInterface;
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

import com.buildmlearn.application.MyApplication;
import com.buildmlearn.fragments.FlashcardQuestionTemplate;
import com.buildmlearn.fragments.LearningQuestionTemplate;
import com.buildmlearn.fragments.QuestionsListFragment;
import com.buildmlearn.fragments.QuizQuestionTemplate;
import com.buildmlearn.fragments.SpellingQuestionTemplate;
import com.buildmlearn.models.Template;
import com.buildmlearn.simulator.SimulationActivity;
import com.buildmlearn.template.flashcard.FlashCardDataTemplate;
import com.buildmlearn.template.flashcard.FlashCardXml;
import com.buildmlearn.template.mlearning.LearningDataTemplate;
import com.buildmlearn.template.mlearning.LearningXml;
import com.buildmlearn.template.quiz.QuizDataTemplate;
import com.buildmlearn.template.quiz.QuizXml;
import com.buildmlearn.template.spellings.SpellingXml;
import com.buildmlearn.template.spellings.SpellingsDataTemplate;
public class ContentActivity extends ActionBarActivity {
	
	private Toolbar toolbar;
	
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
		if(id==R.id.action_save)
		{
			Template template= ((MyApplication)getApplication()).getmModel().getmTemplate();
			if(template==Template.FLASHCARD)
			{
				FlashCardXml xml=new FlashCardXml();
				try {
					xml.writeXml(QuestionsListFragment.mDataList);
				} catch (TransformerConfigurationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ParserConfigurationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (TransformerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else if(template==Template.LEARNING)
			{
				LearningXml xml=new LearningXml();
				try {
					xml.writeXml(QuestionsListFragment.mDataList);
				} catch (TransformerConfigurationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ParserConfigurationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (TransformerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else if(template==Template.QUIZ)
			{
				QuizXml xml=new QuizXml();
				try {
					xml.writeXml(QuestionsListFragment.mDataList);
				} catch (TransformerConfigurationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ParserConfigurationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (TransformerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else if(template==Template.SPELLLING)
			{
				SpellingXml xml=new SpellingXml();
				try {
					xml.writeXml(QuestionsListFragment.mDataList);
				} catch (TransformerConfigurationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ParserConfigurationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (TransformerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		if (id == R.id.action_settings) {
			LearningXml xml=new LearningXml();
			try {
				xml.writeXml(QuestionsListFragment.mDataList);
			} catch (TransformerConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (TransformerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return true;
		}
		if (id == R.id.action_simulate) {
			Intent i = new Intent(getApplicationContext(),SimulationActivity.class);
			startActivity(i);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		final Intent i =new Intent(ContentActivity.this,MainActivity.class);
		startActivity(i);
		finish();
	
	}
	
	
	public void switchFragment(Fragment f)
	{
		Log.e("switch", ""+f.toString());
		 Fragment fragment = f;
	       
	        FragmentManager fragmentManager = getSupportFragmentManager();
	        fragmentManager.beginTransaction().replace(R.id.frame, fragment).commit();
	}
}
