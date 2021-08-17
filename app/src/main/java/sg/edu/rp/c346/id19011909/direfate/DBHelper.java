package sg.edu.rp.c346.id19011909.direfate;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    //Creating Variable {To be Updated},
    private static final String DATABASE_NAME = "DireFate.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_HEADER = "DFDB";
    private static final String COLUMN_ID = "ID";
    private static final String COLUMN_1 = "PlayerProgress";
    private static final String COLUMN_2 = "PlayerHealth";
    private static final String COLUMN_3 = "PlayerGold";

    public DBHelper(Context context)
    {   super(context, DATABASE_NAME, null, DATABASE_VERSION);      }

    //OnCreate Method,
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String createDireFateTableSQL = "CREATE TABLE " + TABLE_HEADER + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_1 + " TEXT, "
                + COLUMN_2 + " INTEGER, "
                + COLUMN_3 + " INTEGER)";

        db.execSQL(createDireFateTableSQL);

        Log.i("Info", "Created Tables");
    }

    //OnUpgrade Method,
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HEADER);
        onCreate(db);
    }



    //==================================================PlayerData==================================================\\



    //Creating PlayerData Method,
    public long insertPlayerInfo(String PlayerProgress, int PlayerHealth, int PlayerGold)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues Data = new ContentValues();

        Data.put(COLUMN_1, PlayerProgress);
        Data.put(COLUMN_2, PlayerHealth);
        Data.put(COLUMN_3, PlayerGold);

        long result = db.insert(TABLE_HEADER, null, Data);
        db.close();
        Log.d("SQL Insert", "ID: " + result);
        return result;

    }

    //Getting PlayerData Method,
    public ArrayList<Player> getAllDetails()
    {
        ArrayList<Player> Details = new ArrayList<Player>();

        String selectedQuery = "SELECT " + COLUMN_ID + ", "
                + COLUMN_1 + ", "
                + COLUMN_2 + ", "
                + COLUMN_3
                + " FROM " + TABLE_HEADER;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectedQuery, null);

        if(cursor.moveToFirst())
        {
            do{
                int id = cursor.getInt(0);
                String PlayerProgress = cursor.getString(1);
                int PlayerHealth = cursor.getInt(2);
                int PlayerGold = cursor.getInt(3);

                Player Data = new Player(id, PlayerProgress, PlayerHealth, PlayerGold);
                Details.add(Data);
            } while(cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return Details;

    }

    //Updating PlayerData Method,
    public int updatePlayerInfo(Player data)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues Data = new ContentValues();

        Data.put(COLUMN_1, data.getPlayerProgress());
        Data.put(COLUMN_2, data.getPlayerHealth());
        Data.put(COLUMN_3, data.getPlayerGold());

        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(data.getPID())};

        int results = db.update(TABLE_HEADER, Data, condition, args);
        db.close();
        return results;

    }

    //Delete PlayerData Method,
    public int deleteDatabase()
    {
        SQLiteDatabase db = this.getWritableDatabase();

        int result = db.delete(TABLE_HEADER, null, null);
        db.close();
        return result;
    }

}
