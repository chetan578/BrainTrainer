package com.example.chetan578.braintrainer;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    int score = 0;
    int chances = 0;
    int rightAnswer, number1, number2, incorrectAnswer;
    Button playAgainButton;
    TextView option1, option2, option3, option4, timerText, scoreDisplay, answerCheck, question, startText;
    Random rand = new Random();
    CountDownTimer CountDownTimer;
    int locationOfRightAnswer;
    boolean gameIsActive = true;
    RelativeLayout gameLayout;



    public void startGame(View view) {
        startText.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(RelativeLayout.VISIBLE);
        playAgain(findViewById(R.id.playAgainButton));
    }


    @SuppressLint("SetTextI18n")
    public void resetTimer() {
        if (gameIsActive) {

            List answers = new ArrayList<>();
            number1 = rand.nextInt(50);
            number2 = rand.nextInt(50);
            question.setText(Integer.toString(number1) + "+ " + Integer.toString(number2));
            rightAnswer = number1 + number2;
            locationOfRightAnswer = rand.nextInt(4);

            for (int i = 0; i < 4; i++) {

                if (locationOfRightAnswer == i) {

                    answers.add(number1 + number2);
                } else {
                    incorrectAnswer = rand.nextInt(41);

                    while (incorrectAnswer == rightAnswer) {
                        incorrectAnswer = rand.nextInt(41);
                    }
                    answers.add(incorrectAnswer);
                }
            }
            option1.setText(Integer.toString((int) answers.get(0)));
            option2.setText(Integer.toString((int) answers.get(1)));
            option3.setText(Integer.toString((int) answers.get(2)));
            option4.setText(Integer.toString((int) answers.get(3)));
        }


    }

    public void chooseAnswer(View view) {
        if (gameIsActive) {

            if (view.getTag().toString().equals(Integer.toString(locationOfRightAnswer))) {
                answerCheck.setText("CORRECT");
                score++;
            } else {
                answerCheck.setText("WRONG");


            }
            chances++;
            scoreDisplay.setText(Integer.toString(score) + "/" + Integer.toString(chances));
            resetTimer();

        }
    }

    public void playAgain(View view) {
        gameIsActive = true;
        chances = 0;
        score = 0;
        scoreDisplay.setText("0/0");
        playAgainButton.setVisibility(View.INVISIBLE);
        answerCheck.setText("");
        resetTimer();
        CountDownTimer = new CountDownTimer(20100, 1000) {

            @Override
            public void onTick(long l) {
                timerText.setText(String.valueOf(l / 1000) + "s");
            }

            @Override
            public void onFinish() {
                gameIsActive = false;
                answerCheck.setText("Your final score is :" + Integer.toString(score) + "/" + Integer.toString(chances));
                // timerText.setText("0s");
                playAgainButton.setVisibility(View.VISIBLE);
                MediaPlayer mplayer = MediaPlayer.create(getApplicationContext(), R.raw.sound);
                mplayer.start();
                if ((score/chances)>0.9)
                {
                    Toast.makeText(MainActivity.this, "well played ,that's a good score", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this, "keep trying ,that's not a great score", Toast.LENGTH_SHORT).show();
                }

                }
        }.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playAgainButton = findViewById(R.id.playAgainButton);
        timerText = findViewById(R.id.timerText);
        answerCheck = findViewById(R.id.answerCheck);
        scoreDisplay = findViewById(R.id.scoreDisplay);
        question = findViewById(R.id.question);
        startText = findViewById(R.id.startText);
        gameLayout = findViewById(R.id.gameLayout);
        number1 = rand.nextInt(50);
        number2 = rand.nextInt(50);
        question.setText(Integer.toString(number1) + "+ " + Integer.toString(number2));
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);
    }

    }

