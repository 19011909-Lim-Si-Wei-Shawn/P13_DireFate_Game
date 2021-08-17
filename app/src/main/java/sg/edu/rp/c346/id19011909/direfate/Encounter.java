package sg.edu.rp.c346.id19011909.direfate;

import java.io.Serializable;

public class Encounter implements Serializable {

    //Creating Variable,
    private int EID;
    private String EncounterName;
    private int EffectHealth;
    private int EffectGold;
    private String EncounterOutcome;

    //Constructor,
    public Encounter(String EncounterName, int EffectHealth, int EffectGold, String EncounterOutcome)
    {
        this.EncounterName = EncounterName;
        this.EffectHealth = EffectHealth;
        this.EffectGold = EffectGold;
        this.EncounterOutcome = EncounterOutcome;
    }

    //Creating Functions,
    public String EncounterName() { return EncounterName;       }

    public int EffectHealth() {  return EffectHealth;        }

    public int EffectGold() {    return EffectGold;        }

    public String EncounterOutcome() {  return EncounterOutcome;        }

}
