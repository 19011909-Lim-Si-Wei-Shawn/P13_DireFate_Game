package sg.edu.rp.c346.id19011909.direfate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //Creating Variable,
    Button btnRedirect, btnContinue;

    ArrayList<Player> CheckerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Linking Variable,
        btnRedirect = findViewById(R.id.btnGoNext);
        btnContinue = findViewById(R.id.btnContinue);

        //Checking if Data Exist in Database,
        DBHelper dbh = new DBHelper(MainActivity.this);
        CheckerList = new ArrayList<Player>();
        CheckerList.addAll(dbh.getAllDetails());

        Log.d("CheckerList", String.valueOf(CheckerList.size()));

        if(CheckerList.size() == 1)
        {
            btnRedirect.setEnabled(false);
            btnRedirect.setTextColor(getResources().getColor(R.color.OrangeRed));
            btnContinue.setEnabled(true);
            btnContinue.setTextColor(getResources().getColor(R.color.white));
        }

        else
        {
            btnRedirect.setEnabled(true);
            btnRedirect.setTextColor(getResources().getColor(R.color.white));
            btnContinue.setEnabled(false);
            btnContinue.setTextColor(getResources().getColor(R.color.OrangeRed));
        }

        //Setting Button Action {btnRedirect},
        btnRedirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                long dataInsert = dbh.insertPlayerInfo("0.0", 100, 0);

                Intent intent = new Intent(MainActivity.this, StoryLineActivity.class);
                startActivity(intent);
            }
        });

        //Setting Button Action {btnContinue},
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, DisplayActivity.class);
                startActivity(intent);
            }
        });

    }
}