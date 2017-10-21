package com.example.powertrafficeapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.powertrafficeapp.R;
import com.example.powertrafficeapp.util.UrlBean;
import com.example.powertrafficeapp.util.Util;


public class GuideActivity extends AppCompatActivity {
    UrlBean urlBean;
    int sd = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        sd = Util.loadSettingtime("sss", "asas", this);
        Log.i("sdfsd", String.valueOf(sd));
        if (sd == 1) {

            Intent intent = new Intent(GuideActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        } else {
            Util.saveSettingtime("sss", "asas", 1, this);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(GuideActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }, 2000);
        }



    }

}