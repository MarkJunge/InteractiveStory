package com.example.ideap.interactivestory.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.ideap.interactivestory.R;

public class MainActivity extends AppCompatActivity {

    private EditText mNameField;
    private Button mStartButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNameField = (EditText) findViewById(R.id.nameEditText);
        mStartButton = (Button) findViewById(R.id.startButton);

        mStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = mNameField.getText().toString();
                startStory(name);
                //The users name is created and stored in this method (the start button)


            }
        });
    }
    private void startStory(String name){
        Intent intent = new Intent(this, StoryActivity.class);
        //The intent is made to open the next activity (StoryActivity)
        intent.putExtra(getString(R.string.key_name), name);
        //The putExtra attach the date (name) to the intent, so the name can be used in the StoryActivity
        startActivity(intent);
        //starting the activity (in this case the StoryActivity.class)
    }
    @Override
    protected void onResume() {
        super.onResume();
        mNameField.setText("");
    }

}
