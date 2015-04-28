package com.imlearn.simulator;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.buildmlearntoolkit.R;
import com.imlearn.application.MyApplication;
import com.imlearn.models.Template;

public class ScoreCardFragment extends Fragment {
	TextView result;
	Button restart,exit;

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		ViewGroup rootView=(ViewGroup)inflater.inflate(R.layout.layout_scorecard, container,false);
		result=(TextView)rootView.findViewById(R.id.result);
		restart=(Button)rootView.findViewById(R.id.btnRestart);
		exit=(Button)rootView.findViewById(R.id.btnExit);
		try{
		result.setText(getArguments().getString("result"));
		}catch(Exception e)
		{
			
		}
		restart.setOnClickListener(new OnClickListener() {
			
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
		exit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				getActivity().finish();
			}
		});
		return rootView;
	}
	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
	}
}
