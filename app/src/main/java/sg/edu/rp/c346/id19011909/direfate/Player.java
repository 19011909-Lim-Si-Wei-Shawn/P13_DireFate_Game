package sg.edu.rp.c346.id19011909.direfate;

import java.io.Serializable;

public class Player implements Serializable {

    //Creating Variable,
    private int PID;
    private String PlayerProgress;
    private int PlayerHealth;
    private int PlayerGold;

    //Constructor,
    public Player(String PlayerProgress, int PlayerHealth, int PlayerGold)
    {
        //this.PID = PID;
        this.PlayerProgress = PlayerProgress;
        this.PlayerHealth = PlayerHealth;
        this.PlayerGold = PlayerGold;
    }

    //Creating Functions,
    public int getPID() {   return PID;     }

    public String getPlayerProgress() { return PlayerProgress;      }

    public void setPlayerProgress(String x) { this.PlayerProgress = x;        }

    public int getPlayerHealth() {  return PlayerHealth;        }

    public void setPlayerHealth(int x) { this.PlayerHealth = x;      }

    public int getPlayerGold() {    return PlayerGold;      }

    public void setPlayerGold(int x) {   this.PlayerGold = x;        }

}
