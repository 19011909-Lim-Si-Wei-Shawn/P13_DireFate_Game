package sg.edu.rp.c346.id19011909.direfate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class StoryLineActivity extends AppCompatActivity {

    //Creating Variable,
    private LinearLayout StoryLine;

    TextView StoryText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_line);

        //Linking Variable,
        StoryLine = findViewById(R.id.StoryLine1);
        StoryText = findViewById(R.id.StoryLines);

        //Inputting Correct Text into StoryText,
        final int[] Counter = {0};

        //Setting Clickable Action {StoryLine},
        StoryLine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Counter[0] == 0) {
                    StoryText.setText(R.string.StoryLine2);
                    Counter[0]++;
                }

                else if (Counter[0] == 1) {
                    StoryText.setText(R.string.StoryLine3);
                    Counter[0]++;
                }

                else if (Counter[0] == 2) {
                    Intent intent = new Intent(StoryLineActivity.this, DisplayActivity.class);
                    startActivity(intent);
                }

            }
        });

    }
}