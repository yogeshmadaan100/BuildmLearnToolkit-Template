package com.buildmlearn.simulator;

import java.util.ArrayList;

import com.buildmlearn.application.MyApplication;
import com.buildmlearn.template.flashcard.FlashCardDataTemplate;
import com.buildmlearn.template.quiz.QuizDataTemplate;
import com.example.buildmlearntoolkit.R;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Animation.AnimationListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class SimulatorFlashcardFragment extends Fragment implements
AnimationListener {
	View answerView, questionView;
	Button flipButton;
	Button preButton;
	Button nextButton;
	boolean isFlipped = false;
	int iQuestionIndex = 0;
	String flashCardanswer;
	ImageView questionImage;
	TextView flashCardText, flashcardNumber;
	TextView questionText,answerText;
	private Animation animation1;
	private Animation animation2;
	private View currentView;
	FrameLayout fl;
	public static ArrayList mDataList=new ArrayList();
	
	int correct=0,incorrect=0;
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		ViewGroup rootView=(ViewGroup)inflater.inflate(R.layout.layout_simulator_flashcard, container,false);
		animation1 = AnimationUtils.loadAnimation(getActivity(), R.anim.to_middle);
		animation1.setAnimationListener(this);
		animation2 = AnimationUtils.loadAnimation(getActivity(), R.anim.from_middle);
		animation2.setAnimationListener(this);

		questionView = (View)rootView.findViewById(R.id.questionInMain);
		answerView = (View)rootView.findViewById(R.id.answerInMain);

		questionView.setVisibility(View.VISIBLE);
		answerView.setVisibility(View.GONE);
		iQuestionIndex = 0;

		questionImage = (ImageView)rootView.findViewById(R.id.questionImage);
		flashCardText = (TextView)rootView.findViewById(R.id.flashCardText);
		questionText = (TextView)rootView.findViewById(R.id.questionhint);
		flashcardNumber = (TextView)rootView.findViewById(R.id.flashCardNumber);
		answerText = (TextView)rootView.findViewById(R.id.answerText);
		flipButton = (Button)rootView.findViewById(R.id.flip_button);
		preButton = (Button)rootView.findViewById(R.id.pre_button);
		nextButton = (Button) rootView.findViewById(R.id.next_button);
		fl=(FrameLayout)rootView.findViewById(R.id.fl);
		return rootView;
	}
	
	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		mDataList=new ArrayList<FlashCardDataTemplate>();
		mDataList=((MyApplication)getActivity().getApplication()).getData();
		
		populateQuestion(iQuestionIndex);
		currentView = questionView;
		fl.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				currentView.clearAnimation();
				currentView.setAnimation(animation1);
				currentView.startAnimation(animation1);
				Log.e("view", "touch");
				return false;
			}
		});
		
		fl.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				currentView.clearAnimation();
				currentView.setAnimation(animation1);
				currentView.startAnimation(animation1);

			}
		});
		
		
		flipButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				currentView.clearAnimation();
				currentView.setAnimation(animation1);
				currentView.startAnimation(animation1);

			}
		});

		preButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (iQuestionIndex != 0) {
					isFlipped = false;

					iQuestionIndex--;
					questionView.setVisibility(View.VISIBLE);
					answerView.setVisibility(View.GONE);
					currentView = questionView;

					populateQuestion(iQuestionIndex);
				}

			}
		});

		nextButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if (iQuestionIndex <mDataList.size() - 1) {
					isFlipped = false;
					iQuestionIndex++;
					questionView.setVisibility(View.VISIBLE);
					answerView.setVisibility(View.GONE);
					currentView = questionView;
					populateQuestion(iQuestionIndex);

				} else {

					ScoreCardFragment f= new ScoreCardFragment();
					Bundle b= new Bundle();
					b.putString("result", "Total Question: "+mDataList.size()+"\nCorrect Answers:"+correct+"\nIncorrect Answers:"+incorrect+"\nUnanswered Questions:"+ (mDataList.size()-correct-incorrect));
					f.setArguments(b);
					((SimulationActivity)getActivity()).switchFragment(f);
					reInitialize();
					
				}

			}
		});
	}
	public void populateQuestion(int index) {
		if (index == 0) {
			preButton.setEnabled(false);

		} else
			preButton.setEnabled(true);

		int cardNum = index + 1;
		flashcardNumber.setText("Card #" + cardNum + " of " + mDataList.size());
		FlashCardDataTemplate mFlash = (FlashCardDataTemplate) mDataList.get(index);
		
		if (mFlash.getQuestion() != null)
			questionText.setText(mFlash.getQuestion());
		if (mFlash.getAnswer() != null) {
			flashCardanswer = mFlash.getAnswer();
			answerText.setText(mFlash.getAnswer());
		}
		/*if (mFlash.getBase64() != null) {
			byte[] decodedString = Base64.decode(mFlash.getBase64(),
					Base64.DEFAULT);
			Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString,
					0, decodedString.length);
			questionImage.setImageBitmap(decodedByte);
		}else
		{
			questionText.setGravity(Gravity.CENTER_VERTICAL);
		}*/

		/*
		 * String[] dataLine = gd.model.get(index).split("__"); if
		 * (dataLine[0].equals("IMAGE")) {
		 * questionImage.setVisibility(View.VISIBLE); Resources r =
		 * getResources(); int picId = r.getIdentifier("image" +
		 * String.valueOf(index), "drawable", "com.buildmlearn.flashcard");
		 * 
		 * questionImage.setImageResource(picId); } else {
		 * questionImage.setVisibility(View.GONE);
		 * 
		 * } String[] dataArray = dataLine[1].split("=="); // //
		 * answerText.setText(dataArray[1]); flashCardanswer = dataArray[1];
		 * 
		 * flashCardText.setText(dataArray[0]);
		 * 
		 * questionText.setVisibility(View.GONE); if (dataArray.length == 3) {
		 * questionText.setVisibility(View.VISIBLE);
		 * questionText.setText(dataArray[2]); }
		 */}

	public void reInitialize() {
		iQuestionIndex = 0;
		
	}

	@Override
	public void onAnimationEnd(Animation animation) {
		if (animation == animation1) {

			

			if (!isFlipped) {

				answerView.setVisibility(View.VISIBLE);
				questionView.setVisibility(View.GONE);
				isFlipped = true;
				answerText.setText(flashCardanswer);
				currentView = answerView;
			} else {

				isFlipped = false;
				answerText.setText("");
				questionView.setVisibility(View.VISIBLE);
				answerView.setVisibility(View.GONE);
				currentView = questionView;
			}
			currentView.clearAnimation();
			currentView.setAnimation(animation2);
			currentView.startAnimation(animation2);

		}

	}

	@Override
	public void onAnimationRepeat(Animation animation) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onAnimationStart(Animation animation) {
		// TODO Auto-generated method stub

	}
}
