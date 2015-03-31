package com.buildmlearn.fragments;

import java.io.File;
import java.util.ArrayList;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.buildmlearntoolkit.R;

public class NoProjectFragment extends Fragment{
	LinearLayout noprojects;
	ArrayList<String> files;
	ListView mList;
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		ViewGroup rootView=(ViewGroup)inflater.inflate(R.layout.layout_no_fragment, container,false);
		noprojects=(LinearLayout)rootView.findViewById(R.id.noprojects);
		mList=(ListView)rootView.findViewById(R.id.listView1);
		files = new ArrayList<String>();
		
			
		
		String path = Environment.getExternalStorageDirectory().toString()+"/buildmLearnFiles";
		Log.e("Files", "Path: " + path);
		File f = new File(path);        
		File file[] = f.listFiles();

		if(file!=null)
		{
			noprojects.setVisibility(View.GONE);
			Log.e("Files", "Size: "+ file.length);
			for (int i=0; i < file.length; i++)
			{
			    Log.e("Files", "FileName:" + file[i].getName());
			    Log.e("Files", "File Location :" +file[i].getAbsolutePath());
			    String temp=file[i].getName().substring(5, file[i].getName().toString().length()-4);
			    Log.e("file name is ", temp);
			    
			     

			 files.add(new String(file[i].getName()));
			}
			mList.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,files));
		}
		
		return rootView;
	}
	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
	}

}
