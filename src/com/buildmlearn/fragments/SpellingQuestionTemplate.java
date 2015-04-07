package com.buildmlearn.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Toast;

import com.buildmlearn.application.MyApplication;
import com.buildmlearn.template.mlearning.LearningDataTemplate;
import com.buildmlearn.template.spellings.SpellingsDataTemplate;
import com.buildmlearn.utils.ProgressGenerator;
import com.dd.processbutton.iml.ActionProcessButton;
import com.example.buildmlearntoolkit.ContentActivity;
import com.example.buildmlearntoolkit.R;
import com.iangclifton.android.floatlabel.FloatLabel;

public class SpellingQuestionTemplate extends Fragment  implements com.buildmlearn.utils.ProgressGenerator.OnCompleteListener {
	private ActionProcessButton mAdd;
	private FloatLabel mWord,mMeaning;
	private ProgressGenerator progressGenerator=new ProgressGenerator(this);
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		ViewGroup rootView=(ViewGroup) inflater.inflate(R.layout.layout_spellings_questions, container,false);
		mWord=(FloatLabel)rootView.findViewById(R.id.word);
		mMeaning=(FloatLabel)rootView.findViewById(R.id.meaning);
		mAdd=(ActionProcessButton)rootView.findViewById(R.id.btnNext);
		mAdd.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(mWord.getEditText().getText().toString().trim().length()!=0&&mMeaning.getEditText().getText().toString().trim().length()!=0)
				{
					MyApplication.mDataList.add(new SpellingsDataTemplate(mWord.getEditText().getText().toString(), mMeaning.getEditText().getText().toString()));
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
