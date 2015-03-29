package com.buildmlearn.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.buildmlearn.utils.ProgressGenerator;
import com.dd.processbutton.iml.ActionProcessButton;
import com.example.buildmlearntoolkit.ContentActivity;
import com.example.buildmlearntoolkit.R;

public class MetaDataFragment extends Fragment  implements com.buildmlearn.utils.ProgressGenerator.OnCompleteListener{
	private ActionProcessButton mNext;
	private ProgressGenerator progressGenerator=new ProgressGenerator(this);
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		ViewGroup rootView=(ViewGroup)inflater.inflate(R.layout.layout_metadata_fragment, container,false);
		mNext=(ActionProcessButton)rootView.findViewById(R.id.btnNext);
		
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
				progressGenerator.start(mNext);
				mNext.setEnabled(false);
			}
		});
	}
	@Override
	public void onComplete() {
		// TODO Auto-generated method stub
		Intent i=new Intent(getActivity(),ContentActivity.class);
		startActivity(i);
		getActivity().finish();
	}

}
