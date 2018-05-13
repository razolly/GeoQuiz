package com.bignerdranch.android.geoquiz2;

/**
 * Created by Razli on 21-Sep-17.
 */

public class Question {

    private int mTextResId;
    private boolean mAnswer;

    public Question(int textResId, boolean answer){
        mTextResId = textResId;
        mAnswer = answer;
    }

    public int getTextResId() {
        return mTextResId;
    }

    public void setTextResId(int textResId) {
        mTextResId = textResId;
    }

    public boolean isAnswer() {
        return mAnswer;
    }

    public void setAnswer(boolean answer) {
        mAnswer = answer;
    }
}
