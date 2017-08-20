package com.example.moham.pullgithubripos.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by moham on 8/20/2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    static final int DATABASE_VERSION = 1;

    // Database Name
    static final String DATABASE_NAME = "reposList";

    // repos table name
    static final String TABLE_REPOS = "repos";

    // repos Table Columns names
    static final String KEY_ID = "id";
    static final String KEY_DESC = "desc";
    static final String KEY_OWNER_URL = "ownerl";
    static final String KEY_REPO_URL = "repol";
    static final String KEY_NAME = "name";
    static final String KEY_OWNER = "owner";
    static final String KEY_FORK = "forkk";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_REPOS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_DESC + " TEXT," + KEY_OWNER
                + " TEXT,"+ KEY_OWNER_URL + " TEXT," +
                KEY_REPO_URL + " TEXT," + KEY_FORK + " INTEGER" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REPOS);

        // Create tables again
        onCreate(db);
    }
}