package com.imlearn.fragments;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.transition.Visibility;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.buildmlearntoolkit.R;
import com.imlearn.adapters.EfficientAdapter;
import com.imlearn.application.MyApplication;
import com.imlearn.xml.XmlReader;

public class NoProjectFragment extends Fragment{
	LinearLayout noprojects;
	public static ArrayList<String> files;
	ListView mList;
	public static File file[];
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		ViewGroup rootView=(ViewGroup)inflater.inflate(R.layout.layout_no_fragment, container,false);
		noprojects=(LinearLayout)rootView.findViewById(R.id.noprojects);
		mList=(ListView)rootView.findViewById(R.id.listView1);
		files = new ArrayList<String>();
		// ((MyApplication)MyApplication.mApplication.getApplication()).resetModel();
			
		
		String path = Environment.getExternalStorageDirectory().toString()+"/buildmLearnFiles/SavedProjects";
		File f = new File(path);        
		 file= f.listFiles();

		if(file!=null)
		{
			if(file.length!=0)
			noprojects.setVisibility(View.GONE);
			Log.e("Files", "Size: "+ file.length);
			for (int i=0; i < file.length; i++)
			{
			    String temp=file[i].getName().substring(0, file[i].getName().toString().length()-4);
			    if(file[i].getName().endsWith(".bml"))
			    	files.add(temp);
			}
			mList.setAdapter(new EfficientAdapter(getActivity(),files));
		}
		else
		{
			noprojects.setVisibility(View.VISIBLE);
		}
		
		mList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				try {
					((MyApplication)MyApplication.mApplication.getApplication()).getmModel().setmFileName(files.get(position));
					XmlReader.readXml(file[position].getAbsolutePath().toString());
				} catch (ParserConfigurationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SAXException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
