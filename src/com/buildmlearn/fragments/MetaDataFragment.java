package com.buildmlearn.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Toast;

import com.buildmlearn.application.MyApplication;
import com.buildmlearn.utils.ProgressGenerator;
import com.dd.processbutton.iml.ActionProcessButton;
import com.example.buildmlearntoolkit.ContentActivity;
import com.example.buildmlearntoolkit.R;
import com.iangclifton.android.floatlabel.FloatLabel;

public class MetaDataFragment extends Fragment  implements com.buildmlearn.utils.ProgressGenerator.OnCompleteListener{
	private ActionProcessButton mNext;
	private ProgressGenerator progressGenerator=new ProgressGenerator(this);
	private FloatLabel mAuthorName,mAppName;
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		ViewGroup rootView=(ViewGroup)inflater.inflate(R.layout.layout_metadata_fragment, container,false);
		mNext=(ActionProcessButton)rootView.findViewById(R.id.btnNext);
		mAuthorName=(FloatLabel)rootView.findViewById(R.id.authorName);
		mAppName=(FloatLabel)rootView.findViewById(R.id.appName);
		try{
			mAuthorName.getEditText().setText(((MyApplication)getActivity().getApplication()).getmModel().getmAuthorName());
			mAppName.getEditText().setText(((MyApplication)getActivity().getApplication()).getmModel().getmAppName());
		}catch(Exception e)
		{
			
		}
		return rootView;
	}
	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		
		mNext.setMode(ActionProcessButton.Mode.ENDLESS);
		mNext.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(mAuthorName.getEditText().getText().toString().trim().length()!=0&&mAppName.getEditText().getText().toString().trim().length()!=0)
				{
					progressGenerator.start(mNext);
					mNext.setEnabled(false);
				}
				else
					Toast.makeText(getActivity(), "Enter Complete Details", 2000).show();
			}
		});
	}
	@Override
	public void onComplete() {
		// TODO Auto-generated method stub
		Intent i=new Intent(getActivity(),ContentActivity.class);
		((MyApplication)getActivity().getApplication()).getmModel().setmAppName(mAppName.getEditText().getText().toString());
		((MyApplication)getActivity().getApplication()).getmModel().setmAuthorName(mAuthorName.getEditText().getText().toString());
		startActivity(i);
		getActivity().finish();
	}

}
