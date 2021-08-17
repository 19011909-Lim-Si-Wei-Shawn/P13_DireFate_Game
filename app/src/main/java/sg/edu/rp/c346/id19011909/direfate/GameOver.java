package sg.edu.rp.c346.id19011909.direfate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GameOver extends AppCompatActivity {

    //Creating Variable,
    Button BtnRedirect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        //Linking Variable,
        BtnRedirect = findViewById(R.id.btnGameOver);

        //Enabling DBHelper,
        DBHelper dbh = new DBHelper(GameOver.this);

        //Setting Button Action {BtnRedirect},
        BtnRedirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameOver.this, MainActivity.class);
                dbh.deleteDatabase();
                startActivity(intent);
            }
        });

    }
}