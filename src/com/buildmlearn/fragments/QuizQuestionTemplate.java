package com.buildmlearn.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.buildmlearn.application.MyApplication;
import com.buildmlearn.template.flashcard.FlashCardDataTemplate;
import com.buildmlearn.template.mlearning.LearningDataTemplate;
import com.buildmlearn.template.quiz.QuizDataTemplate;
import com.buildmlearn.utils.ProgressGenerator;
import com.dd.processbutton.iml.ActionProcessButton;
import com.example.buildmlearntoolkit.ContentActivity;
import com.example.buildmlearntoolkit.R;
import com.iangclifton.android.floatlabel.FloatLabel;

public class QuizQuestionTemplate extends Fragment  implements com.buildmlearn.utils.ProgressGenerator.OnCompleteListener {
	private ActionProcessButton mAdd;
	private EditText mQuestion,mOption1,mOption2,mOption3,mOption4;
	RadioGroup rGroup;
	private ProgressGenerator progressGenerator=new ProgressGenerator(this);
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		ViewGroup rootView=(ViewGroup) inflater.inflate(R.layout.layout_quiz_questions, container,false);
		
		mQuestion=(EditText)rootView.findViewById(R.id.question);
		mOption1=(EditText)rootView.findViewById(R.id.optionEdit1);
		mOption2=(EditText)rootView.findViewById(R.id.optionEdit2);
		mOption3=(EditText)rootView.findViewById(R.id.optionEdit3);
		mOption4=(EditText)rootView.findViewById(R.id.optionEdit4);
		rGroup=(RadioGroup)rootView.findViewById(R.id.options);
		View rButton=rGroup.findViewById(rGroup.getCheckedRadioButtonId());
		final int rselected=rGroup.indexOfChild(rButton);
		mAdd=(ActionProcessButton)rootView.findViewById(R.id.btnNext);
		mAdd.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(mQuestion.getText().toString().trim().length()!=0&&mOption1.getText().toString().trim().length()!=0&&mOption4.getText().toString().trim().length()!=0&&mOption4.getText().toString().trim().length()!=0&&mOption4.getText().toString().trim().length()!=0)
				{
					MyApplication.mDataList.add(new QuizDataTemplate(mQuestion.getText().toString(),mOption1.getText().toString(),mOption2.getText().toString(),mOption3.getText().toString(),mOption4.getText().toString(),rselected));
					progressGenerator.start(mAdd);
					mAdd.setEnabled(false);
				}
				else
					Toast.makeText(getActivity(), "Enter all field", 2000).show();
			}
		});
		return rootView;
	}
	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
	}
	@Override
	public void onComplete() {
		// TODO Auto-generated method stub
		((ContentActivity)getActivity()).switchFragment(new QuestionsListFragment());
	}

}
