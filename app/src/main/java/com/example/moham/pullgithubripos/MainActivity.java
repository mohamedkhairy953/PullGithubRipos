package com.example.moham.pullgithubripos;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.moham.pullgithubripos.fragments.PullFragment;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_PERMISSIONS = 4;
    String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction()
                .replace(android.R.id.content, new PullFragment())
                .commit();
    }

}
