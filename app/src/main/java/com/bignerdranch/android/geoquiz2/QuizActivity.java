package com.bignerdranch.android.geoquiz2;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    private Button mTrueButton;
    private Button mFalseButton;
    private Button mCheatButton;
    private ImageButton mNextButton;
    private ImageButton mPrevButton;
    private TextView mQuestionTextView;
    private static final String TAG = "QuizActivity";
    private static final String KEY_INDEX = "index";
    private static final int REQUEST_CODE_CHEAT = 0;

    private Question[] mQuestionList = new Question[]{
        new Question(R.string.question_rocky, true),
        new Question(R.string.question_mindy, false),
        new Question(R.string.question_africa, false),
        new Question(R.string.question_americas, true),
        new Question(R.string.question_asia, true),
    };

    private int mCurrentQnIndex = 0;
    private boolean mIsCheater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "OnCreate() called");
        setContentView(R.layout.activity_quiz);

        mQuestionTextView = (TextView)findViewById(R.id.question_text_view);
        mQuestionTextView.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                mCurrentQnIndex = (mCurrentQnIndex + 1) % mQuestionList.length;
                updateQuestion();
            }
        });

        if(savedInstanceState != null){
            mCurrentQnIndex = savedInstanceState.getInt(KEY_INDEX, 0);
        }

        updateQuestion();

        mTrueButton = (Button)findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                checkAnswer(true);
            }
        });
        mFalseButton = (Button)findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                checkAnswer(false);
            }
        });
        mNextButton = (ImageButton)findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                mCurrentQnIndex = (mCurrentQnIndex + 1) % mQuestionList.length;
                mIsCheater = false;
                updateQuestion();
            }
        });

        mPrevButton = (ImageButton)findViewById(R.id.previous_button);
        mPrevButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                mCurrentQnIndex = (mCurrentQnIndex - 1) % mQuestionList.length;
                if(mCurrentQnIndex < 0){mCurrentQnIndex = mQuestionList.length - 1;}
                updateQuestion();
            }
        });

        mCheatButton = (Button)findViewById(R.id.cheat_button);
        mCheatButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                boolean whatIsAnswer = mQuestionList[mCurrentQnIndex].isAnswer();
                Intent i = CheatActivity.newIntent(QuizActivity.this, whatIsAnswer);
                startActivityForResult(i, REQUEST_CODE_CHEAT);
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSaveInstanceState");
        savedInstanceState.putInt(KEY_INDEX, mCurrentQnIndex);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode != Activity.RESULT_OK) return;

        if(requestCode == REQUEST_CODE_CHEAT){
            if(data == null) return;
            mIsCheater = CheatActivity.wasAnswerShown(data);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");
    }
    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }
    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }
    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }

    private void updateQuestion(){
        int question = mQuestionList[mCurrentQnIndex].getTextResId();
        mQuestionTextView.setText(question);
    }

    private void checkAnswer(boolean userChoice){
        boolean isAnswerCorrect = mQuestionList[mCurrentQnIndex].isAnswer();

        int messageResId = 0;

        if(mIsCheater){
            messageResId = R.string.judgment_toast;
        } else {
            if (userChoice == isAnswerCorrect)
                messageResId = R.string.correct_toast;
            else
                messageResId = R.string.wrong_toast;
        }

        Toast.makeText(QuizActivity.this, messageResId, Toast.LENGTH_SHORT).show();
    }
}
