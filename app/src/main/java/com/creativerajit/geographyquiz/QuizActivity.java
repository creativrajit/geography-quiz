package com.creativerajit.geographyquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private int mCurrentIndex = 0;

    // Create an array of question objects
    private Question[] mQuestionBank = new Question[]{
            new Question(R.string.quest_ocean, true),
            new Question(R.string.quest_asia, false),
            new Question(R.string.quest_africa, true),
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        // Update the Question to show in TextView
        updateQuestion(mCurrentIndex);

        // reference to true and false button
        mTrueButton = (Button) findViewById(R.id.true_button);
        mFalseButton = (Button) findViewById(R.id.false_button);

        // Waiting for True Button to be clicked
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showResult(true, mCurrentIndex);
            }
        });

        // Waiting for false button to be clicked
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showResult(false, mCurrentIndex);
            }
        });

        // Next button is used to change mQuestionIndex
        mNextButton = (Button) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                updateQuestion(mCurrentIndex);
            }
        });
    }

    // Show results
    private void showResult(boolean userPassedTrue,int index){
        boolean answerTrueOrFalse = mQuestionBank[index].isAnswerTrue();

        int messageResId = 0;

        if (userPassedTrue == answerTrueOrFalse){
            messageResId = R.string.toast_correct;
        } else{
            messageResId = R.string.toast_incorrect;
        }

        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
    }

    // updateQuestion is used to show question in main text using Current Index
    private void updateQuestion(int index){
        // Get reference to TextView
        final TextView mQuestionTextView = (TextView) findViewById(R.id.question_text_view);

        mQuestionTextView.setText(mQuestionBank[index].getTextResId());
    }

}
