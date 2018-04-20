package com.example.android.geoquiz;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.geoquiz.R;

//  This app displays quiz questions and results.

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    int baseScore;
    int rightAnswerCount;
    int wrongAnswerCount;

//  Calculates score and right/wrong answer count

    void calculateScore(boolean rightAnswer) {
        if (rightAnswer) {
            baseScore = baseScore + 2;
            rightAnswerCount++;
        } else {
            baseScore = baseScore - 1;
            wrongAnswerCount++;
        }
    }

//  This method is called when the submit answer button is clicked.

    public void submitAnswer(View view) {
        baseScore = checkAnswer2();
        checkAnswer(R.id.question1);
        checkAnswer(R.id.question2);
        checkAnswer(R.id.question3);
        checkAnswer3(R.id.checkbox1);
        checkAnswer3(R.id.checkbox3);

        EditText nameField = (EditText) findViewById(R.id.input_name);
        String inputName = nameField.getEditableText().toString();

        String resultsMessage = createAnswerSummary(inputName);

//  Displays the game result on the screen
        displayMessage(resultsMessage);
        baseScore = 0;
        rightAnswerCount = 0;
        wrongAnswerCount = 0;
    }

    private void checkAnswer(int rightRadiobuttnId) {
        RadioButton rightOne = (RadioButton) findViewById(rightRadiobuttnId);
        boolean rightAnswer = rightOne.isChecked();
        calculateScore(rightAnswer);
    }

    private void checkAnswer3(int rightCheckboxId) {
        CheckBox rightOne = (CheckBox) findViewById(rightCheckboxId);
        boolean rightAnswer = rightOne.isChecked();
        calculateScore(rightAnswer);
    }

    //    DOES NOT WORK!!
    public int checkAnswer2() {
        EditText answerField = (EditText) findViewById(R.id.textAnswer);
        String inputAnswer = answerField.getEditableText().toString();
        if (inputAnswer.equalsIgnoreCase("latvia")) {
            return baseScore + 2;
        } else {
            return baseScore - 1;
        }
    }
//    Displays the game result on the screen

    private void displayMessage(String message) {
        TextView answerSummaryTextView = (TextView) findViewById(R.id.results_text);
//        answerSummaryTextView.setText(message); // Leaving this line intentionally, so I could get back to it fast
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

//     Creates answer summary.

    private String createAnswerSummary(String inputName) {
        String resultsMessage = "Name: " + inputName;
        resultsMessage += "\nRight answers: " + rightAnswerCount + "\nWrong answers: " + wrongAnswerCount;
        resultsMessage += "\nTotal score: " + baseScore;
        resultsMessage += "\n ";
        resultsMessage += "\nLast country is Croatia by the way :)";
        resultsMessage += "\n ";
        resultsMessage += "\n" + getString(R.string.thank_you);
        return resultsMessage;
    }
}
