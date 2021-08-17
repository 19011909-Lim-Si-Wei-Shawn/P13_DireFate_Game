package sg.edu.rp.c346.id19011909.direfate;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class DisplayActivity extends AppCompatActivity {

    //Creating Variable,
    TextView TVPlayerHealth, TVPlayerGold, TVPlayerProgress, TVOutcome;
    Button BtnForward, BtnQuit;

    ArrayList<Player> PlayerList;
    ArrayList<Encounter> ChanceList;

    Player Data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        //Linking Variable,
        TVPlayerHealth = findViewById(R.id.TVHealthDisplay);
        TVPlayerGold = findViewById(R.id.TVGoldDisplay);
        TVPlayerProgress = findViewById(R.id.TVPlayerProgress);
        TVOutcome = findViewById(R.id.DisplayOutcome);

        BtnForward = findViewById(R.id.btnEncounter);
        BtnQuit = findViewById(R.id.btnQuit);

        //Enabling DBHelper,
        DBHelper dbh = new DBHelper(DisplayActivity.this);

        //Inputting Value to ArrayList,
        PlayerList = new ArrayList<Player>();
        PlayerList.addAll(dbh.getAllDetails());

        TVPlayerProgress.setText(PlayerList.get(0).getPlayerProgress());
        TVPlayerHealth.setText(String.valueOf(PlayerList.get(0).getPlayerHealth()));
        TVPlayerGold.setText(String.valueOf(PlayerList.get(0).getPlayerGold()));

        ChanceList = new ArrayList<Encounter>();
        ChanceList.add(new Encounter("You have found a hidden chest, would you like to open it?", -10,+10, "Upon opening the chest, you were attacked by a mimic disguised as a chest. You manage to collect some gold before running off but took some damage."));
        ChanceList.add(new Encounter("You spotted a rundown cart beside the road while you were traveling, would you like to search it?", 10,10, "You found some health potions and leftover gold hidden inside the cart!"));
        ChanceList.add(new Encounter("A villager claims that his hometown was attacked by monsters, would you like to give him two gold?", 0, -2,"You have given him two gold!"));
        ChanceList.add(new Encounter("While traveling, you meet ten bandits with decent loot gathered around a fireplace. Would you like to engage in battle?", -70, -70, "You were overwhelmed by them and lost a considerable amount of health and gold."));

        Log.d("ArrayList", PlayerList.get(0).getPlayerProgress());
        Log.d("ArrayList", String.valueOf(PlayerList.get(0).getPlayerHealth()));
        Log.d("ArrayList", String.valueOf(PlayerList.get(0).getPlayerGold()));
        Log.d("Random", String.valueOf(ChanceList.size()));

        //Setting Button Action {BtnFoward},
        BtnForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Getting a Random Number,
                int random = new Random().nextInt(ChanceList.size()) + 0;
                int Selected = random;
                Log.d("Random", String.valueOf(Selected));
                Log.d("Random", ChanceList.get(Selected).EncounterName());

                //Building Dialog,
                AlertDialog.Builder myBuilder = new AlertDialog.Builder(DisplayActivity.this);
                myBuilder.setTitle("Chance Encounter");
                myBuilder.setMessage(ChanceList.get(Selected).EncounterName());
                myBuilder.setCancelable(false);

                //Configure the 'Positive' Button,
                myBuilder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        TVOutcome.setText(ChanceList.get(Selected).EncounterOutcome());

                        int TempData1 = Integer.parseInt(TVPlayerHealth.getText().toString()) + ChanceList.get(Selected).EffectHealth();
                        if(TempData1 <= 0)
                        {
                            Intent intent = new Intent(DisplayActivity.this, GameOver.class);
                            startActivity(intent);
                        }
                        TVPlayerHealth.setText(String.valueOf(TempData1));

                        int TempData2 = ChanceList.get(Selected).EffectGold() + Integer.parseInt(TVPlayerGold.getText().toString());
                        TVPlayerGold.setText(String.valueOf(TempData2));
                    }
                });

                //Configure the 'Negative' Button,
                myBuilder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        TVOutcome.setText(R.string.EncounterNo);
                    }
                });

                //Displaying Dialog,
                AlertDialog myDialog = myBuilder.create();
                myDialog.show();

            }
        });

        //Setting Button Action {BtnQuit},
        BtnQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Saving Before Quitting,
                Data.setPlayerProgress(TVPlayerProgress.getText().toString());
                Data.setPlayerHealth(Integer.parseInt(TVPlayerHealth.getText().toString()));
                Data.setPlayerGold(Integer.parseInt(TVPlayerGold.getText().toString()));

                dbh.updatePlayerInfo(Data);
                dbh.close();

                //Redirecting User,
                Intent intent = new Intent(DisplayActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}