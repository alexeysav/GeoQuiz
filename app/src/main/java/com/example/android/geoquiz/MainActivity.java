package com.example.android.geoquiz;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.geoquiz.R;

/**
 * This app displays quiz questions and results.
 */
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    int baseScore = 0;
    int rightAnswerCount = 0;
    int wrongAnswerCount = 0;

//     *@return total attempt score
//     */
    void calculateScore(boolean rightAnswer) {
            if (rightAnswer) {
                baseScore = baseScore + 2;
                rightAnswerCount++;
            } else {
                baseScore = baseScore - 1;
                wrongAnswerCount++;
            }
    }
    /**
     * This method is called when the submit answer button is clicked.
     */
    public void submitAnswer(View view) {
        checkAnswer(R.id.left_checkbox);

        EditText nameField = (EditText) findViewById(R.id.input_name);
        String inputName= nameField.getEditableText().toString();

        String resultsMessage = createAnswerSummary(inputName);

        // *Displays the game result on the screen
//     */
        displayMessage(resultsMessage);
    }

    private void checkAnswer(int rightCheckboxId) {
        CheckBox rightOne = (CheckBox) findViewById(rightCheckboxId);
        boolean rightAnswer = rightOne.isChecked();
        calculateScore(rightAnswer);
    }

    private void displayMessage(String message) {
        TextView answerSummaryTextView = (TextView) findViewById(R.id.results_text);
        answerSummaryTextView.setText(message);
    }
        //private (STARTING FORM HERE THERE WAS A CODE THAT "Calculates the score")

    /**
     * Creates answer summary.
     */
    private String createAnswerSummary (String inputName) {
        String resultsMessage = "Name: " + inputName;
        resultsMessage += "\nRight answers " + rightAnswerCount + "\nWrong answers " + wrongAnswerCount;
        resultsMessage += "\nTotal score: " + baseScore;
        resultsMessage += "\n" + getString(R.string.thank_you);
        return resultsMessage;
    }
}
