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
import android.widget.Toast;

import com.buildmlearn.application.MyApplication;
import com.buildmlearn.models.Mode;
import com.buildmlearn.template.flashcard.FlashCardDataTemplate;
import com.buildmlearn.template.mlearning.LearningDataTemplate;
import com.buildmlearn.utils.ProgressGenerator;
import com.dd.processbutton.iml.ActionProcessButton;
import com.example.buildmlearntoolkit.ContentActivity;
import com.example.buildmlearntoolkit.R;
import com.iangclifton.android.floatlabel.FloatLabel;

public class FlashcardQuestionTemplate extends Fragment  implements com.buildmlearn.utils.ProgressGenerator.OnCompleteListener {
	private ActionProcessButton mAdd;
	private FloatLabel mAnswer,mHint;
	private EditText mQuestion;
	private ImageView mImage;
	private ProgressGenerator progressGenerator=new ProgressGenerator(this);
	Mode mode=Mode.ADDITION;
	int position;
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		ViewGroup rootView=(ViewGroup) inflater.inflate(R.layout.layout_flashcard_questions, container,false);
		mAnswer=(FloatLabel)rootView.findViewById(R.id.answer);
		mHint=(FloatLabel)rootView.findViewById(R.id.hint);
		mQuestion=(EditText)rootView.findViewById(R.id.question);
		
		mAdd=(ActionProcessButton)rootView.findViewById(R.id.btnNext);
		mAdd.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(mAnswer.getEditText().getText().toString().trim().length()!=0&&mHint.getEditText().getText().toString().trim().length()!=0&&mQuestion.getText().toString().trim().length()!=0)
				{
					
						MyApplication.mDataList.add(new FlashCardDataTemplate(mQuestion.getText().toString(),mAnswer.getEditText().getText().toString(),"www.google.com", mHint.getEditText().getText().toString()));
					
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
		try{
			if(getArguments().getString("position","null")!="null")
			{
				position=Integer.parseInt(getArguments().getString("position"));
				mode=Mode.EDITING;
				mQuestion.setText(((FlashCardDataTemplate)QuestionsListFragment.mDataList.get(Integer.parseInt((getArguments().getString("position"))))).getQuestion());
				mAnswer.getEditText().setText(((FlashCardDataTemplate)QuestionsListFragment.mDataList.get(Integer.parseInt((getArguments().getString("position"))))).getAnswer());
				mHint.getEditText().setText(((FlashCardDataTemplate)QuestionsListFragment.mDataList.get(Integer.parseInt((getArguments().getString("position"))))).getHint());
				
			}
		}catch(Exception e)
		{
			
		}
		
	}
	@Override
	public void onComplete() {
		// TODO Auto-generated method stub
		((ContentActivity)getActivity()).switchFragment(new QuestionsListFragment());
	}

}
