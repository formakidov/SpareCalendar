package com.formakidov.sparecalendar.activity;

import android.support.v7.app.AppCompatActivity;

import com.formakidov.sparecalendar.tools.Tools;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onResume() {
        super.onResume();
        Tools.cancelNotification();
    }

}
