package com.formakidov.sparecalendar.presenter;

import com.formakidov.sparecalendar.R;
import com.formakidov.sparecalendar.fragment.BaseFragment;
import com.formakidov.sparecalendar.fragment.CarsFragment;
import com.formakidov.sparecalendar.fragment.ReferenceBookFragment;
import com.formakidov.sparecalendar.fragment.SettingsFragment;
import com.formakidov.sparecalendar.interfaces.IHasFabFragment;
import com.formakidov.sparecalendar.tools.ManagerFragmentId;
import com.formakidov.sparecalendar.view.IMainView;

public class MainPresenterImpl implements IMainPresenter {
    private final IMainView view;
    private int currentFragmentId;

    public MainPresenterImpl(IMainView view) {
        this.view = view;


    }

    @Override
    public void onBackPressed(BaseFragment curFrag) {
        int fragId;
        if (curFrag instanceof CarsFragment) {
            fragId = ManagerFragmentId.carsFragmentId();
        } else {
            if (curFrag instanceof ReferenceBookFragment) {
                fragId = ManagerFragmentId.carsFragmentId();
            } else if (curFrag instanceof SettingsFragment) {
                fragId = ManagerFragmentId.carsFragmentId();
            } else {
                view.finish();
                return;
            }
        }
        BaseFragment prevFrag = getFragmentById(fragId);
        if (prevFrag != null) {
            // TODO: 01.03.2016 check fab
            view.changeFragment(prevFrag);
            if (prevFrag instanceof IHasFabFragment) {
                view.showFab();
            } else {
                view.hideFab();
            }
        } else {
            view.finish();
        }
        currentFragmentId = fragId;
    }

    @Override
    public void onNavigationItemSelected(int itemId) {
        BaseFragment f = null;
        switch (itemId) {
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
            view.changeFragment(f);
        }
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
