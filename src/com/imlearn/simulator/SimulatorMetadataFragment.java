package com.imlearn.simulator;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.buildmlearntoolkit.R;
import com.imlearn.application.MyApplication;
import com.imlearn.fragments.FlashcardQuestionTemplate;
import com.imlearn.fragments.LearningQuestionTemplate;
import com.imlearn.fragments.QuizQuestionTemplate;
import com.imlearn.fragments.SpellingQuestionTemplate;
import com.imlearn.models.Template;
import com.imlearn.template.flashcard.FlashCardDataTemplate;
import com.imlearn.template.mlearning.LearningDataTemplate;
import com.imlearn.template.quiz.QuizDataTemplate;
import com.imlearn.template.spellings.SpellingsDataTemplate;

public class SimulatorMetadataFragment extends Fragment{
	TextView appName,authorName;
	Button btnStart;
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		ViewGroup rootView=(ViewGroup)inflater.inflate(R.layout.layout_simulator_metadata, container,false);
		appName=(TextView)rootView.findViewById(R.id.appName);
		authorName=(TextView)rootView.findViewById(R.id.authorName);
		btnStart=(Button)rootView.findViewById(R.id.btnStart);
		return rootView;
	}
	
	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
	//	try{
			appName.setText(""+((MyApplication)getActivity().getApplication()).getmModel().getmAppName());
			authorName.setText(""+((MyApplication)getActivity().getApplication()).getmModel().getmAuthorName());
			
		/*}catch(Exception e)
		{
			
		}*/
		btnStart.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Template template= ((MyApplication)getActivity().getApplication()).getmModel().getmTemplate();
				
				try{
					
						
						if(template==Template.FLASHCARD)
						{
							((SimulationActivity)getActivity()).switchFragment(new SimulatorFlashcardFragment());
						}
						else if(template==Template.LEARNING)
						{
							((SimulationActivity)getActivity()).switchFragment(new SimulatorLearningFragment());
						}
						else if(template==Template.QUIZ)
						{
							((SimulationActivity)getActivity()).switchFragment(new SimulatorQuizFragment());
						}
						else if(template==Template.SPELLLING)
						{
							((SimulationActivity)getActivity()).switchFragment(new SimulatorSpellingFragment());
						}
					}catch(Exception e)
					{
						
					}
				}
		});
	}

}
