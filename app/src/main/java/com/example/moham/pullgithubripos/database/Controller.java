package com.example.moham.pullgithubripos.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.moham.pullgithubripos.POJOs.Owner;
import com.example.moham.pullgithubripos.POJOs.Repo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by moham on 8/20/2017.
 */

public class Controller {
    DatabaseHandler handler;
    Context context;
    SQLiteDatabase database;


    public Controller(Context c) {
        this.context = c;
        this.handler = new DatabaseHandler(context);
    }

    public void open() {
        database = handler.getWritableDatabase();
    }

    public void close() {
        handler.close();
    }

    public void addRepo(Repo repo) {
        open();
        ContentValues values = new ContentValues();
        values.put(DatabaseHandler.KEY_ID, repo.getId());
        values.put(DatabaseHandler.KEY_DESC, repo.getDesc());
        values.put(DatabaseHandler.KEY_NAME, repo.getName());
        values.put(DatabaseHandler.KEY_REPO_URL, repo.getUrl());
        values.put(DatabaseHandler.KEY_OWNER, repo.getOwner().getLogin());
        values.put(DatabaseHandler.KEY_OWNER_URL, repo.getOwner().getUrl());
        values.put(DatabaseHandler.KEY_FORK, repo.isFork());
        // Inserting Row
        long insert = database.insert(DatabaseHandler.TABLE_REPOS, null, values);
        Log.d("LLLL",insert+"");
        close();
    }

    public List<Repo> getAllRepos() {
        List<Repo> reposList = new ArrayList<Repo>();
        String selectQuery = "SELECT  * FROM " + DatabaseHandler.TABLE_REPOS;

        open();
        Cursor cursor = database.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Repo repo = new Repo();
                repo.setId(cursor.getInt(cursor.getColumnIndex(DatabaseHandler.KEY_ID)));
                repo.setName(cursor.getString(cursor.getColumnIndex(DatabaseHandler.KEY_NAME)));
                repo.setDesc(cursor.getString(cursor.getColumnIndex(DatabaseHandler.KEY_DESC)));
                repo.setUrl(cursor.getString(cursor.getColumnIndex(DatabaseHandler.KEY_REPO_URL)));
                repo.setFork(cursor.getInt(cursor.getColumnIndex(DatabaseHandler.KEY_FORK)) > 0);
                Owner owner = new Owner();
                owner.setLogin(cursor.getString(cursor.getColumnIndex(DatabaseHandler.KEY_OWNER)));
                owner.setUrl(cursor.getString(cursor.getColumnIndex(DatabaseHandler.KEY_OWNER_URL)));
                repo.setOwner(owner);
                //
                Log.d("LLLL",repo.isFork()+"");
                reposList.add(repo);
            } while (cursor.moveToNext());
        }
        Log.d("LLLL",reposList.size()+"");

        cursor.close();
        return reposList;
    }

    public void deleterepos() {
        open();
        int delete = database.delete(DatabaseHandler.TABLE_REPOS, null, null);
        Log.d("LLLL","jj "+delete);
        close();
    }

}
