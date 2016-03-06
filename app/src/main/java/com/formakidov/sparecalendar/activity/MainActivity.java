package com.formakidov.sparecalendar.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.formakidov.sparecalendar.R;
import com.formakidov.sparecalendar.fragment.BaseFragment;
import com.formakidov.sparecalendar.fragment.CarsFragment;
import com.formakidov.sparecalendar.fragment.ReferenceBookFragment;
import com.formakidov.sparecalendar.fragment.SettingsFragment;
import com.formakidov.sparecalendar.interfaces.IHasFabFragment;
import com.formakidov.sparecalendar.tools.ManagerFragmentId;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {
    @Bind(R.id.drawer_layout) DrawerLayout drawer;
    @Bind(R.id.nav_view) NavigationView navigationView;
    @Bind(R.id.fab) FloatingActionButton fab;
    @Bind(R.id.toolbar) Toolbar toolbar;

    private int currentFragmentId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cars);
        ButterKnife.bind(this);

        setupViews();

		setFragment(CarsFragment.newInstance());
	}

    private void setupViews() {
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
    }

    @OnClick(R.id.fab)
    void onFabClick() {
        Fragment f = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (f instanceof IHasFabFragment) {
            ((IHasFabFragment) f).onFabClicked();
        }
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        drawer.closeDrawer(GravityCompat.START);
        BaseFragment f = null;
        switch (item.getItemId()) {
            case R.id.nav_main:
                if (currentFragmentId != ManagerFragmentId.carsFragmentId()) {
                    f = CarsFragment.newInstance();
                }
                currentFragmentId = ManagerFragmentId.carsFragmentId();
                break;
            case R.id.nav_ref_book:
                f = ReferenceBookFragment.newInstance();
                currentFragmentId = ManagerFragmentId.referenceBookFragmentId();
                break;
            case R.id.nav_settings:
                f = SettingsFragment.newInstance();
                currentFragmentId = ManagerFragmentId.settingsFragmentId();
                break;
        }
        if (f != null) {
            setFragment(f);
            if (f instanceof IHasFabFragment) {
                showFab();
            } else {
                hideFab();
            }
        }
        return true;
    }

    private void hideFab() {
        fab.hide();
    }

    private void showFab() {
        fab.show();
    }

    private void setFragment(BaseFragment f) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, f, f.getClass().getSimpleName())
                .addToBackStack(f.getClass().getSimpleName())
                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }

    private BaseFragment getFragmentById(int fragmentId) {
        BaseFragment f = null;
        if (fragmentId == ManagerFragmentId.carsFragmentId()) {
            f = CarsFragment.newInstance();
        } else if (fragmentId == ManagerFragmentId.referenceBookFragmentId()) {
            f = ReferenceBookFragment.newInstance();
        } else if (fragmentId == ManagerFragmentId.settingsFragmentId()) {
            f = SettingsFragment.newInstance();
        }
        return f;
    }

}
