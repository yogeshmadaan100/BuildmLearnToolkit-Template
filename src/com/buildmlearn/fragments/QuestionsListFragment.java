package com.buildmlearn.fragments;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import android.view.View.OnClickListener;

import com.buildmlearn.application.MyApplication;
import com.buildmlearn.models.Template;
import com.buildmlearn.template.flashcard.FlashCardDataTemplate;
import com.buildmlearn.template.mlearning.LearningDataTemplate;
import com.buildmlearn.template.quiz.QuizDataTemplate;
import com.buildmlearn.template.spellings.SpellingsDataTemplate;
import com.example.buildmlearntoolkit.ContentActivity;
import com.example.buildmlearntoolkit.R;

public class QuestionsListFragment extends Fragment {
	private ListView mListView;
	ImageButton mAdd,mRemove,moveUp,moveDown;
	public static ArrayList mDataList=new ArrayList();;
	ArrayList<String> mWords=new ArrayList<String>();
	Fragment f;
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		ViewGroup rootView=(ViewGroup) inflater.inflate(R.layout.layout_questions_list, container,false);
		mListView=(ListView)rootView.findViewById(R.id.list);
		mAdd=(ImageButton)rootView.findViewById(R.id.add);
		mRemove=(ImageButton)rootView.findViewById(R.id.remove);
		moveDown=(ImageButton)rootView.findViewById(R.id.down);
		moveUp=(ImageButton)rootView.findViewById(R.id.up);
		Template template= ((MyApplication)getActivity().getApplication()).getmModel().getmTemplate();
		if(template==Template.FLASHCARD)
		{
			f= new FlashcardQuestionTemplate();
			mDataList=new ArrayList<FlashCardDataTemplate>();
			mDataList=((ContentActivity)getActivity()).getData();
			
			if(mDataList!=null&mDataList.size()!=0)
			{
				for(int i=0;i<mDataList.size();i++)
				{
					
					String title=( (FlashCardDataTemplate) mDataList.get(i)).getQuestion();
					Log.e("title", ""+title);
					mWords.add(title);
				}
			}
		}
		else if(template==Template.LEARNING)
		{
			f=new LearningQuestionTemplate();
			mDataList=new ArrayList<LearningDataTemplate>();
			mDataList=((ContentActivity)getActivity()).getData();
			
			if(mDataList!=null&mDataList.size()!=0)
			{
				for(int i=0;i<mDataList.size();i++)
				{
					
					String title=((LearningDataTemplate) mDataList.get(i)).getmTitle();
					Log.e("title", ""+title);
					mWords.add(title);
				}
			}
		}
		else if(template==Template.QUIZ)
		{
			f=new QuizQuestionTemplate();
			mDataList=new ArrayList<QuizDataTemplate>();
			mDataList=((ContentActivity)getActivity()).getData();
		
			if(mDataList!=null&mDataList.size()!=0)
			{
				for(int i=0;i<mDataList.size();i++)
				{
					
					String title=((QuizDataTemplate) mDataList.get(i)).getQuestion();
					Log.e("title", ""+title);
					mWords.add(title);
				}
			}
		}
		else if(template==Template.SPELLLING)
		{
			f=new SpellingQuestionTemplate();
			mDataList=new ArrayList<SpellingsDataTemplate>();
			mDataList=((ContentActivity)getActivity()).getData();
			
			if(mDataList!=null&mDataList.size()!=0)
			{
				for(int i=0;i<mDataList.size();i++)
				{
					
					String title=((SpellingsDataTemplate) mDataList.get(i)).getmWord();
					Log.e("title", ""+title);
					mWords.add(title);
				}
			}
		}
		
		mListView.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,mWords));
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				//Toast.makeText(getActivity(), ""+mDataList.get(position).getDescription(), 2000).show();
			}
		});
		mAdd.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				((ContentActivity)getActivity()).switchFragment(f);
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