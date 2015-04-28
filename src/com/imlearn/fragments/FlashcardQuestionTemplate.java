package com.imlearn.fragments;

import java.util.Collections;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.dd.processbutton.iml.ActionProcessButton;
import com.example.buildmlearntoolkit.R;
import com.example.imlearntoolkit.ContentActivity;
import com.iangclifton.android.floatlabel.FloatLabel;
import com.imlearn.application.MyApplication;
import com.imlearn.models.Mode;
import com.imlearn.template.flashcard.FlashCardDataTemplate;
import com.imlearn.utils.ProgressGenerator;

public class FlashcardQuestionTemplate extends Fragment  implements com.imlearn.utils.ProgressGenerator.OnCompleteListener {
	protected static final int RESULT_LOAD_IMG = 0;
	private ActionProcessButton mAdd;
	private FloatLabel mAnswer,mHint;
	private EditText mQuestion;
	private ImageView mImage;
	private ProgressGenerator progressGenerator=new ProgressGenerator(this);
	Mode mode=Mode.ADDITION;
	int position;
	private Uri mImageUri;
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		ViewGroup rootView=(ViewGroup) inflater.inflate(R.layout.layout_flashcard_questions, container,false);
		mAnswer=(FloatLabel)rootView.findViewById(R.id.answer);
		mHint=(FloatLabel)rootView.findViewById(R.id.hint);
		mQuestion=(EditText)rootView.findViewById(R.id.question);
		mImage=(ImageView)rootView.findViewById(R.id.imageView1);
		mImage.setImageResource(R.drawable.cat);
		mAdd=(ActionProcessButton)rootView.findViewById(R.id.btnNext);
		mAdd.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(mAnswer.getEditText().getText().toString().trim().length()!=0&&mHint.getEditText().getText().toString().trim().length()!=0&&mQuestion.getText().toString().trim().length()!=0)
				{
					if(mode==Mode.ADDITION)
					{
						MyApplication.mDataList.add(new FlashCardDataTemplate(mQuestion.getText().toString(),mAnswer.getEditText().getText().toString(),mImageUri.toString(), mHint.getEditText().getText().toString()));
					}
					else
					{
						MyApplication.mDataList.remove(position);
						MyApplication.mDataList.add(new FlashCardDataTemplate(mQuestion.getText().toString(),mAnswer.getEditText().getText().toString(),mImageUri.toString(), mHint.getEditText().getText().toString()));
						Collections.rotate(MyApplication.mDataList.subList(position, MyApplication.mDataList.size()), 1);
					}
					progressGenerator.start(mAdd);
					mAdd.setEnabled(false);
				}
				else
					Toast.makeText(getActivity(), "Enter all field", 2000).show();
			}
		});
		mImage.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent galleryIntent = new Intent(Intent.ACTION_PICK,
				        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				// Start the Intent
				startActivityForResult(galleryIntent, RESULT_LOAD_IMG);
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
				try{
					mImage.setImageURI(Uri.parse(((FlashCardDataTemplate)QuestionsListFragment.mDataList.get(Integer.parseInt((getArguments().getString("position"))))).getImageUrl()));
				}catch(Exception e)
				{
					Log.e("image exception", "");
					mImage.setImageResource(R.drawable.cat);
				}
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
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            // When an Image is picked
            if (requestCode == RESULT_LOAD_IMG
                    && null != data) {
                // Get the Image from data
 
                Uri selectedImage = data.getData();
                String[] filePathColumn = { MediaStore.Images.Media.DATA };
                mImageUri=selectedImage;
                mImage.setImageURI(mImageUri);
				
            } else {
                
            }
        } catch (Exception e) {
           
        }
 
    }

}
