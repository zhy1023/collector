package com.zhy.module_app;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Handler handler = new Handler();
        initData();
    }

    private void initData() {

    }

    public void start(View view) {
        try {
            Uri uri = Uri.parse("pgygame://share:8888/detail?page=xxx");
            Intent intentUri = new Intent(Intent.ACTION_VIEW);
            intentUri.setData(uri);
//            intentUri.addCategory(Intent.CATEGORY_BROWSABLE);
//            intentUri.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intentUri);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}