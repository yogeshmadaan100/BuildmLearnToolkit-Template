package com.buildmlearn.simulator;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

import com.buildmlearn.application.MyApplication;
import com.buildmlearn.template.quiz.QuizDataTemplate;
import com.example.buildmlearntoolkit.R;

public class SimulatorQuizFragment extends Fragment {
	
	TextView question_no,question_label;
	RadioGroup options;
	RadioButton option1,option2,option3,option4;
	Button btnNext,btnSubmit;
	private int iQuestionIndex = -1;
	private int iCurrentCorrectAnswer;
	private List<RadioButton> iRadButtonList = new ArrayList<RadioButton>();
	public static ArrayList mDataList=new ArrayList();
	
	int answer=-1,correct=0,incorrect=0;
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		ViewGroup rootView=(ViewGroup)inflater.inflate(R.layout.layout_simulator_quiz, container,false);
		question_no=(TextView)rootView.findViewById(R.id.question_no);
		question_label=(TextView)rootView.findViewById(R.id.question_label);
		options=(RadioGroup)rootView.findViewById(R.id.radioGroup1);
		option1=(RadioButton)rootView.findViewById(R.id.radio0);
		option2=(RadioButton)rootView.findViewById(R.id.radio1);
		option3=(RadioButton)rootView.findViewById(R.id.radio2);
		option4=(RadioButton)rootView.findViewById(R.id.radio3);
		btnNext=(Button)rootView.findViewById(R.id.next_button);
		btnSubmit=(Button)rootView.findViewById(R.id.submit_button);
		iRadButtonList.add(option1);
		iRadButtonList.add(option2);
		iRadButtonList.add(option3);
		iRadButtonList.add(option4);
		
		options.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				switch (checkedId) {
				case R.id.radio0:
					answer=1;
					break;
				case R.id.radio1:
					answer=2;
					break;
				case R.id.radio2:
					answer=3;
					break;
				case R.id.radio3:
					answer=4;
					break;

				default:
					break;
				}
			}
		});
		btnNext.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				for (int i = 0; i < iRadButtonList.size(); i++) {
					iRadButtonList.get(i).setBackgroundColor(Color.TRANSPARENT);
				}
				nextQuestion();
			}
		});
		btnSubmit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				int selectedAnswer = getSelectedAnswer();
				if (selectedAnswer == -1) {
					Toast.makeText(getActivity(),
							"Please select an answer!", 1000).show();
				} else if (selectedAnswer != -1
						&& selectedAnswer == iCurrentCorrectAnswer) {
					iRadButtonList.get(iCurrentCorrectAnswer)
							.setBackgroundColor(Color.GREEN);
					Toast.makeText(getActivity(),
							"That's the correct answer!", 1000).show();
					correct++;
					btnSubmit.setEnabled(false);
					/*
					 * iSubmitButton.setVisibility(View.GONE);
					 * iNextButton.setVisibility(View.VISIBLE);
					 */
				} else {
					iRadButtonList.get(selectedAnswer).setBackgroundColor(
							Color.RED);
					iRadButtonList.get(iCurrentCorrectAnswer)
							.setBackgroundColor(Color.GREEN);
					Toast.makeText(getActivity(),
							"Sorry, wrong answer!", 1000).show();

					btnSubmit.setEnabled(false);
					incorrect++;
					// iSubmitButton.setVisibility(View.GONE);
					// iNextButton.setVisibility(View.VISIBLE);

				}
			}
		});
		return rootView;
	}
	
	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		mDataList=new ArrayList<QuizDataTemplate>();
		mDataList=((MyApplication)getActivity().getApplication()).getData();
		nextQuestion();
		
	}
	public int getSelectedAnswer() {
		int selected = -1;
		for (int i = 0; i < iRadButtonList.size(); i++) {
			if (iRadButtonList.get(i).isChecked()) {
				return i;
			}
		}
		return selected;
	}
	public void nextQuestion()
	{
		question_no.setText(""+(iQuestionIndex+2)+"/"+(mDataList.size()));
		btnSubmit.setEnabled(true);
		options.clearCheck();
		if(iQuestionIndex<mDataList.size()-1)
		{
			iQuestionIndex++;
			question_label.setText(((QuizDataTemplate)mDataList.get(iQuestionIndex)).getQuestion());
			option1.setText(((QuizDataTemplate)mDataList.get(iQuestionIndex)).getOption1());
			option2.setText(((QuizDataTemplate)mDataList.get(iQuestionIndex)).getOption2());
			option3.setText(((QuizDataTemplate)mDataList.get(iQuestionIndex)).getOption3());
			option4.setText(((QuizDataTemplate)mDataList.get(iQuestionIndex)).getOption4());
			
		}
		else
			{
				ScoreCardFragment f= new ScoreCardFragment();
				Bundle b= new Bundle();
				b.putString("result", "Total Question: "+mDataList.size()+"\nCorrect Answers:"+correct+"\nIncorrect Answers:"+incorrect+"\nUnanswered Questions:"+ (mDataList.size()-correct-incorrect));
				f.setArguments(b);
				((SimulationActivity)getActivity()).switchFragment(f);
			}
	}

}
