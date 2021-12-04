package com.codeberry.settingsService;

import static android.os.Build.ID;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.net.IDN;

public class DbHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "vehiclesetup.db";
    public static final String TABLE_NAME = "vehicle";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_VALUE = "VALUE";


    public DbHelper(@Nullable Context context) {
        super(context, "vehiclesetup.db", null, 1);
    }

    /**
     *
     * @param sqLiteDatabase
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY,"
                + COLUMN_VALUE + " INTEGER " + ")");
    }

    /**
     *
     * @param sqLiteDatabase
     * @param i
     * @param i1
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS vehicle");
        onCreate(sqLiteDatabase);

    }

    /**
     *
     * @param data
     */

    public void insertdata(Data data) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, data.getID());
        values.put(COLUMN_VALUE, data.getVALUE());
        sqLiteDatabase.insert(TABLE_NAME, null, values);
        sqLiteDatabase.close();
    }
    public boolean update() {
        SQLiteDatabase  sqLiteDatabase = this.getReadableDatabase();
        sqLiteDatabase.execSQL("UPDATE "+TABLE_NAME+" SET VALUE = "+""+0+" "+ "WHERE ID = "+""+100+"");
        return true;
    }



}
