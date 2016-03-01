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
import com.formakidov.sparecalendar.fragment.ReferenceBookFragment;
import com.formakidov.sparecalendar.interfaces.IHasFabFragment;
import com.formakidov.sparecalendar.presenter.MainPresenterImpl;
import com.formakidov.sparecalendar.view.IMainView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements IMainView, NavigationView.OnNavigationItemSelectedListener {
    @Bind(R.id.drawer_layout) DrawerLayout drawer;
    @Bind(R.id.nav_view) NavigationView navigationView;
    @Bind(R.id.fab) FloatingActionButton fab;
    @Bind(R.id.toolbar) Toolbar toolbar;

    private MainPresenterImpl presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cars);
        ButterKnife.bind(this);

        setupViews();

        presenter = new MainPresenterImpl(this);

        changeFragment(ReferenceBookFragment.newInstance());
    }

    public void setupViews() {
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
            BaseFragment curFrag = (BaseFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
            presenter.onBackPressed(curFrag);
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        presenter.onNavigationItemSelected(item.getItemId());
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void hideFab() {
        fab.hide();
    }

    @Override
    public void showFab() {
        fab.show();
    }

    @Override
    public void changeFragment(BaseFragment f) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, f, f.getClass().getSimpleName())
                .addToBackStack(f.getClass().getSimpleName())
                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }

}
