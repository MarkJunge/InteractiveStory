package com.example.ideap.interactivestory.ui;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ideap.interactivestory.R;
import com.example.ideap.interactivestory.model.Page;
import com.example.ideap.interactivestory.model.Stories;

public class StoryActivity extends AppCompatActivity {

    public static final String Tag = StoryActivity.class.getSimpleName();

    private Stories mStory = new Stories();
    private ImageView mImageView;
    private TextView mTextView;
    private Button mChoice1;
    private Button mChoice2;
    private String mName;
    private Page mCurrentPage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story1);

            //This activity is started with the intent
        Intent intent = getIntent();
            //This create an intent used to start this activity
        mName = intent.getStringExtra(getString(R.string.key_name));//getString(R.string.key_name) is a resource from string.xml
            //This get the key name from the passed intent that is made in the main activity

        if(mName==null){
            mName = "Friend";
        }
            //This if statement secure that no mater if we have typed a wrong key in the mName variable,
            //  getStringExtra("Name") (Name has to be name), then the name value will allways have a value (Friend as default)

        Log.d(Tag, mName);
            //This log show us the name that will be played in the activity, no mater if it is default or typed name

        mImageView = (ImageView) findViewById(R.id.storyImageView);
        mTextView = (TextView) findViewById(R.id.storyTextView);
        mChoice1 = (Button) findViewById(R.id.choiceButton1);
        mChoice2 = (Button) findViewById(R.id.choiceButton2);

        loadPage(0);
    }

    private void loadPage(int choice){
        mCurrentPage = mStory.getPage(choice);

        Drawable drawable = getResources().getDrawable(mCurrentPage.getImageId());
        mImageView.setImageDrawable(drawable);

        String pageText = mCurrentPage.getText();
        //Add the name if placeholder included. Wont add if no placeholder
        pageText = String.format(pageText, mName);
        mTextView.setText(pageText);

        if(mCurrentPage.isFinal()){
            mChoice1.setVisibility(View.INVISIBLE);
            mChoice2.setText("Play AGAIN");
            mChoice2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }

        else {
            mChoice1.setText(mCurrentPage.getChoice1().getText());
            mChoice2.setText(mCurrentPage.getChoice2().getText());

            mChoice1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int nextPage = mCurrentPage.getChoice1().getNextPage();
                    loadPage(nextPage);
                }
            });

            mChoice2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int nextPage = mCurrentPage.getChoice2().getNextPage();
                    loadPage(nextPage);
                }
            });
        }
    }
}
