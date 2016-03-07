package com.formakidov.sparecalendar.activity;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;

import com.formakidov.sparecalendar.R;
import com.formakidov.sparecalendar.tools.Tools;

public abstract class BaseActivity extends AppCompatActivity {

    protected abstract void setupViews();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        brandGlowEdgeEffect();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Tools.cancelNotification();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return keyCode == KeyEvent.KEYCODE_MENU || super.onKeyDown(keyCode, event);
    }

    protected void initToolbar(int titleId) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(titleId);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    protected void initScrollableTabLayout(ViewPager viewPager) {
        TabLayout tabLayout = getTabLayout(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
    }

    private TabLayout getTabLayout(ViewPager viewPager) {
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
        return tabLayout;
    }

    protected void initFixedTabLayout(ViewPager viewPager) {
        TabLayout tabLayout = getTabLayout(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
    }

    private void brandGlowEdgeEffect() {
        if (Tools.isPreLollipop()) {
            int brandColor = ContextCompat.getColor(this, R.color.colorPrimary);

            try {
                int glowDrawableId = getResources().getIdentifier("overscroll_glow", "drawable", "android");
                Drawable androidGlow = ContextCompat.getDrawable(this, glowDrawableId);
                androidGlow.setColorFilter(brandColor, PorterDuff.Mode.SRC_IN);

                int edgeDrawableId = getResources().getIdentifier("overscroll_edge", "drawable", "android");
                Drawable androidEdge = ContextCompat.getDrawable(this, edgeDrawableId);
                androidEdge.setColorFilter(brandColor, PorterDuff.Mode.SRC_IN);
            } catch (OutOfMemoryError e) {
                e.printStackTrace();
            }
        }
    }
}
