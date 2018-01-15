package com.example.kevin.buttongame;

import android.content.Context;
import android.graphics.Color;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    // Declaring method GetRandomABC
    public static String GetRandomABC() {
        Random random = new Random();
        int index = random.nextInt("ABC".length());
        char Letter = "ABC".charAt(index);
        return String.valueOf(Letter);
    }

    // Declaring method ButtonPress
    public void ButtonPress(
            String strLetter,
            long longTimestampStart,
            TextView textView_Letter,
            TextView textView_Score,
            Button btn_A,
            Button btn_B,
            Button btn_C
    ) {
        Vibrator vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        vib.vibrate(100);
        String strCurrentTextViewLetter = textView_Letter.getText().toString();
        int intCurrentScore = Integer.parseInt(textView_Score.getText().toString());

        // if correct button is pressed
        if(strCurrentTextViewLetter.equals(strLetter)) {
            textView_Letter.setText(GetRandomABC());
            int NewScore = intCurrentScore + 1;
            textView_Score.setText(String.valueOf(NewScore));
        }
        else {
            long longTimestampStop = System.currentTimeMillis();
            long longTotalMs = longTimestampStop - longTimestampStart;
            int intTotalMs = Integer.parseInt(String.valueOf(longTotalMs));
            int intMsPerButtonPress = intTotalMs/intCurrentScore;

            textView_Letter.setTextColor(Color.RED);
            textView_Letter.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
            textView_Letter.setText("You finished with an average reaction time of " + String.valueOf(intMsPerButtonPress) + "ms");
            btn_A.setClickable(false);
            btn_B.setClickable(false);
            btn_C.setClickable(false);
        }
    }

    // Loading the activity, in practice starting the app.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Declaring UI elements
        final TextView textView_Letter = (TextView)findViewById(R.id.textView_Letter);
        final TextView textView_Score  = (TextView)findViewById(R.id.textView_score);
        final Button btn_A             = (Button)findViewById(R.id.btn_A);
        final Button btn_B             = (Button)findViewById(R.id.btn_B);
        final Button btn_C             = (Button)findViewById(R.id.btn_C);

        // Setting starting text
        textView_Letter.setText(GetRandomABC());
        textView_Score.setText("0");

        // Getting start time
        final Long longTimestampStart = System.currentTimeMillis();

        // Starting to listen for button presses
        btn_A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ButtonPress("A",longTimestampStart,textView_Letter,textView_Score,btn_A,btn_B,btn_C);
            }
        });
        btn_B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ButtonPress("B",longTimestampStart,textView_Letter,textView_Score,btn_A,btn_B,btn_C);
            }
        });
        btn_C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ButtonPress("C",longTimestampStart,textView_Letter,textView_Score,btn_A,btn_B,btn_C);
            }
        });

    }
}