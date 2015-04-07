package com.buildmlearn.simulator;

import java.util.ArrayList;
import java.util.Locale;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.graphics.Color;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.buildmlearn.application.MyApplication;
import com.buildmlearn.template.spellings.SpellingsDataTemplate;
import com.example.buildmlearntoolkit.R;

public class SimulatorSpellingFragment extends Fragment implements
TextToSpeech.OnInitListener {
	private TextToSpeech textToSpeech;
	public static ArrayList mDataList=new ArrayList();
	private int count=0;
	private AlertDialog mAlert;
	private TextView mTv_WordNumber;
	private Button mBtn_Spell, mBtn_Skip,mBtn_Speak;
	private EditText mEt_Spelling;
	private SeekBar mSb_SpeechRate;
	int correct=0,incorrect=0;

	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		ViewGroup rootView=(ViewGroup)inflater.inflate(R.layout.layout_simulator_spelling,container,false);
		mBtn_Spell = (Button)rootView.findViewById(R.id.btn_ready);
		mBtn_Speak=(Button)rootView.findViewById(R.id.btn_speak);
		mBtn_Skip = (Button)rootView.findViewById(R.id.btn_skip);
		mSb_SpeechRate = (SeekBar)rootView.findViewById(R.id.sb_speech);

		mTv_WordNumber = (TextView)rootView.findViewById(R.id.tv_word_number);
		return rootView;
	}
	
	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		mDataList=new ArrayList<SpellingsDataTemplate>();
		mDataList=((MyApplication)getActivity().getApplication()).getData();
		
		
		textToSpeech = new TextToSpeech(getActivity(), this);
		
		mTv_WordNumber.setText("Word #" + (count + 1) + " of "
				+ mDataList.size());
		mBtn_Spell.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				LayoutInflater factory = LayoutInflater.from(getActivity());
				final View textEntryView = factory.inflate(
						R.layout.dialog_spellinginput, null);
				Builder builder = new Builder(getActivity());
				mAlert = builder.create();
				mAlert.setCancelable(true);
				mAlert.setView(textEntryView, 10, 10, 10, 10);
				if (mAlert != null && !mAlert.isShowing()) {
					mAlert.show();
				}
				mEt_Spelling = (EditText) mAlert.findViewById(R.id.et_spelling);
				Button mBtn_Submit = (Button) mAlert.findViewById(R.id.btn_submit);
				mBtn_Submit.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						submit();
					}
				});
			}
		});
		mBtn_Skip.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (count < mDataList.size() - 1) {

					count++;
					mTv_WordNumber.setText("Word #" + (count + 1) + " of "
							+ mDataList.size());
					mBtn_Spell.setEnabled(false);
					mBtn_Skip.setEnabled(false);
					mBtn_Skip.setTextColor(Color.WHITE);
					mBtn_Spell.setTextColor(Color.WHITE);
				} else {
					ScoreCardFragment f= new ScoreCardFragment();
					Bundle b= new Bundle();
					b.putString("result", "Total Question: "+mDataList.size()+"\nCorrect Answers:"+correct+"\nIncorrect Answers:"+incorrect+"\nUnanswered Questions:"+ (mDataList.size()-correct-incorrect));
					f.setArguments(b);
					((SimulationActivity)getActivity()).switchFragment(f);

				}
			}
		});
		mBtn_Speak.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				convertTextToSpeech(((SpellingsDataTemplate)mDataList.get(count)).getmWord());
				mBtn_Spell.setEnabled(true);
				mBtn_Skip.setEnabled(true);
				mBtn_Skip.setTextColor(Color.RED);
				mBtn_Spell.setTextColor(Color.GREEN);
			}
		});
	}
	public void click(View view) {
		switch (view.getId()) {
		case R.id.btn_skip:
			if (count < mDataList.size() - 1) {

				count++;
				mTv_WordNumber.setText("Word #" + (count + 2) + " of "
						+ mDataList.size());
				mBtn_Spell.setEnabled(false);
				mBtn_Skip.setEnabled(false);
				mBtn_Skip.setTextColor(Color.WHITE);
				mBtn_Spell.setTextColor(Color.WHITE);
			} else {
				ScoreCardFragment f= new ScoreCardFragment();
				Bundle b= new Bundle();
				b.putString("result", "Total Question: "+mDataList.size()+"\nCorrect Answers:"+correct+"\nIncorrect Answers:"+incorrect+"\nUnanswered Questions:"+ (mDataList.size()-correct-incorrect));
				f.setArguments(b);
				((SimulationActivity)getActivity()).switchFragment(f);

			}
			break;

		case R.id.btn_speak:
			convertTextToSpeech(((SpellingsDataTemplate)mDataList.get(count)).getmWord());
			mBtn_Spell.setEnabled(true);
			mBtn_Skip.setEnabled(true);
			mBtn_Skip.setTextColor(Color.RED);
			mBtn_Spell.setTextColor(Color.GREEN);
			// mBtn_Spell.setBackgroundColor(Color.GREEN);
			// mBtn_Skip.setBackgroundColor(Color.RED);
			break;
		case R.id.btn_ready:

			LayoutInflater factory = LayoutInflater.from(getActivity());
			final View textEntryView = factory.inflate(
					R.layout.dialog_spellinginput, null);
			Builder builder = new Builder(MyApplication.mApplication);
			mAlert = builder.create();
			mAlert.setCancelable(true);
			mAlert.setView(textEntryView, 10, 10, 10, 10);
			if (mAlert != null && !mAlert.isShowing()) {
				mAlert.show();
			}
			mEt_Spelling = (EditText) mAlert.findViewById(R.id.et_spelling);
			Button mBtn_Submit = (Button) mAlert.findViewById(R.id.btn_submit);
			mBtn_Submit.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					submit();
				}
			});

			break;
		}
		}
		private void convertTextToSpeech(String text) {

			float speechRate = getProgressValue(mSb_SpeechRate.getProgress());
			textToSpeech.setSpeechRate(speechRate);
			textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null);

		}

		@Override
		public void onInit(int status) {
			if (status == TextToSpeech.SUCCESS) {
				int result = textToSpeech.setLanguage(Locale.US);
				if (result == TextToSpeech.LANG_MISSING_DATA
						|| result == TextToSpeech.LANG_NOT_SUPPORTED) {
					Log.e("error", "This Language is not supported");
				}
			} else {
				Log.e("error", "Initilization Failed!");
			}
		}

		public void submit() {
			String input = mEt_Spelling.getText().toString().trim();
			if (input == null || input.length() == 0) {
				Toast.makeText(getActivity(), "Please enter the spelling",
						Toast.LENGTH_SHORT).show();

			} else {
				mAlert.dismiss();
				boolean isCorrect = false;
				if (mEt_Spelling.getText().toString()
						.equalsIgnoreCase(((SpellingsDataTemplate)mDataList.get(count)).getmWord())) {
					isCorrect = true;
				correct++;
				} else {
					incorrect++;
				}
				
			}
		}

		private float getProgressValue(int percent) {
			float temp = ((float) percent / 100);
			float per = temp * 2;
			return per;
		}


	

}
