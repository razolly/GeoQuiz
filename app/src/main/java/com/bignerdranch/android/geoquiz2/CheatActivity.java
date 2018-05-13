package com.bignerdranch.android.geoquiz2;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {

    private TextView mAnswerTextView;
    private Button mShowAnswer;
    private boolean mAnsToCurrentQn;
    private static final String EXTRA_WHAT_IS_ANSWER = "com.bignerdranch.android.geoquiz2.what_is_answer";
    private static final String EXTRA_ANSWER_SHOWN = "com.bignerdranch.android.geoquiz2.answer_shown";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        mAnsToCurrentQn = getIntent().getBooleanExtra(EXTRA_WHAT_IS_ANSWER, false);

        mAnswerTextView = (TextView)findViewById(R.id.answer_text_view);

        mShowAnswer = (Button)findViewById(R.id.show_answer_button);
        mShowAnswer.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(mAnsToCurrentQn)
                    mAnswerTextView.setText(R.string.true_button);
                else
                    mAnswerTextView.setText(R.string.false_button);

                setAnswerShownResult(true);
            }
        });
    }

    public void setAnswerShownResult(boolean isAnsShown){
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN, isAnsShown);
        setResult(RESULT_OK, data);
    }

    public static boolean wasAnswerShown(Intent result){
        return result.getBooleanExtra(EXTRA_ANSWER_SHOWN, false);
    }

    public static Intent newIntent(Context packageContext, boolean whatIsAnswer){
        Intent i = new Intent(packageContext, CheatActivity.class);
        i.putExtra(EXTRA_WHAT_IS_ANSWER, whatIsAnswer);
        return i;
    }
}
