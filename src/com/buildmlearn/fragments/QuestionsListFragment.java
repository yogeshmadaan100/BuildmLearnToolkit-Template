package com.buildmlearn.fragments;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

import com.buildmlearn.adapters.EfficientAdapter;
import com.buildmlearn.application.MyApplication;
import com.buildmlearn.models.Template;
import com.buildmlearn.template.flashcard.FlashCardDataTemplate;
import com.buildmlearn.template.mlearning.LearningDataTemplate;
import com.buildmlearn.template.quiz.QuizDataTemplate;
import com.buildmlearn.template.spellings.SpellingsDataTemplate;
import com.example.buildmlearntoolkit.ContentActivity;
import com.example.buildmlearntoolkit.R;
import com.terlici.dragndroplist.DragNDropListView;
import com.terlici.dragndroplist.DragNDropListView.OnItemDragNDropListener;
import com.terlici.dragndroplist.DragNDropSimpleAdapter;

public class QuestionsListFragment extends Fragment {
	private DragNDropListView mListView;
	ImageButton mAdd,mRemove,moveUp,moveDown;
	@SuppressWarnings("rawtypes")
	public static ArrayList mDataList=new ArrayList();;
	ArrayList<Map<String, String>> mWords=new ArrayList<>();
	Fragment f;
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		ViewGroup rootView=(ViewGroup) inflater.inflate(R.layout.layout_questions_list, container,false);
		mListView=(DragNDropListView)rootView.findViewById(R.id.list);
		mAdd=(ImageButton)rootView.findViewById(R.id.add);
		mRemove=(ImageButton)rootView.findViewById(R.id.remove);
		moveDown=(ImageButton)rootView.findViewById(R.id.down);
		moveUp=(ImageButton)rootView.findViewById(R.id.up);
		Template template= ((MyApplication)getActivity().getApplication()).getmModel().getmTemplate();
		if(((MyApplication)getActivity().getApplication()).getData().size()==0)
		{
			TextView noquestions=(TextView)rootView.findViewById(R.id.textView1);
			noquestions.setVisibility(View.VISIBLE);
			
		}
		//try{
			
				
				if(template==Template.FLASHCARD)
				{
					f= new FlashcardQuestionTemplate();
					mDataList=new ArrayList<FlashCardDataTemplate>();
					mDataList=((MyApplication)getActivity().getApplication()).getData();
					
					if(mDataList!=null&mDataList.size()!=0)
					{
						for(int i=0;i<mDataList.size();i++)
						{
							try{
							String title=( (FlashCardDataTemplate) mDataList.get(i)).getQuestion();
							Log.e("title", ""+title);
							mWords.add(createPlanet("text", title));
							}catch(Exception e)
							{
								
							}
						}
					}
				}
				else if(template==Template.LEARNING)
				{
					f=new LearningQuestionTemplate();
					mDataList=new ArrayList<LearningDataTemplate>();
					mDataList=((MyApplication)getActivity().getApplication()).getData();
					
					if(mDataList!=null&mDataList.size()!=0)
					{
						for(int i=0;i<mDataList.size();i++)
						{
							
							String title=((LearningDataTemplate) mDataList.get(i)).getmTitle();
							Log.e("title", ""+title);
							mWords.add(createPlanet("text", title));
						}
					}
				}
				else if(template==Template.QUIZ)
				{
					f=new QuizQuestionTemplate();
					mDataList=new ArrayList<QuizDataTemplate>();
					mDataList=((MyApplication)getActivity().getApplication()).getData();
				
					if(mDataList!=null&mDataList.size()!=0)
					{
						for(int i=0;i<mDataList.size();i++)
						{
							
							String title=((QuizDataTemplate) mDataList.get(i)).getQuestion();
							Log.e("title", ""+title);
							mWords.add(createPlanet("text", title));
						}
					}
				}
				else if(template==Template.SPELLLING)
				{
					f=new SpellingQuestionTemplate();
					mDataList=new ArrayList<SpellingsDataTemplate>();
					mDataList=((MyApplication)getActivity().getApplication()).getData();
					
					if(mDataList!=null&mDataList.size()!=0)
					{
						for(int i=0;i<mDataList.size();i++)
						{
							
							String title=((SpellingsDataTemplate) mDataList.get(i)).getmWord();
							Log.e("title", ""+title);
							mWords.add(createPlanet("text", title));
						}
					}
				}
		/*}catch(Exception e)
		{
			Log.e("case exception", ""+e);
		}*/
		/*EfficientAdapter adapter =new EfficientAdapter(getActivity(),mWords);
		mListView.setAdapter(adapter);
		adapter.notifyDataSetChanged();*/
				DragNDropSimpleAdapter adapter = new DragNDropSimpleAdapter(getActivity(),
                       mWords,
                       R.layout.row,
                       new String[]{"text"},
                        new int[]{R.id.text},
                        R.id.handler);

				mListView.setDragNDropAdapter(adapter);
				mListView.setOnItemDragNDropListener(new OnItemDragNDropListener() {
					
					@Override
					public void onItemDrop(DragNDropListView parent, View view,
							int startPosition, int endPosition, long id) {
						// TODO Auto-generated method stub
						Log.e("dragged from"+startPosition, ""+endPosition);
						
					}
					
					@Override
					public void onItemDrag(DragNDropListView parent, View view, int position,
							long id) {
						// TODO Auto-generated method stub
						
					}
				});
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
		mRemove.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			try{
				mDataList.remove(EfficientAdapter.mPosition);
				
			}catch(Exception e)
			{
				Log.e("delete exception", ""+e+EfficientAdapter.mPosition);
			}
			}
		});
		moveUp.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
		return rootView;
	}
	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
	}
	private HashMap<String, String> createPlanet(String key, String name) {
	
		    HashMap<String, String> planet = new HashMap<String, String>();
		    planet.put(key, name);
		    return planet;
		}

}
